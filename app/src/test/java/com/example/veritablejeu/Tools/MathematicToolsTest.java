package com.example.veritablejeu.Tools;

import static org.junit.Assert.*;

import android.graphics.Point;

import org.junit.Test;

public class MathematicToolsTest {

    @Test
    public void testRandomOpen() {
        int min = 1;
        int max = 10;
        for (int i = 0; i < 100; i++) {
            int result = MathematicTools.random_open(min, max);
            assertTrue(result >= min && result <= max);
        }
    }

    @Test
    public void testRandomFloatHalfOpen() {
        float min = 1.0f;
        float max = 10.0f;
        for (int i = 0; i < 100; i++) {
            float result = MathematicTools.random_float_halfOpen(min, max);
            assertTrue(result >= min && result < max);
        }
    }

    @Test
    public void testLongToIntWithinRange() {
        long value = 123456789L;
        int expected = 123456789;
        int result = MathematicTools.long_to_int(value);
        assertEquals(expected, result);
    }

    @Test
    public void testLongToIntBelowMinInt() {
        long value = (long) Integer.MIN_VALUE - 1;
        int expected = Integer.MIN_VALUE;
        int result = MathematicTools.long_to_int(value);
        assertEquals(expected, result);
    }

    @Test
    public void testLongToIntAboveMaxInt() {
        long value = (long) Integer.MAX_VALUE + 1;
        int expected = Integer.MAX_VALUE;
        int result = MathematicTools.long_to_int(value);
        assertEquals(expected, result);
    }

    @Test
    public void testGetDeltaX() {
        Point from = new Point(1, 1);
        Point to = new Point(4, 1);
        int expected = 3;
        int result = MathematicTools.getDeltaX(from, to);
        assertEquals(expected, result);
    }

    @Test
    public void testGetDeltaXWithNull() {
        Point from = new Point(1, 1);
        int result = MathematicTools.getDeltaX(from, null);
        assertEquals(0, result);
    }

    @Test
    public void testGetDeltaY() {
        Point from = new Point(1, 1);
        Point to = new Point(1, 4);
        int expected = 3;
        int result = MathematicTools.getDeltaY(from, to);
        assertEquals(expected, result);
    }

    @Test
    public void testGetDeltaYWithNull() {
        Point from = new Point(1, 1);
        int result = MathematicTools.getDeltaY(from, null);
        assertEquals(0, result);
    }

    @Test
    public void testGetDistance() {
        Point from = new Point(1, 1);
        Point to = new Point(4, 5);
        int expected = 5; // sqrt(3^2 + 4^2) = 5
        int result = MathematicTools.getDistance(from, to);
        assertEquals(expected, result);
    }

    @Test
    public void testGetDistanceWithNull() {
        Point from = new Point(1, 1);
        int result = MathematicTools.getDistance(from, null);
        assertEquals(0, result);
    }

    @Test
    public void testGetAngle() {
        Point from = new Point(0, 0);
        Point to = new Point(1, 1);
        double expected = 45.0;
        double result = MathematicTools.getAngle(from, to);
        assertEquals(expected, result, 0.0001);
    }

    @Test
    public void testGetAngleWithNull() {
        Point from = new Point(0, 0);
        double result = MathematicTools.getAngle(from, null);
        assertEquals(0.0, result, 0.0001);
    }

    @Test
    public void testGetAngleNegative() {
        Point from = new Point(0, 0);
        Point to = new Point(-1, -1);
        double expected = 225.0;
        double result = MathematicTools.getAngle(from, to);
        assertEquals(expected, result, 0.0001);
    }
}