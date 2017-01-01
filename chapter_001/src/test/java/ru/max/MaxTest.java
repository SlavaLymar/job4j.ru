package ru.max;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author slavalymar
 * @since 01.01.2017
 * @version 1
 */
public class MaxTest {

    @Test
    public void maxTest() {
        int x = 10 , y = 12;
        Max maximumValue = new Max();
        assertThat(maximumValue.max(x, y), is(12));
    }

}