package ru.yalymar.nonblock.nonblockcache;

import org.apache.log4j.Logger;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;

/**
 * @author slavalymar
 * @since 14.04.2017
 * @version 1
 */
public class NonBlockCacheTest {

    private static final Logger log4 = Logger.getLogger(NonBlockCache.class);

    @Test
    public void whenAddTasksWithThreadsShouldGetSize() throws InterruptedException {
        NonBlockCache nonBlockCache = new NonBlockCache();
        List<Thread> threads = new ArrayList<>();

        // create 10000 threads that add tasks to map
        for(int i = 0; i<10000; i++) {
            final int count = i;
            threads.add(new Thread() {
                @Override
                public void run() {
                    nonBlockCache.add(count, new Task("Task1", count));
                }
            });
        }

        // start and wait theads
        for (Thread t : threads){
            t.start();
            t.join();
        }
        log4.info(String.format("1. Added 10000 obj were successful!%s",
                System.getProperty("line.separator")));
        assertThat(nonBlockCache.getMap().size(), is(10000));
    }

    @Test
    public void whenAddTasksWithThreadsThenUpdateShouldGetValue() throws InterruptedException {
        NonBlockCache nonBlockCache = new NonBlockCache();

        // start pool from 10 threads
        ExecutorService service = Executors.newFixedThreadPool(10);

        for(int i = 0; i<10; i++){
            final int count = i;
            service.submit(new Runnable() {
                @Override
                public void run() {
                    nonBlockCache.add(count, new Task("Task1", count));
                }
            });
        }
        service.shutdown();

        // run two thread in parallel
        log4.info(String.format("2. Start updating test!"));
        CountDownLatch cdl = new CountDownLatch(2);
        new Thread() {
            @Override
            public void run() {
                this.setName("Thread-1");
                cdl.countDown();
                try {
                    cdl.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                nonBlockCache.update(0, 1990);
                System.out.println(String.format("%s by %s. Version: %d",
                        nonBlockCache.getMap().get(0).getValue(), this.getName(),
                        nonBlockCache.getMap().get(0).getVersion()));
                log4.info(String.format("%s by %s. Version: %d",
                        nonBlockCache.getMap().get(0).getValue(), this.getName(),
                        nonBlockCache.getMap().get(0).getVersion()));
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                this.setName("Thread-2");
                cdl.countDown();
                try {
                    cdl.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                nonBlockCache.update(0, 2000);
                System.out.println(String.format("%s by %s. Version: %d",
                        nonBlockCache.getMap().get(0).getValue(), this.getName(),
                        nonBlockCache.getMap().get(0).getVersion()));
                log4.info(String.format("%s by %s. Version: %d",
                        nonBlockCache.getMap().get(0).getValue(), this.getName(),
                        nonBlockCache.getMap().get(0).getVersion()));
            }
        }.start();

        Thread.sleep(1000);

        log4.info(System.getProperty("line.separator"));
        assertThat(nonBlockCache.getMap().get(0).getVersion(), is(2));
    }

    @Test
    public void whenAddTasksWithThreadsThenDeleteShouldGetSize() throws InterruptedException {
        NonBlockCache nonBlockCache = new NonBlockCache();
        ExecutorService service = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 10; i++) {
            final int count = i;
            service.submit(new Runnable() {
                @Override
                public void run() {
                    nonBlockCache.add(count, new Task("Task1", count));
                }
            });
        }
        service.shutdown();

        log4.info("3. Start deleting test!");
        CountDownLatch cdl = new CountDownLatch(2);
        new Thread() {
            @Override
            public void run() {
                this.setName("Thread-1");
                cdl.countDown();
                try {
                    cdl.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                nonBlockCache.delete(0);
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                this.setName("Thread-2");
                cdl.countDown();
                try {
                    cdl.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                nonBlockCache.delete(0);
            }
        }.start();

        Thread.sleep(1000);

        log4.info(System.getProperty("line.separator"));
        assertNull(nonBlockCache.getMap().get(0));
    }

}