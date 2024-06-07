import database.DBConnectionPool;
import database.DatabaseConnection;
import models.*;
import dao.*;
import report.*;
import factories.*;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Connection connection = null;
        try {
            connection = DBConnectionPool.checkAvailableConnections();
            if (connection != null && !connection.isClosed()) {
                System.out.println("Connection successful!");
            } else {
                System.out.println("Failed to establish a connection.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                DBConnectionPool.releaseConnection(connection);
            }
        }
    }
}
