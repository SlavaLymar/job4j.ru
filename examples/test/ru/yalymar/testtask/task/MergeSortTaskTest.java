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

public class MergeSortTaskTest {

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
    public void whenMergeSortShouldGetInt() throws IOException {
        MergeSortTask sort = new MergeSortTask(this.properties.getProperty("TEMPAREA"), new Random());
        String sourcePath = String.format("%s%stest%smergetest%s",
                this.properties.getProperty("TEMPAREA"),
                File.separator, File.separator);

        String copySrc = String.format("%s%stestScrTmp.txt", this.properties.getProperty("TEMPAREA"), File.separator);
        try(RandomAccessFile s = new RandomAccessFile(sourcePath, "r");
            RandomAccessFile c = new RandomAccessFile(copySrc, "rw")){

            while (s.read() != -1){
                s.seek(s.getFilePointer() -1);
                c.writeBytes(String.format("%s%s", s.readLine(), System.getProperty("line.separator")));
            }
            c.seek(0);
//            countOfLines = sort.countLines(c);
//            sort.sortTmp(countOfLines, c);
            c.seek(0);

//            Assert.assertThat(sort.countLines(c), is(countOfLines));
        }
        sort.deleteFile(new File(copySrc));
    }
}