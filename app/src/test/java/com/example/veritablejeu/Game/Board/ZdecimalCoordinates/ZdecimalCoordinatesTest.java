package com.example.veritablejeu.Game.Board.ZdecimalCoordinates;

import static org.junit.Assert.*;

import com.example.veritablejeu.Game.Board.ZdecimalCoordinates.ZdecimalCharacter.ZdecimalCharacter;

import org.junit.Test;

public class ZdecimalCoordinatesTest {

    @Test
    public void testValidZdecimalCharacterConstruction() {
        ZdecimalCharacter validX = new ZdecimalCharacter('1');
        ZdecimalCharacter validY = new ZdecimalCharacter('a');
        ZdecimalCoordinates coordinates = new ZdecimalCoordinates(validX, validY);
        assertEquals(validX, coordinates.getX());
        assertEquals(validY, coordinates.getY());
    }

    @Test
    public void testInvalidZdecimalCharacterConstruction() {
        ZdecimalCharacter invalidX = new ZdecimalCharacter('%');
        ZdecimalCharacter invalidY = new ZdecimalCharacter('!');
        ZdecimalCoordinates coordinates = new ZdecimalCoordinates(invalidX, invalidY);
        assertEquals(ZdecimalCoordinates.getDefaultChar(), coordinates.getX());
        assertEquals(ZdecimalCoordinates.getDefaultChar(), coordinates.getY());
    }

    @Test
    public void testNullZdecimalCharacterConstruction() {
        ZdecimalCoordinates coordinates = new ZdecimalCoordinates(null, null);
        assertEquals(ZdecimalCoordinates.getDefaultChar(), coordinates.getX());
        assertEquals(ZdecimalCoordinates.getDefaultChar(), coordinates.getY());
    }

    // Test ZdecimalCoordinates with char

    @Test
    public void testValidCharConstruction() {
        ZdecimalCoordinates coordinates = new ZdecimalCoordinates('1', 'a');
        assertEquals('1', coordinates.getX().getCharacter());
        assertEquals('a', coordinates.getY().getCharacter());
    }

    @Test
    public void testInvalidCharConstruction() {
        ZdecimalCoordinates coordinates = new ZdecimalCoordinates('%', '!');
        assertEquals(ZdecimalCoordinates.getDefaultChar(), coordinates.getX());
        assertEquals(ZdecimalCoordinates.getDefaultChar(), coordinates.getY());
    }

    // Test ZdecimalCoordinates with int

    @Test
    public void testValidIntConstruction() {
        ZdecimalCoordinates coordinates = new ZdecimalCoordinates(4, 27);
        assertEquals('4', coordinates.getX().getCharacter());
        assertEquals('r', coordinates.getY().getCharacter());
    }

    @Test
    public void testInvalidIntConstruction() {
        ZdecimalCoordinates coordinates = new ZdecimalCoordinates(-36, 36);
        assertEquals(ZdecimalCoordinates.getDefaultChar(), coordinates.getX());
        assertEquals(ZdecimalCoordinates.getDefaultChar(), coordinates.getY());
    }

    // Test getX() and getY()

    @Test
    public void testGetX() {
        ZdecimalCoordinates coordinates = new ZdecimalCoordinates('1', 'a');
        assertEquals('1', coordinates.getX().getCharacter());
    }

    @Test
    public void testGetY() {
        ZdecimalCoordinates coordinates = new ZdecimalCoordinates('1', 'a');
        assertEquals('a', coordinates.getY().getCharacter());
    }

    // Test toString()

    @Test
    public void testToString() {
        ZdecimalCoordinates coordinates = new ZdecimalCoordinates('1', 'a');
        assertEquals("ZdecimalCoordinates{x=1, y=a}", coordinates.toString());
    }

    // Test equals()

    @Test
    public void testEqualsSameObject() {
        ZdecimalCoordinates coordinates = new ZdecimalCoordinates('1', 'a');
        assertEquals(coordinates, coordinates);
    }

