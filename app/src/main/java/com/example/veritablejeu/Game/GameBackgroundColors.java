package com.example.veritablejeu.Game;

import android.graphics.Color;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.veritablejeu.Tools.BackgroundColoration;

public class GameBackgroundColors {

    private static final int DEFAULT_COLOR = Color.WHITE;

    public static int getDefaultColor() {
        return DEFAULT_COLOR;
    }

    private final Game game;
    private final int[] backgroundColors = new int[2];

    public GameBackgroundColors(Game game) {
        this.game = game;
        setTopColor(DEFAULT_COLOR);
        setBottomColor(DEFAULT_COLOR);
    }

    public int[] getColors() {
        return backgroundColors;
    }

    public int getTopColor() {
        if(backgroundColors.length < 1) {
            return DEFAULT_COLOR;
        }
        return backgroundColors[0];
    }

    public int getBottomColor() {
        if(backgroundColors.length < 2) {
            return DEFAULT_COLOR;
        }
        return backgroundColors[1];
    }

    public void setColors(int topColor, int bottomColor) {
        backgroundColors[0] = topColor;
        backgroundColors[1] = bottomColor;
        setGameBackgroundColors();
    }

    public void setTopColor(int color) {
        backgroundColors[0] = color;
        setGameBackgroundColors();
    }

    public void setBottomColor(int color) {
        backgroundColors[1] = color;
        setGameBackgroundColors();
    }

    private void setGameBackgroundColors() {
        if(game == null) return;
        ConstraintLayout container = game.getContainer();
        BackgroundColoration.colorierBackground(container, backgroundColors);
    }

}
