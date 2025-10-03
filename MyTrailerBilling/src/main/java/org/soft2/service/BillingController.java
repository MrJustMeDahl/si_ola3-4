package org.soft2.service;
import org.soft2.DTO.BillDTO;
import org.soft2.DTO.OrderDTO;
import org.soft2.mockDAO.BillDAO;

public class BillingController {

    // Receives a message from rabbitmq and creates a bill
    public void createBill(BillDTO billDTO) {

        try {


            BillDAO billDAO = BillDAO.getInstance();

            BillDTO bill = billDAO.addBill(billDTO);
            if (bill != null) {
                System.out.println("Creating bill...");
                PaymentSystem paymentSystem = PaymentSystem.getInstance();
                paymentSystem.processPayment(bill);
            } else {
                System.out.println("Bill creation failed");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void lateReturnCharge(int orderId){
        BillDAO billDAO = BillDAO.getInstance();
        BillDTO bill = billDAO.lateReturnCharge(orderId);
        if (bill != null) {
            PaymentSystem paymentSystem = PaymentSystem.getInstance();
            paymentSystem.processLateReturnCharge(bill);
        } else {
            System.out.println("Bill not found for orderId: " + orderId);
        }
    }
}
