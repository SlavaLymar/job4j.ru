package ru.yalymar.mapping.model.dao;

import org.hibernate.AssertionFailure;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import ru.yalymar.mapping.model.crudrepo.*;
import java.util.function.Function;

public abstract class DAO<E> implements Action<E> {

    protected SessionFactory sessionFactory;

    public DAO() {
        this.sessionFactory = new Configuration().configure().buildSessionFactory();

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
                try {
                    tx.commit();
                }
                catch (AssertionFailure e){
                    DAOFactory.logger.error(e.getMessage(), e);
                }
            }
        }
    }

}
