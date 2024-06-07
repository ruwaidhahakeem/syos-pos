package dao;

import models.BillItem;
import database.DBConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BillItemDAOImpl implements BillItemDAO {

    @Override
    public void addBillItem(BillItem billItem) {
        String query = "INSERT INTO BillItems (billId, itemCode, quantity, itemTotal, batchCode) VALUES (?, ?, ?, ?, ?)";
        Connection connection = null;
        try {
            connection = DBConnectionPool.checkAvailableConnections(); // Get a connection from the pool
            if (connection != null) {
                try (PreparedStatement statement = connection.prepareStatement(query)) {
                    statement.setInt(1, billItem.getBillItemId());
                    statement.setString(2, billItem.getItemCode());
                    statement.setInt(3, billItem.getQuantity());
                    statement.setDouble(4, billItem.getItemTotal());
                    statement.setString(5, billItem.getBatchCode());
                    statement.executeUpdate();
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
    public BillItem getBillItem(int billItemId) {
        String query = "SELECT * FROM BillItems WHERE billItemId = ?";
        BillItem billItem = null;
        Connection connection = null;
        try {
            connection = DBConnectionPool.checkAvailableConnections(); // Get a connection from the pool
            if (connection != null) {
                try (PreparedStatement statement = connection.prepareStatement(query)) {
                    statement.setInt(1, billItemId);
                    ResultSet resultSet = statement.executeQuery();
                    if (resultSet.next()) {
                        billItem = new BillItem(
                                resultSet.getInt("billItemId"),
                                resultSet.getString("itemCode"),
                                resultSet.getInt("quantity"),
                                resultSet.getDouble("itemTotal"),
                                resultSet.getString("batchCode")
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
        return billItem;
    }

    @Override
    public void updateBillItem(BillItem billItem) {
        String query = "UPDATE BillItems SET itemCode = ?, quantity = ?, itemTotal = ?, batchCode = ? WHERE billItemId = ?";
        Connection connection = null;
        try {
            connection = DBConnectionPool.checkAvailableConnections(); // Get a connection from the pool
            if (connection != null) {
                try (PreparedStatement statement = connection.prepareStatement(query)) {
                    statement.setString(1, billItem.getItemCode());
                    statement.setInt(2, billItem.getQuantity());
                    statement.setDouble(3, billItem.getItemTotal());
                    statement.setString(4, billItem.getBatchCode());
                    statement.setInt(5, billItem.getBillItemId());
                    statement.executeUpdate();
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
    public void deleteBillItem(int billItemId) {
        String query = "DELETE FROM BillItems WHERE billItemId = ?";
        Connection connection = null;
        try {
            connection = DBConnectionPool.checkAvailableConnections(); // Get a connection from the pool
            if (connection != null) {
                try (PreparedStatement statement = connection.prepareStatement(query)) {
                    statement.setInt(1, billItemId);
                    statement.executeUpdate();
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
    public List<BillItem> getAllBillItems() {
        List<BillItem> billItems = new ArrayList<>();
        String query = "SELECT * FROM BillItems";
        Connection connection = null;
        try {
            connection = DBConnectionPool.checkAvailableConnections(); // Get a connection from the pool
            if (connection != null) {
                try (PreparedStatement statement = connection.prepareStatement(query);
                     ResultSet resultSet = statement.executeQuery()) {

                    while (resultSet.next()) {
                        BillItem billItem = new BillItem(
                                resultSet.getInt("billItemId"),
                                resultSet.getString("itemCode"),
                                resultSet.getInt("quantity"),
                                resultSet.getDouble("itemTotal"),
                                resultSet.getString("batchCode")
                        );
                        billItems.add(billItem);
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

//    @Override
//    public List<BillItem> getBillItemsByBillId(int billId) {
//        List<BillItem> billItems = new ArrayList<>();
//        String query = "SELECT * FROM BillItems WHERE billId = ?";
//        Connection connection = null;
//        try {
//            connection = DBConnectionPool.checkAvailableConnections(); // Get a connection from the pool
//            if (connection != null) {
//                try (PreparedStatement statement = connection.prepareStatement(query)) {
//                    statement.setInt(1, billId);
//                    ResultSet resultSet = statement.executeQuery();
//                    while (resultSet.next()) {
//                        BillItem billItem = new BillItem(
//                                resultSet.getInt("billItemId"),
//                                resultSet.getString("itemCode"),
//                                resultSet.getInt("quantity"),
//                                resultSet.getDouble("itemTotal"),
//                                resultSet.getString("batchCode")
//                        );
//                        billItems.add(billItem);
//                    }
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            if (connection != null) {
//                DBConnectionPool.releaseConnection(connection); // Release the connection back to the pool
//            }
//        }
//        return billItems;
//    }
}
