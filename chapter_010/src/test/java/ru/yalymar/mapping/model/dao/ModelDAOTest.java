package ru.yalymar.mapping.model.dao;

import org.junit.Before;
import org.junit.Test;
import ru.yalymar.mapping.model.models.Manufactor;
import ru.yalymar.mapping.model.models.Model;
import java.util.List;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class ModelDAOTest {

    private DAOFactory mf;
    private int mid;

    @Before
    public void init(){
        this.mf = new DAOFactory();
        //create manufactor
        Manufactor manuf = new Manufactor();
        manuf.setManuf("toyota");
        this.mid = this.mf.getManufactorDAO().create(manuf);
    }

    @Test
    public void whenCreateModelShouldGetId(){
        //create model
        Model model = new Model();
        model.setModel("test1");
        model.setModel("test1");
        model.setManuf(new Manufactor(this.mid));
        int id = this.mf.getModelDAO().create(model);
        assertTrue(id > 0);

        this.mf.getModelDAO().delete(id);
    }

    @Test
    public void whenReadModelShouldGetIt(){
        //create model
        Model model = new Model();
        model.setModel("test1");
        model.setModel("test1");
        model.setManuf(new Manufactor(this.mid));
        int id = this.mf.getModelDAO().create(model);

        Model result = this.mf.getModelDAO().read(1);
        assertNotNull(result);
    }

    @Test
    public void whenReadAllModelsShouldGetThem(){
        //create model
        Model model = new Model();
        model.setModel("test1");
        model.setModel("test1");
        model.setManuf(new Manufactor(this.mid));
        int id = this.mf.getModelDAO().create(model);

        List<Model> models = this.mf.getModelDAO().readAll();
        assertTrue(models.size() > 0);
    }

    @Test
    public void whenDeleteModelShouldGetInt(){
        //create model
        Model model = new Model();
        model.setModel("test1");
        model.setModel("test1");
        model.setManuf(new Manufactor(this.mid));
        int id = this.mf.getModelDAO().create(model);

        int i = this.mf.getModelDAO().delete(id);
        assertThat(i, is(1));
    }

    @Test
    public void whenUpdateModelShouldGetInt(){
        //create model
        Model model = new Model();
        model.setModel("test1");
        model.setModel("test1");
        model.setManuf(new Manufactor(this.mid));
        int id = this.mf.getModelDAO().create(model);

        //update
        Model newModel = new Model();
        newModel.setModel("test2");
        int i = this.mf.getModelDAO().update(id, newModel);
        assertThat(i, is(1));

        //delete
        this.mf.getModelDAO().delete(id);
    }



}