package ru.yalymar.iterator.evennumbers;

import org.junit.Test;
import java.util.Iterator;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class NumbersTest {

    @Test
    public void whenReturnEvenNumbers(){

        Numbers numbers = new Numbers(new int[]{1,2,3,4,5,6,7,8,9,10});
        int[] expectedNumbers = new int[]{2,4,6,8,10};
        int[] result = new int[5];

        int i = 0;
        for (Iterator iterator = numbers.getIterator(); iterator.hasNext();) {
            int number = (Integer) iterator.next();

            if(number != -1) {
                result[i] = number;
                i++;
            }
        }
        assertThat(result, is(expectedNumbers));
    }
}