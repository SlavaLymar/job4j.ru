package ru.yalymar.testtask.task;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.yalymar.testtask.sort.Sort;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.Executors;
import static org.hamcrest.core.Is.is;

public class ScanDirectoryTest {

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
    public void whenSortBySizeShouldGetArr(){
        String path = String.format("%s%ssortbysizetest%s",
                this.properties.getProperty("TEMPAREA"),
                File.separator, File.separator);
        ScanDirectory scanDirectory = new ScanDirectory(Executors.newFixedThreadPool(1),
                path, new Random());
        File[] s = scanDirectory.sortBySize(path);
        Assert.assertThat(s[0].getName(), is("sortdd.txt"));
    }

}