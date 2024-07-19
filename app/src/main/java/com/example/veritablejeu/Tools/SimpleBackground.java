package com.example.veritablejeu.Tools;

import android.graphics.drawable.GradientDrawable;

import androidx.annotation.NonNull;

public class SimpleBackground {

    @NonNull
    public static GradientDrawable create(int fillColor, int borderColor, int borderWidth) {
        GradientDrawable background = new GradientDrawable();
        background.setColor(fillColor);
        background.setStroke(borderWidth, borderColor);
        return background;
    }

    @NonNull
    public static GradientDrawable create(int fillColor) {
        GradientDrawable background = new GradientDrawable();
        background.setColor(fillColor);
        return background;
    }

}
