package ru.yalymar.servlet.model.db;

import org.junit.Test;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.assertTrue;

public class DBManagerTest {

    @Test
    public void whenGetResultSetShouldBeBetterThen1() throws SQLException {
        DBManager dbManager = new DBManager();
        PreparedStatement st = null;
        st = dbManager.getC().prepareStatement("SELECT * FROM users",
                ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = dbManager.getGo().go(st);
        rs.last();
        assertTrue(rs.getRow() >= 1);
    }
}