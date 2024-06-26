package live.smoothing.actuator.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import live.smoothing.actuator.listener.BaseListener;
import live.smoothing.actuator.prop.ConditionProperties;
import live.smoothing.actuator.prop.RabbitMQProperties;
import live.smoothing.actuator.service.ConditionSettingsService;
import live.smoothing.actuator.service.ControlElementService;
import live.smoothing.actuator.service.ControlHistoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * 재실 리스너
 *
 * @author 신민석
 */
@Slf4j
@Service
public class OccupancyListener extends BaseListener {

    public OccupancyListener(RabbitTemplate rabbitTemplate, ApplicationContext applicationContext, RabbitMQProperties properties, ObjectMapper objectMapper, ConditionProperties conditionProperties, ControlHistoryService controlHistoryService, ConditionSettingsService conditionSettingsService, ControlElementService controlElementService, RedisTemplate<String, Boolean> redisTemplate) {
        super(rabbitTemplate, applicationContext, properties, objectMapper, conditionProperties, controlHistoryService, conditionSettingsService, controlElementService, redisTemplate);
    }

    @RabbitListener(queues = "${queue.occupancy}")
    public void receiveMessage(String message) {
        log.info("Received Message from [occupancy-queue]: {}", message);
        handleMessage(message, "occupancyChecker");
    }

    @Override
    protected String createControlMessage(String device) {
        return "";
    }
}
