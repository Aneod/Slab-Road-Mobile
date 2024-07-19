package com.example.veritablejeu.PopUp.PopUpComponent.InlineComponents.Manuel.PageInstruction.ComposantsDePageInstruction.ParagrapheInstruction;

import android.annotation.SuppressLint;
import android.graphics.Color;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.veritablejeu.PopUp.PopUpComponent.InlineComponents.Manuel.PageInstruction.PageInstruction;
import com.example.veritablejeu.Tools.LayoutParams.LayoutParamsDeBase_pourFrameLayout;

@SuppressLint("ViewConstructor")
public class ParagrapheInstruction extends androidx.appcompat.widget.AppCompatTextView implements IParagrapheInstruction {

    private final LayoutParamsDeBase_pourFrameLayout layoutParams;

    @Override
    public int getHauteur() {
        return layoutParams.height;
    }

    @Override
    public void setHauteur(int hauteur) {
        layoutParams.height = hauteur;
    }

    @Override
    public int getTopMargin() {
        return layoutParams.topMargin;
    }

    @Override
    public void setTopMargin(int topMargin) {
        layoutParams.topMargin = topMargin;
    }

    /**
     * Création d'un textView pour un paragraphe du manuel d'instruction.
     * @param parent la page d'instruction parente.
     * @param topMargin la marge supérieure du paragraphe/de la classe.
     */
    public ParagrapheInstruction(PageInstruction parent, int topMargin) {
        super(parent.getContext());
        this.setTextColor(Color.BLACK);
        this.setTextSize(16);
        this.layoutParams = new LayoutParamsDeBase_pourFrameLayout(
                ConstraintLayout.LayoutParams.MATCH_PARENT, 0, 0, topMargin);
        this.setLayoutParams(layoutParams);
    }
}
