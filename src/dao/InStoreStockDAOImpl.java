package dao;

import database.DBConnectionPool;
import models.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InStoreStockDAOImpl implements InStoreStockDAO {

    @Override
    public void addInStoreStock(InStoreStock stock) {
        String query = "INSERT INTO InStoreStock (itemCode, quantityOnShelf, shelfRubric, restockQuantity) VALUES (?, ?, ?, ?)";
        try (Connection connection = DBConnectionPool.checkAvailableConnections();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, stock.getItemCode());
            statement.setInt(2, stock.getQuantityOnShelf());
            statement.setInt(3, stock.getShelfRubric());
            statement.setInt(4, stock.getRestockQuantity());
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public InStoreStock getInStoreStock(String itemCode) {
        String query = "SELECT * FROM InStoreStock WHERE itemCode = ?";
        InStoreStock stock = null;

        try (Connection connection = DBConnectionPool.checkAvailableConnections();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, itemCode);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                stock = new InStoreStock(
                        resultSet.getString("itemCode"),
                        resultSet.getString("itemName"),
                        resultSet.getString("batchCode"),
                        resultSet.getString("variation"),
                        resultSet.getInt("quantityOnShelf"),
                        resultSet.getInt("shelfRubric"),
                        resultSet.getInt("restockQuantity")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return stock;
    }

    @Override
    public void updateInStoreStock(InStoreStock stock) {
        String query = "UPDATE InStoreStock SET quantityOnShelf = ?, shelfRubric = ?, restockQuantity = ? WHERE itemCode = ?";
        try (Connection connection = DBConnectionPool.checkAvailableConnections();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, stock.getQuantityOnShelf());
            statement.setInt(2, stock.getShelfRubric());
            statement.setInt(3, stock.getRestockQuantity());
            statement.setString(4, stock.getItemCode());
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteInStoreStock(String itemCode) {
        String query = "DELETE FROM InStoreStock WHERE itemCode = ?";
        try (Connection connection = DBConnectionPool.checkAvailableConnections();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, itemCode);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<InStoreStock> getAllInStoreStock() {
        List<InStoreStock> stockList = new ArrayList<>();
        String query = "SELECT * FROM InStoreStock";
        try (Connection connection = DBConnectionPool.checkAvailableConnections();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                InStoreStock stock = new InStoreStock(
                        resultSet.getString("itemCode"),
                        resultSet.getString("itemName"),
                        resultSet.getString("batchCode"),
                        resultSet.getString("variation"),
                        resultSet.getInt("quantityOnShelf"),
                        resultSet.getInt("shelfRubric"),
                        resultSet.getInt("restockQuantity")
                );
                stockList.add(stock);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stockList;
    }

    @Override
    public void restockInStoreStock(String itemCode) {
        WarehouseStockDAO warehouseStockDAO = new WarehouseStockDAOImpl();

        // Retrieve warehouse stock sorted by expiry date
        List<WarehouseStock> warehouseStocks = warehouseStockDAO.getStockByExpiry(itemCode);
        if (warehouseStocks.isEmpty()) {
            // If no stock found sorted by expiry, retrieve by arrival date
            warehouseStocks = warehouseStockDAO.getStockByExpiry(itemCode);
        }

        if (!warehouseStocks.isEmpty()) {
            InStoreStock currentStock = getInStoreStock(itemCode);
            if (currentStock != null) {
                int totalRestocked = 0;
                for (WarehouseStock warehouseStock : warehouseStocks) {
                    int restockQuantity = warehouseStock.getQuantityInStock();
                    totalRestocked += restockQuantity;

                    // Update the in-store stock with the restock quantity
                    currentStock.setQuantityOnShelf(currentStock.getQuantityOnShelf() + restockQuantity);
                    updateInStoreStock(currentStock);

                    // Remove the restocked quantity from the warehouse stock
                    warehouseStock.setQuantityInStock(0);
                    warehouseStockDAO.updateWarehouseStock(warehouseStock);

                    // Stop restocking if the shelf rubric is met
                    if (currentStock.getQuantityOnShelf() >= currentStock.getShelfRubric()) {
                        break;
                    }
                }

                System.out.println("Restocked " + totalRestocked + " units of item " + itemCode + " to in-store stock.");
            }
        }
    }
}

