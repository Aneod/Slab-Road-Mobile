package com.slabroad.veritablejeu.Game.Board.ZdecimalCoordinates.ZdecimalCharacter;

/**
 * The only goal of this class is to secure the int -> 36-base characters and vice versa.
 * <br>
 * This class converts all int in [0-9] and all char in [a-z].
 * <u>That's not work with upperCase letters !</u>
 */
public class ZdecimalCharacterConverter {

    private static final char DEFAULT_CHAR = '0';

    public static char getDefaultChar() {
        return DEFAULT_CHAR;
    }

    private static final int DEFAULT_INT = 0;

    public static int getDefaultInt() {
        return DEFAULT_INT;
    }

    /**
     * Converts an integer in the range 0-35 to a character '0'-'9' or 'a'-'z'.
     * @param value the integer value to convert
     * @return the corresponding character
     * <br>
     * Return {@link #DEFAULT_CHAR} if the value is out of range (0-35)
     */
    public static char intDecimal_to_charBase36(int value) {
        if (value >= 0 && value <= 9) {
            return (char) ('0' + value);
        } else if (value >= 10 && value <= 35) {
            return (char) ('a' + (value - 10));
        }
        return DEFAULT_CHAR;
    }

    /**
     * Converts a ZdecimalCharacter to an integer in the range 0-35.
     * @param zdecimalCharacter the ZdecimalCharacter to convert
     * @return the corresponding integer value.
     * <br>
     * Returns -1 if the given zdecimalCharacter is null.
     */
    public static int zdecimalCharacter_to_intDecimal(ZdecimalCharacter zdecimalCharacter) {
        if(zdecimalCharacter == null) {
            return -1;
        }
        return charBase36_to_intDecimal(zdecimalCharacter.getCharacter());
    }

    /**
     * Converts a character '0'-'9' or 'a'-'z' to an integer in the range 0-35.
     * @param c the character to convert
     * @return the corresponding integer value.
     * <br>
     * Return {@link #DEFAULT_INT} if the character is not in the range '0'-'9' or 'a'-'z'
     */
    public static int charBase36_to_intDecimal(char c) {
        if (c >= '0' && c <= '9') {
            return c - '0';
        } else if (c >= 'a' && c <= 'z') {
            return c - 'a' + 10;
        }
        return DEFAULT_INT;
    }

}
