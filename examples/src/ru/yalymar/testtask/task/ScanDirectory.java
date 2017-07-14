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
            File[] files = this.sortBySize(TEMPAREA);

            if(files != null){
                this.threadPool.execute(() -> {

                    if(files.length > 0) {
                        int i1 = 0;
                        File file1 = null;
                        for (int i = 0; i < files.length; i++) {
                            if (!files[i].isDirectory()) {
                                file1 = files[i];
                                i1 = i;
                                break;
                            }
                        }
                        File file2 = null;
                        for (int i = 0; i < files.length; i++) {
                            if (i == i1) continue;
                            if (!files[i].isDirectory()) {
                                file2 = files[i];
                                break;
                            }
                        }
                        if(file1 != null && file2 != null) {
                            new MergeSortTask(TEMPAREA, RANDOM).doTask(file1, file2);
                        }
                    }
                });

                File[] checkFiles = this.sortBySize(TEMPAREA);
                int counter = 0;
                for(int i = 0; i < checkFiles.length; i++){
                    if(checkFiles[i].isDirectory()){
                        counter++;
                    }
                    if(counter == 2) {
                        break;
                    }
                }
                if(counter == 1) done = true;
            }
            else {
                done = true;
            }
        }
        return done;
    }


}
