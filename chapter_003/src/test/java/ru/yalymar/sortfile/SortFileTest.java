package ru.yalymar.sortfile;

import org.junit.Test;

import java.io.*;

/**
 * @author slavalymar
 * @since 29.01.2017
 * @version 1
 */
public class SortFileTest {

    @Test
    public void sortTest() {
        SortFile sortFile = new SortFile();
        File source = new File("C:\\Java\\junior\\chapter_003\\resources\\Task3\\Task3.txt");
        File distance = new File("C:\\Java\\junior\\chapter_003\\resources\\Task3\\Task3sorted.txt");
        sortFile.sort(source, distance);
    }

    @Test
    public void CopyAndCountLinesTest(){
        SortFile sortFile = new SortFile();

        try(RandomAccessFile source =
                    new RandomAccessFile("C:/Java/junior/chapter_003/resources/Task3/Task3.txt", "r");
            RandomAccessFile tmp =
                    new RandomAccessFile("C:/Java/junior/chapter_003/resources/Task3/tmp.txt", "rw")){

            sortFile.countLinesAndCopy(source, tmp);
        }
        catch (IOException e){
                e.printStackTrace();
        }

    }

}