package ru.yalymar.cache;

import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class CacheTest {

    @Test
    public void whenAddIntoCacheShouldGetNull(){
        Cache cache = new Cache();
        File file = new File("D:/dstr/job4j.ru/chapter_006/src/main/resources/lala.txt");
        String result = cache.getObject(file);
        assertNull(result);
    }

    @Test
    public void whenAddIntoCacheShouldGetString(){
        Cache cache = new Cache();
        File file = new File("D:/dstr/job4j.ru/chapter_006/src/main/resources/lala.txt");
        cache.getObject(file);
        String result = cache.getObject(file);

        System.out.println(result);
        assertNotNull(result);
    }
}