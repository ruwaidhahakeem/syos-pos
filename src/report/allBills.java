package report;

import database.DatabaseConnection;
import models.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//this report shows all the bills from 1 to X
public class allBills extends baseReport {
    private List<Bill> billList = new ArrayList<>();

    @Override
    protected void getRequiredData() {
        String allBills = "SELECT billId, billDate, totalAmount, cashReceived, cashChange FROM Bills";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(allBills);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Bill bill = new Bill();
                bill.setBillId(resultSet.getInt("billId"));
                bill.setBillDate(resultSet.getDate("billDate"));
                bill.setTotalAmount(resultSet.getDouble("totalAmount"));
                bill.setCashReceived(resultSet.getDouble("cashReceived"));
                bill.setCashChange(resultSet.getDouble("cashChange"));
                billList.add(bill);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void processData() {

    }

    @Override
    protected void reportFormat() {

    }

    @Override
    protected void reportDisplay() {
        System.out.println("Bill Report:");
        for (Bill bill : billList) {
            System.out.printf("Bill ID: %d, Bill Date: %s, Total Amount: %.2f, Cash Received: %.2f, Cash Change: %.2f%n",
                    bill.getBillId(), bill.getBillDate(), bill.getTotalAmount(), bill.getCashReceived(), bill.getCashChange());
    }
    }
}
