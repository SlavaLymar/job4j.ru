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
public class SortTmpFileH implements CompareLines, DeleteFile, CountOfLines{

    /**
     * instance of temporary file
     */
    private final File tmpSrc;

    private final Random random;

    public SortTmpFileH(File tmpSrc, Random random) {
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

            //sorting #tmpSrc file
            this.sortTmp(countOfLines, rafTmpSrc);

            //rename tmp.txt to sort.txt
            File file = this.tmpSrc.getAbsoluteFile();
            rafTmpSrc.close();
            file.renameTo(new File(file.getAbsolutePath().replace("tmp", "sort")));
        }
        catch (IOException e) {
            Sort.logger.error(e.getMessage(), e);
        }
    }

    /** 
     * @param countOfLines
     * @param rafTmpSrc
     * @throws IOException
     */
    public void sortTmp(int countOfLines, RandomAccessFile rafTmpSrc) throws IOException {
        
        for(int k = countOfLines/2; k > 0; k--){
            this.downheap(rafTmpSrc, k, countOfLines);
        }
    }

    private void downheap(RandomAccessFile rafTmpSrc, int k, int countOfLines) throws IOException {
        
        int cursorPosition = 0;

        int count = 0;
        String t;
        int parentPosition = 0;
        do{
            t = rafTmpSrc.readLine();
            cursorPosition += t.length() + 2;
            parentPosition = cursorPosition - t.length();
            count++;
        }
        while (count != (k - 1));
        
        while (k <= countOfLines/2){
            int j = k + k;
            
            String left = rafTmpSrc.readLine();
            cursorPosition += left.length() + 2;
            String right = rafTmpSrc.readLine();
            cursorPosition += right.length() + 2;

            if((j < countOfLines) && (this.compareLines(left, right) < 0)) j++;
            if(this.compareLines(t, left) >= 0) {
                break;
            }
            else{
                rafTmpSrc.seek(parentPosition);

            }


        }
    }


}
