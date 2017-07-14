package ru.yalymar.testtask.task;

import ru.yalymar.testtask.service.SortBySize;

import java.io.File;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;

public class ScanDirectory implements SortBySize {

    private final ExecutorService threadPool;
    private final String TEMPAREA;
    private final Random RANDOM;
    private final Map<File, Boolean> map;

    public ScanDirectory(final ExecutorService threadPool, final String TEMPAREA,
                         final Random RANDOM) {
        this.threadPool = threadPool;
        this.TEMPAREA = TEMPAREA;
        this.RANDOM = RANDOM;
        this.map = new ConcurrentHashMap<>();
        this.createMap();
    }

    public boolean scanDir() {
        boolean done = false;

        while (!done) {
            File[] files = new File[2];
            int i = 0;
            for (Map.Entry<File, Boolean> file : this.map.entrySet()) {
                if (i == 2) break;
                if (file.getValue()) {
                    continue;
                }
                files[i] = file.getKey();
                i++;
            }
            if (files[0] != null && files[1] != null) {
                this.map.replace(files[0], Boolean.TRUE);
                this.map.replace(files[1], Boolean.TRUE);

                File finalFile1 = files[0];
                File finalFile2 = files[1];
                this.threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName());
                    File newTmp = new MergeSortTask(TEMPAREA, RANDOM).doTask(finalFile1, finalFile2);
                    this.map.put(newTmp, Boolean.FALSE);
                });
            }
            if (files[1] == null) {
                done = true;
            }
        }
        return done;
    }


    private void createMap() {
        File[] files = this.sortBySize(TEMPAREA);
        for (File file : files) {
            this.map.put(file, Boolean.FALSE);
        }
    }
}
