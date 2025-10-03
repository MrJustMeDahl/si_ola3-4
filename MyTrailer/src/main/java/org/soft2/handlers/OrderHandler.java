package org.soft2.handlers;

import org.soft2.DTO.BillDTO;
import org.soft2.DTO.OrderDTO;
import org.soft2.DTO.OrderRequestDTO;
import org.soft2.exceptions.APIException;
import org.soft2.messaging.Producer;
import org.soft2.mockDAO.OrderDaoMock;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import io.javalin.http.Context;
import io.javalin.validation.ValidationException;

/**
 *
 * @author kotteletfisk
 */
public class OrderHandler {

    private final OrderDaoMock dao;
    private final ObjectMapper mapper;

    public OrderHandler(OrderDaoMock dao) {
        this.dao = dao;
        this.mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
    }

    public void handleOrderCreation(Context ctx) throws APIException {

        OrderRequestDTO orderRequest = validateOrderRequest(ctx);

        if (!dao.available(orderRequest)) {
            throw new APIException(410, "Not available!");
        }

        OrderDTO order = dao.createOrder(orderRequest);

        if (order != null) {
            BillDTO bill = BillDTO.fromOrder(order);
            try {
                String message = mapper.writeValueAsString(bill);
                Producer.publishMessage("rental.order.create", message);
                // Send orderid in context
                ctx.json(order.orderId);
                ctx.status(201);
            } catch (JsonProcessingException e) {
                throw new APIException(500, "Failed Serializing Bill: " + bill.toString() + ": " + e.getMessage());
            }
        } else {
            throw new APIException(500, "Failed to create order from request: " + orderRequest.toString());
        }

    }

    private OrderRequestDTO validateOrderRequest(Context ctx) throws ValidationException {

        return ctx.bodyValidator(OrderRequestDTO.class)
                .check(r -> r != null, "Object is null")
                .check(r -> r.trailerId > 0, "Trailer id invalid")
                .check(r -> !r.userId.isEmpty(), "User id is empty")
                .check(r -> !r.endTime.isBefore(r.startTime), "Start Time before End Time!")
                .check(r -> r.endTime.toLocalDate().isEqual(r.startTime.toLocalDate()), "No overnight bookings!")
                .get();
    }
}
