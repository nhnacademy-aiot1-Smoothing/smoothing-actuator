package live.smoothing.actuator.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import live.smoothing.actuator.config.RabbitMQProperties;
import live.smoothing.actuator.dto.DataDTO;
import live.smoothing.actuator.service.ConditionSettingsService;
import live.smoothing.actuator.service.ControlHistoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class Co2Listener extends BaseListener {

    public Co2Listener(RabbitTemplate rabbitTemplate,
                       ApplicationContext applicationContext,
                       ConditionSettingsService conditionSettingsService,
                       RabbitMQProperties properties,
                       ObjectMapper objectMapper,
                       ControlHistoryService controlHistoryService) {

        super(rabbitTemplate, applicationContext, conditionSettingsService, properties, objectMapper, controlHistoryService);
    }

    @RabbitListener(queues = "#{rabbitMQProperties.co2queueName}")
    public void receiveMessage(String message) {
        log.info("Received message from co2-queue: {}", message);
        handleMessage(message, "co2Checker");

    }

    @Override
    protected String createControlMessage(DataDTO data) {

        return "red";
    }
}
