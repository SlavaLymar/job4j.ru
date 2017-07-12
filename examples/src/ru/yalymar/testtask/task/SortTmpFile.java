package ru.yalymar.testtask.task;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Random;

public class SortTmpFile {

    private final File tmpSrc;
    private final Random random;

    public SortTmpFile(File tmpSrc, Random random) {
        this.tmpSrc = tmpSrc;
        this.random = random;
    }

    public void doTask() {
        File tmpDist = new File(String.format("%s%stmpSort%s.txt",
                this.tmpSrc.getParent(), File.separator, this.random.nextInt()*1000));
        try(RandomAccessFile rafTmpSrc = new RandomAccessFile(this.tmpSrc, "rw");
            RandomAccessFile rafTmpDist = new RandomAccessFile(tmpDist, "rw")){

            int countOfLines = countLines(rafTmpSrc);

            //sort tmp.txt and create distance.txt
            this.sortTmp(countOfLines, rafTmpSrc, rafTmpDist);

            //delete tmp.txt
            this.deleteTmp(this.tmpSrc);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public void sortTmp(int countOfLines, RandomAccessFile rafTmpSrc,
                        RandomAccessFile rafTmpDist) throws IOException {

        String minLine = null; // line of min length
        String maxLine = null;    // line of max length
        String line1 = null;
        String line2 = null;
        long cursorPositionMinLine = 0;  // cursor position of min line
        int iteration = 1;

        while (iteration != countOfLines) {
            long cursorPosition = 0;     // cursor position of tmp.txt
            rafTmpSrc.seek(cursorPosition);

            for (int i = 1; i < countOfLines; i++) {
                rafTmpSrc.seek(cursorPosition);
                if(rafTmpSrc.read()!= -1) {
                    rafTmpSrc.seek(cursorPosition);
                    do {
                        line1 = rafTmpSrc.readLine();
                        if(line1 == null) break;
                        if(line1.contains("$"))cursorPosition += line1.length() + 2;
                    }
                    while(line1.contains("$"));
                }

                if(line1 != null){
                    cursorPosition += line1.length() + 2; // distance between lines for read
                    rafTmpSrc.seek(cursorPosition);
                }

                if(rafTmpSrc.read()!= -1) {
                    rafTmpSrc.seek(cursorPosition);
                    do {
                        line2 = rafTmpSrc.readLine();
                        if(line2 == null) break;
                        if(line2.contains("$")) cursorPosition += line2.length() + 2;
                    }
                    while(line2.contains("$"));
                }

                if(line2 != null) cursorPosition += line2.length() + 2;

                // looking for min and max length of line
                if(line1 != null && line2 != null) {
                    if (this.compareLines(line1, line2) < 0) {
                        if (minLine != null) {
                            if (this.compareLines(line1, minLine) < 0) {
                                minLine = line1;
                                cursorPositionMinLine = cursorPosition - line2.length() - line1.length() - 4;
                            }
                        } else {
                            minLine = line1;
                            cursorPositionMinLine = cursorPosition - line2.length() - line1.length() - 4;
                        }
                        if (maxLine != null) {
                            if (this.compareLines(line2, maxLine) > 0) {
                                maxLine = line2;
                            }
                        } else {
                            maxLine = line2;
                        }
                    } else if (this.compareLines(line1, line2) > 0) {
                        if (minLine != null) {
                            if (this.compareLines(line2, minLine) < 0) {
                                minLine = line2;
                                cursorPositionMinLine = cursorPosition - line2.length() - 2;
                            }
                        } else {
                            minLine = line2;
                            cursorPositionMinLine = cursorPosition - line2.length() - 2;
                        }
                        if (maxLine != null) {
                            if (this.compareLines(line1, maxLine) > 0) {
                                maxLine = line1;
                            }
                        } else {
                            maxLine = line1;
                        }
                    }
                }
                else {
                    cursorPosition = 0;
                }
            }

            // write to tmpSort.txt current min line
            rafTmpDist.writeBytes(String.format("%s%s", minLine, System.getProperty("line.separator")));
            rafTmpSrc.seek(cursorPositionMinLine);

            // mark lines that was used before
            if("".equals(minLine)){
                rafTmpSrc.writeBytes(String.format("$%s", System.getProperty("line.separator")));
            }
            else {
                rafTmpSrc.writeBytes("$");
            }
            iteration++;
            minLine = null;
        }
        // write to tmpSort.txt max line
        rafTmpDist.writeBytes(String.format("%s%s", maxLine, System.getProperty("line.separator")));
    }

    /** compares the lines depending on the lexical order.
     * if (line1 < line2) = -1, if (line1 > line2 = 1, else 0)
     * @param line1
     * @param line2
     * @return int
     */
    public int compareLines(String line1, String line2){
        int result = 0;
        int current = 0;
        for(int i = 0; i < line1.length() && i < line2.length(); i++){
            current = i;
            char c1 = line1.charAt(i);
            char c2 = line2.charAt(i);
            if(c1 == c2){
                continue;
            }
            else if(c1 < c2){
                result = -1;
                break;
            }
            else{
                result = 1;
                break;
            }
        }

        // if lines are equals check length of them.
        // if length > current that mean the line is larger then the other one
        if(result == 0){
            if(line1.length() - 1 > current){
                result = 1;
            }
            else if(line2.length() - 1 > current){
                result = -1;
            }
        }
        return result;
    }

    public int countLines(RandomAccessFile source) throws IOException{
        int result = 0;
        while(source.readLine() != null){
            result++;
        }
        return result;
    }

    public void deleteTmp(File tmpFile) {
        boolean bingo = false;
        do {
            bingo = tmpFile.delete();
        }
        while (!bingo);
    }

}
