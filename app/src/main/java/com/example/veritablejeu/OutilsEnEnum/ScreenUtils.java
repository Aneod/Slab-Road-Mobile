package com.example.veritablejeu.OutilsEnEnum;

import android.content.res.Resources;

public class ScreenUtils {

    /**
     * Revoie la largeur de l'écran en pixel.
     */
    public static int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    /**
     * Revoie la hauteur de l'écran en pixel.
     */
    public static int getScreenHeight() {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }
}