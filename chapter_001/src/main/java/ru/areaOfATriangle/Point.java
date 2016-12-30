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
     * @param pointFirst
     * @param pointSecond
     * @return
     */
    public static double distanceTo(Point pointFirst, Point pointSecond ) {
        return Math.sqrt(Math.pow(pointSecond.x - pointFirst.x,2)+Math.pow(pointSecond.y - pointFirst.y,2));
    }
}
