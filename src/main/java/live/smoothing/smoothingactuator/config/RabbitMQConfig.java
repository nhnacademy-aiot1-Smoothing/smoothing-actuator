package live.smoothing.smoothingactuator.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "mq")
public class RabbitMQConfig {

    private String server;
    private int port;
    private String queueName;
    private String routingKey;
    private String virtualHost;
    private String username;
    private String password;
}
