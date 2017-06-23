package ru.yalymar.mapping.model.dao;

import org.junit.Test;
import ru.yalymar.mapping.model.models.Manufactor;
import ru.yalymar.mapping.model.models.Model;
import java.util.List;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class ModelDAOTest {

    public Manufactor getManuf(DAOFactory daoFactory){
        //create manufactor
        Manufactor manuf = new Manufactor();
        manuf.setManuf("toyota");
        daoFactory.getManufactorDAO().create(manuf);
        return manuf;
    }

    @Test
    public void whenCreateModelShouldGetId(){
        DAOFactory daoFactory = new DAOFactory();
        Manufactor manuf = this.getManuf(daoFactory);

        //create model
        Model model = new Model();
        model.setModel("test1");
        model.setManuf(manuf);
        int id = daoFactory.getModelDAO().create(model);
        assertTrue(id > 0);

        daoFactory.getModelDAO().delete(id);
    }

    @Test
    public void whenReadModelShouldGetIt(){
        DAOFactory daoFactory = new DAOFactory();
        Manufactor manuf = this.getManuf(daoFactory);

        //create model
        Model model = new Model();
        model.setModel("test1");
        model.setManuf(manuf);
        int id = daoFactory.getModelDAO().create(model);

        Model result = daoFactory.getModelDAO().read(1);
        assertNotNull(result);
    }

    @Test
    public void whenReadAllModelsShouldGetThem(){
        DAOFactory daoFactory = new DAOFactory();
        Manufactor manuf = this.getManuf(daoFactory);

        //create model
        Model model = new Model();
        model.setModel("test1");
        model.setManuf(manuf);
        int id = daoFactory.getModelDAO().create(model);

        List<Model> models = daoFactory.getModelDAO().readAll();
        assertTrue(models.size() > 0);
    }

    @Test
    public void whenDeleteModelShouldGetInt(){
        DAOFactory daoFactory = new DAOFactory();
        Manufactor manuf = this.getManuf(daoFactory);

        //create model
        Model model = new Model();
        model.setModel("test1");
        model.setManuf(manuf);
        int id = daoFactory.getModelDAO().create(model);

        int i = daoFactory.getModelDAO().delete(id);
        assertThat(i, is(1));
    }

    @Test
    public void whenUpdateModelShouldGetInt(){
        DAOFactory daoFactory = new DAOFactory();
        Manufactor manuf = this.getManuf(daoFactory);

        //create model
        Model model = new Model();
        model.setModel("test1");
        model.setManuf(manuf);
        int id = daoFactory.getModelDAO().create(model);

        //update
        Model newModel = new Model();
        newModel.setModel("test2");
        int i = daoFactory.getModelDAO().update(id, newModel);
        assertThat(i, is(1));

        //delete
        daoFactory.getModelDAO().delete(id);
    }



}