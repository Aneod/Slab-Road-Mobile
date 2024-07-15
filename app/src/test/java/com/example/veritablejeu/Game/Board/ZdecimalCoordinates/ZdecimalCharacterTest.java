package com.example.veritablejeu.Game.Board.ZdecimalCoordinates;

import static org.junit.Assert.*;

import com.example.veritablejeu.Game.Board.ZdecimalCoordinates.ZdecimalCharacter.ZdecimalCharacter;

import org.junit.Test;

public class ZdecimalCharacterTest {

    @Test
    public void testValidDigit() {
        ZdecimalCharacter zChar = new ZdecimalCharacter('5');
        assertEquals('5', zChar.getCharacter());
    }

    @Test
    public void testValidLowerCaseLetter() {
        ZdecimalCharacter zChar = new ZdecimalCharacter('a');
        assertEquals('a', zChar.getCharacter());
    }

    @Test
    public void testValidUpperCaseLetter() {
        ZdecimalCharacter zChar = new ZdecimalCharacter('A');
        assertEquals('a', zChar.getCharacter());
    }

    @Test
    public void testValidUpperBound() {
        ZdecimalCharacter zChar = new ZdecimalCharacter('z');
        assertEquals('z', zChar.getCharacter());
    }

    @Test
    public void testValidLowerBound() {
        ZdecimalCharacter zChar = new ZdecimalCharacter('0');
        assertEquals('0', zChar.getCharacter());
    }

    @Test
    public void testInvalidCharacter() {
        ZdecimalCharacter zChar = new ZdecimalCharacter('%');
        assertEquals(ZdecimalCharacter.getDefaultChar(), zChar.getCharacter());
    }

    @Test
    public void testDefaultCharForNullInput() {
        ZdecimalCharacter zChar = new ZdecimalCharacter('\0');
        assertEquals(ZdecimalCharacter.getDefaultChar(), zChar.getCharacter());
    }

    @Test
    public void testOtherInvalidCharacters() {
        ZdecimalCharacter zChar1 = new ZdecimalCharacter('!');
        ZdecimalCharacter zChar2 = new ZdecimalCharacter('@');
        ZdecimalCharacter zChar3 = new ZdecimalCharacter('[');
        ZdecimalCharacter zChar4 = new ZdecimalCharacter('`');
        ZdecimalCharacter zChar5 = new ZdecimalCharacter('~');
        assertEquals(ZdecimalCharacter.getDefaultChar(), zChar1.getCharacter());
        assertEquals(ZdecimalCharacter.getDefaultChar(), zChar2.getCharacter());
        assertEquals(ZdecimalCharacter.getDefaultChar(), zChar3.getCharacter());
        assertEquals(ZdecimalCharacter.getDefaultChar(), zChar4.getCharacter());
        assertEquals(ZdecimalCharacter.getDefaultChar(), zChar5.getCharacter());
    }

    // Test toString()

    @Test
    public void testToString() {
        ZdecimalCharacter zChar = new ZdecimalCharacter('a');
        assertEquals("ZdecimalCharacter:a", zChar.toString());
    }

    // Test equals()

    @Test
    public void testEqualsSameObject() {
        ZdecimalCharacter zChar1 = new ZdecimalCharacter('a');
        assertTrue(zChar1.equals(zChar1));
    }

    @Test
    public void testEqualsSameCharacter() {
        ZdecimalCharacter zChar1 = new ZdecimalCharacter('a');
        ZdecimalCharacter zChar2 = new ZdecimalCharacter('a');
        assertTrue(zChar1.equals(zChar2));
    }

    @Test
    public void testEqualsDifferentCharacter() {
        ZdecimalCharacter zChar1 = new ZdecimalCharacter('a');
        ZdecimalCharacter zChar2 = new ZdecimalCharacter('b');
        assertFalse(zChar1.equals(zChar2));
    }

    @Test
    public void testEqualsWithDifferentObject() {
        ZdecimalCharacter zChar = new ZdecimalCharacter('a');
        assertFalse(zChar.equals(new Object()));
    }

    // Test hashCode()

    @Test
    public void testHashCodeConsistency() {
        ZdecimalCharacter zChar = new ZdecimalCharacter('a');
        int hashCode1 = zChar.hashCode();
        int hashCode2 = zChar.hashCode();
        assertEquals(hashCode1, hashCode2);
    }

    @Test
    public void testHashCodeEquality() {
        ZdecimalCharacter zChar1 = new ZdecimalCharacter('a');
        ZdecimalCharacter zChar2 = new ZdecimalCharacter('a');
        assertEquals(zChar1.hashCode(), zChar2.hashCode());
    }

    @Test
    public void testValidInt() {
        ZdecimalCharacter zChar = new ZdecimalCharacter(5);
        assertEquals('5', zChar.getCharacter());
    }

    @Test
    public void testValidIntHigherThan9() {
        ZdecimalCharacter zChar = new ZdecimalCharacter(23);
        assertEquals('n', zChar.getCharacter());
    }

    @Test
    public void testIntLowerBound() {
        ZdecimalCharacter zChar = new ZdecimalCharacter(0);
        assertEquals('0', zChar.getCharacter());
    }

    @Test
    public void testIntUpperBound() {
        ZdecimalCharacter zChar = new ZdecimalCharacter(35);
        assertEquals('z', zChar.getCharacter());
    }

    @Test
    public void testIntOutOfBound() {
        ZdecimalCharacter zChar = new ZdecimalCharacter(36);
        assertEquals('0', zChar.getCharacter());
    }

    @Test
    public void testIntBigOutOfBound() {
        ZdecimalCharacter zChar = new ZdecimalCharacter(2698);
        assertEquals('0', zChar.getCharacter());
    }

    @Test
    public void testIntInfiniteOutOfBound() {
        ZdecimalCharacter zChar = new ZdecimalCharacter(Integer.MAX_VALUE);
        assertEquals('0', zChar.getCharacter());
    }

    @Test
    public void testNegativeInt() {
        ZdecimalCharacter zChar = new ZdecimalCharacter(-1);
        assertEquals('0', zChar.getCharacter());
    }

    @Test
    public void testNegativeIntHigherThan9() {
        ZdecimalCharacter zChar = new ZdecimalCharacter(-25);
        assertEquals('0', zChar.getCharacter());
    }

}