package ru.yalymar.mapping.model.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import ru.yalymar.mapping.model.crudrepo.*;

public abstract class DAO<E> implements Read<E>, ReadAll<E>{

    protected SessionFactory sessionFactory;
    protected Create<E> create;
    protected Delete delete;
    protected Update<E> update;

    public DAO() {
        this.sessionFactory = new Configuration().configure().buildSessionFactory();
        this.initCreate();
        this.initDelete();
        this.initUpdate();
    }

    public void closeResources(){
        this.sessionFactory.close();
    }

    private void initUpdate() {
        this.update = (e) -> {
            Session session = null;
            try{
                session = this.sessionFactory.openSession();
                session.beginTransaction();
                session.update(e);
                session.getTransaction().commit();
            }
            finally {
                if(session != null && session.isOpen()){
                    session.close();
                }
            }
        };
    }

    private void initCreate(){
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

    private void initDelete(){
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

}
