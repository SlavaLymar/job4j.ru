package ru.yalymar.generic.store;

import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;

public class AbstractStoreTest {

    @Test
    public void whenAddStoreShouldGetIt(){
        Role role = new Role("programmer");
        RoleStore <Role> roleStore = new RoleStore(10);

        roleStore.add(role);

        assertThat(roleStore.getValues().getArr()[0], is(role));
    }

    @Test
    public void whenUpdateStoreShouldGetNewValue() throws IncorrectIdException {
        User user = new User("Dyadya Vanya");
        UserStore <User> userStore = new UserStore(10);
        userStore.add(user);

        User newUser = new User("Tetya Lena");
        userStore.update(user, newUser);

        assertThat(userStore.getValues().getArr()[0], is(newUser));
    }

    @Test
    public void whenDeleteStoreShouldGetNull() throws IncorrectIdException {
        User user = new User("Dyadya Vanya");
        UserStore <User> userStore = new UserStore(10);
        userStore.add(user);

        userStore.delete(user);

        assertNull(userStore.getValues().getArr()[0]);
    }

    @Test(expected = IncorrectIdException.class)
    public void whenAddStoreThenDeleteShouldGetExceprion() throws IncorrectIdException {
        User user = new User("Dyadya Vanya");
        UserStore <User> userStore = new UserStore(10);
        userStore.add(user);

        User user1 = new User("Dyadya");
        userStore.delete(user1);
    }
}