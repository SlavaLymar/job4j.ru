package ru.yalymar.mapping.model.manager;

import org.junit.Before;
import org.junit.Test;
import ru.yalymar.mapping.model.User;
import static org.junit.Assert.assertNotNull;

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
}