package ru.yalymar.ioc.storages.storages;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.yalymar.ioc.storages.models.Role;
import ru.yalymar.ioc.storages.models.User;
import static org.hamcrest.core.Is.is;

public class HibernateStorageTest {

    @Test
    public void whenLoadUserStorageShouldGetNotNull(){
        ApplicationContext context =
                new ClassPathXmlApplicationContext("spring-context-storages-hibernate.xml");
        UserStorage storage = context.getBean(UserStorage.class);
        Assert.assertNotNull(storage);
    }

    @Test
    public void whenAddUserToDBShouldGetIt(){
        ApplicationContext context =
                new ClassPathXmlApplicationContext("spring-context-storages-hibernate.xml");
        UserStorage storage = context.getBean(UserStorage.class);
        User user = new User("test", "test", new Role(2));
        int id = storage.add(user);
        Assert.assertThat(storage.get(id), is(user));
        storage.delete(id);
    }

    @Test
    public void whenUpdateUserToDBShouldGetIt(){
        ApplicationContext context =
                new ClassPathXmlApplicationContext("spring-context-storages-hibernate.xml");
        UserStorage storage = context.getBean(UserStorage.class);

        //add
        User user = new User("test", "test", new Role(2));
        int id = storage.add(user);

        //update
        storage.update(id, new User("test1", "test1", new Role(2)));
        User result = storage.get(id);
        Assert.assertThat(result.getLogin(), is("test1"));

        //delete
        storage.delete(id);
    }
}