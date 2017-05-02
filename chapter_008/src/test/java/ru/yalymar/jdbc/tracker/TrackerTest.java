package ru.yalymar.jdbc.tracker;

import org.junit.Test;
import ru.yalymar.jdbc.model.Comment;
import ru.yalymar.jdbc.model.DBManager;
import ru.yalymar.jdbc.model.Item;
import java.util.List;
import static junit.framework.TestCase.assertNotNull;
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

    @Test
    public void whenFindItemByIdShouldGetItem(){
        DBManager dbManager = new DBManager();
        Tracker tracker = new Tracker(dbManager);
        dbManager.connectDB();
        Item result = tracker.findById("1");
        tracker.showOneItem(result);
        assertNotNull(result);
        dbManager.disconnectDB();
    }

    @Test
    public void whenFindItemByNameShouldGetItem(){
        DBManager dbManager = new DBManager();
        Tracker tracker = new Tracker(dbManager);
        dbManager.connectDB();
        List<Item> result = tracker.findByName("item1");
        tracker.showOneItem(result.get(0));
        assertNotNull(result);
        dbManager.disconnectDB();
    }

    @Test
    public void whenFindItemByDescShouldGetItem(){
        DBManager dbManager = new DBManager();
        Tracker tracker = new Tracker(dbManager);
        dbManager.connectDB();
        List<Item> result = tracker.findByDescription("blabla");
        tracker.showOneItem(result.get(0));
        assertNotNull(result);
        dbManager.disconnectDB();
    }

    @Test
    public void whenAddCommentShouldGetIt(){
        DBManager dbManager = new DBManager();
        Tracker tracker = new Tracker(dbManager);
        dbManager.connectDB();
        int result = tracker.addCommentByName("item1", new Comment("privet"));
        tracker.showComments("1");
        assertTrue(result > 0);
        dbManager.disconnectDB();
    }

    @Test
    public void whenShowCommentShouldGetIt(){
        DBManager dbManager = new DBManager();
        Tracker tracker = new Tracker(dbManager);
        dbManager.connectDB();
        boolean result = tracker.showComments("1");
        assertTrue(result);
        dbManager.disconnectDB();
    }
}