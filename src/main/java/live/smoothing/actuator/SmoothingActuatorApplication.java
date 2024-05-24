package live.smoothing.actuator;

import live.smoothing.actuator.config.ConditionSettings;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(ConditionSettings.class)
public class SmoothingActuatorApplication {

    public static void main(String[] args) {

        SpringApplication.run(SmoothingActuatorApplication.class, args);

    }

}
