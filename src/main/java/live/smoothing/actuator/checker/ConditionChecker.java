package live.smoothing.actuator.checker;

import live.smoothing.actuator.config.ConditionSettings;
import live.smoothing.actuator.dto.DataDTO;

public interface ConditionChecker {

    boolean checkCondition(DataDTO data);
}
