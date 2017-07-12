package ru.yalymar.testtask.task;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.yalymar.testtask.sort.Sort;
import java.io.*;
import java.util.Properties;
import java.util.Random;
import static org.hamcrest.core.Is.is;

public class SortTmpFileTest {

    private Properties properties;

    @Before
    public void init(){
        this.properties = new Properties();
        Class c = this.getClass();
        InputStream inputStream = c.getResourceAsStream("/settings.properties");
        try {
            this.properties.load(inputStream);
        } catch (IOException e) {
            Sort.logger.error(e.getMessage(), e);
        }
    }

    @Test
    public void whenCompareStringsShouldGetInt(){
        String line2 = "abcde";
        String line1 = "ab";

        SortTmpFile sort = new SortTmpFile(new File(""), new Random());
        int result = sort.compareLines(line1, line2);
        Assert.assertThat(result, is(-1));
    }

    @Test
    public void whenCountOfLinesShouldGetInt() throws IOException {
        SortTmpFile sort = new SortTmpFile(new File(""), new Random());
        int countOfLines = 0;
        String sourcePath = this.properties.getProperty("SOURCEPATH");
        try(RandomAccessFile r = new RandomAccessFile(sourcePath, "r")){
            countOfLines = sort.countLines(r);
        }
        Assert.assertThat(countOfLines, is(11));
    }

    @Test
    public void whenSortShouldGetInt() throws IOException {
        SortTmpFile sort = new SortTmpFile(new File(""), new Random());
        int countOfLines = 0;
        String sourcePath = this.properties.getProperty("SOURCEPATH");
        String distancePath = String.format("%s%stestTmp.txt", this.properties.getProperty("TEMPAREA"), File.separator);
        String copySrc = String.format("%s%stestScrTmp.txt", this.properties.getProperty("TEMPAREA"), File.separator);
        File file = new File(distancePath);
        try(RandomAccessFile s = new RandomAccessFile(sourcePath, "r");
            RandomAccessFile d = new RandomAccessFile(distancePath, "rw");
            RandomAccessFile c = new RandomAccessFile(copySrc, "rw")){

            while (s.read() != -1){
                s.seek(s.getFilePointer() -1);
                c.writeBytes(String.format("%s%s", s.readLine(), System.getProperty("line.separator")));
            }
            c.seek(0);
            countOfLines = sort.countLines(c);
            sort.sortTmp(countOfLines, c, d);
            d.seek(0);

            Assert.assertThat(sort.countLines(d), is(countOfLines));
        }
        sort.deleteTmp(new File(distancePath));
        sort.deleteTmp(new File(copySrc));
    }


}