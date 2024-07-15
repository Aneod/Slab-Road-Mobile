package com.example.veritablejeu.Game.PlateauModulaire.ZdecimalCoordinates;

import static org.junit.Assert.*;

import com.example.veritablejeu.Game.PlateauModulaire.ZdecimalCoordinates.ZdecimalCharacter.ZdecimalCharacter;
import com.example.veritablejeu.Game.PlateauModulaire.ZdecimalCoordinates.ZdecimalCharacter.ZdecimalCharacterSequencer;

import org.junit.Test;

public class ZdecimalCharacterSequencerTest {

    @Test
    public void testCompareSimple() {
        ZdecimalCharacter charA = new ZdecimalCharacter('a');
        ZdecimalCharacter charB = new ZdecimalCharacter('b');
        assertEquals(1, ZdecimalCharacterSequencer.getRelativeGapBetween(charA, charB));
        assertEquals(-1, ZdecimalCharacterSequencer.getRelativeGapBetween(charB, charA));
    }

    @Test
    public void testCompareMax() {
        ZdecimalCharacter charA = new ZdecimalCharacter('0');
        ZdecimalCharacter charB = new ZdecimalCharacter('z');
        assertEquals(35, ZdecimalCharacterSequencer.getRelativeGapBetween(charA, charB));
        assertEquals(-35, ZdecimalCharacterSequencer.getRelativeGapBetween(charB, charA));
    }

    @Test
    public void testCompare_null() {
        ZdecimalCharacter charA = null;
        ZdecimalCharacter charB = new ZdecimalCharacter('z');
        assertEquals(0, ZdecimalCharacterSequencer.getRelativeGapBetween(charA, charB));
        assertEquals(0, ZdecimalCharacterSequencer.getRelativeGapBetween(charB, charA));
    }

    @Test
    public void testCompare_characterNull() {
        ZdecimalCharacter charA = new ZdecimalCharacter('\0');
        ZdecimalCharacter charB = new ZdecimalCharacter('z');
        assertEquals(35, ZdecimalCharacterSequencer.getRelativeGapBetween(charA, charB));
    }

    @Test
    public void testisLowerTo() {
        ZdecimalCharacter charA = new ZdecimalCharacter('a');
        ZdecimalCharacter charB = new ZdecimalCharacter('b');
        assertTrue(ZdecimalCharacterSequencer.isLowerTo(charA, charB));
        assertFalse(ZdecimalCharacterSequencer.isLowerTo(charB, charA));
        assertFalse(ZdecimalCharacterSequencer.isLowerTo(charA, charA));
    }

    @Test
    public void testIsLowerEqualThan() {
        ZdecimalCharacter charA = new ZdecimalCharacter('a');
        ZdecimalCharacter charB = new ZdecimalCharacter('b');
        assertTrue(ZdecimalCharacterSequencer.isLowerEqualTo(charA, charB));
        assertFalse(ZdecimalCharacterSequencer.isLowerEqualTo(charB, charA));
        assertTrue(ZdecimalCharacterSequencer.isLowerEqualTo(charA, charA));
    }

    @Test
    public void testIsEqualTo() {
        ZdecimalCharacter charA = new ZdecimalCharacter('a');
        ZdecimalCharacter charB = new ZdecimalCharacter('b');
        assertFalse(ZdecimalCharacterSequencer.isEqualTo(charA, charB));
        assertFalse(ZdecimalCharacterSequencer.isEqualTo(charB, charA));
        assertTrue(ZdecimalCharacterSequencer.isEqualTo(charA, charA));
    }

    @Test
    public void testIsHigherEqualThan() {
        ZdecimalCharacter charA = new ZdecimalCharacter('a');
        ZdecimalCharacter charB = new ZdecimalCharacter('b');
        assertFalse(ZdecimalCharacterSequencer.isHigherEqualTo(charA, charB));
        assertTrue(ZdecimalCharacterSequencer.isHigherEqualTo(charB, charA));
        assertTrue(ZdecimalCharacterSequencer.isHigherEqualTo(charA, charA));
    }

