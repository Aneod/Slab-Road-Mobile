package com.example.veritablejeu.PopUp.PopUpInitiale;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;

import com.example.veritablejeu.Tools.LayoutParams.LayoutParamsDeBase_pourConstraintLayout;
import com.example.veritablejeu.Tools.ScreenUtils;
import com.example.veritablejeu.PopUp.PopUpInitiale.PopUpInitialeComposants.CroixPopUp;
import com.example.veritablejeu.PopUp.PopUpInitiale.PopUpInitialeComposants.TitrePopUp;

public class PopUpInitiale extends FrameLayout implements IPopUpInitiale {
    private final int INITIAL_HEIGHT = 90;
    private final int largeurBordure = 5;
    public final CroixPopUp croixPopUp;
    private final int largeur;
    private final TitrePopUp titre;

    @Override
    public int getLargeur() {
        return largeur;
    }

    @Override
    public int getHauteurInitiale() { return INITIAL_HEIGHT; }

    @Override
    public int getLargeurBordure() {
        return largeurBordure;
    }

    @Override
    public void setHauteurInitiale_plus_autre(int hauteurEnPlus) {
        int hauteurTotale = INITIAL_HEIGHT + hauteurEnPlus;
        getLayoutParams().height = Math.max(0, hauteurTotale);
    }

    @Override
    public void setTitre(String nouveauTitre) {
        titre.setTexte(nouveauTitre);
    }

    public PopUpInitiale(@NonNull Context context) {
        super(context);

        int margeCotes = 50;
        int largeurLogique = ScreenUtils.getScreenWidth() - 2 * margeCotes;
        this.largeur = Math.min(largeurLogique, 1000);

        LayoutParamsDeBase_pourConstraintLayout layoutParams =
                new LayoutParamsDeBase_pourConstraintLayout(largeur, INITIAL_HEIGHT, margeCotes, 175);
        this.setLayoutParams(layoutParams);

        GradientDrawable drawable = new GradientDrawable();
        drawable.setStroke(largeurBordure, Color.BLACK);
        drawable.setColor(Color.WHITE);
        setBackground(drawable);

        this.titre = new TitrePopUp(this, largeur, INITIAL_HEIGHT);
        this.addView(titre);

        this.croixPopUp = new CroixPopUp(this, INITIAL_HEIGHT);
        this.addView(croixPopUp);
    }
}
