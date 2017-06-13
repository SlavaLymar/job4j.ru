package ru.yalymar.configuration.model.manager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import ru.yalymar.configuration.model.crudrepo.*;
import java.util.function.Function;

/**
 * @author slavalymar
 * @since 13.06.2017
 * @version 1
 */
public abstract class Manager<E> implements Action<E> {

    protected SessionFactory sessionFactory;

    public Manager() {
        this.sessionFactory = new Configuration().configure().buildSessionFactory();
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

    public void closeResources(){
        this.sessionFactory.close();
    }

}
