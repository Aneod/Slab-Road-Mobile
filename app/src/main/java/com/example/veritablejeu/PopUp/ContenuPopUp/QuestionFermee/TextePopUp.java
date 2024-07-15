package com.example.veritablejeu.PopUp.ContenuPopUp.QuestionFermee;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.view.Gravity;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.veritablejeu.PopUp.PopUp;
import com.example.veritablejeu.OutilsEnEnum.LayoutParams.LayoutParamsDeBase_pourConstraintLayout;

@SuppressLint("ViewConstructor")
public class TextePopUp extends androidx.appcompat.widget.AppCompatTextView {

    /**
     * Création du texte de la question d'une popUp.
     * @param popUp le frameLayout parent.
     * @param hauteurTexteApproximative la hauteur supposée du texte.
     */
    public TextePopUp(@NonNull PopUp popUp, int hauteurTexteApproximative) {
        super(popUp.getContext());

        int textSize = 16;
        this.setTextSize(textSize);
        this.setTextColor(Color.BLACK);
        this.setGravity(Gravity.CENTER_HORIZONTAL);

        int margesGDtexte = 125;
        int textWidth = popUp.getLargeur() - 2 * margesGDtexte;
        ConstraintLayout.LayoutParams layoutParamsTexte = new LayoutParamsDeBase_pourConstraintLayout(
                textWidth, hauteurTexteApproximative, margesGDtexte, 0);
        this.setLayoutParams(layoutParamsTexte);
    }
}
