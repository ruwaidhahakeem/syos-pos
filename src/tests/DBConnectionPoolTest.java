package tests;

import database.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

class DBConnectionPoolTest {

    @BeforeEach
    void setUp() {
        // If needed, perform any setup operations
    }

    @AfterEach
    void tearDown() {
        // If needed, perform any cleanup operations
    }

    @Test
    //this tests the initial size of the pool which should be 20
    void testInitialPoolSize() {
        int initialSizeOfPool = DBConnectionPool.getNoOfAvailableConnections();
        assertEquals(20, initialSizeOfPool, "Initial pool size should be 20");
    }

    @Test
    //checks a connection and then sees if no of connections reduce
    //releases and then cehcks again
    void testCheckAvailableConnections() {
        Connection connection = DBConnectionPool.checkAvailableConnections();
        assertNotNull(connection, "Connection should not be null");
        int availableConnections = DBConnectionPool.getNoOfAvailableConnections();
        assertEquals(19, availableConnections, "Available connections should be 19 after one checkout");

        DBConnectionPool.releaseConnection(connection);
        availableConnections = DBConnectionPool.getNoOfAvailableConnections();
        assertEquals(20, availableConnections, "Available connections should be 20 after release");
    }

    //tests if when all connections are used, if the pool is null
    @Test
    void testNoConnectionAvailable() {
        for (int i = 0; i < 20; i++) {
            DBConnectionPool.checkAvailableConnections();
        }

        Connection connection = DBConnectionPool.checkAvailableConnections();
        assertNull(connection, "Connection should be null when pool is empty");
    }

    //takes multiople connections and then releases
    //to see if pool size is managed correctly
    @Test
    void testMultipleReleases() {
        Connection connection1 = DBConnectionPool.checkAvailableConnections();
        Connection connection2 = DBConnectionPool.checkAvailableConnections();

        assertNotNull(connection1, "First connection should't be null");
        assertNotNull(connection2, "Second connection should'nt be null");

        DBConnectionPool.releaseConnection(connection1);
        DBConnectionPool.releaseConnection(connection2);

        int availableConnections = DBConnectionPool.getNoOfAvailableConnections();
        assertEquals(20, availableConnections, "Available con nections should be 20 after releasing two connections");
    }
}
