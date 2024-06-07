package report;

import database.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import report.reportClasses.*;

public class totalSaleForTheDay extends baseReport {

    //list that stores the data we need for the report
    private List<SaleData> saleData;
    private double totalRevenue;

    @Override
    protected void getRequiredData() {
        /*
        get the required data from different tables in db for sales
            Item Name
            Item Code
            Total items sold (per item)
            the quantity sold (per item)
            total amount tendered (per item)
            a list of all the items sold
            total revenue for the day
        */
    saleData= new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection()){
            //bunch of queries to retrieve data for each column
            String query = "SELECT i.itemCode, i.itemName, SUM(bit.quantity) as quantitySold, " +
                    "SUM(bit.itemTotal) as totalPerItem, SUM(b.totalAmount) as totalAmount " +
                    "FROM Items i " +
                    "JOIN BillItems bit ON i.itemCode = bit.itemCode " +
                    "JOIN Bills b ON b.billId = bit.billItemId " +
                    "WHERE DATE(b.billDate) = CURDATE() " +
                    "GROUP BY i.itemCode, i.itemName";

            PreparedStatement statement=connection.prepareStatement(query);
            ResultSet resultSet= statement.executeQuery();

            while (resultSet.next()) {
                //get methods for the data we took since it's priv
                String itemCode = resultSet.getString("itemCode");
                String itemName = resultSet.getString("itemName");
                int quantitySold = resultSet.getInt("quantitySold");
                double totalPerItem = resultSet.getDouble("totalPerItem");
                double totalAmount = resultSet.getDouble("totalAmount");
                saleData.add(new SaleData(itemCode, itemName, quantitySold, totalPerItem, totalAmount));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    protected void processData() {
    //N/A
    }

    @Override
    protected void reportFormat() {
    //N/A
    }

    @Override
    protected void reportDisplay() {
        System.out.println("Total Sale");
        System.out.println("Item-Code | Item-Name | Quantity Sold | Total Amount Per Item | Total Amount");

        //table format
        for (SaleData data : saleData){
            //using placeholders, a table is made for each sale obj
            // string, string, int, float w 2 dp, float w 2 dp, new line
            System.out.printf("%s | %s | %d | %.2f | %.2f%n",
                    data.getItemCode(), data.getItemName(), data.getQuantitySold(), data.getTotalPerItem(), data.getTotalAmount());

            System.out.printf("Revenue for the Day: %.2f%n ", totalRevenue);
        }
    }
}
