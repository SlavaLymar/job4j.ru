package ru.arrays;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author slavalymar
 * @since 04.01.2017
 * @version 1
 */
public class ThirdArrayTest {
    @Test
    public void commonArr() {
        int[] i1 = {1, 2, 3, 4, 5};
        int[] i2 = {10, 11, 12, 13, 14, 15};
        int[] expected = {1, 2, 3, 4, 5, 10, 11, 12, 13, 14, 15};
        ThirdArray thirdArray = new ThirdArray();
        int[] result = thirdArray.commonArr(i1, i2);
        assertThat(result, is(expected));
    }

}