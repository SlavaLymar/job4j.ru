package ru.yalymar.iterator.evennumbers;

import org.junit.Test;
import java.util.Iterator;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class NumbersTest {

    @Test
    public void whenReturnEvenNumbers(){

        int[] array = new int[]{1,2,3,4,5,6,7,8,9,10};
        Numbers numbers = new Numbers(array);
        int[] result = new int[5];
        Iterator iterator = numbers.getIterator();

        int index = 0;
        for (int i = 0; i<array.length; i++) {
            int number = (Integer) iterator.next();

            if(number != -1) {
                result[index++] = number;
            }
        }

        int[] expectedNumbers = new int[]{2,4,6,8,10};
        assertThat(result, is(expectedNumbers));
    }
}