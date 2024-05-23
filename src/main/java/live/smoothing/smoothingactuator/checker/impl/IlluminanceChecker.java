package live.smoothing.smoothingactuator.checker.impl;

import live.smoothing.smoothingactuator.checker.ConditionChecker;
import live.smoothing.smoothingactuator.config.ConditionSettings;
import live.smoothing.smoothingactuator.dto.DataDTO;
import org.springframework.stereotype.Component;

@Component("illuminanceChecker")
public class IlluminanceChecker implements ConditionChecker {

    @Override
    public boolean checkCondition(DataDTO data, ConditionSettings.DeviceCondition settings) {

        return Boolean.parseBoolean(data.getValue());
    }
}
