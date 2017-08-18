package ru.yalymar.mvc.model.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.yalymar.mvc.model.models.*;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-mvc-context-test.xml")
public class CarDAOTest {

    @Autowired
    private DAOFactory daoFactory;

    public Car createCar(){

        //create
        Car car = new Car();
        car.setModel(this.getModel());
        car.setTransmission(this.getTransmission());
        car.setBody(this.getBody());
        car.setColor(this.getColor());
        int id = daoFactory.getCarDAO().create(car);
        System.out.println();
        return car;
    }

    private Color getColor() {
        Color color = new Color();
        color.setColor("red");
        int id = daoFactory.getColorDAO().create(color);
        return color;
    }

    private Body getBody() {
        Body body = new Body();
        body.setBody("sedan");
        int id = daoFactory.getBodyDAO().create(body);
        return body;
    }

    private Transmission getTransmission() {
        Transmission transmission = new Transmission();
        transmission.setName("manual");
        int id = daoFactory.getTransmissionsDAO().create(transmission);
        return transmission;
    }

    private Model getModel() {
        Model model = new Model();
        model.setModel("test1");
        model.setModel("test1");
        model.setManuf(this.getManuf());
        int id = daoFactory.getModelDAO().create(model);
        return model;
    }

    private Manufactor getManuf() {
        Manufactor manuf = new Manufactor();
        manuf.setManuf("toyota");
        int id = daoFactory.getManufactorDAO().create(manuf);
        return manuf;
    }


    @Test
    public void whenCreateCarShouldGetId(){
        Car car = this.createCar();
        assertTrue(car.getId() > 0);
    }

    @Test
    public void whenReadCarShouldGetIt(){
        Car car = this.createCar();
        Car c = daoFactory.getCarDAO().read(car.getId());
        assertNotNull(c);
    }

    @Test
    public void whenReadAllCarsShouldGetThem() {
        this.createCar();
        List<Car> cs = daoFactory.getCarDAO().readAll();
        assertTrue(cs.size() > 0);
    }

    @Test
    public void whenDeleteCarShouldGetInt(){
        Car car = this.createCar();
        int i = daoFactory.getCarDAO().delete(car.getId());
        assertThat(i, is(1));
    }

    @Test
    public void whenUpdateCarShouldGetInt(){
        Car car = this.createCar();

        //update
        Car newCar = new Car();
        newCar.setBody(new Body(1));
        int i = daoFactory.getCarDAO().update(car.getId(), newCar);
        assertThat(i, is(1));
    }
}