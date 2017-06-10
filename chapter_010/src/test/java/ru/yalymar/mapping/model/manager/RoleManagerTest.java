package ru.yalymar.mapping.model.manager;

import org.junit.Before;
import org.junit.Test;
import ru.yalymar.mapping.model.Role;

import static org.junit.Assert.assertNotNull;

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
}