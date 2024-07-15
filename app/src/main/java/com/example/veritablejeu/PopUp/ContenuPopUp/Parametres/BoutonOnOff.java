package com.example.veritablejeu.PopUp.ContenuPopUp.Parametres;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.Gravity;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.veritablejeu.PopUp.BoutonDePopUp.Bouton_activation;
import com.example.veritablejeu.Tools.LayoutParams.LayoutParamsDeBase_pourFrameLayout;

@SuppressLint("ViewConstructor")
public class BoutonOnOff extends FrameLayout {

    /**
     * La largeur occupée par le titre, en pct.
     */
    private static final float WIDTH_TITLED_DISTRIBUTION = .6f;
    private static final int TEXT_SIZE = 16;
    private static final float HEIGHT_ON_OFF_BUTTON_PERCENTAGE = .75f;

    private final AppCompatTextView texte;
    private final Bouton_activation bouton;

    public AppCompatTextView getTexte() {
        return texte;
    }

    public Bouton_activation getBouton() {
        return bouton;
    }

    /**
     * Crée le texte à gauche du bouton d'activation.
     *
     * @param width      la largeur du texte.
     * @param height     la hauteur.
     * @param leftMargin la marge gauche.
     * @return un TextView.
     */
    private AppCompatTextView creationTexte(int width, int height, int leftMargin) {
        AppCompatTextView texte = new AppCompatTextView(getContext());
        texte.setTextSize(TEXT_SIZE);
        FrameLayout.LayoutParams layoutParamsTexte = new LayoutParamsDeBase_pourFrameLayout(
                width, height, leftMargin, 0);
        texte.setLayoutParams(layoutParamsTexte);
        texte.setGravity(Gravity.CENTER_VERTICAL);
        return texte;
    }

    public BoutonOnOff(@NonNull Context context, String contenuTexte, int width, int height, int leftMargin, int topMargin, boolean estActiveDeBase, String texteActive, Runnable activeEffect, String texteDesactive, Runnable disactiveEffect) {
        super(context);

        FrameLayout.LayoutParams layoutParams = new LayoutParamsDeBase_pourFrameLayout(
                width, height, leftMargin, topMargin);
        setLayoutParams(layoutParams);

        int titledWidth = (int) (width * WIDTH_TITLED_DISTRIBUTION);
        texte = creationTexte(titledWidth, ConstraintLayout.LayoutParams.MATCH_PARENT, 0);
        texte.setText(contenuTexte);
        addView(texte);

        int buttonWidth = width - titledWidth;
        int buttonHeight = (int) (height * HEIGHT_ON_OFF_BUTTON_PERCENTAGE);
        int topMarginButton = (height - buttonHeight) / 2;
        bouton = new Bouton_activation(this, buttonWidth, buttonHeight, titledWidth, topMarginButton, estActiveDeBase, texteActive, texteDesactive);
        addView(bouton);

        bouton.setOnClickListener(v -> {
            bouton.changerEtat();
            boolean actif = bouton.getEstActive();
            if(actif) {
                if(activeEffect != null) activeEffect.run();
            }
            else {
                if(disactiveEffect != null) disactiveEffect.run();
            }
        });
    }
}
