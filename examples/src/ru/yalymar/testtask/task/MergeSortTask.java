package ru.yalymar.testtask.task;

import ru.yalymar.testtask.service.CompareLines;
import ru.yalymar.testtask.service.CreateNewFile;
import ru.yalymar.testtask.service.DeleteFile;
import ru.yalymar.testtask.sort.Sort;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Random;

public class MergeSortTask implements CompareLines, DeleteFile, CreateNewFile{

    private final String TEMPAREA;
    private final Random RANDOM;

    public MergeSortTask(final String TEMPAREA, final Random random) {
        this.TEMPAREA = TEMPAREA;
        this.RANDOM = random;
    }

    public void doTask(File file1, File file2) {
        File dist = this.createNewFile(TEMPAREA, "sort", RANDOM);
        try (RandomAccessFile rafFile1 = new RandomAccessFile(file1, "r");
             RandomAccessFile rafFile2 = new RandomAccessFile(file2, "r");
             RandomAccessFile rafDistSortI = new RandomAccessFile(dist, "rw")) {

            boolean success = this.mergeSort(rafFile1, rafFile2, rafDistSortI);

            if(success) {
                this.deleteFile(file1);
                this.deleteFile(file2);
            }
        } catch (IOException e) {
            Sort.logger.error(e.getMessage(), e);
        }

    }

    public boolean mergeSort(RandomAccessFile rafFile1, RandomAccessFile rafFile2,
                          RandomAccessFile rafDistSort) throws IOException {
        boolean done = false;
        long cursorLine1 = 0;
        long cursorLine2 = 0;

        while (!done) {

            rafFile1.seek(cursorLine1);
            rafFile2.seek(cursorLine2);

            String line1 = rafFile1.readLine();
            String line2 = rafFile2.readLine();

            if (line1 == null && line2 == null) {
                done = true;
                break;
            }
            else if (line1 == null && line2 != null) {
                while (rafFile2.read() != -1){
                    rafFile2.seek(rafFile2.getFilePointer() - 1);
                    rafDistSort.writeBytes(rafFile2.readLine());
                }
                done = true;
                break;
            }
            else if(line1 != null && line2 == null){
                while (rafFile1.read() != -1){
                    rafFile1.seek(rafFile1.getFilePointer() - 1);
                    rafDistSort.writeBytes(rafFile1.readLine());
                }
                done = true;
                break;
            }

            cursorLine1 += line1.length() + 2;
            cursorLine2 += line2.length() + 2;

            if(this.compareLines(line1, line2) < 0){
                rafDistSort.writeBytes(line1);
                cursorLine2 = cursorLine2 - line2.length() - 2;
            }
            else if(this.compareLines(line1, line2) >= 0){
                rafDistSort.writeBytes(line2);
                cursorLine1 = cursorLine1 - line1.length() - 2;
            }
        }
        return done;
    }

}
