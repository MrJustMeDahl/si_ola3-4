package org.soft2.returns;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.soft2.exceptions.APIException;
import org.soft2.messaging.Producer;


import java.util.HashMap;
import java.util.Map;

public class ReturnHandler {
    public static final ObjectMapper objectMapper = new ObjectMapper();

    public static void returnTrailer(Context context) throws APIException {
        //Retrieve orderId and latereturn from DB
        String orderId = "12345";
        boolean lateReturn = true;
        String jsonMessage = messageConstructor(orderId, lateReturn);
        boolean result = Producer.publishMessage("rental.return.trailer", jsonMessage);

        if (result){
            context.json("Trailer returner succesfully sent a message");
            context.status(200);
        }

    }

    public static String messageConstructor(String orderId, boolean returned) {
        try{
            Map<String, Object> payload = Map.of(
                    "orderId", orderId,
                    "lateReturn", returned
            );
            return objectMapper.writeValueAsString(payload);
        }
        catch (Exception e){
            throw new RuntimeException("Failed to create Json message", e);
        }
    }
}
