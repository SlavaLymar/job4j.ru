package ru.yalymar.crudservlet.model.db;

import org.junit.Test;
import java.sql.Connection;
import static junit.framework.TestCase.assertNotNull;

public class DBManagerTest {

    @Test
    public void whenCreateConnectToDBShouldGetNotNull() throws InterruptedException {
        DBManager dbManager = new DBManager();
        Connection result = dbManager.getC();
        assertNotNull(result);
    }
}