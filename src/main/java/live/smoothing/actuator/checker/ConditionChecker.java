package live.smoothing.actuator.checker;

import live.smoothing.actuator.dto.DataDTO;

/**
 * 조건 체크 인터페이스
 *
 * @author 신민석
 */
public interface ConditionChecker {

    boolean checkCondition(DataDTO data);
}
