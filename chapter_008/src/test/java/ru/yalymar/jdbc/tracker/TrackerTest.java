package ru.yalymar.jdbc.tracker;

import org.junit.Test;
import ru.yalymar.jdbc.model.DBManager;
import ru.yalymar.jdbc.model.Item;
import static junit.framework.TestCase.assertTrue;

public class TrackerTest {

    @Test
    public void whenAddItemShouldGetInt(){
        DBManager dbManager = new DBManager();
        Tracker tracker = new Tracker(dbManager);
        dbManager.connectDB();
        int result = tracker.add(new Item("item1", "blabla"));
        assertTrue(result > 0);
        dbManager.disconnectDB();
    }

    @Test
    public void whenUpdateItemShouldGetInt(){
        DBManager dbManager = new DBManager();
        Tracker tracker = new Tracker(dbManager);
        dbManager.connectDB();
        int result = tracker.update(new Item("item1", "blabla", "1"));
        assertTrue(result > 0);
        dbManager.disconnectDB();
    }

    @Test
    public void whenShowAllItemsItemShouldGetTrue(){
        DBManager dbManager = new DBManager();
        Tracker tracker = new Tracker(dbManager);
        dbManager.connectDB();
        boolean result = tracker.showAllItems();
        assertTrue(result);
        dbManager.disconnectDB();
    }



}