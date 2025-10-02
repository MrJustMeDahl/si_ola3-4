/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.soft2.orderhandler;

import org.soft2.dao.OrderDaoMock;
import org.soft2.dto.OrderRequestDTO;
import org.soft2.exceptions.APIException;

import io.javalin.http.Context;
import io.javalin.validation.ValidationException;

/**
 *
 * @author kotteletfisk
 */
public class OrderHandler {

    private OrderDaoMock dao;

    public OrderHandler(OrderDaoMock dao) {
        this.dao = dao;
    }

    public void handleOrderCreation(Context ctx) throws APIException {

        OrderRequestDTO orderRequest = validateOrderRequest(ctx);

        if (!dao.available(orderRequest)) {
            throw new APIException(410, "Not available!");
        }

        if (dao.createOrder(orderRequest)) {
            // TODO: Publish bill information to rabbitmq

            
        }


        ctx.status(201);
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
