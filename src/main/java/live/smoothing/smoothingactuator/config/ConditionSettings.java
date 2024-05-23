package live.smoothing.smoothingactuator.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "conditions")
public class ConditionSettings {
    private Map<String, DeviceCondition> devices;

    @Getter
    @Setter
    public static class DeviceCondition {
        private int unoccupiedDuration;
        private int temperatureThreshold;
        private int illuminanceThreshold;
        private int occupancy;
        private int temperature;
        private int co2;
    }

}
