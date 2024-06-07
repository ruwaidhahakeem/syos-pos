package dao;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import models.Bill;
import models.BillItem;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;


class BillItemDAOImplTest {

    private BillDAO billDAO;

    @BeforeEach
    void setUp() {
        billDAO=new BillDAOImpl();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testAddBill() {
        BillDAO billDAO = new BillDAOImpl();
        List<BillItem> billItems = new ArrayList<>();
        // Add BillItems to the list
        Bill bill = new Bill(1, new Date(), billItems, 100.0, 120.0, 20.0);

        billDAO.addBill(bill);

        Bill fetchedBill = billDAO.getBill(bill.getBillId());
        assertNotNull(fetchedBill, "Fetched bill should not be null");
    }

    @Test
    void deleteBillItem() {
        BillDAO billDAO = new BillDAOImpl();
        Bill bill = new Bill(2, new Date(), null, 50.0, 60.0, 10.0);

        billDAO.addBill(bill);
        billDAO.deleteBill(bill.getBillId());

        Bill fetchedBill = billDAO.getBill(bill.getBillId());
        assertNull(fetchedBill, "Fetched bill should be null after deletion");
    }


    @Test
    void getBillItem() {
    }

    @Test
    void updateBillItem() {
    }

    @Test
    void getAllBillItems() {
    }


}