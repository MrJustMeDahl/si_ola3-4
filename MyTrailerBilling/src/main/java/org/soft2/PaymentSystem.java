package org.soft2;

import org.soft2.DTO.BillDTO;

public class PaymentSystem {

    // Mock Payment system
    private static PaymentSystem instance = null;
    private PaymentSystem() {}
    public static PaymentSystem getInstance() {
        if (instance == null) {
            instance = new PaymentSystem();
        }
        return instance;
    }
    public boolean processPayment(BillDTO bill) {
        int amount = 0;
        if (bill.isInsurance()) {
            amount+= 50;
            System.out.println("CONSUMER: Bill has been sent to the payment system for order: " + bill.getOrderId() + ", with insurance. amount: " + amount);
        } else {
            System.out.println("CONSUMER: Bill has been sent to the payment system for order: " + bill.getOrderId() + ", without insurance. amount: " + amount);
        }
        return true;
    }
    public boolean processLateReturnCharge(BillDTO bill) {
        if (bill.isLateReturnCharge()) {
            System.out.println("CONSUMER: Late return charge has been sent to the payment system for order: " + bill.getOrderId() + ", amount: 20");
        }
        return true;
    }
}
