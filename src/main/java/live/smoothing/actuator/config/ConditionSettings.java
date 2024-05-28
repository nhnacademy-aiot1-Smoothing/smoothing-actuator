package live.smoothing.actuator.config;

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
public class ConditionSettings {
    private String unoccupiedDuration;
    private String temperatureThreshold;
    private String illuminanceThreshold;
    private String occupancy;
    private String temperature;
    private String co2;

}
