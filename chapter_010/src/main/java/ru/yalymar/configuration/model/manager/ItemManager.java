package ru.yalymar.configuration.model.manager;

import org.hibernate.Session;
import ru.yalymar.configuration.model.Item;
import java.util.List;

public class ItemManager extends Manager<Item>{

    public int create(Item item) {
        return super.create.daoCreate(item);
    }

    @Override
    public Item read(int id) {
        Session session = null;
        try {
            session = super.sessionFactory.openSession();
            return session.get(Item.class, id);
        }
        finally {
            if(session != null && session.isOpen()){
                session.close();
            }
        }
    }

    @Override
    public List<Item> readAll() {
        Session session = null;
        try{
            session = super.sessionFactory.openSession();
            return session.createQuery("from Item").list();
        }
        finally {
            if(session != null && session.isOpen()){
                session.close();
            }
        }
    }

    public int update(int id, Item newItem) {
        int i = 0;
        Item item = this.read(id);
        if (item != null) {
            if (newItem.getDescription() != null) {
                item.setDescription(newItem.getDescription());
                i++;
            }
            super.update.daoUpdate(item);
        }
        return i;
    }

    public int delete(int id) {
        String query = String.format("delete Item where id = %d", id);
        int i = super.delete.daoDelete(query);
        return i;
    }


}
