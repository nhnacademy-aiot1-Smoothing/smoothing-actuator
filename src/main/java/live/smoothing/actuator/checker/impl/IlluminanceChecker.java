package live.smoothing.actuator.checker.impl;

import live.smoothing.actuator.checker.ConditionChecker;
import live.smoothing.actuator.dto.DataDTO;
import live.smoothing.actuator.prop.ConditionProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * 조도 데이터로 조건의 true/false 판단
 *
 * @author 신민석
 */
@Slf4j
@Component("illuminanceChecker")
@RequiredArgsConstructor
public class IlluminanceChecker implements ConditionChecker {

    private final RedisTemplate<String, LocalDateTime> timeRedisTemplate;
    private final RedisTemplate<String, String> customStringRedisTemplate;
    private final RedisTemplate<String, Long> longRedisTemplate;
    private final ConditionProperties conditionProperties;

    @Override
    public boolean checkCondition(DataDTO data) {
        String activateStartTimeKey = String.join("-",
                data.getLocation(),
                data.getDevice(),
                data.getEvent(),
                conditionProperties.getStartTimeKey()
        );

        String activateTimeoutKey = String.join("-",
                data.getLocation(),
                data.getDevice(),
                data.getEvent(),
                conditionProperties.getTimeoutKey()
        );

        // 재실 여부 가져오기
        String occupancyKey = String.join("-", data.getLocation(), conditionProperties.getOccupancyKey());
        String occupancyValue = customStringRedisTemplate.opsForValue().get(occupancyKey);

        // 사람이 있거나 값이 아예 없는 경우
        if (conditionProperties.getOccupancyValue().equals(occupancyValue)) {
            return false;
        }

        double illumination = Double.parseDouble(data.getValue());

        if (illumination > conditionProperties.getIlluminationThreshold()) {
            // 조명 켜기 시작한 값 들고오기
            LocalDateTime activateStartTime = timeRedisTemplate.opsForValue().get(activateStartTimeKey);
            log.error("Illumination activateStartTime: {}", activateStartTime);
            log.error("Illumination data.getTime(): {}", data.getTime());

            // 조명을 킨 적이 없다면
            if (Objects.isNull(activateStartTime)) {
                timeRedisTemplate.opsForValue().set(activateStartTimeKey, data.getTime());
                return false;
            }

            // 설정 값과 조명 켜기 시작 값 차이 계산
            Duration duration = Duration.between(activateStartTime, data.getTime());
            Long timeout = longRedisTemplate.opsForValue().get(activateTimeoutKey);

            log.error("Illumination duration: {}", duration);

            // 설정된 값이 없다면
            if (Objects.isNull(timeout)) {
                timeout = conditionProperties.getDefaultTimeout();
            }

            // 타임 아웃된 경우
            if (duration.toMinutes() + 1L > timeout) {
                customStringRedisTemplate.delete(activateStartTimeKey);
                return true;
            }
        }

        return false;
    }
}
