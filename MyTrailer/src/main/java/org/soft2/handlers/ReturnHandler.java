package org.soft2.handlers;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;

import org.soft2.DTO.OrderDTO;
import org.soft2.exceptions.APIException;
import org.soft2.messaging.Producer;
import org.soft2.mockDAO.OrderDaoMock;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.javalin.http.Context;

public class ReturnHandler {
    public static final ObjectMapper objectMapper = new ObjectMapper();


    public static void returnTrailer(Context context) throws APIException {
        //Retrieve orderId and latereturn from DB
        try {
            int orderId = Integer.parseInt(context.pathParam("orderId"));
            OrderDaoMock orderDao = OrderDaoMock.getInstance();
            OrderDTO orderDTO = orderDao.findOrder(orderId);
            boolean lateReturn = lateReturnCalculator(orderDTO);

            String jsonMessage = messageConstructor(orderId, lateReturn);
            boolean result = Producer.publishMessage("rental.return.trailer", jsonMessage);

            if (result) {
                context.json("Trailer returner succesfully");
                if (lateReturn) context.json("Late fees added to charge!");
                context.status(200);
            }
        }
        catch (Exception e) {
            context.json(e.getMessage());
        }

    }

    public static String messageConstructor(int orderId, boolean returned) {
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

    public static boolean lateReturnCalculator(OrderDTO orderDTO){

        if (Objects.isNull(orderDTO)){
            throw new RuntimeException("OrderDTO is null");
        }

        if (orderDTO.endTime.isBefore(LocalDateTime.now())){
            return true;
        }
        else{
            return false;
        }
    }
}
