package org.soft2.DTO;

import java.time.LocalDateTime;

/**
 *
 * @author kotteletfisk
 */
public class BillDTO {

    public int orderId;
    public boolean insurance;
    public LocalDateTime startTime;
    public LocalDateTime endTime;

    public BillDTO() {
    }

    public BillDTO(int orderId, boolean insurance, LocalDateTime startTime, LocalDateTime endTime) {
        this.orderId = orderId;
        this.insurance = insurance;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public static BillDTO fromOrder(OrderDTO order) {
        return new BillDTO(
            order.orderId,
            order.insurance,
            order.startTime,
            order.endTime
        );
    }

}
