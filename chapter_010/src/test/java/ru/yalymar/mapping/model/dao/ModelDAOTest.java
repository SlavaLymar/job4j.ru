package ru.yalymar.mapping.model.dao;

import org.junit.Before;
import org.junit.Test;
import ru.yalymar.mapping.model.Manufactor;
import ru.yalymar.mapping.model.Model;
import java.util.List;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class ModelDAOTest {

    private DAOFactory mf;

    @Before
    public void init(){
        this.mf = new DAOFactory();
    }

    @Test
    public void whenReadModelShouldGetIt(){
        Model model = this.mf.getModelDAO().daoRead(1);
        assertNotNull(model);
    }

    @Test
    public void whenReadAllModelsShouldGetThem(){
        List<Model> models = this.mf.getModelDAO().daoReadAll();
        assertTrue(models.size() > 0);
    }

    @Test
    public void whenCreateModelShouldGetId(){
        Model model = new Model();
        model.setModel("test1");
        model.setManuf(new Manufactor(3));
        int id = this.mf.getModelDAO().create(model);
        assertTrue(id > 0);

        this.mf.getModelDAO().delete(id);
    }

    @Test
    public void whenDeleteModelShouldGetInt(){
        Model model = new Model();
        model.setModel("test1");
        model.setManuf(new Manufactor(3));
        int id = this.mf.getModelDAO().create(model);

        int i = this.mf.getModelDAO().delete(id);
        assertThat(i, is(1));
    }

    @Test
    public void whenUpdateModelShouldGetInt(){
        Model model = new Model();
        model.setModel("test1");
        model.setManuf(new Manufactor(3));
        //add
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