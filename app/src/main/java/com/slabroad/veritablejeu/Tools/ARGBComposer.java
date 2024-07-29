package com.slabroad.veritablejeu.Tools;

import android.graphics.Color;

import androidx.annotation.IntRange;

public class ARGBComposer {

    private static final int MIN_VALUE = 0;
    private static final int MAX_VALUE = 255;

    public static int getMinValue() {
        return MIN_VALUE;
    }

    public static int getMaxValue() {
        return MAX_VALUE;
    }

    @IntRange(from = MIN_VALUE, to = MAX_VALUE)
    private static int getValidQuantity(int quantity) {
        return Math.min(Math.max(MIN_VALUE, quantity), MAX_VALUE);
    }

    /**
     * Set the alpha of a given int color.
     * @param color the color to modify.
     * @param quantity the new quantity of alpha.
     * @return the given color with the new quantity of alpha.
     */
    public static int setAlpha(int color, int quantity) {
        int alpha = getValidQuantity(quantity);
        int red = Color.red(color);
        int green = Color.green(color);
        int blue = Color.blue(color);
        return Color.argb(alpha, red, green, blue);
    }

    /**
     * Set the alpha of a given int color.
     * @param color the color to modify.
     * @param quantity the new quantity of red.
     * @return the given color with the new quantity of red.
     */
    public static int setRed(int color, int quantity) {
        int alpha = Color.alpha(color);
        int red = getValidQuantity(quantity);
        int green = Color.green(color);
        int blue = Color.blue(color);
        return Color.argb(alpha, red, green, blue);
    }

    /**
     * Set the alpha of a given int color.
     * @param color the color to modify.
     * @param quantity the new quantity of green.
     * @return the given color with the new quantity of green.
     */
    public static int setGreen(int color, int quantity) {
        int alpha = Color.alpha(color);
        int red = Color.red(color);
        int green = getValidQuantity(quantity);
        int blue = Color.blue(color);
        return Color.argb(alpha, red, green, blue);
    }

    /**
     * Set the alpha of a given int color.
     * @param color the color to modify.
     * @param quantity the new quantity of blue.
     * @return the given color with the new quantity of blue.
     */
    public static int setBlue(int color, int quantity) {
        int alpha = Color.alpha(color);
        int red = Color.red(color);
        int green = Color.green(color);
        int blue = getValidQuantity(quantity);
        return Color.argb(alpha, red, green, blue);
    }

}
