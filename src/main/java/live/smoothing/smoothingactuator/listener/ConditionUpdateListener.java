package live.smoothing.smoothingactuator.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import live.smoothing.smoothingactuator.config.ConditionSettings;
import live.smoothing.smoothingactuator.service.ConditionSettingsService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class ConditionUpdateListener {

    private final ConditionSettingsService conditionSettingsService;

    public ConditionUpdateListener(ConditionSettingsService conditionSettingsService) {

        this.conditionSettingsService = conditionSettingsService;
    }

    @RabbitListener(queues = "condition-update-queue")
    public void receiveMessage(String message) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            ConditionUpdatePayload payload = mapper.readValue(message, ConditionUpdatePayload.class);

            conditionSettingsService.updateDeviceCondition(payload.getDevice(), payload.getNewCondition());

        } catch(Exception e) {}
    }

    @Getter
    @Setter
    private static class ConditionUpdatePayload {
        private String device;
        private ConditionSettings.DeviceCondition newCondition;
    }
}