    @Test
    public void testisHigherTo() {
        ZdecimalCharacter charA = new ZdecimalCharacter('a');
        ZdecimalCharacter charB = new ZdecimalCharacter('b');
        assertFalse(ZdecimalCharacterSequencer.isHigherTo(charA, charB));
        assertTrue(ZdecimalCharacterSequencer.isHigherTo(charB, charA));
        assertFalse(ZdecimalCharacterSequencer.isHigherTo(charA, charA));
    }

    @Test
    public void testNullFrom() {
        ZdecimalCharacter charA = new ZdecimalCharacter('a');
        assertFalse(ZdecimalCharacterSequencer.isLowerTo(null, charA));
    }

    @Test
    public void testNullTo() {
        ZdecimalCharacter charA = new ZdecimalCharacter('a');
        assertFalse(ZdecimalCharacterSequencer.isLowerTo(charA, null));
    }

    @Test
    public void testNextChar_nullCharacter() {
        ZdecimalCharacter zChar = new ZdecimalCharacter('\0');
        ZdecimalCharacter expected = new ZdecimalCharacter('1');
        ZdecimalCharacter actual = ZdecimalCharacterSequencer.getNextChar(zChar);
        assertEquals(expected, actual);
    }

    @Test
    public void testNextChar_nullZdecimalCharacter() {
        ZdecimalCharacter zChar = null;
        ZdecimalCharacter expected = new ZdecimalCharacter(ZdecimalCharacter.getDefaultChar());
        ZdecimalCharacter actual = ZdecimalCharacterSequencer.getNextChar(zChar);
        assertEquals(expected, actual);
    }

    @Test
    public void testNextChar_lowerBound() {
        ZdecimalCharacter zChar = new ZdecimalCharacter('0');
        ZdecimalCharacter expected = new ZdecimalCharacter('1');
        ZdecimalCharacter actual = ZdecimalCharacterSequencer.getNextChar(zChar);
        assertEquals(expected, actual);
    }

    @Test
    public void testNextChar_upperBound() {
        ZdecimalCharacter zChar = new ZdecimalCharacter('y');
        ZdecimalCharacter expected = new ZdecimalCharacter('z');
        ZdecimalCharacter actual = ZdecimalCharacterSequencer.getNextChar(zChar);
        assertEquals(expected, actual);
    }

    @Test
    public void testNextChar_intCharToLetterChar() {
        ZdecimalCharacter zChar = new ZdecimalCharacter('9');
        ZdecimalCharacter expected = new ZdecimalCharacter('a');
        ZdecimalCharacter actual = ZdecimalCharacterSequencer.getNextChar(zChar);
        assertEquals(expected, actual);
    }

    @Test
    public void testNextChar_common() {
        ZdecimalCharacter zChar = new ZdecimalCharacter('r');
        ZdecimalCharacter expected = new ZdecimalCharacter('s');
        ZdecimalCharacter actual = ZdecimalCharacterSequencer.getNextChar(zChar);
        assertEquals(expected, actual);
    }

    @Test
    public void testNextChar_common_impossibleResults() {
        ZdecimalCharacter zChar = new ZdecimalCharacter('r');
        ZdecimalCharacter expected = new ZdecimalCharacter('q');
        ZdecimalCharacter actual = ZdecimalCharacterSequencer.getNextChar(zChar);
        assertNotEquals(expected, actual);
    }

    @Test
    public void testNextChar_outOfBound() {
        ZdecimalCharacter zChar = new ZdecimalCharacter('z');
        ZdecimalCharacter expected = new ZdecimalCharacter(ZdecimalCharacter.getDefaultChar());
        ZdecimalCharacter actual = ZdecimalCharacterSequencer.getNextChar(zChar);
        assertEquals(expected, actual);
    }

    @Test
    public void testNextChar_byInt() {
        ZdecimalCharacter zChar = new ZdecimalCharacter(10);
        ZdecimalCharacter expected = new ZdecimalCharacter('b');
        ZdecimalCharacter actual = ZdecimalCharacterSequencer.getNextChar(zChar);
        assertEquals(expected, actual);
    }

