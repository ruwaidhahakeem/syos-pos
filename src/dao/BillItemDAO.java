package dao;

import database.DatabaseConnection;
import models.BillItem;
import java.util.List;

public interface BillItemDAO {
    void addBillItem(BillItem billItem);
    BillItem getBillItem(int itemCode);
    void updateBillItem(BillItem billItem);
    void deleteBillItem(int itemCode);
    List<BillItem> getAllBillItems();

}
