package com.example.veritablejeu.Game.Board;

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

    public void setTransparency(Board board, float transparency) {
        this.transparency = Math.min(Math.max(MIN_VALUE, transparency), MAX_VALUE);
        setAllSquaresTransparency(board, this.transparency);
    }

    private void setAllSquaresTransparency(Board board, float transparency) {
        if(board == null) {
            return;
        }
        board.getModularSquareSet().forEach(square -> square.setAlpha(transparency));
    }
}