    @Test
    public void testNextChar_byLowerBoundInt() {
        ZdecimalCharacter zChar = new ZdecimalCharacter(0);
        ZdecimalCharacter expected = new ZdecimalCharacter('1');
        ZdecimalCharacter actual = ZdecimalCharacterSequencer.getNextChar(zChar);
        assertEquals(expected, actual);
    }

    @Test
    public void testPreviousChar_nullCharacter() {
        ZdecimalCharacter zChar = new ZdecimalCharacter('\0');
        ZdecimalCharacter expected = new ZdecimalCharacter(ZdecimalCharacter.getDefaultChar());
        ZdecimalCharacter actual = ZdecimalCharacterSequencer.getPreviousChar(zChar);
        assertEquals(expected, actual);
    }

    @Test
    public void testPreviousChar_nullZdecimalCharacter() {
        ZdecimalCharacter zChar = null;
        ZdecimalCharacter expected = new ZdecimalCharacter(ZdecimalCharacter.getDefaultChar());
        ZdecimalCharacter actual = ZdecimalCharacterSequencer.getPreviousChar(zChar);
        assertEquals(expected, actual);
    }

    @Test
    public void testPreviousChar_lowerBound() {
        ZdecimalCharacter zChar = new ZdecimalCharacter('1');
        ZdecimalCharacter expected = new ZdecimalCharacter('0');
        ZdecimalCharacter actual = ZdecimalCharacterSequencer.getPreviousChar(zChar);
        assertEquals(expected, actual);
    }

    @Test
    public void testPreviousChar_upperBound() {
        ZdecimalCharacter zChar = new ZdecimalCharacter('z');
        ZdecimalCharacter expected = new ZdecimalCharacter('y');
        ZdecimalCharacter actual = ZdecimalCharacterSequencer.getPreviousChar(zChar);
        assertEquals(expected, actual);
    }

    @Test
    public void testPreviousChar_intCharToLetterChar() {
        ZdecimalCharacter zChar = new ZdecimalCharacter('a');
        ZdecimalCharacter expected = new ZdecimalCharacter('9');
        ZdecimalCharacter actual = ZdecimalCharacterSequencer.getPreviousChar(zChar);
        assertEquals(expected, actual);
    }

    @Test
    public void testPreviousChar_common() {
        ZdecimalCharacter zChar = new ZdecimalCharacter('s');
        ZdecimalCharacter expected = new ZdecimalCharacter('r');
        ZdecimalCharacter actual = ZdecimalCharacterSequencer.getPreviousChar(zChar);
        assertEquals(expected, actual);
    }

    @Test
    public void testPreviousChar_common_impossibleResults() {
        ZdecimalCharacter zChar = new ZdecimalCharacter('q');
        ZdecimalCharacter expected = new ZdecimalCharacter('r');
        ZdecimalCharacter actual = ZdecimalCharacterSequencer.getPreviousChar(zChar);
        assertNotEquals(expected, actual);
    }

    @Test
    public void testPreviousChar_outOfBound() {
        ZdecimalCharacter zChar = new ZdecimalCharacter('0');
        ZdecimalCharacter expected = new ZdecimalCharacter(ZdecimalCharacter.getDefaultChar());
        ZdecimalCharacter actual = ZdecimalCharacterSequencer.getPreviousChar(zChar);
        assertEquals(expected, actual);
    }

    @Test
    public void testPreviousChar_byInt() {
        ZdecimalCharacter zChar = new ZdecimalCharacter(10);
        ZdecimalCharacter expected = new ZdecimalCharacter('9');
        ZdecimalCharacter actual = ZdecimalCharacterSequencer.getPreviousChar(zChar);
        assertEquals(expected, actual);
    }

    @Test
    public void testPreviousChar_byLowerBoundInt() {
        ZdecimalCharacter zChar = new ZdecimalCharacter('z');
        ZdecimalCharacter expected = new ZdecimalCharacter('y');
        ZdecimalCharacter actual = ZdecimalCharacterSequencer.getPreviousChar(zChar);
        assertEquals(expected, actual);
    }

