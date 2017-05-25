package ru.yalymar.filter.model;

import org.junit.Test;
import static org.junit.Assert.assertTrue;

public class UserManagerTest {

    @Test
    public void whenSignInShouldGetTrue() {
        UserManager userManager = new UserManager();
        boolean result = userManager.isValid("admin", "admin");
        assertTrue(result);
    }

}