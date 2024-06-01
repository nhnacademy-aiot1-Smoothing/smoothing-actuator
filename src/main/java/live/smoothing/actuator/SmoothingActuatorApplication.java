package live.smoothing.actuator;

import live.smoothing.actuator.prop.ConditionProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(ConditionProperties.class)
public class SmoothingActuatorApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmoothingActuatorApplication.class, args);
    }
}
