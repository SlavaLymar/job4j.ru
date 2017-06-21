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
    private User user;

    public int createUser(){
        this.mf = new DAOFactory();
        //create role
        Role role = new Role();
        role.setRole("test1");
        int roleId = this.mf.getRoleDAO().create(role);

        //create user
        User user = new User();
        user.setLogin("test1");
        user.setPassword("test1");
        user.setName("test1");
        user.setCreate(new Timestamp(System.currentTimeMillis()));
        user.setRole(new Role(roleId));
        this.user = user;
        return this.mf.getUserDAO().create(user);
    }

    @Before
    public void init(){
        this.createUser();
    }

    @Test
    public void whenCreateUserShouldGetId(){
        assertTrue(this.user.getId() > 0);
    }

    @Test
    public void whenReadUserShouldGetNotNull(){
        User result = this.mf.getUserDAO().read(this.user.getId());
        assertNotNull(result);
    }

    @Test
    public void whenReadAllUsersShouldGetThem(){
        List<User> users = this.mf.getUserDAO().readAll();
        assertTrue(users.size() > 0);
    }

    @Test
    public void whenDeleteUserShouldGetInt(){
        int i = this.mf.getUserDAO().delete(this.user.getId());
        assertThat(i, is(1));
    }

    @Test
    public void whenUpdateUserShouldGetNewUser(){
        //daoUpdate
        User newUser = new User();
        newUser.setName("test2");
        int i = this.mf.getUserDAO().update(this.user.getId(), newUser);
        assertThat(i, is(1));
    }

    @Test
    public void whenGetUserByLoginPasswordShoildGetIt(){
        User userLP = this.mf.getUserDAO().getByLoginPassword("test1", "test1");
        assertNotNull(userLP);
    }

}