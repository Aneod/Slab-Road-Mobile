package com.example.veritablejeu.PopUp.PopUpComponent.ComposedComponents.RGBPanel;

import android.graphics.Color;

public class ARGBComposer {

    private static final int MIN_VALUE = 0;
    private static final int MAX_VALUE = 255;

    public static int getMinValue() {
        return MIN_VALUE;
    }

    public static int getMaxValue() {
        return MAX_VALUE;
    }

    private static int getCorrectQuantity(int quantity) {
        return Math.min(Math.max(MIN_VALUE, quantity), MAX_VALUE);
    }

    /**
     * Set the alpha of a given int color.
     * @param color the color to modify.
     * @param quantity the new quantity of alpha.
     * @return the given color with the new quantity of alpha.
     */
    public static int setAlpha(int color, int quantity) {
        int alpha = getCorrectQuantity(quantity);
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
        int red = getCorrectQuantity(quantity);
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
        int green = getCorrectQuantity(quantity);
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
        int blue = getCorrectQuantity(quantity);
        return Color.argb(alpha, red, green, blue);
    }

}
