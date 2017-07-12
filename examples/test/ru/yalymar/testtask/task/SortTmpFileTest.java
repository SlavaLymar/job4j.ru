package ru.yalymar.testtask.task;

import org.junit.Assert;
import org.junit.Test;
import java.io.File;
import java.util.Random;
import static org.hamcrest.core.Is.is;

public class SortTmpFileTest {

    @Test
    public void whenCompareStringsShouldGetInt(){
        String line2 = "abcde";
        String line1 = "ab";

        SortTmpFile sort = new SortTmpFile(new File(""), new Random());
        int result = sort.compareLines(line1, line2);
        Assert.assertThat(result, is(-1));
    }
}