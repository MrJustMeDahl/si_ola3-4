package org.soft2.mockDAO;
import org.soft2.DTO.BillDTO;

import java.util.HashMap;
import java.util.Map;

public class BillDAO {

    // In memory database for bills
    // Singleton pattern

    private static BillDAO instance = null;
    private BillDAO() {}
    public static BillDAO getInstance() {
        if (instance == null) {
            instance = new BillDAO();
        }
        return instance;
    }

    public static Map<Integer, BillDTO> bills = new HashMap<>();


    public BillDTO addBill(BillDTO bill) {
        bills.put(bill.getOrderId(), bill);
        System.out.println("Bill added: " + bill.getOrderId());
        System.out.println("Current bills: " + bills.keySet());
        return bill;
    }

    public BillDTO getBill(String orderId) {
        return bills.get(orderId);
    }
    public Map<Integer, BillDTO> getAllBills() {
        return bills;
    }

    public BillDTO lateReturnCharge(int orderId){
        BillDTO bill = bills.get(orderId);
        if (bill != null) {
            bill.setLateReturnCharge(true);
            System.out.println("CONSUMER DAO: Late return charge added to bill: " + bill.getOrderId());
            return bill;
        }
        System.out.println("Current bills: " + bills.keySet());
        return null;
    }


}
