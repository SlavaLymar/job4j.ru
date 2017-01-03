package ru.areaOfATriangle;

import org.junit.Test;

import static org.hamcrest.number.IsCloseTo.closeTo;
import static org.junit.Assert.assertThat;

/**
 * @author slavalymar
 * @since 30.12.2016
 * @version 1
 */

public class PointTest {

    @Test
    public void distanceToTest() {
        Point a = new Point(1, 2);
        Point b = new Point(3, 4);
        double result = a.distanceTo(b);
        assertThat(result, closeTo(Math.sqrt(8),0.01d ));
    }

}