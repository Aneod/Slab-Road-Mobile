package com.example.veritablejeu.PopUp.ContenuPopUp.QuestionFermee;

import android.content.Context;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.veritablejeu.PopUp.BoutonDePopUp.BoutonDePopUp;
import com.example.veritablejeu.PopUp.ContenuPopUp.ContenuPopUp;
import com.example.veritablejeu.PopUp.PopUp;
import com.example.veritablejeu.Tools.LayoutParams.LayoutParamsDeBase_pourFrameLayout;

public class Question extends ContenuPopUp {

    public Question(@NonNull Context context) {
        super(context);
    }

    public Question(@NonNull PopUp popUp, String titre, String question, String reponseA, Runnable runnableA, String reponseB, Runnable runnableB) {
        super(popUp.getContext(), titre);

        int hauteurTexteApproximative = 100;

        int hauteurBoutons = 75;
        int distanceTexteBoutons = 30;
        int largeurBoutons = 200;
        int margeGauchePremierBouton = (popUp.getLargeur() - distanceTexteBoutons - largeurBoutons * 2) / 2;
        int topMarginBoutons = hauteurTexteApproximative + distanceTexteBoutons;
        BoutonDePopUp boutonA = new BoutonDePopUp(popUp, reponseA, largeurBoutons, hauteurBoutons, margeGauchePremierBouton, topMarginBoutons, runnableA);
        boutonA.prendreLaCouleurBlanche();
        this.addView(boutonA);

        int separationEntreLesBoutons = 45;
        int leftMargin = margeGauchePremierBouton + largeurBoutons + separationEntreLesBoutons;
        BoutonDePopUp boutonB = new BoutonDePopUp(popUp, reponseB, largeurBoutons, hauteurBoutons, leftMargin, topMarginBoutons, runnableB);
        boutonB.prendreLaCouleurNoire();
        this.addView(boutonB);

        int margesH = 15;
        int margesB = 40;
        int topMargin = popUp.getHauteurInitiale() + margesH;
        this.hauteurTotale = hauteurTexteApproximative + distanceTexteBoutons + hauteurBoutons + margesH + margesB;
        FrameLayout.LayoutParams layoutParams = new LayoutParamsDeBase_pourFrameLayout(
                ConstraintLayout.LayoutParams.MATCH_PARENT, hauteurTotale, 0, topMargin
        );
        this.setLayoutParams(layoutParams);

        TextePopUp textViewQuestion = new TextePopUp(popUp, hauteurTexteApproximative);
        textViewQuestion.setText(question);
        this.addView(textViewQuestion);
    }
}
