package com.example.veritablejeu.Game;

import static org.junit.Assert.*;

import android.graphics.Color;

import org.junit.Test;

public class GameBackgroundColorsTest {

    private final GameBackgroundColors gameBackgroundColors = new GameBackgroundColors(null);

    @Test
    public void getColors_default() {
        int[] actual = gameBackgroundColors.getColors();
        int defaultColor = GameBackgroundColors.getDefaultColor();
        int[] expected = new int[]{defaultColor, defaultColor};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void setColors() {
        gameBackgroundColors.setColors(Color.RED, Color.BLUE);
        int[] actual = gameBackgroundColors.getColors();
        int[] expected = new int[]{Color.RED, Color.BLUE};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void setTopColor() {
        int color = Color.BLUE;
        gameBackgroundColors.setTopColor(color);
        int actual = gameBackgroundColors.getTopColor();
        assertEquals(color, actual);
    }

    @Test
    public void setBottomColor() {
        int color = Color.RED;
        gameBackgroundColors.setBottomColor(color);
        int actual = gameBackgroundColors.getBottomColor();
        assertEquals(color, actual);
    }
}