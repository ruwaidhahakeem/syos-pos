package tests;

import database.DatabaseConnection;
import models.*;
import dao.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ReportTests {
    @BeforeEach
    void setUp() {
        try (Connection connection = DatabaseConnection.getConnection()) {
            // Insert test data for Items
            String insertItemQuery = "INSERT INTO Items (itemCode, itemName, price, variation, batchCode, itemType) VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement itemStatement = connection.prepareStatement(insertItemQuery)) {
                itemStatement.setString(1, "001");
                itemStatement.setString(2, "Apple");
                itemStatement.setDouble(3, 0.99);
                itemStatement.setString(4, "Organic");
                itemStatement.setString(5, "B001");
                itemStatement.setString(6, "Fresh Produce");
                itemStatement.executeUpdate();

                itemStatement.setString(1, "002");
                itemStatement.setString(2, "Banana");
                itemStatement.setDouble(3, 1.29);
                itemStatement.setString(4, "Fresh");
                itemStatement.setString(5, "B002");
                itemStatement.setString(6, "Fresh Produce");
                itemStatement.executeUpdate();
            }

            // Insert test data for InStoreStock
            String insertInStoreStockQuery = "INSERT INTO InStoreStock (itemCode, quantityOnShelf, shelfRubric, restockQuantity) VALUES (?, ?, ?, ?)";
            try (PreparedStatement stockStatement = connection.prepareStatement(insertInStoreStockQuery)) {
                stockStatement.setString(1, "001");
                stockStatement.setInt(2, 5);
                stockStatement.setInt(3, 20);
                stockStatement.setInt(4, 15);
                stockStatement.executeUpdate();
            }

            // Insert test data for WarehouseStock
            String insertWarehouseStockQuery = "INSERT INTO WarehouseStock (itemCode, quantityInStock) VALUES (?, ?)";
            try (PreparedStatement warehouseStatement = connection.prepareStatement(insertWarehouseStockQuery)) {
                warehouseStatement.setString(1, "001");
                warehouseStatement.setInt(2, 45);
                warehouseStatement.executeUpdate();

                warehouseStatement.setString(1, "002");
                warehouseStatement.setInt(2, 55);
                warehouseStatement.executeUpdate();
            }

            // Insert test data for Bills and BillItems
            String insertBillQuery = "INSERT INTO Bills (billDate, totalAmount, cashReceived, cashChange) VALUES (?, ?, ?, ?)";
            try (PreparedStatement billStatement = connection.prepareStatement(insertBillQuery, PreparedStatement.RETURN_GENERATED_KEYS)) {
                billStatement.setDate(1, new java.sql.Date(new java.util.Date().getTime()));
                billStatement.setDouble(2, 2.28);
                billStatement.setDouble(3, 3.00);
                billStatement.setDouble(4, 0.72);
                billStatement.executeUpdate();

                // Get generated bill ID
                try (var generatedKeys = billStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int billId = generatedKeys.getInt(1);

                        // Insert bill items
                        String insertBillItemQuery = "INSERT INTO BillItems (billId, itemCode, quantity, itemTotal, batchCode) VALUES (?, ?, ?, ?, ?)";
                        try (PreparedStatement billItemStatement = connection.prepareStatement(insertBillItemQuery)) {
                            billItemStatement.setInt(1, billId);
                            billItemStatement.setString(2, "001");
                            billItemStatement.setInt(3, 1);
                            billItemStatement.setDouble(4, 0.99);
                            billItemStatement.setString(5, "B001");
                            billItemStatement.executeUpdate();

                            billItemStatement.setInt(1, billId);
                            billItemStatement.setString(2, "002");
                            billItemStatement.setInt(3, 1);
                            billItemStatement.setDouble(4, 1.29);
                            billItemStatement.setString(5, "B002");
                            billItemStatement.executeUpdate();
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @AfterEach
    void tearDown() {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String deleteQuery = "DELETE FROM Items WHERE itemCode IN ('001', '002');" +
                    "DELETE FROM InStoreStock;" +
                    "DELETE FROM WarehouseStock;" +
                    "DELETE FROM Bills;" +
                    "DELETE FROM BillItems;";
            try (PreparedStatement statement = connection.prepareStatement(deleteQuery)) {
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testTotalSaleForTheDayReport() {

    }

}
