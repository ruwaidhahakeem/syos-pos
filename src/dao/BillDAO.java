package dao;

import database.DatabaseConnection;
import models.Bill;
import models.BillItem;


import java.util.List;

public interface BillDAO {
    void addBill (Bill bill);
    Bill getBill(int billId);
    void updateBill(Bill bill);
    void deleteBill(int billId);
    List<Bill> getAllBills();
    List<BillItem> getBillItems(int billId); // takes all the bill items for a ceratin bill

}
