package com.slabroad.veritablejeu.Tools;

import android.graphics.Color;

import androidx.annotation.NonNull;

/**
 * This class exists for convert 6-characters colors groups String into Lists of ready to be used colors.
 */
public class StringColorConverter {

    private static final int DEFAULT_COLOR = Color.BLACK;

    public static int getDefaultColor() {
        return DEFAULT_COLOR;
    }

    /**
     * Checks if a character is an hexadecimal character.
     * @param c the character to check.
     * @return true if the given character is an hexadecimal character.
     */
    private static boolean isHexCharacter(char c) {
        return (c >= '0' && c <= '9') || (c >= 'A' && c <= 'F') || (c >= 'a' && c <= 'f');
    }

    /**
     * Checks if all characters in a String are hexadecimal friendly.
     * <br>
     * The upperCase letters are accepted.
     * @param hex the String to verify.
     * @return true all characters in a String are an hexadecimal characters.
     */
    private static boolean isNotValidHex(String hex) {
        if(hex == null) {
            return true;
        }
        for (char c : hex.toCharArray()) {
            if (!isHexCharacter(c)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Converts a String like 'RRGGBB' into the Color object 0xRRGGBB.
     * @param code the String to convert into a Color object.
     * @return a Color object.
     * <br>
     * If the given String is null, returns null.
     * <br>
     * If the given String haven't exactly 6 characters, returns null.
     * <br>
     * If the given String contains hexadecimal uncorrect characters, returns null.
     */
    public static int turnIntoColor(String code) {
        if(code == null || code.length() != 6 || isNotValidHex(code)) {
            return DEFAULT_COLOR;
        }
        String stringColor = '#' + code;
        return Color.parseColor(stringColor);
    }

    /**
     * Take a String and discompose it to many hexadecimal colors and collects them in a list.
     * @param code many hexadecimal numbers like : xxxxxxyyyyyyzzzzzz...
     * @return the int[] of all colors in the code.
     */
    @NonNull
    public static int[] turnIntoColors(String code) {
        if(code == null) {
            return new int[]{};
        }
        int howManyColors = code.length() / 6;
        int[] colors = new int[howManyColors];

        for(int index = 0; index < howManyColors; index++) {
            int firstIndex = index * 6;
            String stringColor = code.substring(firstIndex, firstIndex + 6);
            colors[index] = turnIntoColor(stringColor);
        }
        return colors;
    }

    /**
     * Take some Color objects and return a code like xxxxxxyyyyyyzzzzzz...
     * @param colors the colors to codify.
     * @return the code of these colors.
     */
    @NonNull
    public static String turnIntoCode(int... colors) {
        if (colors == null || colors.length == 0) {
            return "";
        }

        String onReturn = "";

        for (int color : colors) {
            String converted = turnIntoCode_single(color);
            onReturn = onReturn.concat(converted);
        }
        return onReturn;
    }

    @NonNull
    public static String turnIntoCode_single(int color) {
        int r = Color.red(color);
        int g = Color.green(color);
        int b = Color.blue(color);
        String rString = turnInto2CharCode(r);
        String gString = turnInto2CharCode(g);
        String bString = turnInto2CharCode(b);
        return rString + gString + bString;
    }

    @NonNull
    private static String turnInto2CharCode(int on255) {
        String on255String = Integer.toHexString(on255);
        if(on255String.length() == 1) {
            return "0" + on255String;
        }
        return on255String;
    }

}
