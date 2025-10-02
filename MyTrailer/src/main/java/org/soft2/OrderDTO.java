package org.soft2;

import java.sql.Timestamp;

public class OrderDTO {
    private String orderId;
    private String trailerId;
    private Timestamp startTime;
    private String userId;
    private boolean insurance;


    public OrderDTO() {
    }
    public OrderDTO(String orderId, boolean insurance, Timestamp startTime) {
        this.orderId = orderId;
        this.insurance = insurance;
        this.startTime = startTime;
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

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
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
