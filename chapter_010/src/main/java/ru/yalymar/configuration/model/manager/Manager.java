package ru.yalymar.configuration.model.manager;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.yalymar.configuration.model.crudrepo.*;

public abstract class Manager<E> implements Create<E>, Read<E>, ReadAll<E>, Update<E>, Delete{

    protected SessionFactory sessionFactory;

    public Manager() {
        this.sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    public void closeResources(){
        this.sessionFactory.close();
    }
}
