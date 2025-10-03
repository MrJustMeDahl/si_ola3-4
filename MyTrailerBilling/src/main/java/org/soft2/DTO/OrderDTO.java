package org.soft2.DTO;


import java.time.LocalDateTime;

public class OrderDTO {
    private String orderId;
    private String trailerId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String userId;
    private boolean insurance;



    public OrderDTO() {
    }
    public OrderDTO(String orderId, boolean insurance, LocalDateTime startTime) {
        this.orderId = orderId;
        this.insurance = insurance;
        this.startTime = startTime;
    }

    public OrderDTO(String orderId, String trailerId, LocalDateTime startTime, LocalDateTime endTime, String userId, boolean insurance) {
        this.orderId = orderId;
        this.trailerId = trailerId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.userId = userId;
        this.insurance = insurance;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getTrailerId() {
        return trailerId;
    }

    public void setTrailerId(String trailerId) {
        this.trailerId = trailerId;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public boolean isInsurance() {
        return insurance;
    }

    public void setInsurance(boolean insurance) {
        this.insurance = insurance;
    }
}
