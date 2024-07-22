package com.example.veritablejeu.LevelsPanel.BottomBar;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.veritablejeu.Menu.PageDeSelection.PartieInferieureAPageNumerotee.ContainerDeFleche;
import com.example.veritablejeu.R;
import com.example.veritablejeu.Tools.LayoutParams.LayoutParamsDeBase_pourFrameLayout;

@SuppressLint("ViewConstructor")
public class BottomBar extends FrameLayout implements IBottomBar {

    private TextView affichageNumeroPage;

    public void setAffichageNumeroPage(String nouveauTexte) {
        affichageNumeroPage.setText(nouveauTexte);
    }

    public TextView getNumeroDePage() {
        this.affichageNumeroPage = new TextView(this.getContext());
        affichageNumeroPage.setTextColor(Color.BLACK);
        affichageNumeroPage.setTextSize(16);
        affichageNumeroPage.setGravity(Gravity.CENTER);
        return affichageNumeroPage;
    }

    public BottomBar(@NonNull FrameLayout parent, int width, int height, int topMargin, int largeurBordure, Runnable previousEffect, Runnable nextEffect) {
        super(parent.getContext());

        GradientDrawable background = new GradientDrawable();
        background.setColor(Color.WHITE);
        background.setStroke(largeurBordure, Color.BLACK);
        setBackground(background);

        FrameLayout.LayoutParams layoutParams = new LayoutParamsDeBase_pourFrameLayout(width, height, 0, topMargin);
        setLayoutParams(layoutParams);

        ContainerDeFleche flecheGauche = new ContainerDeFleche(this, height, 0, 0, R.drawable.fleche_gauche);
        flecheGauche.setOnClickListener(v -> previousEffect.run());
        addView(flecheGauche);

        int leftMarginBoutonDroit = width - height;
        ContainerDeFleche flecheDroite = new ContainerDeFleche(this, height, leftMarginBoutonDroit, 0, R.drawable.fleche_droite);
        flecheDroite.setOnClickListener(v -> nextEffect.run());
        addView(flecheDroite);

        TextView compteurDePage = getNumeroDePage();
        addView(compteurDePage);
    }

    @Override
    public void clear() {

    }

    @Override
    public void showLoadingIcon() {

    }

    @Override
    public void setPageNumber(int pageNumber) {

    }

    @Override
    public void setNumberOfPages(int numberOfPages) {

    }

    @Override
    public void setPreviousPageRunnable(Runnable runnable) {

    }

    @Override
    public void setNextPageRunnable(Runnable runnable) {

    }
}
