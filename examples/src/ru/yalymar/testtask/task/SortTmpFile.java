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
 * @since 13.07.2017
 * @version 1
 */
public class SortTmpFile implements CompareLines, DeleteFile, CountOfLines{

    /**
     * instance of temporary file
     */
    private final File tmpSrc;

    private final Random random;

    public SortTmpFile(File tmpSrc, Random random) {
        this.tmpSrc = tmpSrc;
        this.random = random;
    }

    /**
     * manager of class
     */
    public void doTask() {
        try (RandomAccessFile rafTmpSrc = new RandomAccessFile(this.tmpSrc, "rw")){

            // count of lines
            int countOfLines = countLines(rafTmpSrc);

            //sort tmp.txt
            this.sortTmp(countOfLines, rafTmpSrc);

            //rename tmp.txt to sort1.txt
            File file = this.tmpSrc.getAbsoluteFile();
            rafTmpSrc.close();
            file.renameTo(new File(file.getAbsolutePath().replace("tmp", "sort")));
        }
        catch (IOException e) {
            Sort.logger.error(e.getMessage(), e);
        }
    }

    /** external sorting by a bubble method
     * @param countOfLines
     * @param rafTmpSrc
     * @throws IOException
     */
    public void sortTmp(int countOfLines, RandomAccessFile rafTmpSrc) throws IOException {
        for (int i = countOfLines - 1; i > 0; i--) {
            long cursorPosition = 0;
            rafTmpSrc.seek(cursorPosition);

            for (int j = 0; j < i; j++) {
                long cursorPositionLine1 = cursorPosition;
                String line1 = rafTmpSrc.readLine();
                cursorPosition += line1.length() + 2;

                String line2 = rafTmpSrc.readLine();
                cursorPosition += line2.length() + 2;

                if (this.compareLines(line1, line2) > 0) {
                    rafTmpSrc.seek(cursorPositionLine1);
                    rafTmpSrc.writeBytes(String.format("%s%s", line2, System.getProperty("line.separator")));
                    rafTmpSrc.writeBytes(String.format("%s%s", line1, System.getProperty("line.separator")));
                    cursorPosition = cursorPosition - line1.length() - 2;
                } else {
                    cursorPosition = cursorPosition - line2.length() - 2;
                }
                rafTmpSrc.seek(cursorPosition);
            }
        }
    }



}
