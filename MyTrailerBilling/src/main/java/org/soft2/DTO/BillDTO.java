package org.soft2.DTO;

import java.sql.Timestamp;

public class BillDTO {
    private String orderId;
    private boolean insurance;
    private Timestamp timestamp;
    private float amount;
    private boolean lateReturnCharge;

    public BillDTO(String orderId, boolean insurance, Timestamp timestamp) {
        this.orderId = orderId;
        this.insurance = insurance;
        this.timestamp = timestamp;
        if (insurance) {
            this.amount += 50;
        }
    }

    public BillDTO(OrderDTO orderDTO){
        this.orderId = orderDTO.getOrderId();
        this.insurance = orderDTO.isInsurance();
        this.timestamp = orderDTO.getStartTime();
        if (insurance) {
            this.amount += 50;
        }
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

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
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
