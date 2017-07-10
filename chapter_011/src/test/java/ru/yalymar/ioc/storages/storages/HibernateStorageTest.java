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
//        User user = new User("slava", "lymar", new Role(1));
//        int id = storage.add(user);
//        Assert.assertThat(storage.get(id), is(user));
        storage.delete(3);
    }

    @Test
    public void whenUpdateUserToDBShouldGetIt(){
        ApplicationContext context =
                new ClassPathXmlApplicationContext("spring-context-storages-hibernate.xml");
        UserStorage storage = context.getBean(UserStorage.class);
        storage.update(3, new User("test", "test", new Role(2)));
    }
}