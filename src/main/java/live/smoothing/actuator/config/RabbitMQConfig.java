package live.smoothing.actuator.config;

import live.smoothing.actuator.prop.RabbitMQProperties;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * RabbitMQConfig
 *
 * @author 신민석
 */
@Configuration
public class RabbitMQConfig {

    private final RabbitMQProperties properties;

    @Value("${queue.occupancy}")
    private String occupancyQueueName;

    @Value("${queue.magnet}")
    private String magnetQueueName;

    @Value("${queue.illuminance}")
    private String illuminanceQueueName;

    public RabbitMQConfig(RabbitMQProperties properties) {
        this.properties = properties;
    }

    @Bean
    public CachingConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setHost(properties.getServer());
        connectionFactory.setPort(properties.getPort());
        connectionFactory.setVirtualHost(properties.getVirtualHost());
        connectionFactory.setUsername(properties.getUsername());
        connectionFactory.setPassword(properties.getPassword());
        return connectionFactory;
    }

    @Bean
    public RabbitTemplate rabbitTemplate(CachingConnectionFactory connectionFactory) {
        return new RabbitTemplate(connectionFactory);
    }

    @Bean
    public Queue illuminanceQueue() {
        return new Queue(illuminanceQueueName, true);
    }

    @Bean
    public Queue magnetQueue() {
        return new Queue(magnetQueueName, true);
    }

    @Bean
    public Queue occupancyQueue() {
        return new Queue(occupancyQueueName, true);
    }
}
