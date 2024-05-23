package live.smoothing.smoothingactuator.checker.impl;

import live.smoothing.smoothingactuator.checker.ConditionChecker;
import live.smoothing.smoothingactuator.config.ConditionSettings;
import live.smoothing.smoothingactuator.dto.DataDTO;
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
