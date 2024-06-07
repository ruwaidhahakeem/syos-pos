package dao;

import database.DBConnectionPool;
import models.WarehouseStock;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WarehouseStockDAOImpl implements WarehouseStockDAO {

    @Override
    public void addWarehouseStock(WarehouseStock stock) {
        String query = "INSERT INTO WarehouseStock (itemCode, batchCode, expiryDate, arrivalDate, quantityInStock, stockRubric) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = DBConnectionPool.checkAvailableConnections();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, stock.getItemCode());
            statement.setString(2, stock.getBatchCode());
            statement.setDate(3, new java.sql.Date(stock.getExpiryDate().getTime()));
            statement.setDate(4, new java.sql.Date(stock.getArrivalDate().getTime()));
            statement.setInt(5, stock.getQuantityInStock());
            statement.setInt(6, stock.getStockRubric());
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public WarehouseStock getWarehouseStock(String itemCode) {
        String query = "SELECT * FROM WarehouseStock WHERE itemCode = ?";
        WarehouseStock stock = null;

        try (Connection connection = DBConnectionPool.checkAvailableConnections();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, itemCode);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                stock = new WarehouseStock(resultSet.getString("itemCode"),
                        resultSet.getString("batchCode"),
                        resultSet.getDate("expiryDate"),
                        resultSet.getDate("arrivalDate"),
                        resultSet.getInt("quantityInStock"),
                        resultSet.getInt("stockRubric")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return stock;
    }

    @Override
    public void updateWarehouseStock(WarehouseStock stock) {
        String query = "UPDATE WarehouseStock SET quantityInStock = ? WHERE itemCode = ?";
        try (Connection connection = DBConnectionPool.checkAvailableConnections();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, stock.getBatchCode());
            statement.setDate(2, new java.sql.Date(stock.getExpiryDate().getTime()));
            statement.setDate(3, new java.sql.Date(stock.getArrivalDate().getTime()));
            statement.setInt(4, stock.getQuantityInStock());
            statement.setInt(5, stock.getStockRubric());
            statement.setString(6, stock.getItemCode());
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteWarehouseStock(String itemCode) {
        String query = "DELETE FROM WarehouseStock WHERE itemCode = ?";
        try (Connection connection = DBConnectionPool.checkAvailableConnections();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, itemCode);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<WarehouseStock> getAllWarehouseStock() {
        List<WarehouseStock> stockList = new ArrayList<>();
        String query = "SELECT * FROM WarehouseStock";
        try (Connection connection = DBConnectionPool.checkAvailableConnections();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                WarehouseStock stock = new WarehouseStock(
                        resultSet.getString("itemCode"),
                        resultSet.getString("batchCode"),
                        resultSet.getDate("expiryDate"),
                        resultSet.getDate("arrivalDate"),
                        resultSet.getInt("quantityInStock"),
                        resultSet.getInt("stockRubric")
                );
                stockList.add(stock);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stockList;
    }

    @Override
    public List<WarehouseStock> getStockByExpiry(String itemCode) {
        List<WarehouseStock> stockList = new ArrayList<>();
        String query = "SELECT * FROM WarehouseStock WHERE itemCode = ? ORDER BY expiryDate";
        try (Connection connection = DBConnectionPool.checkAvailableConnections();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, itemCode);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                WarehouseStock stock = new WarehouseStock(
                        resultSet.getString("itemCode"),
                        resultSet.getString("batchCode"),
                        resultSet.getDate("expiryDate"),
                        resultSet.getDate("arrivalDate"),
                        resultSet.getInt("quantityInStock"),
                        resultSet.getInt("stockRubric")
                );
                stockList.add(stock);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stockList;
    }

    @Override
    public List<WarehouseStock> getStockByArrival(String itemCode) {

        List<WarehouseStock> stockList = new ArrayList<>();
        String query = "SELECT * FROM WarehouseStock WHERE itemCode = ? ORDER BY arrivalDate";
        try (Connection connection = DBConnectionPool.checkAvailableConnections();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, itemCode);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                WarehouseStock stock = new WarehouseStock(
                        resultSet.getString("itemCode"),
                        resultSet.getString("batchCode"),
                        resultSet.getDate("expiryDate"),
                        resultSet.getDate("arrivalDate"),
                        resultSet.getInt("quantityInStock"),
                        resultSet.getInt("stockRubric")
                );
                stockList.add(stock);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stockList;
    }
}

