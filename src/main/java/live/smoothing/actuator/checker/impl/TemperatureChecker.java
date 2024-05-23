package live.smoothing.actuator.checker.impl;

import live.smoothing.actuator.checker.ConditionChecker;
import live.smoothing.actuator.config.ConditionSettings;
import live.smoothing.actuator.dto.DataDTO;
import org.springframework.stereotype.Component;

@Component("temperatureChecker")
public class TemperatureChecker implements ConditionChecker {

    @Override
    public boolean checkCondition(DataDTO data, ConditionSettings.DeviceCondition settings) {
        int currentTemperature = Integer.parseInt(data.getValue());
        int settingTemperature = settings.getTemperature();

        return currentTemperature > settingTemperature;
    }
}
