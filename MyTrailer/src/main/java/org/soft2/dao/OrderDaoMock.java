/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.soft2.dao;

import java.util.ArrayList;
import java.util.List;

import org.soft2.dto.OrderDTO;
import org.soft2.dto.OrderRequestDTO;

/**
 *
 * @author kotteletfisk
 */

public class OrderDaoMock {

    private final List<OrderDTO> orders;
    private int count;

    public OrderDaoMock() {
        this.orders = new ArrayList<>();
        this.count = 1;
    }

    // TODO: This only checks booking overlap, not trailer return status
    public boolean available(OrderRequestDTO req) {

        return this.orders.stream().noneMatch((order -> {
            return req.startTime.isBefore(order.endTime) && order.startTime.isBefore(req.endTime);
        }));
    }

    public boolean createOrder(OrderRequestDTO orderRequest) {
        OrderDTO order = new OrderDTO(
            count++,
            orderRequest.trailerId,
            orderRequest.userId,
            orderRequest.insurance,
            orderRequest.startTime,
            orderRequest.endTime
        );

        return this.orders.add(order);
    }

}
