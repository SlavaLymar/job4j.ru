package ru.yalymar.testtask.task;

import ru.yalymar.testtask.service.SortBySize;

import java.io.File;
import java.util.Random;
import java.util.concurrent.ExecutorService;

public class ScanDirectory implements SortBySize{

    private final ExecutorService threadPool;
    private final String TEMPAREA;
    private final Random RANDOM;

    public ScanDirectory(final ExecutorService threadPool, final String TEMPAREA,
                         final Random RANDOM) {
        this.threadPool = threadPool;
        this.TEMPAREA = TEMPAREA;
        this.RANDOM = RANDOM;
    }

    public boolean scanDir(){
        boolean done = false;
        while(!done){
            String[] files = this.sortBySize(TEMPAREA);

            if(files.length > 1){
                this.threadPool.execute(() -> {
                    File file1 = new File(TEMPAREA, files[0]);
                    File file2 = new File(TEMPAREA, files[1]);
                    new MergeSortTask(TEMPAREA, RANDOM).doTask(file1, file2);
                });
            }
            else {
                done = true;
            }
        }
        return done;
    }
    
}
