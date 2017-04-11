package ru.yalymar.waitnotify.threadpool;

import org.junit.Test;

public class ThreadPoolTest {

    @Test
    public void whenAddTaskIntoThreadPoolShouldGetIt(){
        ThreadPool threadPool = new ThreadPool();
        threadPool.add(new Task("Vasya"));
    }
}