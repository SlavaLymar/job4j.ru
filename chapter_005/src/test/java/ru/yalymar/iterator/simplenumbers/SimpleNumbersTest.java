package ru.yalymar.iterator.simplenumbers;

import org.junit.Test;
import java.util.Iterator;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class SimpleNumbersTest {

    @Test
    public void whenNumberIsSimple(){
        int[] array = new int[]{1,2,3,4,5,6,7,8,9,10};
        SimpleNumbers numbers = new SimpleNumbers(array);
        int[] result = new int[4];
        Iterator iterator = numbers.getIterator();

        int index = 0;
        for (int i = 0; i<array.length; i++) {
            int number = (Integer) iterator.next();

            if(number != -1) {
                result[index++] = number;
            }
        }

        int[] expectedNumbers = new int[]{2,3,5,7};
        assertThat(result, is(expectedNumbers));
    }

}