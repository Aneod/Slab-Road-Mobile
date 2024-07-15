package com.example.veritablejeu.Game.PlateauModulaire.ZdecimalCoordinates;

import static org.junit.Assert.assertEquals;

import com.example.veritablejeu.Game.PlateauModulaire.ZdecimalCoordinates.ZdecimalCharacter.ZdecimalCharacter;
import com.example.veritablejeu.Game.PlateauModulaire.ZdecimalCoordinates.ZdecimalCharacter.ZdecimalCharacterConverter;

import org.junit.Test;

public class ZdecimalCharacterConverterTest {

    @Test
    public void testIntToCharConversion() {
        // Test conversion de 0 à 9
        for (int i = 0; i < 10; i++) {
            char expectedChar = (char) ('0' + i);
            assertEquals(expectedChar, ZdecimalCharacterConverter.intDecimal_to_charBase36(i));
        }

        // Test conversion de 10 à 35
        for (int i = 10; i < 36; i++) {
            char expectedChar = (char) ('a' + (i - 10));
            assertEquals(expectedChar, ZdecimalCharacterConverter.intDecimal_to_charBase36(i));
        }
    }

    @Test
    public void testCharToIntConversion() {
        // Test conversion de '0' à '9'
        for (char c = '0'; c <= '9'; c++) {
            int expectedInt = c - '0';
            assertEquals(expectedInt, ZdecimalCharacterConverter.charBase36_to_intDecimal(c));
        }

        // Test conversion de 'a' à 'z'
        for (char c = 'a'; c <= 'z'; c++) {
            int expectedInt = c - 'a' + 10;
            assertEquals(expectedInt, ZdecimalCharacterConverter.charBase36_to_intDecimal(c));
        }
    }

    @Test
    public void testUpperCaseCharToIntConversion() {
        // Test conversion de 'A' à 'Z'
        for (char c = 'A'; c <= 'Z'; c++) {
            assertEquals(ZdecimalCharacterConverter.getDefaultInt(), ZdecimalCharacterConverter.charBase36_to_intDecimal(c));
        }
    }

    @Test
    public void testIntToCharOutOfRange() {
        assertEquals(ZdecimalCharacterConverter.getDefaultChar(), ZdecimalCharacterConverter.intDecimal_to_charBase36(36));
    }

    @Test
    public void testIntToCharNegativeValue() {
        assertEquals(ZdecimalCharacterConverter.getDefaultChar(), ZdecimalCharacterConverter.intDecimal_to_charBase36(-1));
    }

    @Test
    public void testCharToIntInvalidChar() {
        assertEquals(ZdecimalCharacterConverter.getDefaultInt(), ZdecimalCharacterConverter.charBase36_to_intDecimal('@'));
    }

    @Test
    public void testZdecimalCharacterToIntConversion() {
        // Test conversion de ZdecimalCharacter de '0' à '9'
        for (char c = '0'; c <= '9'; c++) {
            ZdecimalCharacter zdecimalCharacter = new ZdecimalCharacter(c);
            int expectedInt = c - '0';
            assertEquals(expectedInt, ZdecimalCharacterConverter.zdecimalCharacter_to_intDecimal(zdecimalCharacter));
        }

        // Test conversion de 'a' à 'z'
        for (char c = 'a'; c <= 'z'; c++) {
            ZdecimalCharacter zdecimalCharacter = new ZdecimalCharacter(c);
            int expectedInt = c - 'a' + 10;
            assertEquals(expectedInt, ZdecimalCharacterConverter.zdecimalCharacter_to_intDecimal(zdecimalCharacter));
        }
    }

    @Test
    public void testZdecimalCharacterToIntConversion_null() {
        assertEquals(-1, ZdecimalCharacterConverter.zdecimalCharacter_to_intDecimal(null));
    }
}