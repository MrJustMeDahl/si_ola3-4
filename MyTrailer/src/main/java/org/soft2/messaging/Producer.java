package org.soft2.messaging;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.soft2.exceptions.APIException;

public class Producer {

    private static final String EXCHANGE_NAME = "rental";

    public static boolean publishMessage(String routingKey, String message) throws APIException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
        Channel channel = connection.createChannel()) {
            channel.exchangeDeclare(EXCHANGE_NAME, "topic");
            channel.confirmSelect();

            channel.basicPublish(EXCHANGE_NAME, routingKey, null, message.getBytes("UTF-8"));
            // Maybe log the message that is being sent?

            if(!channel.waitForConfirms(5000)) {
                // Maybe log this error too?
                throw new APIException(500, "Could not process your request due to an internal error.");
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace(); //Maybe we should log this to keep track of it?
            throw new APIException(500, "Could not process your request due to an internal error, connection failed.");
        }
    }
}
