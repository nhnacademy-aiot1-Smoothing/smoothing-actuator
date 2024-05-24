package live.smoothing.actuator.service;

import live.smoothing.actuator.config.ConditionSettings;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class ConditionSettingsService {

    private final ConditionSettings conditionSettings;

    public ConditionSettingsService(@Qualifier("conditionSettings") ConditionSettings conditionSettings) {
        this.conditionSettings = conditionSettings;
    }

    public ConditionSettings getConditionSettings() {
        return conditionSettings;
    }

    public void updateCondition(String unoccupiedDuration, String temperatureThreshold, String illuminanceThreshold, String occupancy, String temperature, String co2) {
        if (unoccupiedDuration != null) {
            conditionSettings.setUnoccupiedDuration(unoccupiedDuration);
        }
        if (temperatureThreshold != null) {
            conditionSettings.setTemperatureThreshold(temperatureThreshold);
        }
        if (illuminanceThreshold != null) {
            conditionSettings.setIlluminanceThreshold(illuminanceThreshold);
        }
        if (occupancy != null) {
            conditionSettings.setOccupancy(occupancy);
        }
        if (temperature != null) {
            conditionSettings.setTemperature(temperature);
        }
        if (co2 != null) {
            conditionSettings.setCo2(co2);
        }
    }
}
