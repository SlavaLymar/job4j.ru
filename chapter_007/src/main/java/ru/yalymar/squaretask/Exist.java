package ru.yalymar.squaretask;

import java.lang.Math;
import java.util.Arrays;

/**
 * @author slavalymar
 * @since 19.04.2017
 * @version 1
 */
public class Exist {

    // four points
    private final Point A;
    private final Point B;
    private final Point C;
    private final Point D;

    public Exist(final Point a, final Point b, final Point c, final Point d) {
        A = a;
        B = b;
        C = c;
        D = d;
    }

    /** return true if it is a square
     * @return boolean
     */
    public boolean exist(){
        boolean result = false;

        // calculate three segment from a point
        int sqS1 = (int) ((Math.pow((B.getX()-A.getX()), 2)) + (Math.pow((B.getY()-A.getY()), 2)));
        int sqS2 = (int) ((Math.pow((D.getX()-A.getX()), 2)) + (Math.pow((D.getY()-A.getY()), 2)));
        int sqS3 = (int) ((Math.pow((C.getX()-A.getX()), 2)) + (Math.pow((C.getY()-A.getY()), 2)));

        // if couple segments` length are equal then these are the cathetuses
        if(sqS1 == sqS2){
           result = this.compareHypotenuse(sqS1, sqS2, sqS3, B, D);
           if(result){
               this.getOrderedPoints();
               return result;
           }
           else {
               return result;
           }
        }
        if(sqS2 == sqS3){
            result = this.compareHypotenuse(sqS2, sqS3, sqS1, D, C);
            if(result){
                this.getOrderedPoints();
                return result;
            }
            else {
                return result;
            }
        }
        if(sqS1 == sqS3){
            result = this.compareHypotenuse(sqS1, sqS3, sqS2, B, C);
            if(result){
                this.getOrderedPoints();
                return result;
            }
            else {
                return result;
            }
        }
        return result;
    }

    /** return true if (Hypotenuse equal diagonal) && equal another diagonal
     * @param sqCathetus1
     * @param sqCathetus2
     * @param sqHypotenuse
     * @param X
     * @param Y
     * @return boolean
     */
    private boolean compareHypotenuse(int sqCathetus1, int sqCathetus2, int sqHypotenuse,
                                      Point X, Point Y) {
        int sqH = sqCathetus1 + sqCathetus2;
        int sqHDiagonal = (int) ((Math.pow((X.getX()-Y.getX()), 2)) + (Math.pow((X.getY()-Y.getY()), 2)));
        return sqHypotenuse == sqH && sqH == sqHDiagonal;
    }

    /** return points in order
     * @return Point[]
     */
    public Point[] getOrderedPoints() {
        Point[] points = new Point[4];
        points[0] = A;
        points[1] = B;
        points[2] = C;
        points[3] = D;
        Arrays.sort(points);
        Point tmp = points[3];
        points[3] = points[2];
        points[2] = tmp;
        int i = 1;
        for(Point p: points){
            System.out.println(String.format("Point %d = ( %d, %d )", i++, p.getX(), p.getY()));
        }
        return points;
    }

    // class that describe point
    public static class Point implements Comparable<Point>{
        private final int X;
        private final int Y;

        public Point(final int x, final int y) {
            X = x;
            Y = y;
        }

        @Override
        public int compareTo(Point o) {
            if(this.hashCode() > o.hashCode()){
                return 1;
            }
            if(this.hashCode() == o.hashCode()){
                return 0;
            }
            return -1;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Point point = (Point) o;

            if (X != point.X) return false;
            return Y == point.Y;
        }

        @Override
        public int hashCode() {
            int result = X;
            result = 31 * result + Y;
            return result;
        }

        public int getX() {
            return X;
        }

        public int getY() {
            return Y;
        }
    }

}
