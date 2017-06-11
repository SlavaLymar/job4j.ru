package ru.yalymar.configuration.model.manager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import ru.yalymar.configuration.model.crudrepo.*;

public abstract class Manager<E> implements Read<E>, ReadAll<E>{

    protected SessionFactory sessionFactory;
    protected Create<E> create;
    protected Update<E> update;
    protected Delete delete;

    public Manager() {
        this.sessionFactory = new Configuration().configure().buildSessionFactory();
        this.initCreate();
        this.initUpdate();
        this.initDelete();
    }

    private void initDelete() {
        this.delete = (q) -> {
            Session session = null;
            int i;
            try {
                session = this.sessionFactory.openSession();
                session.beginTransaction();
                Query query = session.createQuery(q);
                i = query.executeUpdate();
                session.getTransaction().commit();
                return i;
            }
            finally {
                if(session != null && session.isOpen()){
                    session.close();
                }
            }
        };
    }

    private void initUpdate() {
        this.update = (e) -> {
            Session session = null;
            try {
                session = this.sessionFactory.openSession();
                session.beginTransaction();
                session.update(e);
                session.getTransaction().commit();
            } finally {
                if (session != null && session.isOpen()) {
                    session.close();
                }
            }
        };
    }

    private void initCreate() {
        this.create = (e) -> {
            Session session = null;
            try {
                session = this.sessionFactory.openSession();
                session.beginTransaction();
                int i = (Integer) session.save(e);
                session.getTransaction().commit();
                return i;
            }
            finally {
                if(session != null && session.isOpen()){
                    session.close();
                }
            }
        };
    }

    public void closeResources(){
        this.sessionFactory.close();
    }

}
