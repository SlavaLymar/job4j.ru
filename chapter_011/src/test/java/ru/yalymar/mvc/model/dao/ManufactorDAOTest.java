package ru.yalymar.mvc.model.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.yalymar.mvc.model.models.Manufactor;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-mvc-context-test.xml")
public class ManufactorDAOTest {

    @Autowired
    private DAOFactory mf;

    @Test
    public void whenCreateManufShouldGetId(){
        //add
        Manufactor manuf = new Manufactor();
        manuf.setManuf("toyota");
        int id = mf.getManufactorDAO().create(manuf);
        assertTrue(id > 0);

        mf.getManufactorDAO().delete(id);
    }

    @Test
    public void whenDeleteManufShouldGetInt(){

        //add
        Manufactor manuf = new Manufactor();
        manuf.setManuf("toyota");
        int id = mf.getManufactorDAO().create(manuf);

        int i = mf.getManufactorDAO().delete(id);
        assertThat(i, is(1));
    }

    @Test
    public void whenUpdateManufShouldGetInt(){
        Manufactor manuf = new Manufactor();
        manuf.setManuf("toyota");
        //add
        int id = mf.getManufactorDAO().create(manuf);

        //daoUpdate
        Manufactor newM = new Manufactor();
        newM.setManuf("test1");
        int i = mf.getManufactorDAO().update(id, newM);
        assertThat(i, is(1));

        //daoDelete
        mf.getManufactorDAO().delete(id);
    }

    @Test
    public void whenReadManufShouldGetIt(){

        //add
        Manufactor manuf = new Manufactor();
        manuf.setManuf("toyota");
        int id = mf.getManufactorDAO().create(manuf);

        Manufactor m = mf.getManufactorDAO().read(id);
        assertNotNull(m);
    }

    @Test
    public void whenReadAllManufShouldGetThem(){

        //add
        Manufactor manuf = new Manufactor();
        manuf.setManuf("toyota");
        int id = mf.getManufactorDAO().create(manuf);

        List<Manufactor> ms = mf.getManufactorDAO().readAll();
        assertTrue(ms.size() > 0);
    }
}