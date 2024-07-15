package com.example.veritablejeu.PopUp.ContenuPopUp.Message;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.veritablejeu.PopUp.ContenuPopUp.ContenuPopUp;
import com.example.veritablejeu.PopUp.PopUp;
import com.example.veritablejeu.PopUp.ContenuPopUp.QuestionFermee.TextePopUp;
import com.example.veritablejeu.Tools.LayoutParams.LayoutParamsDeBase_pourConstraintLayout;
import com.example.veritablejeu.Tools.LayoutParams.LayoutParamsDeBase_pourFrameLayout;

@SuppressLint("ViewConstructor")
public class Message extends ContenuPopUp {

    private void fermerPopUpSiContenuInchange(@NonNull PopUp popUp) {
        FrameLayout contenuAuMomentDeFermer = popUp.getContenu();
        boolean leContenuEstInchange = contenuAuMomentDeFermer.equals(Message.this);
        if(leContenuEstInchange) {
            popUp.cacher();
        }
    }

    @NonNull
    private ScaleAnimation getBarreAnimation(PopUp popUp, int duration) {
        ScaleAnimation scaleAnimation = new ScaleAnimation(1.0f, 0.0f, 1.0f, 1.0f);
        scaleAnimation.setDuration(duration);
        scaleAnimation.setFillAfter(true);

        scaleAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {}

            @Override
            public void onAnimationEnd(Animation animation) {
                fermerPopUpSiContenuInchange(popUp);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {}
        });

        return scaleAnimation;
    }

    @NonNull
    private View getBarreDeProgression(@NonNull PopUp popUp) {
        View barre = new View(getContext());
        int leftMargin = popUp.getLargeurBordure();
        int width = popUp.getLargeur() - 2 * leftMargin;
        int hauteurBarre = 10;
        int topMarginBarre = hauteurTotale - hauteurBarre - leftMargin;
        ConstraintLayout.LayoutParams layout = new LayoutParamsDeBase_pourConstraintLayout(
                width, hauteurBarre, leftMargin, topMarginBarre
        );
        barre.setLayoutParams(layout);
        barre.setBackgroundColor(Color.LTGRAY);
        return barre;
    }

    public Message(@NonNull PopUp popUp, String titre, String texte, int duration) {
        super(popUp.getContext(), titre);

        int hauteurTexteApproximative = 100;

        int margesH = 0;
        int margesB = 30;
        int topMargin = popUp.getHauteurInitiale();
        this.hauteurTotale = margesH + hauteurTexteApproximative + margesB;
        FrameLayout.LayoutParams layoutParams =
                new LayoutParamsDeBase_pourFrameLayout(
                        ConstraintLayout.LayoutParams.MATCH_PARENT, hauteurTotale, 0, topMargin
                );
        this.setLayoutParams(layoutParams);

        TextView affirmation = new TextePopUp(popUp, hauteurTexteApproximative);
        affirmation.setText(texte);
        this.addView(affirmation);

        View barreDeProgression = getBarreDeProgression(popUp);
        ScaleAnimation animation = getBarreAnimation(popUp, duration);
        barreDeProgression.startAnimation(animation);
        this.addView(barreDeProgression);
    }
}
