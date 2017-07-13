package ru.yalymar.testtask.manager;

import ru.yalymar.testtask.sort.Sort;
import ru.yalymar.testtask.task.SortTmpFile;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.ExecutorService;

public class Manager {

    private final Properties properties;
    private final ExecutorService threadPool;
    private final Random random = new Random();
    private final int COUNTOFLINES;

    public Manager(final Properties properties,
                   final ExecutorService threadPool) {
        this.properties = properties;
        this.threadPool = threadPool;
        this.COUNTOFLINES = Integer.parseInt(this.properties.getProperty("COUNFOFLINES"));
    }

    public void readFile(){
        String sourcePath = this.properties.getProperty("SOURCEPATH");
        String distancePath = this.properties.getProperty("DISTANCEPATH");

        File distance = new File(distancePath);

        try(RandomAccessFile rafSrc = new RandomAccessFile(sourcePath, "r");
            RandomAccessFile rafDisc = new RandomAccessFile(distancePath, "rw")){

            while (rafSrc.read() != -1){ // TODO
                rafSrc.seek(rafSrc.getFilePointer() - 1);
                File file = this.createTmpFile();

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
                this.threadPool.execute(() -> {
                    new SortTmpFile(file, this.random).doTask();
                });




            }
        }
        catch (IOException e){
            Sort.logger.error(e.getMessage(), e);
        }

    }

    private File createTmpFile() {
        File file;
        do {
            file = new File(this.properties.getProperty("TEMPAREA"),
                    String.format("%stmp%s.txt", File.separator, this.random.nextInt() * 1000));
        }
        while (file.exists());
        return file;
    }


}
