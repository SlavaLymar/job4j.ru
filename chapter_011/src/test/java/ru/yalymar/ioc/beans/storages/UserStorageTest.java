package ru.yalymar.ioc.beans.storages;

import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.yalymar.ioc.beans.models.Role;
import ru.yalymar.ioc.beans.models.User;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

public class UserStorageTest {

    @Test
    public void whenGetUserStorageShouldBeNotNull(){
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-context-beans-memory.xml");
        UserStorage storage = context.getBean(UserStorage.class);
        assertNotNull(storage);
    }

    @Test
    public void whenLoadContextShouldGetBeans(){
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-context-beans-memory.xml");
        MemoryStorage storage = context.getBean(MemoryStorage.class);
        User user = new User("slava", "lymar", new Role("admin"));
        user.setId("1");
        storage.add(user);
        assertThat(storage.get(user), is(user));
    }

    @Test
    public void whenLoadBeanFactoryShouldGetBeans(){
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("spring-context-beans-jdbc.xml");
        JdbcStorage storage = beanFactory.getBean(JdbcStorage.class);
        User user = new User("slava", "lymar", new Role("admin"));
        user.setId("1");
        storage.add(user);
        assertThat(storage.get(user), is(user));
    }
}