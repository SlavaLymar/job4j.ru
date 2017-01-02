package ru.factorial;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author slavalymar
 * @since 02.01.2017
 * @version 1
 */
public class FactorialTest {
    @Test
    public void factorialTest() {
        Factorial f = new Factorial();
        int result = f.factorial(5);
        assertThat(result, is(120));
    }

}