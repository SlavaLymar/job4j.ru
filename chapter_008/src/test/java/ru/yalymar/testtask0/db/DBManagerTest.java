package ru.yalymar.testtask0.db;

import org.junit.Test;
import java.sql.Connection;
import static junit.framework.TestCase.assertNotNull;

public class DBManagerTest {

    @Test
    public void whenConnectingToDBShouldGet(){
        DBManager dbManager = new DBManager();
        Connection c = dbManager.connectDB();
        assertNotNull(c);
    }
}