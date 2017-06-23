package ru.yalymar.mapping.model.dao;

import org.junit.Test;
import ru.yalymar.mapping.model.models.*;
import java.util.List;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class CarDAOTest {

    public Car createCar(DAOFactory daoFactory){

        //create
        Car car = new Car();
        car.setModel(this.getModel(daoFactory));
        car.setTransmission(this.getTransmission(daoFactory));
        car.setBody(this.getBody(daoFactory));
        car.setColor(this.getColor(daoFactory));
        int id = daoFactory.getCarDAO().create(car);
        System.out.println();
        return car;
    }

    private Color getColor(DAOFactory daoFactory) {
        Color color = new Color();
        color.setColor("red");
        int id = daoFactory.getColorDAO().create(color);
        return color;
    }

    private Body getBody(DAOFactory daoFactory) {
        Body body = new Body();
        body.setBody("sedan");
        int id = daoFactory.getBodyDAO().create(body);
        return body;
    }

    private Transmission getTransmission(DAOFactory daoFactory) {
        Transmission transmission = new Transmission();
        transmission.setName("manual");
        int id = daoFactory.getTransmissionsDAO().create(transmission);
        return transmission;
    }

    private Model getModel(DAOFactory daoFactory) {
        Model model = new Model();
        model.setModel("test1");
        model.setModel("test1");
        model.setManuf(this.getManuf(daoFactory));
        int id = daoFactory.getModelDAO().create(model);
        return model;
    }

    private Manufactor getManuf(DAOFactory daoFactory) {
        Manufactor manuf = new Manufactor();
        manuf.setManuf("toyota");
        int id = daoFactory.getManufactorDAO().create(manuf);
        return manuf;
    }


    @Test
    public void whenCreateCarShouldGetId(){
        DAOFactory daoFactory = new DAOFactory();
        Car car = this.createCar(daoFactory);
        assertTrue(car.getId() > 0);
    }

    @Test
    public void whenReadCarShouldGetIt(){
        DAOFactory daoFactory = new DAOFactory();
        Car car = this.createCar(daoFactory);
        Car c = daoFactory.getCarDAO().read(car.getId());
        assertNotNull(c);
    }

    @Test
    public void whenReadAllCarsShouldGetThem() {
        DAOFactory daoFactory = new DAOFactory();
        this.createCar(daoFactory);
        List<Car> cs = daoFactory.getCarDAO().readAll();
        assertTrue(cs.size() > 0);
    }

    @Test
    public void whenDeleteCarShouldGetInt(){
        DAOFactory daoFactory = new DAOFactory();
        Car car = this.createCar(daoFactory);
        int i = daoFactory.getCarDAO().delete(car.getId());
        assertThat(i, is(1));
    }

    @Test
    public void whenUpdateCarShouldGetInt(){
        DAOFactory daoFactory = new DAOFactory();
        Car car = this.createCar(daoFactory);

        //update
        Car newCar = new Car();
        newCar.setBody(new Body(1));
        int i = daoFactory.getCarDAO().update(car.getId(), newCar);
        assertThat(i, is(1));
    }
}