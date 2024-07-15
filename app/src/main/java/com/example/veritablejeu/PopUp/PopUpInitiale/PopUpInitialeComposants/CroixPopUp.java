package com.example.veritablejeu.PopUp.PopUpInitiale.PopUpInitialeComposants;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.example.veritablejeu.OutilsEnEnum.LayoutParams.LayoutParamsDeBase_pourFrameLayout;
import com.example.veritablejeu.R;

@SuppressLint("ViewConstructor")
public class CroixPopUp extends FrameLayout {

    /**
     * Calcul la hauteur de la croix de fermeture. Equivalent à 45% de la hauteur de la
     * barre supérieure.
     * @param hauteurBarSuperieure la hauteur de la barre supérieure en pixels.
     * @return la hauteur de la croix en int.
     */
    private int getHauteurDeCroix(int hauteurBarSuperieure) {
        return (int) (hauteurBarSuperieure * 0.45);
    }

    /**
     * Création du layoutParams de la croix.
     * @param hauteur la hauteur de du frameLayout contenant la croix.
     * @return un ConstraintLayout.LayoutParams.
     */
    private FrameLayout.LayoutParams layoutParamsDeCroix(int hauteur) {
        int hauteurCroix = getHauteurDeCroix(hauteur);
        int margeGaucheEtSuperieur = (hauteur - hauteurCroix) / 2;
        return new LayoutParamsDeBase_pourFrameLayout(
                hauteurCroix, hauteurCroix, margeGaucheEtSuperieur, margeGaucheEtSuperieur);
    }

    /**
     * Création la croix de la popUp.
     * @param hauteur la hauteur de du frameLayout contenant la croix.
     * @return un imageView.
     */
    private ImageView croix(int hauteur) {
        ImageView croix = new ImageView(getContext());
        croix.setImageResource(R.drawable.croix);
        croix.setColorFilter(Color.BLACK);
        FrameLayout.LayoutParams layoutParamsDeCroix = layoutParamsDeCroix(hauteur);
        croix.setLayoutParams(layoutParamsDeCroix);
        return croix;
    }

    /**
     * Création d'un frameLayout avec un croix dedans, mais qui ne rempli pas tout l'espace.
     * Ceci afin de pouvoir augmenter facilement la surface qui ferme la popUp sans agrandir
     * la croix.
     * @param parent le frameLayout parent.
     * @param hauteur la hauteur de du frameLayout contenant la croix.
     */
    public CroixPopUp(@NonNull FrameLayout parent, int hauteur) {
        super(parent.getContext());

        FrameLayout.LayoutParams layoutparamsCroixContainer = new FrameLayout.LayoutParams(hauteur, hauteur);
        this.setLayoutParams(layoutparamsCroixContainer);

        ImageView croix = croix(hauteur);
        this.addView(croix);
    }
}
