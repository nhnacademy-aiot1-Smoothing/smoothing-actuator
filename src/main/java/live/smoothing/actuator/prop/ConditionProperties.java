package live.smoothing.actuator.prop;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 *  디바이스 제어 조건에 필요한 설정값 클래스
 *
 * @author 신민석
 */
@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "conditions.devices")
public class ConditionProperties {
    private Long defaultTimeout;
    private Integer illuminationThreshold;
    private String thresholdKey;
    private String timeoutKey;
    private String startTimeKey;
    private String activateKey;
    private String occupancyKey;
    private String occupancyValue;
    private String flagKey;
    private String statusKey;
}
