package live.smoothing.actuator.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

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
