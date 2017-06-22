package ru.yalymar.mapping.model.dao;

import org.junit.Before;
import org.junit.Test;
import ru.yalymar.mapping.model.models.*;
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

    private DAOFactory mf;
    private Ad ad;
    private Model model;
    private Manufactor manuf;
    private Body body;
    private Transmission transmission;
    private Color color;

    public int createAd(){


        //create car
        Car car = new Car();
        car.setModel(this.model);
        car.setTransmission(this.transmission);
        car.setBody(this.body);
        car.setColor(this.color);
        car.setId(this.mf.getCarDAO().create(car));

        //create user
        Role role = new Role();
        role.setRole("test1");
        role.setId(this.mf.getRoleDAO().create(role));

        User user = new User();
        user.setLogin("test1");
        user.setPassword("test1");
        user.setName("test1");
        user.setCreate(new Timestamp(System.currentTimeMillis()));
        user.setRole(role);
        user.setId(this.mf.getUserDAO().create(user));

        //create ad
        Ad ad = new Ad();
        ad.setTittle("test2");
        ad.setDone(false);
        ad.setCar(car);
        ad.setUser(user);
        ad.setCreate(new Timestamp(System.currentTimeMillis()));
        ad.setPrice(10);

        int id = this.mf.getAdDAO().create(ad);
        ad.setId(id);
        ad.setImages(new HashSet<Image>(){{
            add(new Image("urltest21", ad));
            add(new Image("urltest22", ad));
        }});
        this.ad = ad;
        return id;
    }

    private Color getColor() {
        Color color = new Color();
        color.setColor("red");
        int id =  this.mf.getColorDAO().create(color);
        color.setId(id);
        return color;
    }

    private Body getBody() {
        Body body = new Body();
        body.setBody("sedan");
        int id = this.mf.getBodyDAO().create(body);
        body.setId(id);
        return body;
    }

    private Transmission getTransmission() {
        Transmission transmission = new Transmission();
        transmission.setName("manual");
        int id = this.mf.getTransmissionsDAO().create(transmission);
        transmission.setId(id);
        return transmission;
    }

    private Model getModel() {
        Model model = new Model();
        model.setModel("test1");
        model.setManuf(this.manuf);
        int id = this.mf.getModelDAO().create(model);
        model.setId(id);
        return model;
    }

    private Manufactor getManuf() {
        Manufactor manuf = new Manufactor();
        manuf.setManuf("toyota");
        int id = this.mf.getManufactorDAO().create(manuf);
        manuf.setId(id);
        return manuf;
    }

    @Before
    public void init(){
        this.mf = new DAOFactory();
        this.manuf = this.getManuf();
        this.model = this.getModel();
        this.body = this.getBody();
        this.color = this.getColor();
        this.transmission = this.getTransmission();
        this.createAd();
    }

    @Test
    public void whenReadAdShouldGetIt(){
        Ad ad1 = this.mf.getAdDAO().read(this.ad.getId());
        assertNotNull(ad1);
    }

    @Test
    public void whenReadAllAdsShouldGetThem(){
        List<Ad> ads = this.mf.getAdDAO().readAll();
        assertTrue(ads.size() > 0);
    }

    @Test
    public void whenCreateAdsShouldGetId(){
        assertTrue(this.ad.getId() > 0);
    }

    @Test
    public void whenDeleteAdsShouldGetInt(){
        int i = this.mf.getAdDAO().delete(this.ad.getId());
        assertThat(i, is(1));
    }

    @Test
    public void whenUpdateAdsShouldGetInt(){
        //update
        Ad newAd = new Ad();
        newAd.setTittle("tittle");
        int i = this.mf.getAdDAO().update(this.ad.getId(), newAd);
        assertThat(i, is(1));
    }

    @Test
    public void whenGetAdByManufShouldGetIt(){
        Map<String, String> m = new HashMap<>();
        m.put("manuf", "toyota");
        m.put("from", "");
        m.put("to", "");
        List<Ad> ads = this.mf.getAdDAO().getAdByFilters(m);
        assertTrue(ads.size() > 0);
    }
}