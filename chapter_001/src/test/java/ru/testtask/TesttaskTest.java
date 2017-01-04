package ru.testtask;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author slavalymar
 * @since 04.01.2017
 * @version 1
 */
public class TesttaskTest {
    @Test
    public void containsTest() {

        Testtask testtask = new Testtask();
        String origin = "I can feel i can fly";
        String sub = "feel";
        boolean result = testtask.contains(origin, sub);
        assertThat(result, is(true));
    }
}