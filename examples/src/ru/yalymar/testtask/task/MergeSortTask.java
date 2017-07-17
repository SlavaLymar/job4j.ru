package ru.yalymar.testtask.task;

import ru.yalymar.testtask.service.CompareLines;
import ru.yalymar.testtask.service.CountOfLines;
import ru.yalymar.testtask.service.DeleteFile;
import ru.yalymar.testtask.sort.Sort;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Random;

/**
 * @author slavalymar
 * @since 17.07.2017
 * @version 1
 */
public class MergeSortTask implements CompareLines,
                                      DeleteFile,
                                      CountOfLines{

    /**
     * properties of "resources/settings.properties"
     */
    private final String TEMPAREA;
    private final Random RANDOM;

    public MergeSortTask(final String TEMPAREA, final Random random) {
        this.TEMPAREA = TEMPAREA;
        this.RANDOM = random;
    }

    /** method operates phase 2
     * @param file1
     * @param file2
     * @param dist
     */
    public void doTask(File file1, File file2, File dist) {

        try (RandomAccessFile rafFile1 = new RandomAccessFile(file1, "rw");
             RandomAccessFile rafFile2 = new RandomAccessFile(file2, "rw");
             RandomAccessFile rafDistSortI = new RandomAccessFile(dist, "rw")) {

                boolean success = this.mergeSort(rafFile1, rafFile2, rafDistSortI);
                if (success) {
                    rafFile1.close();
                    this.deleteFile(file1);
                    rafFile2.close();
                    this.deleteFile(file2);
                }
        } catch (IOException e) {
            Sort.logger.error(e.getMessage(), e);
        }
    }

    /** merge sorting.
     * @param rafFile1 - sorting tmp file
     * @param rafFile2 - sorting tmp file
     * @param rafDistSort - distance tmp file
     * @return boolean
     * @throws IOException
     */
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
                rafDistSort.writeBytes(String.format("%s%s",
                        line2, System.getProperty("line.separator")));
                while (rafFile2.read() != -1){
                    rafFile2.seek(rafFile2.getFilePointer() - 1);
                    rafDistSort.writeBytes(String.format("%s%s",
                            rafFile2.readLine(), System.getProperty("line.separator")));
                }
                done = true;
                break;
            }
            else if(line1 != null && line2 == null){
                rafDistSort.writeBytes(String.format("%s%s",
                        line1, System.getProperty("line.separator")));
                while (rafFile1.read() != -1){
                    rafFile1.seek(rafFile1.getFilePointer() - 1);
                    rafDistSort.writeBytes(String.format("%s%s",
                            rafFile1.readLine(), System.getProperty("line.separator")));
                }
                done = true;
                break;
            }

            cursorLine1 += line1.length() + 2;
            cursorLine2 += line2.length() + 2;

            if(this.compareLines(line1, line2) < 0){
                rafDistSort.writeBytes(String.format("%s%s",
                        line1, System.getProperty("line.separator")));
                cursorLine2 = cursorLine2 - line2.length() - 2;
            }
            else if(this.compareLines(line1, line2) >= 0){
                rafDistSort.writeBytes(String.format("%s%s",
                        line2, System.getProperty("line.separator")));
                cursorLine1 = cursorLine1 - line1.length() - 2;
            }
        }
        return done;
    }

}
