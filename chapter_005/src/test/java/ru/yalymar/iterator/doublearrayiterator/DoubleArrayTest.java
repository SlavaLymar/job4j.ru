package ru.yalymar.iterator.doublearrayiterator;

import org.junit.Test;
import java.util.Iterator;

import static org.junit.Assert.assertArrayEquals;

public class DoubleArrayTest {

    @Test
    public void whenCreateDoubleIterator(){

        int[][] dArray = {
            {1, 2, 3, 4, 5},
            {6, 7, 8, 9, 10},
            {11, 12, 13, 14, 15}
        };
        DoubleArray doubleArray = new DoubleArray(dArray);
        Iterator iterator = doubleArray.getIterator();
        int[][] result = new int[dArray.length][dArray[0].length];


        for(int i = 0; i<result.length; i++){
            for(int j = 0; j<result[0].length; j++){
                result[i][j] = (int)iterator.next();
            }
        }

    assertArrayEquals(dArray, result);
    }
}