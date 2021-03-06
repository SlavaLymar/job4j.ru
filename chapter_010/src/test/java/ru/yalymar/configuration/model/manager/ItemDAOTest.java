package ru.yalymar.configuration.model.manager;

import org.junit.Before;
import org.junit.Test;
import ru.yalymar.configuration.model.Item;
import java.sql.Timestamp;
import java.util.List;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class ItemDAOTest {

    private ManagersFactory mf;

    @Before
    public void init(){
        this.mf = new ManagersFactory();
    }

    @Test
    public void whenGetItemShouldGetInt(){
        Item item = this.mf.getIm().read(1);
        assertNotNull(item);
    }

    @Test
    public void whenAddItemShouldGetID(){
        //add
        Item item = new Item();
        item.setDescription("test2");
        item.setCreated(new Timestamp(System.currentTimeMillis()));
        item.setDone(false);
        int id = this.mf.getIm().create(item);
        System.out.println(id);
        assertTrue(id > 0);

        //daoDelete
        this.mf.getIm().delete(id);
    }

    @Test
    public void whenGetAllItemsShouldGetOnes(){
        List<Item> items = this.mf.getIm().readAll();
        assertNotNull(items);
    }

    @Test
    public void whenUpdateItemShouldGetInt(){
        //add
        Item item = new Item();
        item.setDescription("test2");
        item.setCreated(new Timestamp(System.currentTimeMillis()));
        item.setDone(false);
        int id = this.mf.getIm().create(item);

        //daoUpdate
        Item newItem = new Item();
        newItem.setCreated(new Timestamp(System.currentTimeMillis()));
        newItem.setDescription("daoUpdate");
        int i = this.mf.getIm().update(id, newItem);
        assertTrue(i == 1);

        //daoDelete
        this.mf.getIm().delete(id);
    }

    @Test
    public void whenDeleteItemShouldGetNull(){
        //add
        Item item = new Item();
        item.setDescription("test2");
        item.setCreated(new Timestamp(System.currentTimeMillis()));
        item.setDone(false);
        int id = this.mf.getIm().create(item);

        //daoDelete
        this.mf.getIm().delete(id);

        assertNull(this.mf.getIm().read(id));
    }
}