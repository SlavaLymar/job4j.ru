package ru.areaOfATriangle;

/**
 * @author slavalymar
 * @since 30.12.2016
 * @version 1
 */

public class Triangle {
    public Point a;
    public Point b;
    public Point c;

    /**
     * @param a
     * @param b
     * @param c
     */
    public Triangle(Point a, Point b, Point c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    /**
     * @return area
     */
    public double area() {
        double perimeter = Point.distanceTo(a,b) + Point.distanceTo(a,c) + Point.distanceTo(b,c);
        return Math.sqrt(perimeter*(perimeter-Point.distanceTo(a,b))*(perimeter-Point.distanceTo(a,c))*(perimeter-Point.distanceTo(b,c)));
    }
}
