package live.smoothing.actuator.checker.impl;

import live.smoothing.actuator.checker.ConditionChecker;
import live.smoothing.actuator.config.ConditionSettings;
import live.smoothing.actuator.dto.DataDTO;
import org.springframework.stereotype.Component;

@Component("illuminanceChecker")
public class IlluminanceChecker implements ConditionChecker {

    @Override
    public boolean checkCondition(DataDTO data, ConditionSettings.DeviceCondition settings) {

        return Boolean.parseBoolean(data.getValue());
    }
}
