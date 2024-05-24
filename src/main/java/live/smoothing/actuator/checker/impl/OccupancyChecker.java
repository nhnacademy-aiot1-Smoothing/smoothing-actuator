package live.smoothing.actuator.checker.impl;

import live.smoothing.actuator.checker.ConditionChecker;
import live.smoothing.actuator.dto.DataDTO;
import live.smoothing.actuator.service.ConditionSettingsService;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * 재실 데이터로 조건의 true/false 판단
 *
 * @author 신민석
 */
@Component("occupancyChecker")
public class OccupancyChecker implements ConditionChecker {
    private Map<String, LocalDateTime> lastOccupiedMap = new HashMap<>();
    private Map<String, Integer> countMap = new HashMap<>();

    private final ConditionSettingsService settingsService;

    public OccupancyChecker(ConditionSettingsService settingsService) {

        this.settingsService = settingsService;
    }

    @Override
    public boolean checkCondition(DataDTO data) {
        int value = Integer.parseInt(data.getValue());
        String location = data.getLocation();
        int requiredValue = Integer.parseInt(settingsService.getConditionSettings().getOccupancy());

        if(value == requiredValue) {
            lastOccupiedMap.put(location, data.getTime());
            countMap.put(location, value);
            return false;
        } else {
            LocalDateTime lastTime =  lastOccupiedMap.get(location);
            Integer lastCount = countMap.get(location);

            if((lastTime != null) && (lastCount != null) && (lastCount == requiredValue)) {
                return lastTime.plusMinutes(Long.parseLong(settingsService.getConditionSettings().getUnoccupiedDuration())).isBefore(LocalDateTime.now());
            } else {
                return false;
            }

        }
    }
}
