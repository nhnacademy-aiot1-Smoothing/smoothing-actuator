package live.smoothing.actuator.connect;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import live.smoothing.actuator.config.RabbitMQProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@Component
@AllArgsConstructor
public class RabbitMQConnection {

    @Getter
    private RabbitMQProperties config;

    public Connection createConnection() throws IOException, TimeoutException {

        ConnectionFactory factory = new ConnectionFactory();

        factory.setHost(config.getServer());
        factory.setPort(config.getPort());
        factory.setVirtualHost(config.getVirtualHost());
        factory.setUsername(config.getUsername());
        factory.setPassword(config.getPassword());

        return factory.newConnection();
    }

    public Channel createChannel(Connection connection) throws IOException {
        return connection.createChannel();
    }
}
