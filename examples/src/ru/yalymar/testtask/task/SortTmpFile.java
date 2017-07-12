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
        File tmpDist = new File(String.format("%s%stmpSort%s",
                this.tmpSrc.getParent(), File.separator, this.random.nextInt()*1000));
        try(RandomAccessFile rafTmpSrc = new RandomAccessFile(this.tmpSrc, "r");
            RandomAccessFile rafTmpDist = new RandomAccessFile(tmpDist, "rw")){

            int countOfLines = countLines(rafTmpSrc);

            //sort tmp.txt and create distance.txt
            sortTmp(countOfLines, rafTmpSrc, rafTmpDist);

            //delete tmp.txt
            deleteTmp(this.tmpSrc);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    private void sortTmp(int countOfLines, RandomAccessFile rafTmpSrc,
                         RandomAccessFile rafTmpDist) throws IOException {

        String minLine = "    "; // line of min length
        String maxLine = " ";    // line of max length
        String line1 = " ";
        String line2 = "  ";
        long cursorPositionMinLine = 0;  // cursor position of min line
        int time = 1;

        while (time != countOfLines) {
            long cursorPosition = 0;     // cursor position of tmp.txt
            rafTmpSrc.seek(cursorPosition);
            minLine = String.format("%100s", " ");

            for (int i = 1; i < countOfLines; i++) {

                if(rafTmpSrc.read()!= -1) {
                    rafTmpSrc.seek(cursorPosition);
                    do {
                        line1 = rafTmpSrc.readLine();
                        if(line1.contains("$"))cursorPosition += line1.length() + 2;
                    }
                    while(line1.contains("$"));
                }

                cursorPosition += line1.length() + 2; // distance between lines for read
                rafTmpSrc.seek(cursorPosition);

                if(rafTmpSrc.read()!= -1) {
                    rafTmpSrc.seek(cursorPosition);
                    do {
                        line2 = rafTmpSrc.readLine();
                        if(line2.contains("$")) cursorPosition += line2.length() + 2;
                    }
                    while(line2.contains("$"));
                }

                cursorPosition += line2.length() + 2;

                // looking for min and max length of line
                if (line1.length()<line2.length()) {
                    if (line1.length() < minLine.length()) {
                        minLine = line1;
                        cursorPositionMinLine = cursorPosition-line2.length()-line1.length()-4;
                    }
                    if(line2.length()>maxLine.length()){
                        maxLine = line2;
                    }
                }
                else if (line1.length()>line2.length()) {
                    if (line2.length() < minLine.length()) {
                        minLine = line2;
                        cursorPositionMinLine = cursorPosition-line2.length()-2;
                    }
                    if(line1.length()>maxLine.length()){
                        maxLine = line1;
                    }
                }

            }

            // write to distance.txt current min line
            rafTmpDist.writeBytes(String.format("%s%s", minLine, System.getProperty("line.separator")));
            rafTmpSrc.seek(cursorPositionMinLine);
            rafTmpSrc.writeBytes("$");     // mark lines that was used before
            time++;
        }
        // write to distance.txt max line
        rafTmpDist.writeBytes(String.format("%s%s", maxLine, System.getProperty("line.separator")));
    }

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
        while(source.readLine()!= null){
            result++;
        }
        return result;
    }

    private void deleteTmp(File tmpFile) {
        tmpFile.delete();
    }

}
