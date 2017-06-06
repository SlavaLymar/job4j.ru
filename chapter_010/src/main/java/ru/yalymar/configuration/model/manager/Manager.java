package ru.yalymar.configuration.model.manager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.yalymar.configuration.model.crudrepo.Create;
import ru.yalymar.configuration.model.crudrepo.Delete;
import ru.yalymar.configuration.model.crudrepo.Read;
import ru.yalymar.configuration.model.crudrepo.Update;

public abstract class Manager<E> implements Create<E>, Read<E>, Update, Delete{

    private SessionFactory sessionFactory;
    protected Session session;

    public Manager() {
        this.sessionFactory = new Configuration().configure().buildSessionFactory();
        this.session = sessionFactory.openSession();
    }
}
