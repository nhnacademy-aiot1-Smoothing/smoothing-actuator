package live.smoothing.actuator.config;

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

    @Value("${spring.rabbitmq.queueName}")
    private String queueName;

    @Value("${spring.rabbitmq.routingKey}")
    private String routingKey;

    @Value("${spring.rabbitmq.virtualHost}")
    private String virtualHost;

    @Value("${spring.rabbitmq.username}")
    private String username;

    @Value("${spring.rabbitmq.password}")
    private String password;

    @Value("${spring.rabbitmq.exchangeName}")
    private String exchangeName;

    @Value("${spring.rabbitmq.co2queueName}")
    private String co2queueName;

    @Value("${spring.rabbitmq.co2routingKey}")
    private String co2routingKey;
}
