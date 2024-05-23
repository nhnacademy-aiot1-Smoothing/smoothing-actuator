package live.smoothing.actuator.listener;

import live.smoothing.actuator.config.ConditionSettings;
import live.smoothing.actuator.dto.DataDTO;
import live.smoothing.actuator.service.ConditionSettingsService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class OccupancyListener extends BaseListener{

    public OccupancyListener(RabbitTemplate rabbitTemplate, ApplicationContext applicationContext, ConditionSettingsService conditionSettingsService) {

        super(rabbitTemplate, applicationContext, conditionSettingsService);
    }

    @RabbitListener(queues = "occupancy-queue")
    public void receiveMessage(String message) {
        handleMessage(message, "occupancyChecker");
    }

    @Override
    protected String createControlMessage(DataDTO data, ConditionSettings.DeviceCondition settings) {

        return "";
    }
}
