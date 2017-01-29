package ru.yalymar.sortfile;

import java.io.*;

/**
 * @author slavalymar
 * @since 29.01.2017
 * @version 1
 */
public class SortFile{

    /** Create tmp.txt, sort tmp.txt and create distance.txt
     * @param source
     * @param distance
     */
    public void sort(File source, File distance){

        try(RandomAccessFile rafSrc = new RandomAccessFile(source, "r");
            RandomAccessFile rafDist = new RandomAccessFile(distance, "rw") ){

            File tmpFile = new File(distance.getParent()+"\\tmp.txt");
            RandomAccessFile tmp = new RandomAccessFile(tmpFile, "rw");
            int countOfLines = countLinesAndCopy(rafSrc, tmp);

            //sort tmp.txt and create distance.txt
            sortAndCreateDistanceFile(countOfLines, tmp, rafDist);

            //delete tmp.txt
            tmp.close();
            deleteTmp(tmpFile);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * @param countOfLines
     * @param tmp
     * @param rafDist
     * @throws IOException
     */
    private void sortAndCreateDistanceFile(int countOfLines, RandomAccessFile tmp,
                                          RandomAccessFile rafDist) throws IOException {

        String minLine = "    "; // line of min length
        String maxLine = " ";    // line of max length
        String line1 = " ";
        String line2 = "  ";
        long cursorPositionMinLine = 0;  // cursor position of min line
        int time = 1;

        while (time != countOfLines) {
            long cursorPosition = 0;     // cursor position of tmp.txt
            tmp.seek(cursorPosition);
            minLine = String.format("%100s", " ");

            for (int i = 1; i < countOfLines; i++) {

                if(tmp.read()!= -1) {
                    tmp.seek(cursorPosition);
                    do {
                        line1 = tmp.readLine();
                        if(line1.contains("$"))cursorPosition += line1.length() + 2;
                    }
                    while(line1.contains("$"));
                }

                cursorPosition += line1.length() + 2; // distance between lines for read
                tmp.seek(cursorPosition);

                if(tmp.read()!= -1) {
                    tmp.seek(cursorPosition);
                    do {
                        line2 = tmp.readLine();
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
            rafDist.writeBytes(String.format("%s%s", minLine, System.getProperty("line.separator")));
            tmp.seek(cursorPositionMinLine);
            tmp.writeBytes("$");     // mark lines that was used before
            time++;
        }
        // write to distance.txt max line
        rafDist.writeBytes(String.format("%s%s", maxLine, System.getProperty("line.separator")));
    }

    /** count lines in source.txt and copy in tmp.txt
     * @param source
     * @param tmp
     * @return int
     * @throws IOException
     */
    public int countLinesAndCopy(RandomAccessFile source, RandomAccessFile tmp) throws IOException{
        int result = 0;
        String str;
        while((str = source.readLine())!= null){
            tmp.writeBytes(String.format("%s%s", str, System.getProperty("line.separator")));
            result++;
        }
        return result;
    }

    /** delete tmp.txt
     * @param tmpFile
     */
    private void deleteTmp(File tmpFile) {
        tmpFile.delete();
    }
}
