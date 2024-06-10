package database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//this is what manages the pool of db connections
//handles the taking and the releasing of db connections

public class DBConnectionPool {


    private static final int POOL_SIZE = 20; //only 20 reusable objects
    private static final List<Connection> currentConnections = new ArrayList<>(POOL_SIZE); //available Connections
    private static final List<Connection> usedConnections = new ArrayList<>();// connections currently being used

// 3 methods, initializePool, getDBConnection and releaseConnection
    // and get no of available connections shows the no of active connections

    static {
        try{
            //using a static method, the makeConnection method is called
            //a db connection pool is initialized when the class is loaded

            for (int i = 0; i < POOL_SIZE; i++) {
                currentConnections.add(makeConnection());
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    // creates a connection w the db
    //createConnection
    private static Connection makeConnection() throws SQLException {
        //createConnection
        // Update with your database details
        String url = "jdbc:mysql://localhost:3306/pos_db";
        String username = "root";
        String password = "r0otPa55//orD";
        return DriverManager.getConnection(url, username, password);
    }

    //checks the current available connections
    //getConnection
    public static Connection checkAvailableConnections() {

        //checks availability of connections
        if (currentConnections.isEmpty()){
            System.out.println("There are No Connections");
            return null;
        }
        //if there are connections, removes and retrieves the last connections from currentConnection List
        Connection connection = currentConnections.remove(currentConnections.size()-1);

        //adds the connection to the usedConnections list
        usedConnections.add(connection);
        return connection;
    }

    public static boolean releaseConnection(Connection connection){
        if (connection !=null){
            //remove from used connection list
            usedConnections.remove(connection);
            currentConnections.add(connection); //add i =t back to available connections
            //allows for reuse of connections rather than creating a new instance

            return true;
        }
        return false;
    }

    //no of connections
    //getAvailableConnectionsCount()
    public static int getNoOfAvailableConnections(){
        return currentConnections.size();
    }


    public static int getNoOfUsedConnections(){
        return usedConnections.size();
    }
}

