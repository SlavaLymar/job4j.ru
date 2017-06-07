package ru.yalymar.configuration.model.manager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import ru.yalymar.configuration.model.Item;

import java.util.List;

public class ItemManager extends Manager<Item>{

    @Override
    public int create(Item item) {
        Session session = null;
        try {
            session = super.sessionFactory.openSession();
            session.beginTransaction();
            int i = (Integer) session.save(item);
            session.getTransaction().commit();
            return i;
        }
        finally {
            if(session != null && session.isOpen()){
                session.close();
            }
        }
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

    @Override
    public int update(int id, Item newItem) {
        Session session = null;
        int i = -1;
        try{
            session = super.sessionFactory.openSession();
            Item item = this.read(id);
            if(item != null) {
                session.beginTransaction();
                if(!item.getDescription().equals(newItem.getDescription())){
                    i = this.updateDescription(id, newItem.getDescription(), session);
                }
                session.getTransaction().commit();
            }
            return i;
        }
        finally {
            if(session != null && session.isOpen()){
                session.close();
            }
        }
    }

    private int updateDescription(int id, String description, Session session) {
        Query query = session.createQuery(
                "update Item set description = :d where id = :i");
        query.setParameter("d", description);
        query.setParameter("i", id);
        return query.executeUpdate();
    }

    @Override
    public int delete(int id) {
        Session session = null;
        int i = -1;
        try {
            session = super.sessionFactory.openSession();
            session.beginTransaction();
            Query query = session.createQuery(
                    "delete Item where id = :i");
            query.setParameter("i", id);
            i = query.executeUpdate();
            session.getTransaction().commit();
            return i;
        }
        finally {
            if(session != null && session.isOpen()){
                session.close();
            }
        }
    }


}
