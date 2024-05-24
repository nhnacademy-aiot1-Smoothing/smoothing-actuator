package live.smoothing.actuator.checker.impl;

import live.smoothing.actuator.checker.ConditionChecker;
import live.smoothing.actuator.dto.DataDTO;
import live.smoothing.actuator.service.ConditionSettingsService;
import org.springframework.stereotype.Component;

@Component("temperatureChecker")
public class TemperatureChecker implements ConditionChecker {

    private final ConditionSettingsService settingsService;

    public TemperatureChecker(ConditionSettingsService settingsService) {

        this.settingsService = settingsService;
    }

    @Override
    public boolean checkCondition(DataDTO data) {
        int currentTemperature = Integer.parseInt(data.getValue());
        int settingTemperature = Integer.parseInt(settingsService.getConditionSettings().getTemperature());

        return currentTemperature > settingTemperature;
    }
}
