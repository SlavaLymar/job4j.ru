package ru.yalymar.testtask.model.dao;

import ru.yalymar.testtask.model.db.DBManager;

/**
 * @author slavalymar
 * @version 1
 * @since 04.06.2017
 */
public abstract class Manager<E> implements DAO<E>{

    /**
     * database manager
     */
    protected DBManager dbManager;

    /**
     * object that consist of all dao objects
     */
    protected DAOFabric daoFabric;

    public Manager(DAOFabric daoFabric) {
        this.dbManager = new DBManager();
        this.daoFabric = daoFabric;
    }

}
