package ru.yalymar.testtask.task;

import ru.yalymar.testtask.service.CreateNewFile;
import ru.yalymar.testtask.service.SortBySize;
import java.io.File;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;

/**
 * @author slavalymar
 * @since 17.07.2017
 * @version 1
 */
public class ScanDirectory implements SortBySize,
                                      CreateNewFile {

    /**
     * properties of "resources/settings.properties"
     */
    private final String TEMPAREA;
    private final Random RANDOM;

    /**
     * instance of thread pool
     */
    private final ExecutorService threadPool;

    /**
     * collection of tmp files
     */
    private final Map<File, Boolean> map;

    public ScanDirectory(final ExecutorService threadPool,
                         final String TEMPAREA,
                         final Random RANDOM) {
        this.threadPool = threadPool;
        this.TEMPAREA = TEMPAREA;
        this.RANDOM = RANDOM;
        this.map = new ConcurrentHashMap<>();
        this.createMap();
    }

    /**
     * scanning #TEMPAREA directory for merge sorting.
     * files must be ready to merge (map.getValue() == true)
     */
    public void scanDir() {
        boolean done = false;

        while (!done) {
            File[] files = new File[2];
            int i = 0;
            for (Map.Entry<File, Boolean> file : this.map.entrySet()) {
                if (i == 2) break;
                if (!file.getValue()) {
                    continue;
                }
                files[i] = file.getKey();
                i++;
            }
            if (files[0] != null && files[1] != null) {

                File finalFile1 = files[0];
                File finalFile2 = files[1];
                File dist = this.createNewFile(TEMPAREA, "sort", RANDOM);
                this.map.put(dist, Boolean.FALSE);

                this.threadPool.execute(() -> {
                    new MergeSortTask(TEMPAREA, RANDOM).doTask(finalFile1, finalFile2, dist);
                    this.map.replace(dist, Boolean.TRUE);
                });
                this.map.remove(files[0]);
                this.map.remove(files[1]);
            }

            if (this.map.size() == 1) {
                done = true;
            }
        }

    }

    /**
     * create collection of tmp files
     */
    private void createMap() {
        File[] files = this.sortBySize(TEMPAREA);
        for (File file : files) {
            this.map.put(file, Boolean.TRUE);
        }
    }
}