    @Test
    public void testPreviousNextChar() {
        for(int index = 1; index < 36; index++) {
            ZdecimalCharacter zChar = new ZdecimalCharacter(index);
            ZdecimalCharacter previousNext = ZdecimalCharacterSequencer.getNextChar(ZdecimalCharacterSequencer.getPreviousChar(zChar));
            assertEquals(zChar, previousNext);
        }
    }

    @Test
    public void testAbsoluteGapBetween_simple() {
        ZdecimalCharacter from = new ZdecimalCharacter('t');
        ZdecimalCharacter to = new ZdecimalCharacter('v');
        int gap = ZdecimalCharacterSequencer.getAbsoluteGapBetween(from, to);
        int expected = 2;
        assertEquals(expected, gap);
    }

    @Test
    public void testAbsoluteGapBetween_same() {
        ZdecimalCharacter from = new ZdecimalCharacter('t');
        int gap = ZdecimalCharacterSequencer.getAbsoluteGapBetween(from, from);
        int expected = 0;
        assertEquals(expected, gap);
    }

    @Test
    public void testAbsoluteGapBetween_maxIs35() {
        ZdecimalCharacter from = new ZdecimalCharacter('0');
        ZdecimalCharacter to = new ZdecimalCharacter('z');
        int gap = ZdecimalCharacterSequencer.getAbsoluteGapBetween(from, to);
        int expected = 35;
        assertEquals(expected, gap);
    }

    @Test
    public void testAbsoluteGapBetween_simple_butToFrom() {
        ZdecimalCharacter from = new ZdecimalCharacter('t');
        ZdecimalCharacter to = new ZdecimalCharacter('v');
        int gap = ZdecimalCharacterSequencer.getAbsoluteGapBetween(to, from);
        int expected = 2;
        assertEquals(expected, gap);
    }

    @Test
    public void testAbsoluteGapBetween_widthInt() {
        ZdecimalCharacter from = new ZdecimalCharacter(1);
        ZdecimalCharacter to = new ZdecimalCharacter(5);
        int gap = ZdecimalCharacterSequencer.getAbsoluteGapBetween(from, to);
        int expected = 4;
        assertEquals(expected, gap);
    }

    @Test
    public void testAbsoluteGapBetween_null() {
        ZdecimalCharacter from = null;
        ZdecimalCharacter to = new ZdecimalCharacter(5);
        int gap = ZdecimalCharacterSequencer.getAbsoluteGapBetween(from, to);
        int expected = 0;
        assertEquals(expected, gap);
    }

    @Test
    public void testAbsoluteGapBetween_null_null() {
        ZdecimalCharacter from = null;
        ZdecimalCharacter to = null;
        int gap = ZdecimalCharacterSequencer.getAbsoluteGapBetween(from, to);
        int expected = 0;
        assertEquals(expected, gap);
    }

    @Test
    public void testAbsoluteGapBetween_uncorrect() {
        ZdecimalCharacter from = new ZdecimalCharacter('0');
        ZdecimalCharacter to = new ZdecimalCharacter(36);
        int gap = ZdecimalCharacterSequencer.getAbsoluteGapBetween(from, to);
        int expected = 0;
        assertEquals(expected, gap);
    }

    @Test
    public void testIsMinChar() {
        ZdecimalCharacter minChar = new ZdecimalCharacter(ZdecimalCharacter.getMinValidChar());
        assertTrue(ZdecimalCharacterSequencer.isMinChar(minChar));

        ZdecimalCharacter notMinChar = new ZdecimalCharacter('1');
        assertFalse(ZdecimalCharacterSequencer.isMinChar(notMinChar));

        assertFalse(ZdecimalCharacterSequencer.isMinChar(null));
    }

    @Test
    public void testIsMaxChar() {
        ZdecimalCharacter maxChar = new ZdecimalCharacter(ZdecimalCharacter.getMaxValidChar());
        assertTrue(ZdecimalCharacterSequencer.isMaxChar(maxChar));

        ZdecimalCharacter notMaxChar = new ZdecimalCharacter('1');
        assertFalse(ZdecimalCharacterSequencer.isMaxChar(notMaxChar));

        assertFalse(ZdecimalCharacterSequencer.isMaxChar(null));
    }
}