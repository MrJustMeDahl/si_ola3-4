
package org.soft2.dto;

import java.time.LocalDateTime;

/**
 *
 * @author kotteletfisk
 */
public class OrderDTO {

    public int orderId;
    public int trailerId;
    public String userId;
    public boolean insurance;
    public LocalDateTime startTime;
    public LocalDateTime endTime;

    public OrderDTO(){}

    public OrderDTO(int orderId, int trailerId, String userId, boolean insurance, LocalDateTime startTime, LocalDateTime endTime) {
        this.orderId = orderId;
        this.trailerId = trailerId;
        this.userId = userId;
        this.insurance = insurance;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
