package ru.yalymar.mapping.model.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import ru.yalymar.mapping.model.crudrepo.*;
import java.util.function.Function;

public abstract class DAO<E> implements Read<E>, ReadAll<E>, Create<E>{

    protected SessionFactory sessionFactory;
    protected Create<E> create;
    protected Delete delete;
    protected Update<E> update;

    public DAO() {
        this.sessionFactory = new Configuration().configure().buildSessionFactory();

//        this.initCreate();
//        this.initDelete();
//        this.initUpdate();
    }

    public void closeResources(){
        this.sessionFactory.close();
    }

    @Override
    public <V, K> V tx(Function<Session, V> function) {
        try (Session session = this.sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            try {
                return function.apply(session);
            } finally {
                tx.commit();
            }
        }
    }

    /*
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
*/


}
