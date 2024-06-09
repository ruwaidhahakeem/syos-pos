package dao;

import models.Bill;
import models.BillItem;

import java.util.List;

public class BillDAOImpl implements BillDAO {
    @Override
    public void addBill(Bill bill) {

    }

    @Override
    public Bill getBill(int billId) {
        return null;
    }

    @Override
    public void updateBill(Bill bill) {

    }

    @Override
    public void deleteBill(int billId) {

    }

    @Override
    public List<Bill> getAllBills() {
        return List.of();
    }

    @Override
    public List<BillItem> getBillItems(int billId) {
        return List.of();
    }
}
