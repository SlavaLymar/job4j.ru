package ru.yalymar.mvc.model.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.yalymar.mvc.model.models.Role;
import ru.yalymar.mvc.model.models.User;

import java.sql.Timestamp;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-mvc-context-test.xml")
public class UserDAOTest {

    @Autowired
    private DAOFactory daoFactory;

    public User createUser(){

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
        User user = this.createUser();
        assertTrue(user.getId() > 0);
    }

    @Test
    public void whenReadUserShouldGetNotNull(){
        User user = this.createUser();
        User result = daoFactory.getUserDAO().read(user.getId());
        assertNotNull(result);
    }

    @Test
    public void whenReadAllUsersShouldGetThem(){
        this.createUser();
        List<User> users = daoFactory.getUserDAO().readAll();
        assertTrue(users.size() > 0);
    }

    @Test
    public void whenDeleteUserShouldGetInt(){
        User user = this.createUser();
        int i = daoFactory.getUserDAO().delete(user.getId());
        assertThat(i, is(1));
    }

    @Test
    public void whenUpdateUserShouldGetNewUser(){
        User user = this.createUser();

        //daoUpdate
        User newUser = new User();
        newUser.setName("test2");
        int i = daoFactory.getUserDAO().update(user.getId(), newUser);
        assertThat(i, is(1));
    }

    @Test
    public void whenGetUserByLoginPasswordShoildGetIt(){
        User user = this.createUser();
        User userLP = daoFactory.getUserDAO().getByLoginPassword("test1", "test1");
        assertNotNull(userLP);
    }

}