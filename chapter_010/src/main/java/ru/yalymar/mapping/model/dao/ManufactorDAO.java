package ru.yalymar.mapping.model.dao;

import org.hibernate.Session;
import org.hibernate.query.Query;
import ru.yalymar.mapping.model.Manufactor;
import java.util.List;

public class ManufactorDAO extends DAO<Manufactor> {

    @Override
    public int create(Manufactor m) {
        Session session = null;
        try {
            session = super.sessionFactory.openSession();
            session.beginTransaction();
            int i = (Integer) session.save(m);
            int id = -1;
            if(i > 0){
                id = m.getId();
            }
            session.getTransaction().commit();
            return id;
        }
        finally {
            if(session != null && session.isOpen()){
                session.close();
            }
        }
    }

    @Override
    public Manufactor read(int id) {
        Session session = null;
        try {
            session = super.sessionFactory.openSession();
            return session.get(Manufactor.class, id);
        }
        finally {
            if(session != null && session.isOpen()){
                session.close();
            }
        }
    }

    @Override
    public List<Manufactor> readAll() {
        Session session = null;
        try{
            session = super.sessionFactory.openSession();
            return session.createQuery("from Manufactor").list();
        }
        finally {
            if(session != null && session.isOpen()){
                session.close();
            }
        }
    }

    @Override
    public int update(int id, Manufactor newM) {
        return -1;
    }

    @Override
    public int delete(int id) {
        Session session = null;
        int i;
        try {
            session = super.sessionFactory.openSession();
            session.beginTransaction();
            Query query = session.createQuery(
                    "delete Manufactor where id = :i");
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
