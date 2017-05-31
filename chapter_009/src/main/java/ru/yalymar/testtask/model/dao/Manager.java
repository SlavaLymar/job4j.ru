package ru.yalymar.testtask.model.dao;

import ru.yalymar.testtask.model.db.DBManager;

public abstract class Manager<E> implements DAO<E>{

    protected DBManager dbManager;

    public Manager() {
        this.dbManager = new DBManager();
    }

}
