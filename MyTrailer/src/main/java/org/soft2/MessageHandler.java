package org.soft2;

import io.javalin.http.Context;
import org.soft2.exceptions.APIException;
import org.soft2.messaging.Producer;

public class MessageHandler {

    public static void sendTestMessage(Context context) throws APIException {
        String jsonMessage = context.body();
        boolean result = Producer.publishMessage("rental.test", jsonMessage);
        if (result) {
            context.json("Your message was sent successfully.");
            context.status(200);
        }
    }
    public static void createBill(Context context) throws APIException {
        String jsonMessage = context.body();
        OrderDTO orderDTO = context.bodyAsClass(OrderDTO.class);
        if(orderDTO == null) {
            throw new APIException(400, "Invalid order data.");
        }
        else {
            boolean result = Producer.publishMessage("rental.order.create", jsonMessage);
            if (result) {
                context.json("Your Bill creation request was sent successfully.");
                context.status(200);
            }
        }
    }

}
