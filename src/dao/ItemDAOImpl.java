package dao;

import models.*;
import factories.*;
import database.DBConnectionPool;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemDAOImpl implements ItemDAO {
    private final ItemFactory itemFactory = new ItemConcreteFactory();
    @Override
    public void addItem(Item item) {
        String insertItem = "INSERT INTO Items (itemCode, itemName, price, variation, batchCode, itemType) VALUES (?, ?, ?, ?, ?, ?)";
        Connection connection = null;
        try {
            connection = DBConnectionPool.checkAvailableConnections(); // Get a connection from the pool
            if (connection != null) {
                try (PreparedStatement statement = connection.prepareStatement(insertItem)) {
                    statement.setString(1, item.getItemCode());
                    statement.setString(2, item.getItemName());
                    statement.setDouble(3, item.getPrice());
                    statement.setString(4, item.getVariation());
                    statement.setString(5, item.getBatchCode());
                    statement.setString(6, item.getItemType());
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
    public Item getItem(String itemCode) {
        String query = "SELECT * FROM Items WHERE itemCode = ?";
        Connection connection = null;
        Item item = null;
        try {
            connection = DBConnectionPool.checkAvailableConnections(); // Get a connection from the pool
            if (connection != null) {
                try (PreparedStatement statement = connection.prepareStatement(query)) {
                    statement.setString(1, itemCode);
                    System.out.println("Executing query: " + statement); // Debug statement
                    try (ResultSet resultSet = statement.executeQuery()) {
                        if (resultSet.next()) {
                            System.out.println("Item found in database"); // Debug statement
                            String itemType = resultSet.getString("itemType");
                            switch (itemType) {
                                case "Fresh Produce":
                                    item = new FreshProduce(
                                            resultSet.getString("itemCode"),
                                            resultSet.getString("itemName"),
                                            resultSet.getDouble("price"),
                                            resultSet.getString("variation"),
                                            resultSet.getString("batchCode"),
                                            resultSet.getString("itemType")
                                    );
                                    break;
                                case "Tinned Goods":
                                    item = new TinnedGoods(
                                            resultSet.getString("itemCode"),
                                            resultSet.getString("itemName"),
                                            resultSet.getDouble("price"),
                                            resultSet.getString("variation"),
                                            resultSet.getString("batchCode"),
                                            resultSet.getString("itemType")
                                    );
                                    break;
                                default:
                                    System.out.println("Not Categorized: " + itemType);
                                    item = new NotCategorized(
                                            resultSet.getString("itemCode"),
                                            resultSet.getString("itemName"),
                                            resultSet.getDouble("price"),
                                            resultSet.getString("variation"),
                                            resultSet.getString("batchCode"),
                                            resultSet.getString("itemType")
                                    );
                                    break;
                            }
                            if (item != null) {
                                System.out.println("Item details: " + item.getItemCode() + ", " + item.getItemName() + ", " + item.getPrice()); // Debug statement
                            }
                        } else {
                            System.out.println("No item found with itemCode: " + itemCode); // Debug statement
                        }
                    }
                }
            } else {
                System.out.println("Connection to database is null"); // Debug statement
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                DBConnectionPool.releaseConnection(connection); // Release the connection back to the pool
            }
        }
        return item;
    }


    @Override
    public void updateItem(Item item) {
        String query = "UPDATE Items SET itemName = ?, price = ?, variation = ?, batchCode = ?, itemType = ? WHERE itemCode = ?";
        Connection connection = null;
        try {
            connection = DBConnectionPool.checkAvailableConnections(); // Get a connection from the pool
            if (connection != null) {
                try (PreparedStatement statement = connection.prepareStatement(query)) {
                    statement.setString(1, item.getItemName());
                    statement.setDouble(2, item.getPrice());
                    statement.setString(3, item.getVariation());
                    statement.setString(4, item.getBatchCode());
                    statement.setString(5, item.getItemType());
                    statement.setString(6, item.getItemCode());
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
    public void deleteItem(String itemCode) {
        String query = "DELETE FROM Items WHERE itemCode = ?";
        Connection connection = null;
        try {
            connection = DBConnectionPool.checkAvailableConnections(); // Get a connection from the pool
            if (connection != null) {
                try (PreparedStatement statement = connection.prepareStatement(query)) {
                    statement.setString(1, itemCode);
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
    public List<Item> getAllItems() {
        List<Item> items = new ArrayList<>();
        String query = "SELECT * FROM Items";
        Connection connection = null;
        try {
            connection = DBConnectionPool.checkAvailableConnections(); // Get a connection from the pool
            if (connection != null) {
                try (PreparedStatement statement = connection.prepareStatement(query);
                     ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        Item item = itemFactory.createItem(
                                resultSet.getString("itemCode"),
                                resultSet.getString("itemName"),
                                resultSet.getDouble("price"),
                                resultSet.getString("variation"),
                                resultSet.getString("batchCode"),
                                resultSet.getString("itemType")
                        );
                        items.add(item);
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
        return items;
    }
}
