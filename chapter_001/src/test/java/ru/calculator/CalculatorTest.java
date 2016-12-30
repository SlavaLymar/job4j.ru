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
    public void addTest() {
        final Calculator c = new Calculator();
        c.add(2,2);
        final double r = c.getResult();
        assertThat(r, is(4d));
    }

    @Test
    public void substructTest() {
        final Calculator c = new Calculator();
        c.substruct(2,2);
        final double r = c.getResult();
        assertThat(r, is(0d));
    }

    @Test
    public void divTest() {
        final Calculator c = new Calculator();
        c.div(2,2);
        final double r = c.getResult();
        assertThat(r, is(1d));
    }

    @Test
    public void multipleTest() {
        final Calculator c = new Calculator();
        c.multiple(2,2);
        final double r = c.getResult();
        assertThat(r, is(4d));
    }

    @Test
    public void getResultTest() {
        final Calculator c = new Calculator();
        c.setResult(2);
        assertThat(c.getResult(), is(2d));
    }

    @Test
    public void setResultTest() {
        final Calculator c = new Calculator();
        c.setResult(2);
        assertThat(c.getResult(), is(2d));
    }
}