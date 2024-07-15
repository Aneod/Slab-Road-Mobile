package com.example.veritablejeu.Game.PlateauModulaire.ZdecimalCoordinates.ZdecimalCharacter;

import androidx.annotation.NonNull;

import java.util.Objects;

public class ZdecimalCharacter {
    private static final char DEFAULT_CHAR = '0';
    private static final char MIN_VALID_CHAR = '0';
    private static final char MAX_VALID_DIGIT = '9';
    private static final char MIN_VALID_LETTER = 'a';
    private static final char MAX_VALID_CHAR = 'z';
    private static final char MIN_UPPER_CASE_LETTER = 'A';
    private static final char MAX_UPPER_CASE_LETTER = 'Z';

    public static char getDefaultChar() {
        return DEFAULT_CHAR;
    }
    public static char getMinValidChar() {
        return MIN_VALID_CHAR;
    }
    public static char getMaxValidChar() {
        return MAX_VALID_CHAR;
    }

    private final char character;

    private boolean isZdecimalValidChar(char c) {
        return (c >= MIN_VALID_CHAR && c <= MAX_VALID_DIGIT) || (c >= MIN_VALID_LETTER && c <= MAX_VALID_CHAR);
    }

    private boolean isUpperCaseLetterChar(char c) {
        return c >= MIN_UPPER_CASE_LETTER && c <= MAX_UPPER_CASE_LETTER;
    }

    /**
     * Take a character, and return a zdecimal compatible character.
     * <br>
     * [0-9a-z] are autorized.
     * <br>
     * [A-Z] can be turn into [a-z] to become compatible.
     * <br>
     * <u>All others characters</u> are refused. In that case, the method will return the {@link #DEFAULT_CHAR} by default.
     * @param c the character to analyze.
     * @return a zdecimal compatible character.
     */
    private char getZdecimalValidChar(char c) {
        if(isZdecimalValidChar(c)) {
            return c;
        }
        if(isUpperCaseLetterChar(c)) {
            char lowerC = Character.toLowerCase(c);
            if(isZdecimalValidChar(lowerC)) {
                return lowerC;
            }
        }
        return DEFAULT_CHAR;
    }

    public ZdecimalCharacter(char c) {
        this.character = getZdecimalValidChar(c);
    }

    public ZdecimalCharacter(int i) {
        this.character = getZdecimalValidChar(ZdecimalCharacterConverter.intDecimal_to_charBase36(i));
    }

    public char getCharacter() {
        return character;
    }

    @NonNull
    @Override
    public String toString() {
        return "ZdecimalCharacter:" + character;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ZdecimalCharacter that = (ZdecimalCharacter) o;
        return character == that.character;
    }

    @Override
    public int hashCode() {
        return Objects.hash(character);
    }
}
