package ru.yalymar.jdbc.model;

import org.junit.Test;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class DBManagerTest {

    @Test
    public void whenConnectDBShouldGetConnection() throws SQLException {
        DBManager dbManager = new DBManager();
        //dbManager.createDB();
        Connection connection = dbManager.connectDB();
        assertNotNull(connection);

        dbManager.disconnectDB();
        assertTrue(dbManager.getC().isClosed());
    }

    //@Test
    public void whenCreateItemTableShouldGetTrue() throws SQLException {
        DBManager dbManager = new DBManager();
        Connection c = dbManager.connectDB();
        dbManager.createItemsTable();

        PreparedStatement st = c.prepareStatement("SELECT * FROM Items");
        boolean result1 = st.execute();
        assertTrue(result1);

        dbManager.disconnectDB();
        st.close();
    }

    //@Test
    public void whenCreateCommentsTableShouldGetTrue() throws SQLException {
        DBManager dbManager = new DBManager();
        Connection c = dbManager.connectDB();
        dbManager.createCommentsTable();

        PreparedStatement st = c.prepareStatement("SELECT * FROM Comments");
        boolean result2 = st.execute();
        assertTrue(result2);
        dbManager.disconnectDB();
        st.close();
    }
}