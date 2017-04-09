package ru.yalymar.monitorsync.synclist;

import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class SyncLinkedListTest {

    SyncLinkedList<Integer> syncLinkedList = new SyncLinkedList<>();

    @Test
    public void whenAddObjShouldGetIt() throws InterruptedException {

        new Thread(){
            @Override
            public void run() {
                syncLinkedList.add(10);
            }
        }.start();

        new Thread(){
            @Override
            public void run() {
                System.out.println(syncLinkedList.get(0));
            }
        }.start();

        new Thread(){
            @Override
            public void run() {
                syncLinkedList.set(0, 20);
            }
        }.start();

        do{
            Thread.sleep(1);
        }
        while (Thread.activeCount() > 2);

        for(int i = 0; i<syncLinkedList.size(); i++){
            System.out.println(syncLinkedList.get(i));
        }
        assertThat(syncLinkedList.get(0), is(20));
    }


}