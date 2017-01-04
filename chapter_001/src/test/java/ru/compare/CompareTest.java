package ru.compare;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/** @author slavalymar
 * @since 04.01.2017
 * @version 1
 */
public class CompareTest {
    @Test
    public void deleteArrTest() {
        Compare compare = new Compare();
        String[] arr = {"Привет", "Мир", "Привет", "Мир", "Java", "Java", "Java", "Homes"};
        String[] expected = {"Привет", "Мир", "Java", "Homes"};
        String[] result = compare.deleteArr(arr);
        assertThat(result, is(expected));
    }

    @Test
    public void countMethodTest() {
        Compare compare = new Compare();
        String[] arr = {"Привет", "Мир", "Привет", "Мир", "Java", "Java", "Java", "Homes"};
        int expected = 4;
        int result = compare.countMethod(arr);
        assertThat(result, is(expected));
    }

}
