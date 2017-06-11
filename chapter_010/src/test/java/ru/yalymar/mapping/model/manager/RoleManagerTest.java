package ru.yalymar.mapping.model.manager;

import org.junit.Before;
import org.junit.Test;
import ru.yalymar.mapping.model.Role;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class RoleManagerTest {

    private ManagersFactory mf;

    @Before
    public void init(){
        this.mf = new ManagersFactory();
    }

    @Test
    public void whenReadRoleShouldGetNotNull(){
        Role role = this.mf.getRoleManager().read(1);
        assertNotNull(role);
    }

    @Test
    public void whenReadAllRolesShouldGetThem(){
        List<Role> roles = this.mf.getRoleManager().readAll();
        assertTrue(roles.size() > 0);
    }

    @Test
    public void whenCreateRoleShouldGetId(){
        Role role = new Role();
        role.setRole("test1");
        int id = this.mf.getRoleManager().create(role);
        assertTrue(id > 0);

        this.mf.getRoleManager().delete(id);
    }

    @Test
    public void whenDeleteRoleShouldGetInt(){
        Role role = new Role();
        role.setRole("test1");
        int id = this.mf.getRoleManager().create(role);

        int i = this.mf.getRoleManager().delete(id);
        assertTrue(i > 0);
    }

}