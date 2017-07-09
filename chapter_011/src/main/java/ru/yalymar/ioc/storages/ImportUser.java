package ru.yalymar.ioc.storages;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.yalymar.ioc.storages.storages.MemoryStorage;

public class ImportUser {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-context-beans-memory.xml");
        MemoryStorage storage = context.getBean(MemoryStorage.class);
    }
}
