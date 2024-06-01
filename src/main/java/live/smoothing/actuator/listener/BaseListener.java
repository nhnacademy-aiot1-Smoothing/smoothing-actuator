package live.smoothing.actuator.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import live.smoothing.actuator.checker.ConditionChecker;
import live.smoothing.actuator.dto.DataDTO;
import live.smoothing.actuator.entity.ControlDevice;
import live.smoothing.actuator.entity.ControlElement;
import live.smoothing.actuator.prop.ConditionProperties;
import live.smoothing.actuator.prop.RabbitMQProperties;
import live.smoothing.actuator.service.ConditionSettingsService;
import live.smoothing.actuator.service.ControlElementService;
import live.smoothing.actuator.service.ControlHistoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Objects;

/**
 * BaseListener
 *
 * @author 신민석
 */
@Slf4j
@RequiredArgsConstructor
public abstract class BaseListener {

    protected final RabbitTemplate rabbitTemplate;
    protected final ApplicationContext applicationContext;
    protected final RabbitMQProperties properties;
    protected final ObjectMapper objectMapper;
    private final ConditionProperties conditionProperties;

    protected final ControlHistoryService controlHistoryService;
    protected final ConditionSettingsService conditionSettingsService;
    private final ControlElementService controlElementService;
    private final RedisTemplate<String, Boolean> booleanRedisTemplate;

    protected void handleMessage(String message, String conditionCheckerBeanName) {
        try {
            DataDTO data = objectMapper.readValue(message, DataDTO.class);
            ConditionChecker checker = (ConditionChecker) applicationContext.getBean(conditionCheckerBeanName);
            ControlElement element = controlElementService.findByPlaceAndDeviceAndEvent(data.getLocation(), data.getDevice(), data.getEvent());

            Boolean isActivate = booleanRedisTemplate.opsForValue().get(String.join("-", element.getControlDevice().getEui(), conditionProperties.getStatusKey()));

            if((Objects.isNull(isActivate) || isActivate.equals(Boolean.FALSE)) && checker.checkCondition(data)) {

                ControlDevice controlDevice = element.getControlDevice();

                String controlMessage =  createControlMessage(controlDevice.getEui());
                String routingKey = String.join("-", controlDevice.getEui(), conditionProperties.getActivateKey());

                rabbitTemplate.convertAndSend(properties.getExchangeName(), routingKey, controlMessage);
                booleanRedisTemplate.opsForValue().set(String.join("-", controlDevice.getEui(), conditionProperties.getStatusKey()), Boolean.FALSE);
                log.error("routingKey: {}", routingKey);
            }

        } catch(Exception e) {
            log.error("Error while handling message: {}", e.getMessage());
        }
    }

    protected abstract String createControlMessage(String device);
}
