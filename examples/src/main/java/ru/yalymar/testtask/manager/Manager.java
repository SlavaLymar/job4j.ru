package ru.yalymar.testtask.manager;

import ru.yalymar.testtask.service.CopyTextFile;
import ru.yalymar.testtask.service.CreateNewFile;
import ru.yalymar.testtask.service.DeleteFile;
import ru.yalymar.testtask.service.SortBySize;
import ru.yalymar.testtask.sort.Sort;
import ru.yalymar.testtask.task.ScanDirectory;
import ru.yalymar.testtask.task.SortTmpFileB;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author slavalymar
 * @since 17.07.2017
 * @version 1
 *
 * Class that operate phases of sorting
 */
public class Manager implements CreateNewFile,
                                DeleteFile,
                                CopyTextFile,
                                SortBySize {

    /**
     * properties of "resources/settings.properties"
     */
    private final Properties properties;
    private final int COUNTOFLINES;
    private final String TEMPAREA;
    private final String COPYBUFFER;
    private final String SOURCEPATH;
    private final String DISTANCEPATH;

    /**
     * instance of thread pool
     */
    private final ExecutorService threadPool;

    private final Random RANDOM = new Random();

    public Manager(final Properties properties,
                   final ExecutorService threadPool) {
        this.properties = properties;
        this.threadPool = threadPool;
        COUNTOFLINES = Integer.parseInt(this.properties.getProperty("COUNFOFLINES"));
        TEMPAREA = this.properties.getProperty("TEMPAREA");
        COPYBUFFER = this.properties.getProperty("COPYBUFFER");
        SOURCEPATH = this.properties.getProperty("SOURCEPATH");
        DISTANCEPATH = this.properties.getProperty("DISTANCEPATH");
    }

    /**
     * method operates of phases of sorting
     */
    public void managerOfApp(){

        File distance = new File(DISTANCEPATH);

        try(RandomAccessFile rafSrc = new RandomAccessFile(SOURCEPATH, "r")){

            while (rafSrc.read() != -1){
                rafSrc.seek(rafSrc.getFilePointer() - 1);
                File file = this.createNewFile(TEMPAREA,"tmp", RANDOM);

                try (RandomAccessFile tmp = new RandomAccessFile(file, "rw")) {
                    int count = 0;

                    //read the source file
                    while (COUNTOFLINES != count && rafSrc.read() != -1){
                        rafSrc.seek(rafSrc.getFilePointer() - 1);
                        String line = rafSrc.readLine();
                        count++;
                        if(line != null) {
                            tmp.writeBytes(String.format("%s%s",
                                    line, System.getProperty("line.separator")));
                        }
                    }
                }
                catch (IOException e) {
                    Sort.logger.error(e.getMessage(), e);
                }

                // phase 1: sorting parts of source file
                this.threadPool.execute(() -> {
                    new SortTmpFileB(file, RANDOM).doTask();
                });
            }

            // wait until the threads finished phase 1
            this.waitThreadPoolThreads();


            // phase 2: merge sort of sorting file
            new ScanDirectory(this.threadPool, TEMPAREA, RANDOM).scanDir();

            // wait until the threads finished phase 2
            this.waitThreadPoolThreads();


            // phase 3: copy sorting file to DISTANCEPATH
            boolean copyComplete;
            do{
                copyComplete = this.copyTmpToDistance(distance);
            }
            while (!copyComplete);
        }
        catch (IOException e){
            Sort.logger.error(e.getMessage(), e);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /** copy sorted tmp file to distance
     * @param distance
     * @return boolean
     */
    private boolean copyTmpToDistance(File distance) {
        File[] files = this.sortBySize(TEMPAREA);
        if(files.length == 1){
            this.copy(files[0], distance, COPYBUFFER);
            this.deleteFile(files[0]);
            return true;
        }
        return false;
    }

    /** wait until the threads finish
     * @throws InterruptedException
     */
    private void waitThreadPoolThreads() throws InterruptedException {
        ThreadPoolExecutor tp = (ThreadPoolExecutor) this.threadPool;
        do{
            Thread.sleep(100);
        }
        while (tp.getActiveCount() > 0);
    }



}
