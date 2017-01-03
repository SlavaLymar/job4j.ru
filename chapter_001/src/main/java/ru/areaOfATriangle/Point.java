package ru.areaOfATriangle;

/**
 * @author slavalymar
 * @since 30.12.2016
 * @version 1
 */

public class Point {
    public double x;
    public double y;

    /**
     * @param x
     * @param y
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @param point
     * @return distanceTo
     */
    public double distanceTo(Point point) {
        return Math.sqrt(Math.pow(point.x - this.x,2)+Math.pow(point.y - this.y,2));
    }
}
