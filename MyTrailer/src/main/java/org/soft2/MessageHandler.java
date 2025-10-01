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

}
