package ru.yalymar.mapping.model.dao;

import org.junit.Before;
import org.junit.Test;
import ru.yalymar.mapping.model.models.Role;
import java.util.List;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class RoleDAOTest {

    private DAOFactory mf;

    @Before
    public void init(){
        this.mf = new DAOFactory();
    }

    @Test
    public void whenCreateRoleShouldGetId(){
        Role role = new Role();
        role.setRole("test1");
        int id = this.mf.getRoleDAO().create(role);
        assertTrue(id > 0);

        this.mf.getRoleDAO().delete(id);
    }

    @Test
    public void whenReadRoleShouldGetNotNull(){
        //add
        Role role = new Role();
        role.setRole("test1");
        int id = this.mf.getRoleDAO().create(role);

        Role result = this.mf.getRoleDAO().read(1);
        assertNotNull(result);
    }

    @Test
    public void whenReadAllRolesShouldGetThem(){
        //add
        Role role = new Role();
        role.setRole("test1");
        int id = this.mf.getRoleDAO().create(role);

        List<Role> roles = this.mf.getRoleDAO().readAll();
        assertTrue(roles.size() > 0);
    }

    @Test
    public void whenDeleteRoleShouldGetInt(){
        //add
        Role role = new Role();
        role.setRole("test1");
        int id = this.mf.getRoleDAO().create(role);

        int i = this.mf.getRoleDAO().delete(id);
        assertTrue(i > 0);
    }

    @Test
    public void whenUpdateRoleShouldGetInt(){
        Role role = new Role();
        role.setRole("test1");
        //add
        int id = this.mf.getRoleDAO().create(role);

        //daoUpdate
        Role newRole = new Role();
        newRole.setRole("test2");
        int i = this.mf.getRoleDAO().update(id, newRole);
        assertTrue(i > 0);

        //daoDelete
        this.mf.getRoleDAO().delete(id);
    }
}