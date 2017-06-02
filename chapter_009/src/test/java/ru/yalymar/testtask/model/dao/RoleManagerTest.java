package ru.yalymar.testtask.model.dao;

import org.junit.Before;

public class RoleManagerTest {

    private DAOFabric daoFabric;
    private AddressManager manager;

    @Before
    public void init(){
        this.daoFabric = new DAOFabric();
        this.manager = this.daoFabric.getAddressManager();
    }

    
}