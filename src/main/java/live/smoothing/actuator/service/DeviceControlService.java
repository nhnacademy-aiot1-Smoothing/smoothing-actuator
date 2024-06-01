package live.smoothing.actuator.service;

import live.smoothing.actuator.dto.DeviceDTO;
import live.smoothing.actuator.entity.ControlDevice;
import live.smoothing.actuator.prop.ConditionProperties;
import live.smoothing.actuator.prop.RabbitMQProperties;
import live.smoothing.actuator.repository.ControlDeviceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class DeviceControlService {

    private final RabbitTemplate rabbitTemplate;
    private final RedisTemplate<String, Boolean> booleanRedisTemplate;
    private final ConditionProperties conditionProperties;
    private final ControlDeviceRepository deviceRepository;
    private final RabbitMQProperties rabbitMQProperties;

    public void turnDevice(String eui, String action) {
        String key = String.join("-", eui, conditionProperties.getActivateKey());

        if ("on".equals(action)) {
            rabbitTemplate.convertAndSend(rabbitMQProperties.getExchangeName(), key, "red");
            booleanRedisTemplate.opsForValue().set(String.join("-", eui, conditionProperties.getStatusKey()), Boolean.TRUE);
        } else if ("off".equals(action)) {
            rabbitTemplate.convertAndSend(rabbitMQProperties.getExchangeName(), key, "green");
            booleanRedisTemplate.opsForValue().set(String.join("-", eui, conditionProperties.getStatusKey()), Boolean.FALSE);
        }
    }

    public void turnControl(String eui, String action) {
        String flagKey = String.join("-", eui, conditionProperties.getFlagKey());

        if ("manual".equals(action)) {
            Set<String> timeoutKeys = booleanRedisTemplate.keys("*" + conditionProperties.getTimeoutKey() + "*");

            if (timeoutKeys != null) {
                booleanRedisTemplate.delete(timeoutKeys);
            }

            booleanRedisTemplate.opsForValue().set(flagKey, Boolean.TRUE);
        } else if ("auto".equals(action)) {
            booleanRedisTemplate.opsForValue().set(flagKey, Boolean.FALSE);
        }
    }

    public List<DeviceDTO> getDevices() {
        List<ControlDevice> devices = deviceRepository.findAll();
        List<DeviceDTO> deviceDTOList = new ArrayList<>();

        for (ControlDevice device : devices) {
            String deviceEui = device.getEui();
            String name = device.getName();
            Boolean isActivate = booleanRedisTemplate.opsForValue().get(String.join("-", deviceEui, conditionProperties.getStatusKey()));
            Boolean isManual = booleanRedisTemplate.opsForValue().get(String.join("-", deviceEui, conditionProperties.getFlagKey()));

            if (Objects.isNull(isActivate)) { isActivate = Boolean.FALSE; }
            if (Objects.isNull(isManual)) { isManual = Boolean.TRUE; }

            DeviceDTO deviceDTO = new DeviceDTO(deviceEui, name, isActivate, isManual);
            deviceDTOList.add(deviceDTO);
        }

        return deviceDTOList;
    }
}
