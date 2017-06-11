package ru.yalymar.mapping.model.dao;

import org.junit.Before;
import org.junit.Test;
import ru.yalymar.mapping.model.Car;

public class CarDAOTest {

    private DAOFactory mf;

    @Before
    public void init(){
        this.mf = new DAOFactory();
    }

    @Test
    public void whenCreateCarShouldGetIt(){
        Car car = new Car();
//        car.setModel();
//        int id = this.mf.getCarDAO().daoCreate();
    }
}