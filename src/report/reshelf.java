package report;

import database.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.*;
import report.reportClasses.*;

//displays items needed to be reshelved in the store
public class reshelf extends baseReport {

    private List<InStoreStock> itemsToReshelve = new ArrayList<>();

    @Override
    protected void getRequiredData() {
    String queryGetData= "SELECT i.itemCode, i.itemName, is.quantityOnShelf, is.shelfRubric, is.restockQuantity " +
            "FROM InStoreStock s " +
            "JOIN Items i ON s.itemCode = i.itemCode " +
            "WHERE is.quantityOnShelf < is.shelfRubric";
    //when the no of items on shelf goes down a ceratin amount
        //needs to be reshelved

    try(Connection connection = DatabaseConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(queryGetData);
        ResultSet resultSet = statement.executeQuery()){

        while (resultSet.next()) {
            InStoreStock stock = new InStoreStock();

            stock.setItemCode(resultSet.getString("itemCode"));
            stock.setItemName(resultSet.getString("itemName"));
            stock.setQuantityOnShelf(resultSet.getInt("quantityOnShelf"));
            stock.setShelfRubric(resultSet.getInt("shelfRubric"));
            stock.setRestockQuantity(resultSet.getInt("restockQuantity"));
            itemsToReshelve.add(stock);
        }

        } catch(SQLException e){
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
        System.out.println("Items to Re-shelf:");
        System.out.printf("%-10s | %-20s | %-10s | %-10s%n", "Item Code", "Item Name", "Quantity on Shelf", "Restock Quantity");
        System.out.println("----------------------------------------------------------");
        for (InStoreStock stock : itemsToReshelve) {
            System.out.printf("%-10s | %-20s | %-10d | %-10d%n",
                    stock.getItemCode(), stock.getItemName(), stock.getQuantityOnShelf(), stock.getRestockQuantity());
        }
    }
}
