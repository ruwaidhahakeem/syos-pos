package report;

import database.DatabaseConnection;
import models.Item;
import models.stockData;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//displays all available reports
public class availableStock extends baseReport {
    private List<stockData> stockDataList=new ArrayList<>();

    @Override
    protected void getRequiredData() {
        String query = "SELECT i.itemCode, i.itemName, iss.quantityOnShelf, " +
                "(SELECT SUM(ws.quantityInStock) FROM WarehouseStock ws WHERE ws.itemCode = i.itemCode) AS quantityInWarehouse " +
                "FROM Items i " +
                "JOIN InStoreStock iss ON i.itemCode = iss.itemCode";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                String itemCode = resultSet.getString("itemCode");
                String itemName = resultSet.getString("itemName");
                int quantityOnShelf = resultSet.getInt("quantityOnShelf");
                int quantityInWarehouse = resultSet.getInt("quantityInWarehouse");

            stockData availableStockData=new stockData(itemCode, itemName, quantityOnShelf, quantityInWarehouse);
            stockDataList.add(availableStockData);
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
        System.out.printf("%-15s %-30s %-20s %-20s%n", "Item Code", "Item Name", "Quantity on Shelf", "Quantity in Warehouse");
        for (stockData data : stockDataList) {
            System.out.printf("%-15s %-30s %-20d %-20d%n", data.getItemCode(), data.getItemName(), data.getQuantityOnShelf(), data.getQuantityInWarehouse());
        }
    }

    }

