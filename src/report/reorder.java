package report;


import database.DatabaseConnection;
import models.Item;

import database.DatabaseConnection;
import models.WarehouseStock;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//shows all the items that need to be reordered and have fallen below 50
public class reorder extends baseReport {
    private List<WarehouseStock> itemsToReorder = new ArrayList<>();

    @Override
    protected void getRequiredData() {
        // get the item's that have fallen below 50Nos
        //i have to get data from warehouse stock where quantity should be <50
        String queryGetRestockItem = "SELECT itemCode, quantityInStock FROM WarehouseStock WHERE quantityInStock < 50";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(queryGetRestockItem);
             ResultSet resultSet = statement.executeQuery()) {

            // Iterate through the result set and add data to itemsToReorder list
            while (resultSet.next()) {
                WarehouseStock toReorder = new WarehouseStock();
                toReorder.setItemCode(resultSet.getString("itemCode"));
                toReorder.setQuantityInStock(resultSet.getInt("quantityInStock"));
                itemsToReorder.add(toReorder);
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
        // Display the reorder report
        System.out.println("Reorder Report:");
        System.out.printf("%-10s | %-20s%n", "Item Code", "Quantity in Stock");
        System.out.println("-------------------------------");
        for (WarehouseStock stock : itemsToReorder) {
            System.out.printf("%-10s | %-20d%n", stock.getItemCode(), stock.getQuantityInStock());
        }
    }
}
