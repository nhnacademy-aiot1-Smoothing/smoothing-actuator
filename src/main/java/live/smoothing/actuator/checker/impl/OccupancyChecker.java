package live.smoothing.actuator.checker.impl;

import live.smoothing.actuator.checker.ConditionChecker;
import live.smoothing.actuator.dto.DataDTO;
import live.smoothing.actuator.prop.ConditionProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * 재실 데이터로 조건의 true/false 판단
 *
 * @author 신민석
 */
@Component("occupancyChecker")
@RequiredArgsConstructor
public class OccupancyChecker implements ConditionChecker {

    private final RedisTemplate<String, String> customStringRedisTemplate;
    private final ConditionProperties conditionProperties;

    @Override
    public boolean checkCondition(DataDTO data)  {
        String occupancyKey = String.join("-", data.getLocation(), conditionProperties.getOccupancyKey());
        customStringRedisTemplate.opsForValue().set(occupancyKey, data.getValue());
        return false;
    }
}
