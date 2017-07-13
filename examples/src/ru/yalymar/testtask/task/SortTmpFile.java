package ru.yalymar.testtask.task;

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
public class SortTmpFile {

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

    /**
     * compares the lines depending on the lexical order.
     * if (line1 < line2) = -1, if (line1 > line2 = 1, else 0)
     *
     * @param line1
     * @param line2
     * @return int
     */
    public int compareLines(String line1, String line2) {
        int result = 0;
        int current = 0;
        for (int i = 0; i < line1.length() && i < line2.length(); i++) {
            current = i;
            char c1 = line1.charAt(i);
            char c2 = line2.charAt(i);
            if (c1 == c2) {
                continue;
            } else if (c1 < c2) {
                result = -1;
                break;
            } else {
                result = 1;
                break;
            }
        }

        // if lines are equals check length of them.
        // if length > current that mean the line is larger then the other one
        if (result == 0) {
            if (line1.length() - 1 > current) {
                result = 1;
            } else if (line2.length() - 1 > current) {
                result = -1;
            }
        }
        return result;
    }

    /** return numerous of lines in the file
     * @param source
     * @return int
     * @throws IOException
     */
    public int countLines(RandomAccessFile source) throws IOException {
        int result = 0;
        while (source.readLine() != null) {
            result++;
        }
        return result;
    }

    public void deleteFile(File tmpFile) {
        boolean bingo = false;
        do {
            bingo = tmpFile.delete();
        }
        while (!bingo);
    }

}
