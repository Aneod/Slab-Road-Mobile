package com.slabroad.veritablejeu.Game.Board;

import com.slabroad.veritablejeu.Game.Board.ZdecimalCoordinates.ZdecimalCharacter.ZdecimalCharacter;
import com.slabroad.veritablejeu.Game.Board.ZdecimalCoordinates.ZdecimalCharacter.ZdecimalCharacterConverter;

public class BoardTransparency {

    private static final float MIN_VALUE = 0.0f;
    private static final float MAX_VALUE = 1.0f;
    private static final float DEFAULT_VALUE = MAX_VALUE;

    public static float getMinValue() {
        return MIN_VALUE;
    }

    public static float getMaxValue() {
        return MAX_VALUE;
    }

    public static float getDefaultValue() {
        return DEFAULT_VALUE;
    }



    private float transparency;

    public BoardTransparency() {
        this(DEFAULT_VALUE);
    }

    public BoardTransparency(float transparency) {
        this.transparency = transparency;
    }

    public float getTransparency() {
        return transparency;
    }

    public String getTransparencyToTwoChar() {
        float on100 = transparency * 100.0f;

        int firstInt = (int) Math.floor(on100 / 36.0f);
        ZdecimalCharacter first = new ZdecimalCharacter(firstInt);
        char firstChar = first.getCharacter();

        int secondInt = (int) Math.floor((double) on100 - firstInt * 36);
        ZdecimalCharacter second = new ZdecimalCharacter(secondInt);
        char secondChar = second.getCharacter();

        if(firstChar == '0') {
            return "" + secondChar;
        }
        return "" + firstChar + secondChar;
    }

    /**
     * The code is just a Zdecimal number between 0 and 100 (0 and 2s).
     */
    public void setTransparencyByCode(Board board, String code) {
        float transparency = convertStringToPorcentage(code);
        setTransparency(board, transparency);
    }

    public float convertStringToPorcentage(String code) {
        if(code == null) {
            return DEFAULT_VALUE;
        }
        if(code.length() > 2) {
            return MAX_VALUE;
        }
        if(code.isEmpty()) {
            return MIN_VALUE;
        }
        char firstChar = code.charAt(0);
        ZdecimalCharacter first = new ZdecimalCharacter(firstChar);
        int firstInt = ZdecimalCharacterConverter.zdecimalCharacter_to_intDecimal(first);
        int secondInt;
        if(code.length() > 1) {
            char secondChar = code.charAt(1);
            ZdecimalCharacter second = new ZdecimalCharacter(secondChar);
            secondInt = ZdecimalCharacterConverter.zdecimalCharacter_to_intDecimal(second);
        } else {
            secondInt = 0;
        }
        int on100 = firstInt * 36 + secondInt;
        float on1 = on100 / 100.0f;
        return Math.min(Math.max(MIN_VALUE, on1), MAX_VALUE);
    }

    public void setTransparency(Board board, float transparency) {
        this.transparency = Math.min(Math.max(MIN_VALUE, transparency), MAX_VALUE);
        setAllSquaresTransparency(board);
    }

    public void setAllSquaresTransparency(Board board) {
        if(board == null) return;
        board.getModularSquareSet().forEach(
                square -> square.setAlpha(transparency)
        );
    }
}
