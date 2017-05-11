package ru.yalymar.jdbcconnectionpool.db;

import org.junit.Test;
import static org.junit.Assert.assertNotNull;

public class DBManagerTest {

    @Test
    public void whenConnectToDBShouldGetNotNull(){
        DBManager dbManager = new DBManager();
        assertNotNull(dbManager.getC());
    }
}