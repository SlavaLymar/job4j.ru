package ru.turn;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author slavalymar
 * @since 02.01.2017
 * @version 1
 */
public class TurnTest {
    @Test
    public void back() {
        Turn turn = new Turn();
        int[] in = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int[] expected = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        int[] result = turn.back(in);
        assertThat(result, is(expected));
    }

}