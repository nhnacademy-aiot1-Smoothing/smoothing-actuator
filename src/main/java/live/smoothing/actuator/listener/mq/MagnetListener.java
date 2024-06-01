package live.smoothing.actuator.listener.mq;

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
 * 온도 리스너
 *
 * @author 신민석
 */
@Slf4j
@Service
public class MagnetListener extends BaseListener {

    public MagnetListener(RabbitTemplate rabbitTemplate, ApplicationContext applicationContext, RabbitMQProperties properties, ObjectMapper objectMapper, ConditionProperties conditionProperties, ControlHistoryService controlHistoryService, ConditionSettingsService conditionSettingsService, ControlElementService controlElementService, RedisTemplate<String, Boolean> redisTemplate) {
        super(rabbitTemplate, applicationContext, properties, objectMapper, conditionProperties, controlHistoryService, conditionSettingsService, controlElementService, redisTemplate);
    }

    @RabbitListener(queues = "${queue.magnet}")
    public void receiveMessage(String message) {
        log.info("Received Message from [magnet-queue]: {}", message);
        handleMessage(message, "activateChecker");
    }

    @Override
    protected String createControlMessage(String device) {
        controlHistoryService.save(device, "개폐 감지 센서에 의해 제어 되었습니다.");
        return "green";
    }
}
