package com.example.veritablejeu.Menu.PageDeSelection.PartieInferieureAPageNumerotee;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.veritablejeu.Tools.LayoutParams.LayoutParamsDeBase_pourFrameLayout;
import com.example.veritablejeu.R;

@SuppressLint("ViewConstructor")
public class PartieInferieureAPageNumerotee extends FrameLayout {

    private final ContainerDeFleche flecheGauche;
    private final ContainerDeFleche flecheDroite;
    private TextView affichageNumeroPage;

    public ContainerDeFleche getFlecheGauche() {
        return flecheGauche;
    }

    public ContainerDeFleche getFlecheDroite() {
        return flecheDroite;
    }

    public void setAffichageNumeroPage(String nouveauTexte) {
        affichageNumeroPage.setText(nouveauTexte);
    }

    /**
     * Crée le titre du panneau.
     * @return un textView.
     */
    public TextView getNumeroDePage() {
        this.affichageNumeroPage = new TextView(this.getContext());
        affichageNumeroPage.setTextColor(Color.BLACK);
        affichageNumeroPage.setTextSize(16);
        affichageNumeroPage.setGravity(Gravity.CENTER);
        return affichageNumeroPage;
    }

    public PartieInferieureAPageNumerotee(@NonNull FrameLayout parent, int width, int height, int topMargin, int largeurBordure, Runnable previousEffect, Runnable nextEffect) {
        super(parent.getContext());

        GradientDrawable background = new GradientDrawable();
        background.setColor(Color.WHITE);
        background.setStroke(largeurBordure, Color.BLACK);
        setBackground(background);

        FrameLayout.LayoutParams layoutParams = new LayoutParamsDeBase_pourFrameLayout(width, height, 0, topMargin);
        setLayoutParams(layoutParams);

        flecheGauche = new ContainerDeFleche(this, height, 0, 0, R.drawable.fleche_gauche);
        flecheGauche.setOnClickListener(v -> previousEffect.run());
        addView(flecheGauche);

        int leftMarginBoutonDroit = width - height;
        flecheDroite = new ContainerDeFleche(this, height, leftMarginBoutonDroit, 0, R.drawable.fleche_droite);
        flecheDroite.setOnClickListener(v -> nextEffect.run());
        addView(flecheDroite);

        TextView compteurDePage = getNumeroDePage();
        addView(compteurDePage);
    }
}
