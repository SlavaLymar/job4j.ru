package ru.yalymar.mapping.model.dao;

import org.junit.Before;
import org.junit.Test;
import ru.yalymar.mapping.model.models.*;
import java.util.List;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class CarDAOTest {

    private DAOFactory mf;
    private int modelId;
    private int manufId;
    private int bodyId;
    private int transmissionId;
    private int colorId;
    private Car car;

    public int createCar(){
        this.mf = new DAOFactory();
        this.manufId = this.getManufId();
        this.modelId = this.getModelId();
        this.transmissionId = this.getTransmissionId();
        this.bodyId = this.getBodyId();
        this.colorId = this.getColorId();

        //create
        Car car = new Car();
        car.setModel(new Model(this.modelId));
        car.setTransmission(new Transmission(this.transmissionId));
        car.setBody(new Body(this.bodyId));
        car.setColor(new Color(this.colorId));
        this.car = car;
        return this.mf.getCarDAO().create(car);
    }

    @Before
    public void init(){
        this.createCar();
    }

    private int getColorId() {
        Color color = new Color();
        color.setColor("red");
        return this.mf.getColorDAO().create(color);
    }

    private int getBodyId() {
        Body body = new Body();
        body.setBody("sedan");
        return this.mf.getBodyDAO().create(body);
    }

    private int getTransmissionId() {
        Transmission transmission = new Transmission();
        transmission.setName("manual");
        return this.mf.getTransmissionsDAO().create(transmission);
    }

    private int getModelId() {
        Model model = new Model();
        model.setModel("test1");
        model.setModel("test1");
        model.setManuf(new Manufactor(this.manufId));
        return this.mf.getModelDAO().create(model);
    }

    private int getManufId() {
        Manufactor manuf = new Manufactor();
        manuf.setManuf("toyota");
        return this.mf.getManufactorDAO().create(manuf);
    }


    @Test
    public void whenCreateCarShouldGetId(){
        assertTrue(this.car.getId() > 0);
    }

    @Test
    public void whenReadCarShouldGetIt(){
        Car c = this.mf.getCarDAO().read(this.car.getId());
        assertNotNull(c);
    }

    @Test
    public void whenReadAllCarsShouldGetThem() {
        List<Car> cs = this.mf.getCarDAO().readAll();
        assertTrue(cs.size() > 0);
    }

    @Test
    public void whenDeleteCarShouldGetInt(){
        int i = this.mf.getCarDAO().delete(this.car.getId());
        assertThat(i, is(1));
    }

    @Test
    public void whenUpdateCarShouldGetInt(){
        //update
        Car newCar = new Car();
        newCar.setBody(new Body(1));
        int i = this.mf.getCarDAO().update(this.car.getId(), newCar);
        assertThat(i, is(1));
    }
}