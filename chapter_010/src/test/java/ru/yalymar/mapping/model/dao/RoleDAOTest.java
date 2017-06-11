package ru.yalymar.mapping.model.dao;

import org.junit.Before;
import org.junit.Test;
import ru.yalymar.mapping.model.Role;
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
    public void whenReadRoleShouldGetNotNull(){
        Role role = this.mf.getRoleDAO().daoRead(1);
        assertNotNull(role);
    }

    @Test
    public void whenReadAllRolesShouldGetThem(){
        List<Role> roles = this.mf.getRoleDAO().daoReadAll();
        assertTrue(roles.size() > 0);
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
    public void whenDeleteRoleShouldGetInt(){
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