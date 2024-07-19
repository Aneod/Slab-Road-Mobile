package com.example.veritablejeu.Tools.LayoutParams;

import android.widget.FrameLayout;

import androidx.annotation.NonNull;

public class LayoutParamsDeBase_pourFrameLayout extends FrameLayout.LayoutParams {

    /**
     * Création d'un layoutParams tout ce qu'il y de plus classique.
     * @param largeur la largeur du layoutParams.
     * @param hauteur la hauteur.
     * @param leftMargin la marge gauche.
     * @param topMargin la marge supérieure.
     */
    public LayoutParamsDeBase_pourFrameLayout(int largeur, int hauteur, int leftMargin, int topMargin) {
        super(largeur, hauteur);
        this.leftMargin = leftMargin;
        this.topMargin = topMargin;
    }

    public LayoutParamsDeBase_pourFrameLayout(@NonNull ConstraintParams constraintParams) {
        super(constraintParams.getWidth(), constraintParams.getHeight());
        this.leftMargin = constraintParams.getLeftMargin();
        this.topMargin = constraintParams.getTopMargin();
    }
}
