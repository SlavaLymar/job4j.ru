package ru.yalymar.mapping.model.dao;

import org.junit.Before;
import org.junit.Test;
import ru.yalymar.mapping.model.*;
import java.util.List;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class CarDAOTest {

    private DAOFactory mf;

    @Before
    public void init(){
        this.mf = new DAOFactory();
    }

    @Test
    public void whenReadCarShouldGetIt(){
        Car car = this.mf.getCarDAO().daoRead(1);
        assertNotNull(car);
    }

    @Test
    public void whenReadAllCarsShouldGetThem(){
        List<Car> cars = this.mf.getCarDAO().daoReadAll();
        assertTrue(cars.size() > 0);
    }

    @Test
    public void whenCreateCarShouldGetId(){
        Car car = new Car();
        car.setModel(new Model(5));
        car.setTransmission(new Transmission(1));
        car.setBody(new Body(2));
        car.setColor(new Color(3));
        int id = this.mf.getCarDAO().create(car);
        assertTrue(id > 0);

        this.mf.getCarDAO().delete(id);
    }

    @Test
    public void whenDeleteCarShouldGetInt(){
        Car car = new Car();
        car.setModel(new Model(5));
        car.setTransmission(new Transmission(1));
        car.setBody(new Body(2));
        car.setColor(new Color(3));
        int id = this.mf.getCarDAO().create(car);

        int i = this.mf.getCarDAO().delete(id);
        assertThat(i, is(1));
    }

    @Test
    public void whenUpdateCarShouldGetInt(){
        Car car = new Car();
        car.setModel(new Model(5));
        car.setTransmission(new Transmission(1));
        car.setBody(new Body(2));
        car.setColor(new Color(3));
        //add
        int id = this.mf.getCarDAO().create(car);

        //update
        Car newCar = new Car();
        newCar.setBody(new Body(1));
        int i = this.mf.getCarDAO().update(id, newCar);
        assertThat(i, is(1));

        //delete
        this.mf.getCarDAO().delete(id);
    }
}