package ru.yalymar.testtask.model.dao;

import org.junit.Before;
import org.junit.Test;
import ru.yalymar.testtask.model.TypeOfMusic;
import ru.yalymar.testtask.model.User;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class UserManagerTest {

    private DAOFabric daoFabric;
    private UserManager manager;

    @Before
    public void init(){
        this.daoFabric = new DAOFabric();
        this.manager = this.daoFabric.getUserManager();
    }

    @Test
    public void whenAddUserShouldGetId(){
        User user = new User("test2", "test2", "test2",
                new Timestamp(Calendar.getInstance().getTimeInMillis()),
                "user", "Test2 st. ap test2");
        user.getTypes().add(new TypeOfMusic("rock"));
        user.getTypes().add(new TypeOfMusic("rap"));
        user.getTypes().add(new TypeOfMusic("folk"));

        int id = this.manager.create(user);
        assertTrue(id > 0);

        this.manager.remove(id);
    }

    @Test
    public void whenDeleteUserShouldGetId(){
        User user = new User("test2", "test2", "test2",
                new Timestamp(Calendar.getInstance().getTimeInMillis()),
                "user", "Test2 st. ap test2");
        user.getTypes().add(new TypeOfMusic("rock"));
        user.getTypes().add(new TypeOfMusic("rap"));
        user.getTypes().add(new TypeOfMusic("folk"));
        int id = this.manager.create(user);

        int i = this.manager.remove(id);
        assertTrue(i > 0);
    }

    @Test
    public void whenGetAllUsersShouldGetIt(){
        List<User> users = this.manager.getAll();
        assertTrue(users.size() > 0);
    }

    @Test
    public void whenGetAddressByIdShouldGetIt(){
        User user = this.manager.getById(1);
        assertNotNull(user);
    }

    @Test
    public void whenEditUserShouldGetNewOne(){
        User user = new User("test2", "test2", "test2",
                new Timestamp(Calendar.getInstance().getTimeInMillis()),
                "user", "Test2 st. ap test2");
        User newUser = new User("test1", "test1", "test1",
                null, "mandator", "Test1 st. ap test1");
        int id = this.manager.create(user);

        int i = this.manager.edit(id, newUser);
        assertTrue(i > 0);
        System.out.println(i);

        this.manager.remove(id);
    }
}