package ru.yalymar.squaretask;

import org.junit.Test;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

public class ExistTest {

    @Test
    public void whenAddPointsShouldGetTrue1(){
        Exist.Point A = new Exist.Point(0, 0);
        Exist.Point B = new Exist.Point(0, 5);
        Exist.Point C = new Exist.Point(5, 5);
        Exist.Point D = new Exist.Point(5, 0);
        Exist exist = new Exist(A, B, C, D);
        boolean result = exist.exist();
        assertTrue(result);
    }

    @Test
    public void whenAddPointsShouldGetTrue2(){
        Exist.Point A = new Exist.Point(4, -2);
        Exist.Point B = new Exist.Point(-2, -2);
        Exist.Point C = new Exist.Point(4, 4);
        Exist.Point D = new Exist.Point(-2, 4);
        Exist exist = new Exist(A, B, C, D);
        boolean result = exist.exist();
        assertTrue(result);
    }

    @Test
    public void whenAddPointsShouldGetTrue3(){
        Exist.Point A = new Exist.Point(5, -3);
        Exist.Point B = new Exist.Point(0, 0);
        Exist.Point C = new Exist.Point(4, 1);
        Exist.Point D = new Exist.Point(1, -4);
        Exist exist = new Exist(A, B, C, D);
        boolean result = exist.exist();
        assertTrue(result);
    }

    @Test
    public void whenAddPointsShouldGetFalse1(){
        Exist.Point A = new Exist.Point(5, -3);
        Exist.Point B = new Exist.Point(0, 0);
        Exist.Point C = new Exist.Point(5, 1);
        Exist.Point D = new Exist.Point(1, -5);
        Exist exist = new Exist(A, B, C, D);
        boolean result = exist.exist();
        assertFalse(result);
    }
}