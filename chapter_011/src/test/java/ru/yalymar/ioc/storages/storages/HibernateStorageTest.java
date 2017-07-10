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
        User user = new User("slava", "lymar", new Role(1));
//        storage.add(user);

        System.out.println(storage.get(3));
//        storage.delete(user);
//        Assert.assertThat(storage.get(user), is(user));
    }
}