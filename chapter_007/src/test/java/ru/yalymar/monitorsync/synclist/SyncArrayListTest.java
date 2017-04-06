package ru.yalymar.monitorsync.synclist;

import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class SyncArrayListTest {

    SyncArrayList<Integer> syncArrayList = new SyncArrayList<>();

    @Test
    public void whenAddObjWithSeveralThreadsShouldGetIt() throws InterruptedException {

        for(int i = 0; i<1000; i++){
            final int index = i;
            new Thread() {
                @Override
                public void run() {
                    syncArrayList.add(index);
                }
            }.start();
        }

        do{
            Thread.sleep(10);
        }
        while(Thread.activeCount() > 2);

        int result = 0;
        Object[] integers = this.syncArrayList.getArray();
        for(int i = 0; i < integers.length; i++){
            if(integers[i] != null) {
                result++;
                System.out.println(String.format("[index = %d]: %d", i, integers[i]));
            }
        }

        assertThat(result, is(1000));
    }

    @Test
    public void whenEditObjShouldGetIt() throws InterruptedException {

        Thread t1 = new Thread() {
            @Override
            public void run() {
                for(int i = 0; i<100; i++) {
                    syncArrayList.add(10);
                }
            }
        };

        t1.start();
        t1.join();

        new Thread() {
            @Override
            public void run() {
                for(int i = 0; i<100; i++) {
                    System.out.println(Thread.currentThread().getName());
                    syncArrayList.add(i, 20);
                }
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                int count = 0;
                for(int i = 0; i<100; i++) {
                    System.out.println(Thread.currentThread().getName());
                    if(syncArrayList.get(i) == 10) count++;
                }
                System.out.println(String.format("Count: %d", count));
            }
        }.start();

        do{
            Thread.sleep(1);
        }
        while(Thread.activeCount() > 2);

        //assertThat(result, is(1000));
    }





}