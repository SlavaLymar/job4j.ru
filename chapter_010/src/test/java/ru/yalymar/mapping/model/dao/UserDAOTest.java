package ru.yalymar.mapping.model.dao;

import org.junit.Before;
import org.junit.Test;
import ru.yalymar.mapping.model.models.Role;
import ru.yalymar.mapping.model.models.User;
import java.sql.Timestamp;
import java.util.List;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class UserDAOTest {

    private DAOFactory mf;

    @Before
    public void init(){
        this.mf = new DAOFactory();
    }

    @Test
    public void whenReadUserShouldGetNotNull(){
        User user = this.mf.getUserDAO().read(1);
        assertNotNull(user);
    }

    @Test
    public void whenReadAllUsersShouldGetThem(){
        List<User> users = this.mf.getUserDAO().readAll();
        assertTrue(users.size() > 0);
    }

    @Test
    public void whenCreateUserShouldGetId(){
        User user = new User();
        user.setLogin("test1");
        user.setPassword("test1");
        user.setName("test1");
        user.setCreate(new Timestamp(System.currentTimeMillis()));
        user.setRole(new Role(2));
        int id = this.mf.getUserDAO().create(user);
        assertTrue(id > 0);

        this.mf.getUserDAO().delete(id);
    }

    @Test
    public void whenDeleteUserShouldGetInt(){
        User user = new User();
        user.setLogin("test1");
        user.setPassword("test1");
        user.setName("test1");
        user.setCreate(new Timestamp(System.currentTimeMillis()));
        user.setRole(new Role(2));
        int id = this.mf.getUserDAO().create(user);

        int i = this.mf.getUserDAO().delete(id);
        assertThat(i, is(1));
    }

    @Test
    public void whenUpdateUserShouldGetNewUser(){
        User user = new User();
        user.setLogin("test1");
        user.setPassword("test1");
        user.setName("test1");
        user.setCreate(new Timestamp(System.currentTimeMillis()));
        user.setRole(new Role(2));

        //add
        int id = this.mf.getUserDAO().create(user);

        //daoUpdate
        User newUser = new User();
        newUser.setName("test2");
        int i = this.mf.getUserDAO().update(id, newUser);
        assertThat(i, is(1));

        //daoDelete
        this.mf.getUserDAO().delete(id);
    }

    @Test
    public void whenGetUserByLoginPasswordShoildGetIt(){
        User user = new User();
        user.setLogin("test1");
        user.setPassword("test1");
        user.setName("test1");
        user.setCreate(new Timestamp(System.currentTimeMillis()));
        user.setRole(new Role(2));
        int id = this.mf.getUserDAO().create(user);

        User userLP = this.mf.getUserDAO().getByLoginPassword("test1", "test1");
        assertNotNull(userLP);

        this.mf.getUserDAO().delete(id);
    }

}