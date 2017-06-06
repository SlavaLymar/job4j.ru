package ru.yalymar.configuration.model.manager;

import ru.yalymar.configuration.model.Item;
import java.util.List;

public class ItemManager extends Manager<Item>{

    @Override
    public void create(Item item) {
        super.session.beginTransaction();
        super.session.save(item);
        super.session.getTransaction().commit();
        //TODO close session, factory
    }


    @Override
    public List<Item> read(int id) {
        super.session.beginTransaction();
        List<Item> items = super.session.createQuery("from Item").list();
        super.session.getTransaction().commit();
        return items;
        //TODO close session, factory
    }
}
