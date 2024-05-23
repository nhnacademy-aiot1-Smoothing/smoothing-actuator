package live.smoothing.smoothingactuator.checker.impl;

import live.smoothing.smoothingactuator.checker.ConditionChecker;
import live.smoothing.smoothingactuator.config.ConditionSettings;
import live.smoothing.smoothingactuator.dto.DataDTO;
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
