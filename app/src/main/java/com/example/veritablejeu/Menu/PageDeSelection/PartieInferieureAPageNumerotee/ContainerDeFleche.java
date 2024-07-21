package com.example.veritablejeu.Menu.PageDeSelection.PartieInferieureAPageNumerotee;

import android.annotation.SuppressLint;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.example.veritablejeu.Tools.LayoutParams.LayoutParamsDeBase_pourFrameLayout;

@SuppressLint("ViewConstructor")
public class ContainerDeFleche extends FrameLayout {

    private final ImageView image;

    public ImageView getImage() {
        return image;
    }

    /**
     * Création d'un imageView banal. Avec les paramètres suivantes :
     * @param longueurCote la longueur en pixel (horizontal et vertical) de l'imageView.
     * @param leftTopMargin la marge gauche et supérieure.
     * @param image l'image à l'intérieur de l'imageView.
     * @return un imageView.
     */
    @NonNull
    private ImageView getImageView(int longueurCote, int leftTopMargin, int image) {
        ImageView fleche = new ImageView(this.getContext());
        fleche.setImageResource(image);
        FrameLayout.LayoutParams layoutParamsFleche = new LayoutParamsDeBase_pourFrameLayout(longueurCote, longueurCote, leftTopMargin, leftTopMargin);
        fleche.setLayoutParams(layoutParamsFleche);
        return fleche;
    }

    /**
     * Création d'un container avec une image à l'intérieure. Classe pensée pour les flèches
     * en base des pages de sélection.
     * @param parent la PartieInferieureAPageNumerotee parente.
     * @param leftMargin la marge gauche de la classe.
     * @param topMargin la marge supérieure de la classe.
     * @param image l'image mise dans le container.
     */
    public ContainerDeFleche(@NonNull PartieInferieureAPageNumerotee parent, int leftMargin, int topMargin, int image) {
        super(parent.getContext());

        int hauteur = parent.getHauteur();
        FrameLayout.LayoutParams layoutParamsContainer = new LayoutParamsDeBase_pourFrameLayout(hauteur, hauteur, leftMargin, topMargin);
        this.setLayoutParams(layoutParamsContainer);

        int MARGE = 20;
        int hauteurDeImage = hauteur - 2 * MARGE;
        this.image = getImageView(hauteurDeImage, MARGE, image);
        this.addView(this.image);
    }
}
