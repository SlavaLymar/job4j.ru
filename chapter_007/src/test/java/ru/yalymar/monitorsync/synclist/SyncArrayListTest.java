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





}