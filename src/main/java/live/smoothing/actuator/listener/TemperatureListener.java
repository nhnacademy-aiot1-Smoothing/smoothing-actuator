package live.smoothing.actuator.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import live.smoothing.actuator.config.RabbitMQProperties;
import live.smoothing.actuator.service.ConditionSettingsService;
import live.smoothing.actuator.dto.DataDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class TemperatureListener extends BaseListener {

    public TemperatureListener(RabbitTemplate rabbitTemplate,
                               ApplicationContext applicationContext,
                               ConditionSettingsService conditionSettingsService,
                               ObjectMapper objectMapper,
                               RabbitMQProperties properties) {

        super(rabbitTemplate, applicationContext, conditionSettingsService, properties, objectMapper);
    }

    @RabbitListener(queues = "temperature-queue")
    public void receiveMessage(String message) {
        handleMessage(message, "temperatureChecker");
    }

    @Override
    protected String createControlMessage(DataDTO data) {

        return "";
    }
}