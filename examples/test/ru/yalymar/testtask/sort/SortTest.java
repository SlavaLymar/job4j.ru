package ru.yalymar.testtask.sort;

import org.junit.Test;

public class SortTest {

    @Test
    public void sortTest() throws InterruptedException {
        Sort sort = new Sort();
        sort.sort();
        sort.getThreadPool().shutdown();
        do {
            Thread.sleep(1000);
        }
        while(Thread.activeCount() > 2);
    }
}