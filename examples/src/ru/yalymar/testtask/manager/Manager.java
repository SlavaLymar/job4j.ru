package ru.yalymar.testtask.manager;

import ru.yalymar.testtask.service.CopyTextFile;
import ru.yalymar.testtask.service.CreateNewFile;
import ru.yalymar.testtask.service.DeleteFile;
import ru.yalymar.testtask.service.SortBySize;
import ru.yalymar.testtask.sort.Sort;
import ru.yalymar.testtask.task.ScanDirectory;
import ru.yalymar.testtask.task.SortTmpFile;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.ExecutorService;

public class Manager implements CreateNewFile, DeleteFile, CopyTextFile, SortBySize {

    private final Properties properties;
    private final ExecutorService threadPool;
    private final Random RANDOM = new Random();
    private final int COUNTOFLINES;
    private final String TEMPAREA;
    private final String COPYBUFFER;

    public Manager(final Properties properties,
                   final ExecutorService threadPool) {
        this.properties = properties;
        this.threadPool = threadPool;
        this.COUNTOFLINES = Integer.parseInt(this.properties.getProperty("COUNFOFLINES"));
        this.TEMPAREA = this.properties.getProperty("TEMPAREA");
        this.COPYBUFFER = this.properties.getProperty("COPYBUFFER");
    }

    public void readFile(){
        String sourcePath = this.properties.getProperty("SOURCEPATH");
        String distancePath = this.properties.getProperty("DISTANCEPATH");

        File distance = new File(distancePath);

        try(RandomAccessFile rafSrc = new RandomAccessFile(sourcePath, "r")){

            while (rafSrc.read() != -1){
                rafSrc.seek(rafSrc.getFilePointer() - 1);
                File file = this.createNewFile(this.properties.getProperty("TEMPAREA"),
                        "tmp", RANDOM);

                try (RandomAccessFile tmp = new RandomAccessFile(file, "rw")) {
                    int count = 0;
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

                //sort tmp.txt
                this.threadPool.execute(() -> {
                    new SortTmpFile(file, RANDOM).doTask();
                });
            }

            boolean complete = new ScanDirectory(this.threadPool, TEMPAREA, RANDOM).scanDir();

            if(complete) {
                this.copyTmpToDistance(distance);
            }
        }
        catch (IOException e){
            Sort.logger.error(e.getMessage(), e);
        }
    }

    private void copyTmpToDistance(File distance) {
        File[] files = new File(TEMPAREA).listFiles();
        for(int i = 0; i < files.length; i++){
            if(!files[i].isDirectory()){
                this.copy(files[i], distance, COPYBUFFER);
                this.deleteFile(files[i]);
                break;
            }
        }
    }


}
