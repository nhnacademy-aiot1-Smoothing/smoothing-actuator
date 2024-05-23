package live.smoothing.actuator.checker.impl;

import live.smoothing.actuator.checker.ConditionChecker;
import live.smoothing.actuator.config.ConditionSettings;
import live.smoothing.actuator.dto.DataDTO;
import org.springframework.stereotype.Component;

@Component("co2Checker")
public class Co2Checker implements ConditionChecker {

    @Override
    public boolean checkCondition(DataDTO data, ConditionSettings.DeviceCondition settings) {

        int value = Integer.parseInt(data.getValue());
        int settingValue = settings.getCo2();

        return value > settingValue;
    }
}
