package com.example.veritablejeu.PopUp.PopUpInitiale.PopUpInitialeComposants;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.veritablejeu.Tools.ScreenUtils;

@SuppressLint("ViewConstructor")
public class TitrePopUp extends androidx.appcompat.widget.AppCompatTextView {

    public void setTexte(String nouveauTexte) {
        this.setText(nouveauTexte);
    }

    /**
     * Création de l'évènement tactile du titre. Pour bouger la popUp.
     * @param parentView la frameLayoutParent.
     * @param hauteur la hauteur de la fenêtre du titre.
     */
    @SuppressLint("ClickableViewAccessibility")
    public void enableParentTranslationOnSwipe(FrameLayout parentView, int hauteur) {
        this.setOnTouchListener(new View.OnTouchListener() {
            private float startX;
            private float startY;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        startX = event.getRawX();
                        startY = event.getRawY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        float dx = event.getRawX() - startX;
                        float dy = event.getRawY() - startY;

                        if(parentView.getTranslationY() + dy < 0 && dy < 0) dy = 0;
                        else if (parentView.getTranslationY() + dy > ScreenUtils.getScreenHeight() - 4 * hauteur && dy > 0) dy = 0;

                        if(parentView.getTranslationX() + dx < (float) -ScreenUtils.getScreenWidth() / 2 && dx < 0) dx = 0;
                        else if(parentView.getTranslationX() + dx > (float) ScreenUtils.getScreenWidth() / 2 && dx > 0) dx = 0;

                        parentView.setTranslationX(parentView.getTranslationX() + dx);
                        parentView.setTranslationY(parentView.getTranslationY() + dy);

                        startX = event.getRawX();
                        startY = event.getRawY();
                        break;
                }
                return true;
            }
        });
    }

    /**
     * Création du titre visuelle de la popUp.
     * @param parent le frameLayout parent.
     * @param largeurInitiale la largeur de la popUp.
     * @param hauteur la hauteur.
     */
    public TitrePopUp(FrameLayout parent, int largeurInitiale, int hauteur) {
        super(parent.getContext());
        this.setTextColor(Color.BLACK);
        this.setTextSize(18);
        ConstraintLayout.LayoutParams layoutParamsDeTitre = new ConstraintLayout.LayoutParams(largeurInitiale, hauteur);
        layoutParamsDeTitre.leftToLeft = ConstraintLayout.LayoutParams.PARENT_ID;
        layoutParamsDeTitre.topToTop = ConstraintLayout.LayoutParams.PARENT_ID;
        this.setLayoutParams(layoutParamsDeTitre);
        this.setGravity(Gravity.CENTER);
        enableParentTranslationOnSwipe(parent, hauteur);
    }
}
