package com.example.veritablejeu.PopUp.ContenuPopUp.QuestionFermee;

import android.content.Context;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.veritablejeu.PopUp.BoutonDePopUp.BoutonDePopUp;
import com.example.veritablejeu.PopUp.ContenuPopUp.ContenuPopUp;
import com.example.veritablejeu.PopUp.PopUp;
import com.example.veritablejeu.Tools.LayoutParams.LayoutParamsDeBase_pourFrameLayout;

public class Question extends ContenuPopUp {

    private final int hauteurTexteApproximative = 100;
    private final int hauteurBoutons = 75;
    private final int distanceTexteBoutons = 30;
    private final int largeurBoutons = 200;
    private final int separationEntreLesBoutons = 45;
    private final int margesH = 15;
    private final int margesB = 40;

    private final TextePopUp text;
    private final BoutonDePopUp boutonA;
    private final BoutonDePopUp boutonB;

    public Question(@NonNull Context context) {
        super(context);
        PopUp popUp = PopUp.getInstance(context);
        text = new TextePopUp(popUp, 0);
        boutonA = new BoutonDePopUp(popUp);
        boutonB = new BoutonDePopUp(popUp);
    }

    public Question(@NonNull PopUp popUp, String titre, String question, String reponseA, @Nullable Runnable runnableA, String reponseB, @Nullable Runnable runnableB) {
        super(popUp.getContext(), titre);

        int margeGauchePremierBouton = (popUp.getLargeur() - distanceTexteBoutons - largeurBoutons * 2) / 2;
        int topMarginBoutons = hauteurTexteApproximative + distanceTexteBoutons;
        boutonA = new BoutonDePopUp(popUp, reponseA, largeurBoutons, hauteurBoutons, margeGauchePremierBouton, topMarginBoutons, runnableA);
        boutonA.prendreLaCouleurBlanche();
        this.addView(boutonA);

        int leftMargin = margeGauchePremierBouton + largeurBoutons + separationEntreLesBoutons;
        boutonB = new BoutonDePopUp(popUp, reponseB, largeurBoutons, hauteurBoutons, leftMargin, topMarginBoutons, runnableB);
        boutonB.prendreLaCouleurNoire();
        this.addView(boutonB);

        int topMargin = popUp.getHauteurInitiale() + margesH;
        this.hauteurTotale = hauteurTexteApproximative + distanceTexteBoutons + hauteurBoutons + margesH + margesB;
        FrameLayout.LayoutParams layoutParams = new LayoutParamsDeBase_pourFrameLayout(
                ConstraintLayout.LayoutParams.MATCH_PARENT, hauteurTotale, 0, topMargin
        );
        this.setLayoutParams(layoutParams);

        text = new TextePopUp(popUp, hauteurTexteApproximative);
        text.setText(question);
        this.addView(text);
    }

    public void setText(String text) {
        this.text.setText(text);
    }

    public void setRunnableA(Runnable runnable) {
        boutonA.setRunnable(runnable);
    }

    public void setRunnableB(Runnable runnable) {
        boutonB.setRunnable(runnable);
    }
}
