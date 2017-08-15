package ru.yalymar.mvc.model.dao;

import org.junit.Test;
import ru.yalymar.mvc.model.models.*;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import static junit.framework.Assert.assertNotNull;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class AdDAOTest {

    public Ad createAd(DAOFactory daoFactory, String manuf){

        //create car
        Car car = new Car();
        car.setModel(this.getModel(daoFactory, manuf));
        car.setTransmission(this.getTransmission(daoFactory));
        car.setBody(this.getBody(daoFactory));
        car.setColor(this.getColor(daoFactory));
        daoFactory.getCarDAO().create(car);

        //create user
        Role role = new Role();
        role.setRole("test1");
        daoFactory.getRoleDAO().create(role);

        User user = new User();
        user.setLogin("test1");
        user.setPassword("test1");
        user.setName("test1");
        user.setCreate(new Timestamp(System.currentTimeMillis()));
        user.setRole(role);
        daoFactory.getUserDAO().create(user);

        //create ad
        Ad ad = new Ad();
        ad.setTittle("test2");
        ad.setDone(false);
        ad.setCar(car);
        ad.setUser(user);
        ad.setCreate(new Timestamp(System.currentTimeMillis()));
        ad.setPrice(10);

        int id = daoFactory.getAdDAO().create(ad);
        ad.setImages(new HashSet<Image>(){{
            add(new Image("urltest21", ad));
            add(new Image("urltest22", ad));
        }});
        return ad;
    }

    private Color getColor(DAOFactory daoFactory) {
        Color color = new Color();
        color.setColor("red");
        int id =  daoFactory.getColorDAO().create(color);
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

    private Model getModel(DAOFactory daoFactory, String manuf) {
        Model model = new Model();
        model.setModel("test1");
        model.setManuf(this.getManuf(daoFactory, manuf));
        int id = daoFactory.getModelDAO().create(model);
        return model;
    }

    private Manufactor getManuf(DAOFactory daoFactory, String m) {
        Manufactor manuf = new Manufactor();
        manuf.setManuf(m);
        int id = daoFactory.getManufactorDAO().create(manuf);
        return manuf;
    }

    @Test
    public void whenReadAdShouldGetIt(){
        DAOFactory daoFactory = new DAOFactory();
        Ad ad = this.createAd(daoFactory, "toyota");
        Ad ad1 = daoFactory.getAdDAO().read(ad.getId());
        assertNotNull(ad1);
    }

    @Test
    public void whenReadAllAdsShouldGetThem(){
        DAOFactory daoFactory = new DAOFactory();
        this.createAd(daoFactory, "toyota");
        List<Ad> ads = daoFactory.getAdDAO().readAll();
        assertTrue(ads.size() > 0);
    }

    @Test
    public void whenCreateAdsShouldGetId(){
        DAOFactory daoFactory = new DAOFactory();
        Ad ad = this.createAd(daoFactory, "toyota");
        assertTrue(ad.getId() > 0);
    }

    @Test
    public void whenDeleteAdsShouldGetInt(){
        DAOFactory daoFactory = new DAOFactory();
        Ad ad = this.createAd(daoFactory, "toyota");
        int i = daoFactory.getAdDAO().delete(ad.getId());
        assertThat(i, is(1));
    }

    @Test
    public void whenUpdateAdsShouldGetInt(){
        DAOFactory daoFactory = new DAOFactory();
        Ad ad = this.createAd(daoFactory, "toyota");

        //update
        Ad newAd = new Ad();
        newAd.setTittle("tittle");
        int i = daoFactory.getAdDAO().update(ad.getId(), newAd);
        assertThat(i, is(1));
    }

    @Test
    public void whenGetAdByManufShouldGetIt(){
        DAOFactory daoFactory = new DAOFactory();
        this.createAd(daoFactory, "toyota");

        Map<String, String> m = new HashMap<>();
        m.put("manuf", "toyota");
        m.put("from", "");
        m.put("to", "");
        List<Ad> ads = daoFactory.getAdDAO().getAdByFilters(m);
        assertTrue(ads.size() > 0);
    }

}