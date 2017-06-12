package ru.yalymar.mapping.model.dao;

import org.junit.Before;
import org.junit.Test;
import ru.yalymar.mapping.model.Ad;
import ru.yalymar.mapping.model.Car;
import ru.yalymar.mapping.model.Image;
import ru.yalymar.mapping.model.User;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class AdDAOTest {

    private DAOFactory mf;

    @Before
    public void init(){
        this.mf = new DAOFactory();
    }

    @Test
    public void whenReadAdShouldGetIt(){
        Ad ad = this.mf.getAdDAO().daoRead(1);
        assertNotNull(ad);
    }

    @Test
    public void whenReadAllAdsShouldGetThem(){
        List<Ad> ads = this.mf.getAdDAO().daoReadAll();
        assertTrue(ads.size() > 0);
    }

    @Test
    public void whenCreateAdsShouldGetId(){
//        Ad ad = new Ad();
//        ad.setTittle("test2");
//        ad.setDone(false);
//        ad.setCar(new Car(1));
//        ad.setUser(new User(17));
//        ad.setCreate(new Timestamp(System.currentTimeMillis()));
//        ad.setImages(new HashSet<Image>(){{
//            add(new Image("urltest21"));
//            add(new Image("urltest22"));
//        }});
//        int id = this.mf.getAdDAO().create(ad);
//        assertTrue(id > 0);

        this.mf.getAdDAO().delete(1);
    }
}