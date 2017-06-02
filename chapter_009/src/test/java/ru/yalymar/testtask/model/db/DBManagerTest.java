package ru.yalymar.testtask.model.db;

import org.junit.Test;
import java.sql.Connection;
import static junit.framework.TestCase.assertNotNull;

public class DBManagerTest {

    @Test
    public void whenGetConnectionShouldBeNotNull(){
        DBManager dbManager = new DBManager();
        Connection c = dbManager.getC();
        assertNotNull(c);
    }
}