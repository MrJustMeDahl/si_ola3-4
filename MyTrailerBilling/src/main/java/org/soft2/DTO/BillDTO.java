package org.soft2.DTO;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class BillDTO {
    private String orderId;
    private boolean insurance;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private float amount;
    private boolean lateReturnCharge;

    public BillDTO(String orderId, boolean insurance, LocalDateTime startTime, LocalDateTime endTime) {
        this.orderId = orderId;
        this.insurance = insurance;
        this.startTime = startTime;
        this.endTime = endTime;
        if (insurance) {
            this.amount += 50;
        }
    }

    public BillDTO(OrderDTO orderDTO){
        this.orderId = orderDTO.getOrderId();
        this.insurance = orderDTO.isInsurance();
        this.startTime = orderDTO.getStartTime();
        this.endTime = orderDTO.getEndTime();
        if (insurance) {
            this.amount += 50;
        }
    }
    public BillDTO(){

    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public boolean isInsurance() {
        return insurance;
    }

    public void setInsurance(boolean insurance) {
        this.insurance = insurance;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public boolean isLateReturnCharge() {
        return lateReturnCharge;
    }

    public void setLateReturnCharge(boolean lateReturnCharge) {
        this.lateReturnCharge = lateReturnCharge;
        this.amount += 50;
    }
}
