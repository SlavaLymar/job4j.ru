package ru.yalymar.start;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * @author slavalymar
 * @since 13.01.2017
 * @version 1
 */
public class ConsoleInputTest {
    @Test
    public void askTest() {
        ConsoleInput consoleInput = new ConsoleInput();

        int result = consoleInput.ask("q", new int[]{0, 1, 2, 3, 4, 5});
        int expected = 0;
        assertThat(result, is(expected));
    }

}