package live.smoothing.smoothingactuator.rabbitMq;

import com.rabbitmq.client.*;
import live.smoothing.smoothingactuator.connect.RabbitMQConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class RabbitMQExample implements CommandLineRunner {

    @Autowired
    private RabbitMQConnection rabbitMQConnection;

    @Override
    public void run(String... args) throws Exception {

        try {
            Connection connection = rabbitMQConnection.createConnection();
            Channel channel = rabbitMQConnection.createChannel(connection);

            String queueName = rabbitMQConnection.getConfig().getQueueName();
            String exchangeName = rabbitMQConnection.getConfig().getRoutingKey();
            String routingKey = rabbitMQConnection.getConfig().getRoutingKey();

            channel.queueDeclare(queueName, true, false, false, null);
            channel.exchangeDeclare(exchangeName, "direct", true);
            channel.queueBind(queueName, exchangeName, routingKey);

            String message = "red";
            channel.basicPublish(exchangeName, routingKey, null, message.getBytes());
            System.out.println("Sent: " + message);

            channel.basicConsume(queueName, true, new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {

                    String receivedMessage = new String(body, "UTF-8");
                    System.out.println("QQQ: " + receivedMessage);
                }
            });

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
