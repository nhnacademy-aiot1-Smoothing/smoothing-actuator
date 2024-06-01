package live.smoothing.actuator.service;

import live.smoothing.actuator.dto.ModifyTimoutConditionRequest;
import live.smoothing.actuator.dto.TimeoutResponse;
import live.smoothing.actuator.entity.ControlDevice;
import live.smoothing.actuator.entity.ControlElement;
import live.smoothing.actuator.prop.ConditionProperties;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * 조건 설정 서비스
 *
 * @author 신민석
 */
@Service
@RequiredArgsConstructor
public class ConditionSettingsService {

    private final ConditionProperties conditionProperties;
    private final RedisTemplate<String, Long> longRedisTemplate;
    private final ControlDeviceService controlDeviceService;

    public void modifyIlluminationTimeout(ModifyTimoutConditionRequest request) {
        String device = request.getEui();
        Long timeout = request.getTimeout();

        if (timeout <= 0) {
            throw new IllegalArgumentException("0 또는 음수");
        }

        ControlDevice controlDevice = controlDeviceService.findByEui(device);
        ControlElement controlElement = controlDevice.getControlElement();
        String place = controlElement.getPlace();
        String elementDevice = controlElement.getDevice();
        String event = controlElement.getEvent();

        longRedisTemplate.opsForValue().set(String.join("-", place, elementDevice, event, conditionProperties.getTimeoutKey()), timeout);
    }

    public TimeoutResponse getTimeout(String eui) {
        ControlDevice controlDevice = controlDeviceService.findByEui(eui);
        ControlElement controlElement = controlDevice.getControlElement();
        String place = controlElement.getPlace();
        String elementDevice = controlElement.getDevice();
        String event = controlElement.getEvent();

        Long timeout = longRedisTemplate.opsForValue().get(String.join("-", place, elementDevice, event, conditionProperties.getTimeoutKey()));

        if (Objects.isNull(timeout)) {
            return new TimeoutResponse(conditionProperties.getDefaultTimeout());
        }

        return new TimeoutResponse(timeout);
    }
}
