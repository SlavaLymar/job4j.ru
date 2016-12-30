package ru.calculator;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/** @author slavalymar
 * @since 30/12/2016
 * @version 1
 */

public class CalculatorTest {

    @Test
    void addTest() {
        final Calculator c = new Calculator();
        c.add(2,2);
        final double r = c.getResult();
        assertThat(r, is(4d));
    }

    @Test
    void substructTest() {
        final Calculator c = new Calculator();
        c.substruct(2,2);
        final double r = c.getResult();
        assertThat(r, is(4d));
    }

    @Test
    void divTest() {
        final Calculator c = new Calculator();
        c.div(2,2);
        final double r = c.getResult();
        assertThat(r, is(4d));
    }

    @Test
    void multipleTest() {
        final Calculator c = new Calculator();
        c.multiple(2,2);
        final double r = c.getResult();
        assertThat(r, is(4d));
    }

    @Test
    void getResultTest() {
        final Calculator c = new Calculator();
        c.setResult(2);
        assertThat(c.getResult(), is(2d));
    }

    @Test
    void setResultTest() {
        final Calculator c = new Calculator();
        c.setResult(2);
        assertThat(c.getResult(), is(2d));
    }
}