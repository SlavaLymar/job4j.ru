package ru.yalymar.mvc.model.dao;

import org.junit.Test;
import ru.yalymar.mvc.model.models.Role;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class RoleDAOTest {

    @Test
    public void whenCreateRoleShouldGetId(){
        DAOFactory mf = new DAOFactory();
        Role role = new Role();
        role.setRole("test1");
        int id = mf.getRoleDAO().create(role);
        assertTrue(id > 0);

        mf.getRoleDAO().delete(id);
    }

    @Test
    public void whenReadRoleShouldGetNotNull(){
        DAOFactory mf = new DAOFactory();

        //add
        Role role = new Role();
        role.setRole("test1");
        int id = mf.getRoleDAO().create(role);

        Role result = mf.getRoleDAO().read(1);
        assertNotNull(result);
    }

    @Test
    public void whenReadAllRolesShouldGetThem(){
        DAOFactory mf = new DAOFactory();

        //add
        Role role = new Role();
        role.setRole("test1");
        int id = mf.getRoleDAO().create(role);

        List<Role> roles = mf.getRoleDAO().readAll();
        assertTrue(roles.size() > 0);
    }

    @Test
    public void whenDeleteRoleShouldGetInt(){
        DAOFactory mf = new DAOFactory();

        //add
        Role role = new Role();
        role.setRole("test1");
        int id = mf.getRoleDAO().create(role);

        int i = mf.getRoleDAO().delete(id);
        assertTrue(i > 0);
    }

    @Test
    public void whenUpdateRoleShouldGetInt(){
        DAOFactory mf = new DAOFactory();

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