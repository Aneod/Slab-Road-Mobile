package com.example.veritablejeu.Tools;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.TransitionDrawable;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;

import org.jetbrains.annotations.Contract;

public class BackgroundColoration {

    private static final int FLASH_DURATION = 2000;
    private static boolean flashesEnable = true;
    public static boolean isFlashesEnable() {
        return flashesEnable;
    }
    public static void setFlashesEnable(boolean flashesEnable) {
        BackgroundColoration.flashesEnable = flashesEnable;
    }

    /**
     * Set the background colors of the given ConstraintLayout.
     * @param container the ConstraintLayout who the background change.
     * @param colors the new colors (from top to bottom) of the ConstraintLayout background.
     */
    public static void colorierBackground(@NonNull ConstraintLayout container, int... colors) {
        GradientDrawable gradientDrawable = createFadeOfColors(colors);
        container.setBackground(gradientDrawable);
    }

    /**
     * Do a color flash on a ConstraintLayout.
     * @param container the ConstraintLayout who take the flash.
     * @param monoFlashColor the color of the flash.
     * @param colorsAfterFlash the colors who compose the ConstraintLayout background after the flash.
     */
    public static void colorFlash(@NonNull ConstraintLayout container, int monoFlashColor, @NonNull int... colorsAfterFlash) {
        if(!flashesEnable) return;
        GradientDrawable gradientDrawable = createFadeOfColors(monoFlashColor);
        GradientDrawable initial = createFadeOfColors(colorsAfterFlash);

        Drawable[] layers = {gradientDrawable, initial};
        TransitionDrawable transitionDrawable = new TransitionDrawable(layers);
        container.setBackground(transitionDrawable);
        transitionDrawable.startTransition(FLASH_DURATION);
    }

    @NonNull
    @Contract("_ -> new")
    private static GradientDrawable createFadeOfColors(int... colors) {
        return new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, colors);
    }
}
