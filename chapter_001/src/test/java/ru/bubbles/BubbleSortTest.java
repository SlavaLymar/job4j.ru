package ru.bubbles;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author slavalymar
 * @since 02.01.2017
 * @version 1
 */
public class BubbleSortTest {
    @Test
    public void bubbleSort() {
        BubbleSort bs = new BubbleSort();
        int[] arr = {1, 2, 6, 12, 32, 0, 12, 5};
        int[] expected = {0, 1, 2, 5, 6, 12, 12, 32};
        int[] result = bs.bubbleSort(arr);
        assertThat(result, is(expected));
    }

}