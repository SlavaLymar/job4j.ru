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
    public void init() {
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
                File.separator, File.separator, File.separator);

        String[] files = new File(sourcePath).list();
        if (files.length > 0) {

            String copySrc1 = String.format("%stmp1.txt", sourcePath);
            String copySrc2 = String.format("%stmp2.txt", sourcePath);
            String dist = String.format("%ssort.txt", sourcePath);

            try (RandomAccessFile s1 = new RandomAccessFile(String.format("%s%s",
                    sourcePath, files[0]), "r");
                 RandomAccessFile s2 = new RandomAccessFile(String.format("%s%s",
                         sourcePath, files[1]), "r");
                 RandomAccessFile c1 = new RandomAccessFile(copySrc1, "rw");
                 RandomAccessFile c2 = new RandomAccessFile(copySrc2, "rw");
                 RandomAccessFile d = new RandomAccessFile(dist, "rw")) {

                while (s1.read() != -1) {
                    s1.seek(s1.getFilePointer() - 1);
                    c1.writeBytes(String.format("%s%s", s1.readLine(), System.getProperty("line.separator")));
                }
                while (s2.read() != -1) {
                    s2.seek(s2.getFilePointer() - 1);
                    c2.writeBytes(String.format("%s%s", s2.readLine(), System.getProperty("line.separator")));
                }
                c1.seek(0);
                c2.seek(0);
                sort.mergeSort(c1, c2, d);

                c1.seek(0);
                c2.seek(0);
                d.seek(0);
                Assert.assertThat(sort.countLines(d), is(sort.countLines(c1) + sort.countLines(c2)));
            }

            sort.deleteFile(new File(copySrc1));
            sort.deleteFile(new File(copySrc2));
            sort.deleteFile(new File(dist));
        }


    }
}