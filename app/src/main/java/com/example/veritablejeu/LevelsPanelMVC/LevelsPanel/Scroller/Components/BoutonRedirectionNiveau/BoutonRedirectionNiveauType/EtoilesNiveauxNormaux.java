package com.example.veritablejeu.LevelsPanelMVC.LevelsPanel.Scroller.Components.BoutonRedirectionNiveau.BoutonRedirectionNiveauType;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;

import com.example.veritablejeu.Tools.LayoutParams.LayoutParamsDeBase_pourFrameLayout;

@SuppressLint("ViewConstructor")
public class EtoilesNiveauxNormaux extends FrameLayout {

    private View creationEtoile(@NonNull Context context, boolean active, int widthHeight, int leftMargin) {
        View etoile = new View(context);

        FrameLayout.LayoutParams layoutParams = new LayoutParamsDeBase_pourFrameLayout(
                widthHeight, widthHeight, leftMargin, 0
        );
        etoile.setLayoutParams(layoutParams);

        GradientDrawable drawable = new GradientDrawable();
        drawable.setShape(GradientDrawable.OVAL);
        int couleur = active ? Color.BLACK : Color.LTGRAY;
        drawable.setColor(couleur);
        etoile.setBackground(drawable);

        return etoile;
    }

    public EtoilesNiveauxNormaux(@NonNull Context context, int nbEtoileActive, int width, int height, int leftMargin, int topMargin) {
        super(context);

        FrameLayout.LayoutParams layoutParams = new LayoutParamsDeBase_pourFrameLayout(
                width, height, leftMargin, topMargin
        );
        setLayoutParams(layoutParams);

        int distanceEntreLesEtoiles = height + 10;
        for(int index = 0; index < 3; index++) {
            int leftMarginEtoile = distanceEntreLesEtoiles * index;
            boolean etoileActive = index + 1 <= nbEtoileActive;
            View etoile = creationEtoile(context, etoileActive, height, leftMarginEtoile);
            addView(etoile);
        }
    }
}
