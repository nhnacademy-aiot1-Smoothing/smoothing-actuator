package live.smoothing.smoothingactuator.checker;

import live.smoothing.smoothingactuator.config.ConditionSettings;
import live.smoothing.smoothingactuator.dto.DataDTO;

public interface ConditionChecker {

    boolean checkCondition(DataDTO data, ConditionSettings.DeviceCondition settings);
}
