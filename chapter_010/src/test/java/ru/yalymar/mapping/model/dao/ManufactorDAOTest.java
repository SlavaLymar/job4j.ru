package ru.yalymar.mapping.model.dao;

import org.junit.Before;
import org.junit.Test;
import ru.yalymar.mapping.model.Manufactor;
import java.util.List;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class ManufactorDAOTest {

    private DAOFactory mf;

    @Before
    public void init(){
        this.mf = new DAOFactory();
    }

    @Test
    public void whenCreateManufShouldGetId(){
        Manufactor manuf = new Manufactor();
        manuf.setManuf("toyota");
        int id = this.mf.getManufactorDAO().create(manuf);
        assertTrue(id > 0);

        this.mf.getManufactorDAO().delete(id);
    }

    @Test
    public void whenDeleteManufShouldGetInt(){
        Manufactor manuf = new Manufactor();
        manuf.setManuf("toyota");
        int id = this.mf.getManufactorDAO().create(manuf);

        int i = this.mf.getManufactorDAO().delete(id);
        assertThat(i, is(1));
    }

    @Test
    public void whenUpdateManufShouldGetInt(){
        Manufactor manuf = new Manufactor();
        manuf.setManuf("toyota");
        //add
        int id = this.mf.getManufactorDAO().create(manuf);

        //daoUpdate
        Manufactor newM = new Manufactor();
        newM.setManuf("test1");
        int i = this.mf.getManufactorDAO().update(id, newM);
        assertThat(i, is(1));

        //daoDelete
        this.mf.getManufactorDAO().delete(id);
    }

    @Test
    public void whenReadManufShouldGetIt(){
        Manufactor m = this.mf.getManufactorDAO().daoRead(3);
        assertNotNull(m);
    }

    @Test
    public void whenReadAllManufShouldGetThem(){
        List<Manufactor> ms = this.mf.getManufactorDAO().daoReadAll();
        assertTrue(ms.size() > 0);
    }
}