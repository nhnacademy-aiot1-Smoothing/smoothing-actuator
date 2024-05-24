package live.smoothing.actuator.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import live.smoothing.actuator.checker.ConditionChecker;
import live.smoothing.actuator.config.RabbitMQProperties;
import live.smoothing.actuator.dto.DataDTO;
import live.smoothing.actuator.service.ConditionSettingsService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.ApplicationContext;

@RequiredArgsConstructor
public abstract class BaseListener {

    protected final RabbitTemplate rabbitTemplate;
    protected final ApplicationContext applicationContext;
    protected final ConditionSettingsService conditionSettingsService;
    protected final RabbitMQProperties properties;
    protected final ObjectMapper objectMapper;

    protected void handleMessage(String message, String conditionCheckerBeanName) {
        try {
            DataDTO data = objectMapper.readValue(message, DataDTO.class);

            ConditionChecker checker = (ConditionChecker) applicationContext.getBean(conditionCheckerBeanName);

            if(checker.checkCondition(data)) {
                String controlMessage =  createControlMessage(data);
                rabbitTemplate.convertAndSend(properties.getExchangeName(), properties.getRoutingKey(), controlMessage);
            }

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    protected abstract String createControlMessage(DataDTO data);
}
