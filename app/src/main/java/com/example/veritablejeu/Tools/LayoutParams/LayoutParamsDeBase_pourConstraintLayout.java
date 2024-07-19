package com.example.veritablejeu.Tools.LayoutParams;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;

public class LayoutParamsDeBase_pourConstraintLayout extends ConstraintLayout.LayoutParams {

    /**
     * Création d'un layoutParams tout ce qu'il y de plus classique.
     * @param largeur la largeur du layoutParams.
     * @param hauteur la hauteur.
     * @param leftMargin la marge gauche.
     * @param topMargin la marge supérieure.
     */
    public LayoutParamsDeBase_pourConstraintLayout(int largeur, int hauteur, int leftMargin, int topMargin) {
        super(largeur, hauteur);
        this.leftToLeft = ConstraintLayout.LayoutParams.PARENT_ID;
        this.topToTop = ConstraintLayout.LayoutParams.PARENT_ID;
        this.leftMargin = leftMargin;
        this.topMargin = topMargin;
    }

    public LayoutParamsDeBase_pourConstraintLayout(@NonNull ConstraintParams constraintParams) {
        super(constraintParams.getWidth(), constraintParams.getHeight());
        this.leftToLeft = ConstraintLayout.LayoutParams.PARENT_ID;
        this.topToTop = ConstraintLayout.LayoutParams.PARENT_ID;
        this.leftMargin = constraintParams.getLeftMargin();
        this.topMargin = constraintParams.getTopMargin();
    }
}
