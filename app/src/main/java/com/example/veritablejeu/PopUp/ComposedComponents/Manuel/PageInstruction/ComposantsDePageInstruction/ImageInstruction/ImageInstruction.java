package com.example.veritablejeu.PopUp.ComposedComponents.Manuel.PageInstruction.ComposantsDePageInstruction.ImageInstruction;

import android.annotation.SuppressLint;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.veritablejeu.PopUp.ComposedComponents.Manuel.PageInstruction.PageInstruction;
import com.example.veritablejeu.Tools.LayoutParams.LayoutParamsDeBase_pourFrameLayout;

@SuppressLint("ViewConstructor")
public class ImageInstruction extends androidx.appcompat.widget.AppCompatImageView implements IImageInstruction {

    private final LayoutParamsDeBase_pourFrameLayout layoutParams;

    @Override
    public int getTopMargin() {
        return layoutParams.topMargin;
    }

    @Override
    public void setTopMargin(int topMargin) {
        layoutParams.topMargin = topMargin;
    }

    @Override
    public void changerImage(int image) {
        setImageResource(image);
    }

    /**
     * Crée une image pour lee manuel d'instruction.
     * @param parent la PageInstruction parente.
     * @param hauteur la hauteur de l'image.
     * @param topMargin la marge supérieure dee l'image.
     */
    public ImageInstruction(PageInstruction parent, int hauteur, int topMargin) {
        super(parent.getContext());
        this.layoutParams = new LayoutParamsDeBase_pourFrameLayout(
                ConstraintLayout.LayoutParams.MATCH_PARENT, hauteur,
                0, topMargin);
        this.setLayoutParams(layoutParams);
    }
}
