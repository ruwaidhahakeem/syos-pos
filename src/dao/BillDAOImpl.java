package dao;

import models.Bill;
import models.BillItem;
import database.DBConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BillDAOImpl implements BillDAO {

    @Override
    public void addBill(Bill bill) {
        String billQuery = "INSERT INTO Bills (billDate, totalAmount, cashReceived, cashChange) VALUES (?, ?, ?, ?)";
        String billItemQuery = "INSERT INTO BillItems (billId, itemCode, quantity, itemTotal, batchCode) VALUES (?, ?, ?, ?, ?)";
        Connection connection = null;
        try {
            connection = DBConnectionPool.checkAvailableConnections(); // Get a connection from the pool
            if (connection != null) {
                try (PreparedStatement billStatement = connection.prepareStatement(billQuery, Statement.RETURN_GENERATED_KEYS);
                     PreparedStatement billItemStatement = connection.prepareStatement(billItemQuery)) {

                    billStatement.setDate(1, new java.sql.Date(bill.getBillDate().getTime()));
                    billStatement.setDouble(2, bill.getTotalAmount());
                    billStatement.setDouble(3, bill.getCashReceived());
                    billStatement.setDouble(4, bill.getCashChange());
                    billStatement.executeUpdate();

                    // Get generated billId
                    try (ResultSet generatedKeys = billStatement.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            int billId = generatedKeys.getInt(1);
                            bill.setBillId(billId);

                            // Insert billItems
                            for (BillItem item : bill.getItems()) {
                                billItemStatement.setInt(1, billId);
                                billItemStatement.setString(2, item.getItemCode());
                                billItemStatement.setInt(3, item.getQuantity());
                                billItemStatement.setDouble(4, item.getItemTotal());
                                billItemStatement.setString(5, item.getBatchCode());
                                billItemStatement.executeUpdate();
                            }
                        } else {
                            throw new SQLException("Bill creation failed, no ID obtained.");
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                DBConnectionPool.releaseConnection(connection); // Release the connection back to the pool
            }
        }
    }

    @Override
    public Bill getBill(int billId) {
        String billQuery = "SELECT * FROM Bills WHERE billId = ?";
        Bill bill = null;
        Connection connection = null;
        try {
            connection = DBConnectionPool.checkAvailableConnections(); // Get a connection from the pool
            if (connection != null) {
                try (PreparedStatement billStatement = connection.prepareStatement(billQuery)) {

                    billStatement.setInt(1, billId);
                    ResultSet billResultSet = billStatement.executeQuery();

                    if (billResultSet.next()) {
                        bill = new Bill(
                                billResultSet.getInt("billId"),
                                billResultSet.getDate("billDate"),
                                getBillItems(billId), // Get associated BillItems
                                billResultSet.getDouble("totalAmount"),
                                billResultSet.getDouble("cashReceived"),
                                billResultSet.getDouble("cashChange")
                        );
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                DBConnectionPool.releaseConnection(connection); // Release the connection back to the pool
            }
        }
        return bill;
    }

    @Override
    public void updateBill(Bill bill) {
        String billQuery = "UPDATE Bills SET billDate = ?, totalAmount = ?, cashReceived = ?, cashChange = ? WHERE billId = ?";
        String deleteBillItemsQuery = "DELETE FROM BillItems WHERE billId = ?";
        String billItemQuery = "INSERT INTO BillItems (billId, itemCode, quantity, itemTotal, batchCode) VALUES (?, ?, ?, ?, ?)";
        Connection connection = null;
        try {
            connection = DBConnectionPool.checkAvailableConnections(); // Get a connection from the pool
            if (connection != null) {
                try (PreparedStatement billStatement = connection.prepareStatement(billQuery);
                     PreparedStatement deleteBillItemsStatement = connection.prepareStatement(deleteBillItemsQuery);
                     PreparedStatement billItemStatement = connection.prepareStatement(billItemQuery)) {

                    billStatement.setDate(1, new java.sql.Date(bill.getBillDate().getTime()));
                    billStatement.setDouble(2, bill.getTotalAmount());
                    billStatement.setDouble(3, bill.getCashReceived());
                    billStatement.setDouble(4, bill.getCashChange());
                    billStatement.setInt(5, bill.getBillId());
                    billStatement.executeUpdate();

                    // Delete old bill items
                    deleteBillItemsStatement.setInt(1, bill.getBillId());
                    deleteBillItemsStatement.executeUpdate();

                    // Insert new bill items
                    for (BillItem item : bill.getItems()) {
                        billItemStatement.setInt(1, bill.getBillId());
                        billItemStatement.setString(2, item.getItemCode());
                        billItemStatement.setInt(3, item.getQuantity());
                        billItemStatement.setDouble(4, item.getItemTotal());
                        billItemStatement.setString(5, item.getBatchCode());
                        billItemStatement.executeUpdate();
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                DBConnectionPool.releaseConnection(connection); // Release the connection back to the pool
            }
        }
    }

    @Override
    public void deleteBill(int billId) {
        String billQuery = "DELETE FROM Bills WHERE billId = ?";
        String billItemsQuery = "DELETE FROM BillItems WHERE billId = ?";
        Connection connection = null;
        try {
            connection = DBConnectionPool.checkAvailableConnections(); // Get a connection from the pool
            if (connection != null) {
                try (PreparedStatement billStatement = connection.prepareStatement(billQuery);
                     PreparedStatement billItemsStatement = connection.prepareStatement(billItemsQuery)) {

                    // Delete bill items first
                    billItemsStatement.setInt(1, billId);
                    billItemsStatement.executeUpdate();

                    // Delete bill
                    billStatement.setInt(1, billId);
                    billStatement.executeUpdate();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                DBConnectionPool.releaseConnection(connection); // Release the connection back to the pool
            }
        }
    }

    @Override
    public List<Bill> getAllBills() {
        List<Bill> bills = new ArrayList<>();
        String query = "SELECT * FROM Bills";
        Connection connection = null;
        try {
            connection = DBConnectionPool.checkAvailableConnections(); // Get a connection from the pool
            if (connection != null) {
                try (PreparedStatement statement = connection.prepareStatement(query);
                     ResultSet resultSet = statement.executeQuery()) {

                    while (resultSet.next()) {
                        Bill bill = new Bill(
                                resultSet.getInt("billId"),
                                resultSet.getDate("billDate"),
                                getBillItems(resultSet.getInt("billId")), // Get associated BillItems
                                resultSet.getDouble("totalAmount"),
                                resultSet.getDouble("cashReceived"),
                                resultSet.getDouble("cashChange")
                        );
                        bills.add(bill);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                DBConnectionPool.releaseConnection(connection); // Release the connection back to the pool
            }
        }
        return bills;
    }

    @Override
    public List<BillItem> getBillItems(int billId) {
        List<BillItem> billItems = new ArrayList<>();
        String query = "SELECT * FROM BillItems WHERE billId = ?";
        Connection connection = null;
        try {
            connection = DBConnectionPool.checkAvailableConnections(); // Get a connection from the pool
            if (connection != null) {
                try (PreparedStatement statement = connection.prepareStatement(query)) {
                    statement.setInt(1, billId);
                    ResultSet resultSet = statement.executeQuery();
                    while (resultSet.next()) {
                        BillItem item = new BillItem(
                                resultSet.getInt("billItemId"),
                                resultSet.getString("itemCode"),
                                resultSet.getInt("quantity"),
                                resultSet.getDouble("itemTotal"),
                                resultSet.getString("batchCode")
                        );
                        billItems.add(item);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                DBConnectionPool.releaseConnection(connection); // Release the connection back to the pool
            }
        }
        return billItems;
    }
}
