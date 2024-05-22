package live.smoothing.smoothingactuator.checker.impl;

import live.smoothing.smoothingactuator.checker.ConditionChecker;
import live.smoothing.smoothingactuator.config.ConditionSettings;
import live.smoothing.smoothingactuator.dto.DataDTO;
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

    @Override
    public boolean checkCondition(DataDTO data, ConditionSettings.DeviceCondition settings) {
        int value = Integer.parseInt(data.getValue());
        String location = data.getLocation();
        int requiredValue = settings.getOccupancy();

        if(value == requiredValue) {
            lastOccupiedMap.put(location, data.getTime());
            countMap.put(location, value);
            return false;
        } else {
            LocalDateTime lastTime =  lastOccupiedMap.get(location);
            Integer lastCount = countMap.get(location);

            if((lastTime != null) && (lastCount != null) && (lastCount == requiredValue)) {
                return lastTime.plusMinutes(settings.getUnoccupiedDuration()).isBefore(LocalDateTime.now());
            } else {
                return false;
            }

        }
    }
}
