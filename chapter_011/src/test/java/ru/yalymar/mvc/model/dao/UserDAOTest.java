package ru.yalymar.mvc.model.dao;

import org.junit.Test;
import ru.yalymar.mvc.model.models.Role;
import ru.yalymar.mvc.model.models.User;

import java.sql.Timestamp;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class UserDAOTest {

    public User createUser(DAOFactory daoFactory){

        //create role
        Role role = new Role();
        role.setRole("test1");
        int roleId = daoFactory.getRoleDAO().create(role);

        //create user
        User user = new User();
        user.setLogin("test1");
        user.setPassword("test1");
        user.setName("test1");
        user.setCreate(new Timestamp(System.currentTimeMillis()));
        user.setRole(new Role(roleId));
        daoFactory.getUserDAO().create(user);
        return user;
    }

    @Test
    public void whenCreateUserShouldGetId(){
        DAOFactory daoFactory = new DAOFactory();
        User user = this.createUser(daoFactory);
        assertTrue(user.getId() > 0);
    }

    @Test
    public void whenReadUserShouldGetNotNull(){
        DAOFactory daoFactory = new DAOFactory();
        User user = this.createUser(daoFactory);
        User result = daoFactory.getUserDAO().read(user.getId());
        assertNotNull(result);
    }

    @Test
    public void whenReadAllUsersShouldGetThem(){
        DAOFactory daoFactory = new DAOFactory();
        this.createUser(daoFactory);
        List<User> users = daoFactory.getUserDAO().readAll();
        assertTrue(users.size() > 0);
    }

    @Test
    public void whenDeleteUserShouldGetInt(){
        DAOFactory daoFactory = new DAOFactory();
        User user = this.createUser(daoFactory);
        int i = daoFactory.getUserDAO().delete(user.getId());
        assertThat(i, is(1));
    }

    @Test
    public void whenUpdateUserShouldGetNewUser(){
        DAOFactory daoFactory = new DAOFactory();
        User user = this.createUser(daoFactory);

        //daoUpdate
        User newUser = new User();
        newUser.setName("test2");
        int i = daoFactory.getUserDAO().update(user.getId(), newUser);
        assertThat(i, is(1));
    }

    @Test
    public void whenGetUserByLoginPasswordShoildGetIt(){
        DAOFactory daoFactory = new DAOFactory();
        this.createUser(daoFactory);
        User userLP = daoFactory.getUserDAO().getByLoginPassword("test1", "test1");
        assertNotNull(userLP);
    }

}