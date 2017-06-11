package ru.yalymar.mapping.model.manager;

import org.junit.Before;
import org.junit.Test;
import ru.yalymar.mapping.model.Role;
import ru.yalymar.mapping.model.User;

import java.sql.Timestamp;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class UserManagerTest {

    private ManagersFactory mf;

    @Before
    public void init(){
        this.mf = new ManagersFactory();
    }

    @Test
    public void whenReadUserShouldGetNotNull(){
        User user = this.mf.getUm().read(1);
        assertNotNull(user);
    }

    @Test
    public void whenReadAllUsersShouldGetThem(){
        List<User> users = this.mf.getUm().readAll();
        assertTrue(users.size() > 0);
    }

    @Test
    public void whenCreateUserShouldGetThem(){
        User user = new User();
        user.setLogin("test1");
        user.setPassword("test1");
        user.setName("test1");
        user.setCreate(new Timestamp(System.currentTimeMillis()));
        user.setRole(new Role(2));
        int id = this.mf.getUm().create(user);
        assertTrue(id > 0);

        this.mf.getUm().delete(id);
    }

    @Test
    public void whenDeleteUserShouldGetInt(){
        User user = new User();
        user.setLogin("test1");
        user.setPassword("test1");
        user.setName("test1");
        user.setCreate(new Timestamp(System.currentTimeMillis()));
        user.setRole(new Role(2));
        int id = this.mf.getUm().create(user);

        int i = this.mf.getUm().delete(id);
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
        int id = this.mf.getUm().create(user);

        User newUser = new User();
        newUser.setName("test2");
        int i = this.mf.getUm().update(id, newUser);
        assertThat(i, is(1));

        //delete
        this.mf.getUm().delete(id);
    }

}