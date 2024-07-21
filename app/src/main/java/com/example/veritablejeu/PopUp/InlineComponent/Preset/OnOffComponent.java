package com.example.veritablejeu.PopUp.InlineComponent.Preset;

import android.annotation.SuppressLint;
import android.view.Gravity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.veritablejeu.PopUp.InlineComponent.AtomicComponents.Text;
import com.example.veritablejeu.PopUp.InlineComponent.AtomicComponents.OnOffButton.OnOffButton;
import com.example.veritablejeu.PopUp.InlineComponent.InlineComponent;
import com.example.veritablejeu.PopUp.PopUp;

@SuppressLint("ViewConstructor")
public class OnOffComponent extends InlineComponent {

    private static final float WIDTH_TITLED_DISTRIBUTION = .6f;

    public static float getWidthTitledDistribution() {
        return WIDTH_TITLED_DISTRIBUTION;
    }

    private final OnOffButton bouton;

    public OnOffButton getBouton() {
        return bouton;
    }

    public OnOffComponent(@NonNull PopUp popUp, String title,
                          boolean estActiveDeBase,
                          String texteActive, @Nullable Runnable activeEffect,
                          String texteDesactive, @Nullable Runnable disactiveEffect) {
        super(popUp);
        int width = getLayoutParams().width;
        int height = OnOffButton.getHEIGHT();
        int titledWidth = (int) (width * WIDTH_TITLED_DISTRIBUTION);
        Text text = new Text(this, title, titledWidth, height, Gravity.CENTER_VERTICAL);
        addView(text);

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
            bouton.refreshLayoutParams();
        }
    }
}
