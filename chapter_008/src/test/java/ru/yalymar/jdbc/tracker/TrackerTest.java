package ru.yalymar.jdbc.tracker;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import ru.yalymar.jdbc.model.Comment;
import ru.yalymar.jdbc.model.DBManager;
import ru.yalymar.jdbc.model.Item;

import java.util.List;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TrackerTest {

    private DBManager dbManager;

    @Before
    public void before() {
        this.dbManager = new DBManager();
        dbManager.connectDB();
        this.dbManager.createItemsTable();
        this.dbManager.createCommentsTable();
    }

    @After
    public void after() {
        this.dbManager.dropCommentsTable();
        this.dbManager.dropItemsTable();
        dbManager.disconnectDB();
    }

    @Test
    public void whenAddItemShouldGetInt(){
        Tracker tracker = new Tracker(dbManager);
        int result = tracker.add(new Item("item1", "blabla"));
        assertTrue(result > 0);
    }

    @Test
    public void whenUpdateItemShouldGetInt(){
        Tracker tracker = new Tracker(dbManager);
        tracker.add(new Item("item1", "blabla"));
        int result = tracker.update(new Item("item1", "b", "1"));
        assertTrue(result > 0);
    }

    @Test
    public void whenShowAllItemsItemShouldGetTrue(){
        Tracker tracker = new Tracker(dbManager);
        boolean result = tracker.showAllItems();
        assertTrue(result);
    }

    @Test
    public void whenFindItemByIdShouldGetItem(){
        Tracker tracker = new Tracker(dbManager);
        tracker.add(new Item("item1", "blabla", "1"));
        Item result = tracker.findById("1");
        tracker.showOneItem(result);
        assertNotNull(result);
    }

    @Test
    public void whenFindItemByNameShouldGetItem(){
        Tracker tracker = new Tracker(dbManager);
        tracker.add(new Item("item1", "blabla"));
        List<Item> result = tracker.findByName("item1");
        tracker.showOneItem(result.get(0));
        assertNotNull(result);
    }

    @Test
    public void whenFindItemByDescShouldGetItem(){
        Tracker tracker = new Tracker(dbManager);
        tracker.add(new Item("item1", "blabla"));
        List<Item> result = tracker.findByDescription("blabla");
        tracker.showOneItem(result.get(0));
        assertNotNull(result);
    }

    @Test
    public void whenAddCommentShouldGetIt(){
        Tracker tracker = new Tracker(dbManager);
        tracker.add(new Item("item1", "blabla"));
        int result = tracker.addCommentByName("item1", new Comment("privet"));
        tracker.showComments("1");
        assertTrue(result > 0);
    }

    @Test
    public void whenShowCommentShouldGetIt(){
        Tracker tracker = new Tracker(dbManager);
        tracker.add(new Item("item1", "blabla"));
        tracker.addCommentByName("item1", new Comment("privet"));
        boolean result = tracker.showComments("1");
        assertTrue(result);
    }
}