package ru.yalymar.ioc.storages.storages;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.yalymar.ioc.storages.models.Role;
import ru.yalymar.ioc.storages.models.User;
import static org.hamcrest.core.Is.is;

public class MemoryStorageTest {

    @Test
    public void whenLoadUserStorageShouldGetNotNull(){
        ApplicationContext context =
                new ClassPathXmlApplicationContext("spring-context-beans-memory.xml");
        UserStorage storage = context.getBean(UserStorage.class);
        Assert.assertNotNull(storage);
    }

    @Test
    public void whenAddUserToStorageShouldGetIt(){
        ApplicationContext context =
                new ClassPathXmlApplicationContext("spring-context-beans-memory.xml");
        UserStorage storage = context.getBean(UserStorage.class);
        User user = new User("test", "test", new Role(2));
        int index = storage.add(user);
        Assert.assertThat(storage.get(index), is(user));
        storage.delete(index);
    }

    @Test
    public void whenUpdateUserToStorageShouldGetIt(){
        ApplicationContext context =
                new ClassPathXmlApplicationContext("spring-context-beans-memory.xml");
        UserStorage storage = context.getBean(UserStorage.class);

        //add
        User user = new User("test", "test", new Role(2));
        int index = storage.add(user);

        //update
        storage.update(index, new User("test1", "test1", new Role(2)));
        User result = storage.get(index);
        Assert.assertThat(result.getLogin(), is("test1"));

        //delete
        storage.delete(index);
    }
}