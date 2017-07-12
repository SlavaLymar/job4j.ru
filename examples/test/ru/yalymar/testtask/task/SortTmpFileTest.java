package ru.yalymar.testtask.task;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.yalymar.testtask.sort.Sort;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
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
    public void whenSortShouldGetInt(){
        
    }
}