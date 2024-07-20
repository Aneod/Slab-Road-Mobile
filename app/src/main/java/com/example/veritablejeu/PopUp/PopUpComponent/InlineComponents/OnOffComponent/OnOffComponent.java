package com.example.veritablejeu.PopUp.PopUpComponent.InlineComponents.OnOffComponent;

import android.annotation.SuppressLint;
import android.view.Gravity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.veritablejeu.PopUp.PopUpComponent.AtomicComponents.PopUpText;
import com.example.veritablejeu.PopUp.PopUpComponent.AtomicComponents.OnOffButton;
import com.example.veritablejeu.PopUp.PopUpComponent.PopUpComponent;
import com.example.veritablejeu.PopUp.PopUp;
import com.example.veritablejeu.Tools.LayoutParams.ConstraintParams;

@SuppressLint("ViewConstructor")
public class OnOffComponent extends PopUpComponent {

    /**
     * La largeur occupée par le titre, en pct.
     */
    private static final float WIDTH_TITLED_DISTRIBUTION = .6f;

    private final OnOffButton bouton;

    public OnOffButton getBouton() {
        return bouton;
    }

    public OnOffComponent(@NonNull PopUp popUp, String title, int height,
                          boolean estActiveDeBase,
                          String texteActive, @Nullable Runnable activeEffect,
                          String texteDesactive, @Nullable Runnable disactiveEffect) {
        super(popUp);
        int width = getLayoutParams().width;
        int titledWidth = (int) (width * WIDTH_TITLED_DISTRIBUTION);
        PopUpText popUpText = new PopUpText(this, title, titledWidth, height, Gravity.CENTER_VERTICAL);
        addView(popUpText);

        int buttonWidth = width - titledWidth;
        bouton = new OnOffButton(
                this, buttonWidth, titledWidth,
                estActiveDeBase,
                texteActive, activeEffect,
                texteDesactive, disactiveEffect);
        addView(bouton);
    }

    @Override
    public void setHeight(int height) {
        layoutParams.height = height;
        setLayoutParams(layoutParams);
        popUp.refreshHeight();
        if(bouton != null) {
            bouton.refreshHeight();
        }
    }
}
