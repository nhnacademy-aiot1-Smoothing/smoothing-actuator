package live.smoothing.actuator.prop;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * RabbitMQ 설정 클래스
 *
 * @author 신민석
 */
@Getter
@Setter
@Configuration
public class RabbitMQProperties {

    @Value("${spring.rabbitmq.server}")
    private String server;

    @Value("${spring.rabbitmq.port}")
    private int port;

    @Value("${spring.rabbitmq.virtualHost}")
    private String virtualHost;

    @Value("${spring.rabbitmq.username}")
    private String username;

    @Value("${spring.rabbitmq.password}")
    private String password;

    @Value("${spring.rabbitmq.exchangeName}")
    private String exchangeName;
}
