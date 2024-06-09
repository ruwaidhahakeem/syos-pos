import database.DatabaseConnection;
import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            if (connection != null) {
                System.out.println("Connected to the database successfully!");
            } else {
                System.out.println("Failed to make connection!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
