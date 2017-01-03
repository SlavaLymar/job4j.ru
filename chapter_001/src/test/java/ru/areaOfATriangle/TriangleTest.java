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
        Point a = new Point(1, 2);
        Point b = new Point(3, 4);
        Point c = new Point(5, 6);
        Triangle triangle = new Triangle(a, b, c);
        double result = triangle.area();
        assertThat(result, closeTo(Math.sqrt(4608),0.01d ));
    }

}