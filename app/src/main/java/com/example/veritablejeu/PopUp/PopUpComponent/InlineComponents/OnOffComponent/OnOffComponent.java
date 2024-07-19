package com.example.veritablejeu.PopUp.PopUpComponent.InlineComponents.OnOffComponent;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.veritablejeu.PopUp.PopUpComponent.AtomicComponents.LeftText;
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
    private static final float HEIGHT_ON_OFF_BUTTON_PERCENTAGE = .75f;

    private final OnOffButton bouton;

    public OnOffButton getBouton() {
        return bouton;
    }

    public OnOffComponent(@NonNull PopUp popUp, String title, int height,
                          boolean estActiveDeBase,
                          String texteActive, @Nullable Runnable activeEffect,
                          String texteDesactive, @Nullable Runnable disactiveEffect) {
        super(popUp);
        setHeight(height);
        int width = getLayoutParams().width;
        int titledWidth = (int) (width * WIDTH_TITLED_DISTRIBUTION);
        LeftText leftText = new LeftText(this, title, titledWidth);
        addView(leftText);

        int buttonWidth = width - titledWidth;
        int buttonHeight = (int) (height * HEIGHT_ON_OFF_BUTTON_PERCENTAGE);
        int topMarginButton = (height - buttonHeight) / 2;
        ConstraintParams constraintParams = new ConstraintParams(buttonWidth, buttonHeight, titledWidth, topMarginButton);
        bouton = new OnOffButton(
                popUp, constraintParams,
                estActiveDeBase,
                texteActive, activeEffect,
                texteDesactive, disactiveEffect);
        addView(bouton);
    }
}
