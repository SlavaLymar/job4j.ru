package ru.yalymar.testtask.model.dao;

import org.junit.Before;
import org.junit.Test;
import ru.yalymar.testtask.model.TypeOfMusic;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class TypeOfMusicManagerTest {

    private DAOFabric daoFabric;
    private TypeOfMusicManager manager;

    @Before
    public void init(){
        this.daoFabric = new DAOFabric();
        this.manager = this.daoFabric.getTypeOfMusicManager();
    }

    @Test
    public void whenAddTypeOfMusicShouldGetId(){
        TypeOfMusic type = new TypeOfMusic("electro-pop");
        int id = this.manager.create(type);
        assertTrue(id > 0);

        this.manager.remove(id);
    }

    @Test
    public void whenRemoveTypeShouldGetInt(){
        TypeOfMusic type = new TypeOfMusic("electro-pop");
        int id = this.manager.create(type);
        int i = this.manager.remove(id);
        assertTrue(i > 0);
    }

    @Test
    public void whenGetAddressByIdShouldGetIt(){
        TypeOfMusic type = this.manager.getById(1);
        assertNotNull(type);
    }

    @Test
    public void whenGetAllAddressesShouldGetIt(){
        List<TypeOfMusic> types = this.manager.getAll();
        assertTrue(types.size() > 0);
    }

    @Test
    public void whenEditAddressShouldGetNewOne(){
        TypeOfMusic type = new TypeOfMusic("electro-pop");
        TypeOfMusic newType = new TypeOfMusic("bober-rock");
        int id = this.manager.create(type);

        int i = this.manager.edit(id, newType);
        assertTrue(i > 0);

        this.manager.remove(id);
    }
}