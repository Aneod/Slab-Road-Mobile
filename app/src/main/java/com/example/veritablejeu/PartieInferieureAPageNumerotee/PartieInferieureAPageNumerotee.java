package com.example.veritablejeu.PartieInferieureAPageNumerotee;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.veritablejeu.OutilsEnEnum.LayoutParams.LayoutParamsDeBase_pourFrameLayout;
import com.example.veritablejeu.R;

@SuppressLint("ViewConstructor")
public class PartieInferieureAPageNumerotee extends FrameLayout {

    private final int hauteur;
    private final ContainerDeFleche flecheGauche;
    private final ContainerDeFleche flecheDroite;
    private TextView affichageNumeroPage;

    public ContainerDeFleche getFlecheGauche() {
        return flecheGauche;
    }

    public ContainerDeFleche getFlecheDroite() {
        return flecheDroite;
    }

    public int getHauteur() {
        return hauteur;
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

    public PartieInferieureAPageNumerotee(FrameLayout parent, int width, int height, int topMargin, int largeurBordure, Runnable previousEffect, Runnable nextEffect) {
        super(parent.getContext());
        this.hauteur = height;

        GradientDrawable background = new GradientDrawable();
        background.setColor(Color.WHITE);
        background.setStroke(largeurBordure, Color.BLACK);
        setBackground(background);

        FrameLayout.LayoutParams layoutParams = new LayoutParamsDeBase_pourFrameLayout(width, hauteur, 0, topMargin);
        setLayoutParams(layoutParams);

        flecheGauche = new ContainerDeFleche(this, 0, 0, R.drawable.fleche_gauche);
        flecheGauche.setOnClickListener(v -> previousEffect.run());
        addView(flecheGauche);

        int leftMarginBoutonDroit = width - hauteur;
        flecheDroite = new ContainerDeFleche(this, leftMarginBoutonDroit, 0, R.drawable.fleche_droite);
        flecheDroite.setOnClickListener(v -> nextEffect.run());
        addView(flecheDroite);

        TextView compteurDePage = getNumeroDePage();
        addView(compteurDePage);
    }
}
