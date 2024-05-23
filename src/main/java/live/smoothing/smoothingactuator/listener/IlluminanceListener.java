package live.smoothing.smoothingactuator.listener;

import live.smoothing.smoothingactuator.config.ConditionSettings;
import live.smoothing.smoothingactuator.dto.DataDTO;
import live.smoothing.smoothingactuator.service.ConditionSettingsService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class IlluminanceListener extends BaseListener {

    public IlluminanceListener(RabbitTemplate rabbitTemplate, ApplicationContext applicationContext, ConditionSettingsService conditionSettingsService) {
        super(rabbitTemplate, applicationContext, conditionSettingsService);
    }

    @RabbitListener(queues = "illuminance-queue")
    public void receiveMessage(String message) {
        handleMessage(message, "illuminanceChecker");
    }

    @Override
    protected String createControlMessage(DataDTO data, ConditionSettings.DeviceCondition settings) {

        return "";
    }
}
