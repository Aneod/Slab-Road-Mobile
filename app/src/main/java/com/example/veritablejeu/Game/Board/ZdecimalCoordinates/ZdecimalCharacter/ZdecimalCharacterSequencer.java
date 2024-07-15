package com.example.veritablejeu.Game.Board.ZdecimalCoordinates.ZdecimalCharacter;

import androidx.annotation.NonNull;

import org.jetbrains.annotations.Contract;

/**
 * This class is destined to manage the position gap between ZdecimalCharacters.
 * <br>
 * Like to obtain the next ou previous ZdecimalCharacters, or to know if a ZdecimalCharacters is
 * higher than an other or not, etc.
 */
public class ZdecimalCharacterSequencer {


    /**
     * Returns the next ZdecimalCharacter of the given one.
     * @param current the ZdecimalCharacter to get the next one.
     *                If this is out of bounds this method will returns the {@link ZdecimalCharacter#getDefaultChar()}.
     * @return the next ZdecimalCharacter.
     */
    @NonNull
    @Contract("_ -> new")
    public static ZdecimalCharacter getNextChar(ZdecimalCharacter current) {
        if(current == null) {
            return new ZdecimalCharacter(ZdecimalCharacter.getDefaultChar());
        }
        int currentInt = ZdecimalCharacterConverter.zdecimalCharacter_to_intDecimal(current);
        return new ZdecimalCharacter(currentInt + 1);
    }

    /**
     * Returns the previous ZdecimalCharacter of the given one.
     * @param current the ZdecimalCharacter to get the previous one.
     *                If this is out of bounds this method will returns the {@link ZdecimalCharacter#getDefaultChar()}.
     * @return the previous ZdecimalCharacter.
     */
    @NonNull
    @Contract("_ -> new")
    public static ZdecimalCharacter getPreviousChar(ZdecimalCharacter current) {
        if(current == null) {
            return new ZdecimalCharacter(ZdecimalCharacter.getDefaultChar());
        }
        int currentInt = ZdecimalCharacterConverter.zdecimalCharacter_to_intDecimal(current);
        return new ZdecimalCharacter(currentInt - 1);
    }



    /**
     * Returns how many positions separates two ZdecimalCharacter. The results is relative.
     * <br>
     * If ones of the ZdecimalCharacter is <i>null</i>, returns 0.
     * @param from one of the couple of ZdecimalCharacter.
     * @param to one of the couple of ZdecimalCharacter.
     * @return how many relative positions separates the givens ZdecimalCharacter.
     */
    public static int getRelativeGapBetween(ZdecimalCharacter from, ZdecimalCharacter to) {
        if(from == null || to == null) {
            return 0;
        }
        int fromInt = ZdecimalCharacterConverter.zdecimalCharacter_to_intDecimal(from);
        int toInt = ZdecimalCharacterConverter.zdecimalCharacter_to_intDecimal(to);
        return toInt - fromInt;
    }

    /**
     * Returns the absolute separation between two ZdecimalCharacter.
     * <br>
     * If ones of the ZdecimalCharacter is <i>null</i>, returns 0.
     * @param from one of the couple of ZdecimalCharacter.
     * @param to one of the couple of ZdecimalCharacter.
     * @return how many relative positions separates the givens ZdecimalCharacter.
     */
    public static int getAbsoluteGapBetween(ZdecimalCharacter from, ZdecimalCharacter to) {
        return Math.abs(getRelativeGapBetween(from, to));
    }



    /**
     * Returns if a ZdecimalCharacter is lower than an other.
     * @param from the ZdecimalCharacter of which we want to know if it's lower than an other.
     * @param to the ZdecimalCharacter compared with the first one.
     * @return true if <i>from</i> is strictly lower than <i>to</i>.
     */
    public static boolean isLowerTo(ZdecimalCharacter from, ZdecimalCharacter to) {
        return compare(from, to) < 0;
    }

    /**
     * Returns if a ZdecimalCharacter is lower or equals than an other.
     * @param from the ZdecimalCharacter of which we want to know if it's lower or equals than an other.
     * @param to the ZdecimalCharacter compared with the first one.
     * @return true if <i>from</i> is lower or equals than <i>to</i>.
     */
    public static boolean isLowerEqualTo(ZdecimalCharacter from, ZdecimalCharacter to) {
        return compare(from, to) <= 0;
    }

    /**
     * Returns if a ZdecimalCharacter is equals than an other.
     * @param from the ZdecimalCharacter of which we want to know if it's equals than an other.
     * @param to the ZdecimalCharacter compared with the first one.
     * @return true if <i>from</i> is strictly equals than <i>to</i>.
     */
    public static boolean isEqualTo(ZdecimalCharacter from, ZdecimalCharacter to) {
        return compare(from, to) == 0;
    }

    /**
     * Returns if a ZdecimalCharacter is higher or equals than an other.
     * @param from the ZdecimalCharacter of which we want to know if it's higher or equals than an other.
     * @param to the ZdecimalCharacter compared with the first one.
     * @return true if <i>from</i> is higher or equals than <i>to</i>.
     */
    public static boolean isHigherEqualTo(ZdecimalCharacter from, ZdecimalCharacter to) {
        return compare(from, to) >= 0;
    }

    /**
     * Returns if a ZdecimalCharacter is higher than an other.
     * @param from the ZdecimalCharacter of which we want to know if it's higher than an other.
     * @param to the ZdecimalCharacter compared with the first one.
     * @return true if <i>from</i> is strictly higher than <i>to</i>.
     */
    public static boolean isHigherTo(ZdecimalCharacter from, ZdecimalCharacter to) {
        return compare(from, to) > 0;
    }

    /**
     * Compare the ACSII value between two ZdecimalCharacter.
     * <br>
     * If one of the two ZdecimalCharacter is null, or the both are null, returns 0.
     * @param from one of the ZdecimalCharacter.
     * @param to one of the ZdecimalCharacter.
     * @return the ASCII value comparison bewteen the two ZdecimalCharacter.
     */
    private static int compare(ZdecimalCharacter from, ZdecimalCharacter to) {
        if (from == null || to == null) {
            return 0;
        }
        return Character.compare(from.getCharacter(), to.getCharacter());
    }



    /**
     * Checks if the given ZdecimalCharacter is the minimum valid character.
     *
     * @param zdecimalCharacter the ZdecimalCharacter to check
     * @return true if the given ZdecimalCharacter is the minimum valid character, false otherwise
     */
    public static boolean isMinChar(ZdecimalCharacter zdecimalCharacter) {
        if(zdecimalCharacter == null) {
            return false;
        }
        return zdecimalCharacter.getCharacter() == ZdecimalCharacter.getMinValidChar();
    }

    /**
     * Checks if the given ZdecimalCharacter is the maximum valid character.
     *
     * @param zdecimalCharacter the ZdecimalCharacter to check
     * @return true if the given ZdecimalCharacter is the maximum valid character, false otherwise
     */
    public static boolean isMaxChar(ZdecimalCharacter zdecimalCharacter) {
        if(zdecimalCharacter == null) {
            return false;
        }
        return zdecimalCharacter.getCharacter() == ZdecimalCharacter.getMaxValidChar();
    }

}
