package ru.yalymar.ioc.storages;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.yalymar.ioc.models.Role;
import ru.yalymar.ioc.models.User;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

public class UserStorageTest {

    @Test
    public void whenAddToStorageUserShouldSafeIt(){
        MemoryStorage memoryStorage = new MemoryStorage();
        UserStorage storage = new UserStorage(memoryStorage);
        User user = new User("slava", "lymar", new Role("admin"));
        user.setId("1");
        storage.add(user);
        assertNotNull(storage);
    }

    @Test
    public void whenLoadContextShouldGetBeans(){
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
        MemoryStorage storage = context.getBean(MemoryStorage.class);
        User user = new User("slava", "lymar", new Role("admin"));
        user.setId("1");
        storage.add(user);
        assertThat(storage.get(user), is(user));
    }
}