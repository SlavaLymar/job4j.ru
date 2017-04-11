package ru.yalymar.waitnotify.threadpool;

import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class ThreadPoolTest {

    @Test
    public void whenAddTaskIntoThreadPoolShouldGetIt() throws InterruptedException {
        ThreadPool threadPool = new ThreadPool();
        threadPool.add(new Task("Task1"));
        threadPool.add(new Task("Task2"));
        threadPool.add(new Task("Task3"));
        threadPool.add(new Task("Task4"));
        threadPool.add(new Task("Task5"));
        threadPool.add(new Task("Task6"));
        threadPool.add(new Task("Task7"));

        Thread.sleep(1000);

        assertThat(Thread.activeCount(), is(6)); // 1- main, 2 - io, 3-6 - MyThread (4 cpu)

        threadPool.stop();
    }
}