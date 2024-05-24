package live.smoothing.actuator.checker.impl;

import live.smoothing.actuator.checker.ConditionChecker;
import live.smoothing.actuator.dto.DataDTO;
import live.smoothing.actuator.service.ConditionSettingsService;
import org.springframework.stereotype.Component;

@Component("illuminanceChecker")
public class IlluminanceChecker implements ConditionChecker {

    private final ConditionSettingsService settingsService;

    public IlluminanceChecker(ConditionSettingsService settingsService) {

        this.settingsService = settingsService;
    }

    @Override
    public boolean checkCondition(DataDTO data) {
        //TODO
        return true;
    }
}
