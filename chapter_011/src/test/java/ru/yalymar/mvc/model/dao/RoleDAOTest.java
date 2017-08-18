package ru.yalymar.mvc.model.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.yalymar.mvc.model.models.Role;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-mvc-context-test.xml")
public class RoleDAOTest {

    @Autowired
    private DAOFactory mf;

    @Test
    public void whenCreateRoleShouldGetId(){
        Role role = new Role();
        role.setRole("test1");
        int id = mf.getRoleDAO().create(role);
        assertTrue(id > 0);

        mf.getRoleDAO().delete(id);
    }

    @Test
    public void whenReadRoleShouldGetNotNull(){

        //add
        Role role = new Role();
        role.setRole("test1");
        int id = mf.getRoleDAO().create(role);

        Role result = mf.getRoleDAO().read(id);
        assertNotNull(result);
    }

    @Test
    public void whenReadAllRolesShouldGetThem(){

        //add
        Role role = new Role();
        role.setRole("test1");
        int id = mf.getRoleDAO().create(role);

        List<Role> roles = mf.getRoleDAO().readAll();
        assertTrue(roles.size() > 0);
    }

    @Test
    public void whenDeleteRoleShouldGetInt(){

        //add
        Role role = new Role();
        role.setRole("test1");
        int id = mf.getRoleDAO().create(role);

        int i = mf.getRoleDAO().delete(id);
        assertTrue(i > 0);
    }

    @Test
    public void whenUpdateRoleShouldGetInt(){

        Role role = new Role();
        role.setRole("test1");
        //add
        int id = mf.getRoleDAO().create(role);

        //daoUpdate
        Role newRole = new Role();
        newRole.setRole("test2");
        int i = mf.getRoleDAO().update(id, newRole);
        assertTrue(i > 0);

        //daoDelete
        mf.getRoleDAO().delete(id);
    }
}