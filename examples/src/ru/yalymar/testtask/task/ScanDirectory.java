package ru.yalymar.testtask.task;

import ru.yalymar.testtask.service.SortBySize;
import ru.yalymar.testtask.sort.Sort;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileLock;
import java.nio.channels.OverlappingFileLockException;
import java.util.Random;
import java.util.concurrent.ExecutorService;

public class ScanDirectory implements SortBySize {

    private final ExecutorService threadPool;
    private final String TEMPAREA;
    private final Random RANDOM;

    public ScanDirectory(final ExecutorService threadPool, final String TEMPAREA,
                         final Random RANDOM) {
        this.threadPool = threadPool;
        this.TEMPAREA = TEMPAREA;
        this.RANDOM = RANDOM;
    }

    public boolean scanDir() {
        boolean done = false;
        while (!done) {
            File[] files = this.sortBySize(TEMPAREA);

            if (files != null) {

                if (files.length > 0) {
                    int i1 = 0;
                    File file1 = null;
                    for (int i = 0; i < files.length; i++) {
                        if (this.isLockFile(files[i])) {
                            file1 = files[i];
                            i1 = i;
                            break;
                        }
                    }
                    File file2 = null;
                    for (int i = 0; i < files.length; i++) {
                        if (i == i1) continue;
                        if (this.isLockFile(files[i])) {
                            file2 = files[i];
                            break;
                        }
                    }
                    if (file1 != null && file2 != null) {
                        File finalFile1 = file1;
                        File finalFile2 = file2;
                        this.threadPool.execute(() -> {
                            new MergeSortTask(TEMPAREA, RANDOM).doTask(finalFile1, finalFile2);
                        });
                    }
                }

                File[] checkFiles = this.sortBySize(TEMPAREA);
                int counter = 0;
                for (int i = 0; i < checkFiles.length; i++) {
                    if (!checkFiles[i].isDirectory()) {
                        counter++;
                    }
                    if (counter == 2) {
                        break;
                    }
                }
                if (counter == 1) done = true;
            } else {
                done = true;
            }
        }
        return done;
    }

    private synchronized boolean isLockFile(File file) {
        boolean result = false;
        try (RandomAccessFile rafFile = new RandomAccessFile(file, "rw")) {

            FileLock lock = rafFile.getChannel().tryLock();
            if (lock != null) {
                result = true;
            }
        } catch (IOException | OverlappingFileLockException e) {
            Sort.logger.error(e.getMessage(), e);
            return false;
        }
        return result;
    }


}
