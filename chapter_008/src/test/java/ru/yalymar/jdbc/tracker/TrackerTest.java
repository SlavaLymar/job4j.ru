package ru.yalymar.jdbc.tracker;

import org.junit.Test;
import java.sql.Connection;
import java.sql.SQLException;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class TrackerTest {

    @Test
    public void whenConnectDB() throws SQLException {
        Tracker tracker = new Tracker();
        //tracker.createDB();
        Connection connection = tracker.connectDB();
        assertNotNull(connection);

        tracker.disconnectDB();
        assertTrue(tracker.getConn().isClosed());
    }



}