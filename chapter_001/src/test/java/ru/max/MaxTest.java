package ru.max;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class MaxTest {
    @Test
    public void maxTest() {
        int x = 10 , y = 12;
        Max maximumValue = new Max();
        assertThat(maximumValue.max(x, y), is(12));
    }

}