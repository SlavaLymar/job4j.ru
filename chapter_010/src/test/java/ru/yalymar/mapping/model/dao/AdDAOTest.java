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
    private int modelId;
    private int manufId;
    private int bodyId;
    private int transmissionId;
    private int colorId;

    public int createAd(){
        this.mf = new DAOFactory();

        //create car
        this.manufId = this.getManufId();
        this.modelId = this.getModelId();
        this.transmissionId = this.getTransmissionId();
        this.bodyId = this.getBodyId();
        this.colorId = this.getColorId();

        Car car = new Car();
        car.setModel(new Model(this.modelId));
        car.setTransmission(new Transmission(this.transmissionId));
        car.setBody(new Body(this.bodyId));
        car.setColor(new Color(this.colorId));
        int carId = this.mf.getCarDAO().create(car);

        //create user
        Role role = new Role();
        role.setRole("test1");
        int roleId = this.mf.getRoleDAO().create(role);

        User user = new User();
        user.setLogin("test1");
        user.setPassword("test1");
        user.setName("test1");
        user.setCreate(new Timestamp(System.currentTimeMillis()));
        user.setRole(new Role(roleId));
        int userId = this.mf.getUserDAO().create(user);

        //create ad
        Ad ad = new Ad();
        ad.setTittle("test2");
        ad.setDone(false);
        ad.setCar(new Car(carId));
        ad.setUser(new User(userId));
        ad.setCreate(new Timestamp(System.currentTimeMillis()));
        ad.setPrice(10);

        this.ad = ad;
        int id = this.mf.getAdDAO().create(ad);
        ad.setImages(new HashSet<Image>(){{
            add(new Image("urltest21", new Ad(id)));
            add(new Image("urltest22", new Ad(id)));
        }});
        return id;
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

    @Before
    public void init(){
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
        m.put("manuf", "vaz");
        m.put("from", "");
        m.put("to", "");
        List<Ad> ads = this.mf.getAdDAO().getAdByFilters(m);
        assertTrue(ads.size() > 0);
    }
}