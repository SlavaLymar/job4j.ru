package ru.areaOfATriangle;

import org.junit.Test;

import static org.hamcrest.number.IsCloseTo.closeTo;
import static org.junit.Assert.assertThat;
import static ru.areaOfATriangle.Point.distanceTo;

/**
 * @author slavalymar
 * @since 30.12.2016
 * @version 1
 */

public class PointTest {

    @Test
    public void distanceToTest() {
        double result = distanceTo(new Point(1,2), new Point(3,4));
        assertThat(result, closeTo(Math.sqrt(8),0.01d ));
    }

}