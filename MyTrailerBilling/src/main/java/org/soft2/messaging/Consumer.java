package org.soft2.messaging;

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import org.soft2.DTO.BillDTO;
import org.soft2.service.BillingController;
import org.soft2.DTO.OrderDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;


public class Consumer {
    private static final ObjectMapper objectMapper = new ObjectMapper();



    private static final String EXCHANGE_NAME = "rental";

    public static void consumeMessages(String[] bindingKeys) throws Exception{
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, "topic", true);
        String queueName = channel.queueDeclare().getQueue();

        for (String bindingKey : bindingKeys) {
            channel.queueBind(queueName, EXCHANGE_NAME, bindingKey);
        }

        channel.basicConsume(queueName, true, deliverCallback(), consumerTag -> { });
    }

    private static DeliverCallback deliverCallback(){
        return (consumerTag, delivery) -> {
            if(delivery.getEnvelope().getRoutingKey().equals("rental.order.create")) {
                objectMapper.registerModule(new JavaTimeModule());
                BillDTO bill = objectMapper.readValue(delivery.getBody(), BillDTO.class);
                //OrderDTO orderDTO = objectMapper.readValue(delivery.getBody(), OrderDTO.class);
                System.out.println(" [x] Received '" +
                        delivery.getEnvelope().getRoutingKey() + "':'" + bill + "'");

                BillingController billingController = new BillingController();

                billingController.createBill(bill);

            }
            else if(delivery.getEnvelope().getRoutingKey().equals("rental.return.trailer")){
                // Handle trailer return message
                System.out.println(" [x] Received '" +
                        delivery.getEnvelope().getRoutingKey() + "':'" + new String(delivery.getBody(), "UTF-8") + "'");
                String body = new String(delivery.getBody(), "UTF-8");
                Map<String, Object> payload = objectMapper.readValue(body, Map.class);
                String orderId = (String) payload.get("orderId");
                boolean lateReturn = (boolean) payload.get("lateReturn");

                if(lateReturn){
                    BillingController billingController = new BillingController();
                    billingController.lateReturnCharge(orderId);
                }


            }
        };
    }
}