    @Test
    public void testEqualsSameValues() {
        ZdecimalCoordinates coordinates1 = new ZdecimalCoordinates('1', 'a');
        ZdecimalCoordinates coordinates2 = new ZdecimalCoordinates('1', 'a');
        assertEquals(coordinates1, coordinates2);
    }

    @Test
    public void testNotEqualsDifferentType() {
        ZdecimalCoordinates coordinates = new ZdecimalCoordinates('1', 'a');
        assertNotEquals("ZdecimalCoordinates", coordinates);
    }

    @Test
    public void testNotEqualsDifferentValues() {
        ZdecimalCoordinates coordinates1 = new ZdecimalCoordinates('1', 'a');
        ZdecimalCoordinates coordinates2 = new ZdecimalCoordinates('2', 'b');
        assertNotEquals(coordinates1, coordinates2);
    }

    @Test
    public void areSame_intermediateValues() {
        ZdecimalCoordinates first = new ZdecimalCoordinates('d', 't');
        ZdecimalCoordinates second = new ZdecimalCoordinates('d', 't');
        boolean result = first.equals(second);
        assertTrue(result);
    }

    @Test
    public void areSame_with_one_upperCase() {
        ZdecimalCoordinates first = new ZdecimalCoordinates('d', 't');
        ZdecimalCoordinates second = new ZdecimalCoordinates('d', 'T');
        boolean result = first.equals(second);
        assertTrue(result);
    }

    @Test
    public void areSame_with_two_upperCases() {
        ZdecimalCoordinates first = new ZdecimalCoordinates('d', 't');
        ZdecimalCoordinates second = new ZdecimalCoordinates('D', 'T');
        boolean result = first.equals(second);
        assertTrue(result);
    }

    @Test
    public void areSame_false() {
        ZdecimalCoordinates first = new ZdecimalCoordinates('d', 'u');
        ZdecimalCoordinates second = new ZdecimalCoordinates('d', 't');
        boolean result = first.equals(second);
        assertFalse(result);
    }

    @Test
    public void areSame_reverse() {
        ZdecimalCoordinates first = new ZdecimalCoordinates('t', 'd');
        ZdecimalCoordinates second = new ZdecimalCoordinates('d', 't');
        boolean result = first.equals(second);
        assertFalse(result);
    }

    @Test
    public void areSame_workWhateverIsVerified() {
        ZdecimalCoordinates first = new ZdecimalCoordinates('@', '-');
        ZdecimalCoordinates second = new ZdecimalCoordinates('@', '-');
        boolean result = first.equals(second);
        assertTrue(result);
    }

    @Test
    public void areSame_lowerBound() {
        ZdecimalCoordinates first = new ZdecimalCoordinates('0', '0');
        ZdecimalCoordinates second = new ZdecimalCoordinates('0', '0');
        boolean result = first.equals(second);
        assertTrue(result);
    }

    @Test
    public void areSame_upperBound() {
        ZdecimalCoordinates first = new ZdecimalCoordinates('z', 'z');
        ZdecimalCoordinates second = new ZdecimalCoordinates('z', 'z');
        boolean result = first.equals(second);
        assertTrue(result);
    }

    @Test
    public void areSame_nullTo() {
        ZdecimalCoordinates first = new ZdecimalCoordinates('z', 'z');
        ZdecimalCoordinates second = null;
        assertFalse(first.equals(second));
    }

    // Test hashCode()

    @Test
    public void testHashCodeConsistency() {
        ZdecimalCoordinates coordinates1 = new ZdecimalCoordinates('1', 'a');
        ZdecimalCoordinates coordinates2 = new ZdecimalCoordinates('1', 'a');
        assertEquals(coordinates1.hashCode(), coordinates2.hashCode());
    }

    @Test
    public void testHashCodeNonZero() {
        ZdecimalCoordinates coordinates = new ZdecimalCoordinates('1', 'a');
        assertNotEquals(0, coordinates.hashCode());
    }
}