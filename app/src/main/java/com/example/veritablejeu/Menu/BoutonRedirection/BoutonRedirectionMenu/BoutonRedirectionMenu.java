package com.example.veritablejeu.Menu.BoutonRedirection.BoutonRedirectionMenu;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;

import androidx.annotation.NonNull;

import com.example.veritablejeu.Menu.BoutonRedirection.BoutonRedirection;

public class BoutonRedirectionMenu extends BoutonRedirection implements IBoutonRedirectionMenu {

    private final int color;

    public BoutonRedirectionMenu(@NonNull Context context) {
        super(context);
        this.color = Color.WHITE;
    }

    public BoutonRedirectionMenu(@NonNull Context context, String titre, int width, int height, int leftMargin, int topMargin, int color) {
        super(context, titre, width, height, leftMargin, topMargin);
        this.color = color;

        GradientDrawable drawable = new GradientDrawable();
        drawable.setColor(color);
        drawable.setStroke(3, Color.BLACK);
        setBackground(drawable);
    }

    @Override
    public int getColor() {
        return color;
    }
}
