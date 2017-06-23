package ru.yalymar.mapping.model.dao;

import org.junit.Test;
import ru.yalymar.mapping.model.models.Manufactor;
import java.util.List;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class ManufactorDAOTest {

    @Test
    public void whenCreateManufShouldGetId(){
        DAOFactory mf = new DAOFactory();
        //add
        Manufactor manuf = new Manufactor();
        manuf.setManuf("toyota");
        int id = mf.getManufactorDAO().create(manuf);
        assertTrue(id > 0);

        mf.getManufactorDAO().delete(id);
    }

    @Test
    public void whenDeleteManufShouldGetInt(){
        DAOFactory mf = new DAOFactory();

        //add
        Manufactor manuf = new Manufactor();
        manuf.setManuf("toyota");
        int id = mf.getManufactorDAO().create(manuf);

        int i = mf.getManufactorDAO().delete(id);
        assertThat(i, is(1));
    }

    @Test
    public void whenUpdateManufShouldGetInt(){
        DAOFactory mf = new DAOFactory();
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
        DAOFactory mf = new DAOFactory();

        //add
        Manufactor manuf = new Manufactor();
        manuf.setManuf("toyota");
        int id = mf.getManufactorDAO().create(manuf);

        Manufactor m = mf.getManufactorDAO().read(id);
        assertNotNull(m);
    }

    @Test
    public void whenReadAllManufShouldGetThem(){
        DAOFactory mf = new DAOFactory();

        //add
        Manufactor manuf = new Manufactor();
        manuf.setManuf("toyota");
        int id = mf.getManufactorDAO().create(manuf);

        List<Manufactor> ms = mf.getManufactorDAO().readAll();
        assertTrue(ms.size() > 0);
    }
}