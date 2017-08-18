package ru.yalymar.mvc.model.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.yalymar.mvc.model.models.Manufactor;
import ru.yalymar.mvc.model.models.Model;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-mvc-context-test.xml")
public class ModelDAOTest {

    @Autowired
    private DAOFactory daoFactory;

    public Manufactor getManuf(){
        //create manufactor
        Manufactor manuf = new Manufactor();
        manuf.setManuf("toyota");
        daoFactory.getManufactorDAO().create(manuf);
        return manuf;
    }

    @Test
    public void whenCreateModelShouldGetId(){
        Manufactor manuf = this.getManuf();

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
        Manufactor manuf = this.getManuf();

        //create model
        Model model = new Model();
        model.setModel("test1");
        model.setManuf(manuf);
        int id = daoFactory.getModelDAO().create(model);

        Model result = daoFactory.getModelDAO().read(id);
        assertNotNull(result);
    }

    @Test
    public void whenReadAllModelsShouldGetThem(){
        Manufactor manuf = this.getManuf();

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
        Manufactor manuf = this.getManuf();

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
        Manufactor manuf = this.getManuf();

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