package com.slabroad.veritablejeu.Tools;

import static org.junit.Assert.*;

import org.junit.Test;

public class LongToReadableTimeTest {

    @Test
    public void testNegativeNumber() {
        long elapsedTime = -1_000_000_000L;
        String expected = "-1s 000ms";
        String actual = LongToReadableTime.getReadable(elapsedTime);
        assertEquals(expected, actual);
    }

    @Test
    public void testZeroMilliseconds() {
        long elapsedTime = 0;
        String expected = "000ms";
        String actual = LongToReadableTime.getReadable(elapsedTime);
        assertEquals(expected, actual);
    }

    @Test
    public void testMillisecondsOnly() {
        long elapsedTime = 999_000_000L; // 999 milliseconds
        String expected = "999ms";
        String actual = LongToReadableTime.getReadable(elapsedTime);
        assertEquals(expected, actual);
    }

    @Test
    public void testSecondsAndMilliseconds() {
        long elapsedTime = 12_345_678_000L; // 12 seconds and 345 milliseconds
        String expected = "12s 345ms";
        String actual = LongToReadableTime.getReadable(elapsedTime);
        assertEquals(expected, actual);
    }

    @Test
    public void testMinutesSecondsAndMilliseconds() {
        long elapsedTime = 192_345_789_000L; // 3 minutes, 12 seconds and 345 milliseconds
        String expected = "03m 12s 345ms";
        String actual = LongToReadableTime.getReadable(elapsedTime);
        assertEquals(expected, actual);
    }

    @Test
    public void testHoursMinutesSecondsAndMilliseconds() {
        long elapsedTime = 4_343_678_000_000L; // 1 hour, 12 minutes, 23 seconds and 456 milliseconds
        String expected = "01h 12m 23s 678ms";
        String actual = LongToReadableTime.getReadable(elapsedTime);
        assertEquals(expected, actual);
    }

    @Test
    public void testOnlyMinutesAndSeconds() {
        long elapsedTime = 2_000_000_000L; // 2 seconds
        String expected = "02s 000ms";
        String actual = LongToReadableTime.getReadable(elapsedTime);
        assertEquals(expected, actual);
    }

    @Test
    public void testFullHour() {
        long elapsedTime = 3_600_000_000_000L; // 1 hour
        String expected = "01h 00m 00s 000ms";
        String actual = LongToReadableTime.getReadable(elapsedTime);
        assertEquals(expected, actual);
    }

    @Test
    public void testAlmostAnHour() {
        long elapsedTime = 3_599_999_999_000L; // 59 minutes 59 seconds 999 milliseconds
        String expected = "59m 59s 999ms";
        String actual = LongToReadableTime.getReadable(elapsedTime);
        assertEquals(expected, actual);
    }

    @Test
    public void testMultipleHours() {
        long elapsedTime = 7_200_000_000_000L; // 2 hours
        String expected = "02h 00m 00s 000ms";
        String actual = LongToReadableTime.getReadable(elapsedTime);
        assertEquals(expected, actual);
    }
}