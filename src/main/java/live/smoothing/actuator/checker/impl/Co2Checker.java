package live.smoothing.actuator.checker.impl;

import live.smoothing.actuator.checker.ConditionChecker;
import live.smoothing.actuator.dto.DataDTO;
import live.smoothing.actuator.service.ConditionSettingsService;
import org.springframework.stereotype.Component;

/**
 * 이산화탄소 데이터로 조건의 true/false 판단
 *
 * @author 신민석
 */
@Component("co2Checker")
public class Co2Checker implements ConditionChecker {

    private final ConditionSettingsService settingsService;

    public Co2Checker(ConditionSettingsService settingsService) {

        this.settingsService = settingsService;
    }

    @Override
    public boolean checkCondition(DataDTO data) {

        int value = Integer.parseInt(data.getValue());
        int settingValue = Integer.parseInt(settingsService.getConditionSettings().getCo2());
        return value > settingValue;
    }
}
