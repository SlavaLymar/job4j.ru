package ru.yalymar.mapping.model.dao;

import org.junit.Before;
import org.junit.Test;
import ru.yalymar.mapping.model.models.*;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import static junit.framework.Assert.assertNotNull;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class AdDAOTest {

    private DAOFactory mf;

    @Before
    public void init(){
        this.mf = new DAOFactory();
    }

    @Test
    public void whenReadAdShouldGetIt(){
        Ad ad = new Ad();
        ad.setTittle("test2");
        ad.setDone(false);
        ad.setCar(new Car(new Model(6), new Transmission(2), new Body(3), new Color(3)));
        ad.setUser(new User(26));
        ad.setCreate(new Timestamp(System.currentTimeMillis()));
        ad.setImages(new HashSet<Image>(){{
            add(new Image("urltest21"));
            add(new Image("urltest22"));
        }});
        int id = this.mf.getAdDAO().create(ad);

        Ad ad1 = this.mf.getAdDAO().read(id);
        assertNotNull(ad1);

        this.mf.getAdDAO().delete(id);
    }

    @Test
    public void whenReadAllAdsShouldGetThem(){
        Ad ad = new Ad();
        ad.setTittle("test2");
        ad.setDone(false);
        ad.setCar(new Car(new Model(6), new Transmission(2), new Body(3), new Color(3)));
        ad.setUser(new User(26));
        ad.setCreate(new Timestamp(System.currentTimeMillis()));
        ad.setImages(new HashSet<Image>(){{
            add(new Image("urltest21"));
            add(new Image("urltest22"));
        }});
        int id = this.mf.getAdDAO().create(ad);

        List<Ad> ads = this.mf.getAdDAO().readAll();
        assertTrue(ads.size() > 0);

        this.mf.getAdDAO().delete(id);
    }

    @Test
    public void whenCreateAdsShouldGetId(){
        Ad ad = new Ad();
        ad.setTittle("test2");
        ad.setDone(false);
        ad.setCar(new Car(new Model(6), new Transmission(2), new Body(3), new Color(3)));
        ad.setUser(new User(26));
        ad.setCreate(new Timestamp(System.currentTimeMillis()));
        ad.setImages(new HashSet<Image>(){{
            add(new Image("urltest21"));
            add(new Image("urltest22"));
        }});
        int id = this.mf.getAdDAO().create(ad);
        assertTrue(id > 0);

        this.mf.getAdDAO().delete(id);
    }

    @Test
    public void whenDeleteAdsShouldGetInt(){
        Ad ad = new Ad();
        ad.setTittle("test2");
        ad.setDone(false);
        ad.setCar(new Car(new Model(6), new Transmission(2), new Body(3), new Color(3)));
        ad.setUser(new User(26));
        ad.setCreate(new Timestamp(System.currentTimeMillis()));
        ad.setImages(new HashSet<Image>(){{
            add(new Image("urltest21"));
            add(new Image("urltest22"));
        }});
        int id = this.mf.getAdDAO().create(ad);

        int i = this.mf.getAdDAO().delete(id);
        assertThat(i, is(1));
    }

    @Test
    public void whenUpdateAdsShouldGetInt(){
        Ad ad = new Ad();
        ad.setTittle("test2");
        ad.setDone(false);
        ad.setCar(new Car(new Model(6), new Transmission(2), new Body(3), new Color(3)));
        ad.setUser(new User(26));
        ad.setCreate(new Timestamp(System.currentTimeMillis()));
        ad.setImages(new HashSet<Image>(){{
            add(new Image("urltest21"));
            add(new Image("urltest22"));
        }});
        //add
        int id = this.mf.getAdDAO().create(ad);

        //update
        Ad newAd = new Ad();
        newAd.setTittle("tittle");
        int i = this.mf.getAdDAO().update(id, newAd);
        assertThat(i, is(1));

        //delete
        this.mf.getAdDAO().delete(id);
    }
}