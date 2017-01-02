package ru.squarearr;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author slavalymar
 * @since 02.01.2017
 * @version 1
 */
public class SquareArrTest {
    @Test
    public void turnTest() {
        SquareArr sq = new SquareArr();
        int[][] arr = {
                {1,2,3},
                {4,5,6},
                {7,8,9}
        };
        int[][] expected = {
                {3,6,9},
                {2,5,8},
                {1,4,7}
        };
        int[][] result = sq.turn(arr);
        assertThat(result, is(expected));

    }

}