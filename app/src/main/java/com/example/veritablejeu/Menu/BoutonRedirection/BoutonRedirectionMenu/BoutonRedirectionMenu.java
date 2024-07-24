package com.example.veritablejeu.Menu.BoutonRedirection.BoutonRedirectionMenu;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.Gravity;

import androidx.annotation.NonNull;

import com.example.veritablejeu.Menu.BoutonRedirection.BoutonRedirection;
import com.example.veritablejeu.Tools.SimpleBackground;

public class BoutonRedirectionMenu extends BoutonRedirection implements IBoutonRedirectionMenu {

    private final int color;

    public BoutonRedirectionMenu(@NonNull Context context) {
        super(context);
        this.color = Color.WHITE;
    }

    public BoutonRedirectionMenu(@NonNull Context context, String titre, int width, int height, int leftMargin, int topMargin, int color) {
        super(context, titre, width, height, leftMargin, topMargin);
        this.color = color;
        textViewTitre.setGravity(Gravity.CENTER_VERTICAL);

        GradientDrawable drawable = SimpleBackground.create(
                color, Color.BLACK, 3);
        setBackground(drawable);
    }

    @Override
    public int getColor() {
        return color;
    }
}
