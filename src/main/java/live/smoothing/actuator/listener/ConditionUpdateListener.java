package live.smoothing.actuator.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import live.smoothing.actuator.service.ConditionSettingsService;
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

            conditionSettingsService.updateCondition(
                    payload.getUnoccupiedDuration(),
                    payload.getTemperatureThreshold(),
                    payload.getIlluminanceThreshold(),
                    payload.getOccupancy(),
                    payload.getTemperature(),
                    payload.getCo2()
            );

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Getter
    @Setter
    private static class ConditionUpdatePayload {
        private String unoccupiedDuration;
        private String temperatureThreshold;
        private String illuminanceThreshold;
        private String occupancy;
        private String temperature;
        private String co2;
    }
}
