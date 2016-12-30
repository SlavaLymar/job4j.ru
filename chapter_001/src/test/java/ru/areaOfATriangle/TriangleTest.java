package ru.areaOfATriangle;

import org.junit.Test;

import static org.hamcrest.number.IsCloseTo.closeTo;
import static org.junit.Assert.assertThat;

/**
 * @author slavalymar
 * @since 30.12.2016
 * @version 1
 */

public class TriangleTest {

    @Test
    public void areaTest() {
        Triangle triangle = new Triangle(new Point(1,2), new Point(3,4),new Point(5,6));
        double result = triangle.area();
        assertThat(result, closeTo(Math.sqrt(4608),0.01d ));
    }

}