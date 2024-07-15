package com.example.veritablejeu.Menu.BoutonRedirection.BoutonRedirectionNiveau;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.veritablejeu.LevelFile.LevelFile;
import com.example.veritablejeu.Tools.LayoutParams.LayoutParamsDeBase_pourConstraintLayout;
import com.google.gson.Gson;

/**
 * L'objectif de cette classe est d'avoir une méthode avec un code de plateau en paramètre, pour
 * reçevoir une silhouette de ce plateau sous forme de FrameLayout.
 */
@SuppressLint("ViewConstructor")
public class SilhouettePlateau extends FrameLayout {

    /**
     * Créer un carré noir qui représente un square du plateau.
     * @param parent le frameLayout parent.
     * @param largeurCaseDefinitive la largeur en pixel que prendra le carré.
     * @param posX la marge gauche du carré.
     * @param posY la marge supérieure.
     * @return un imageView.
     */
    private View creationCaseNoire(FrameLayout parent, int largeurCaseDefinitive, int posX, int posY) {
        View carre = new View(parent.getContext());
        carre.setBackgroundColor(Color.BLACK);
        ConstraintLayout.LayoutParams layoutParamsCarre =
                new LayoutParamsDeBase_pourConstraintLayout(
                        largeurCaseDefinitive, largeurCaseDefinitive,
                        posX, posY
                );
        carre.setLayoutParams(layoutParamsCarre);
        return carre;
    }

    /**
     * Ajoute à la classe actuelle un carré noir aux taille et position spécifiées.
     * @param parent la frameLayout parent.
     * @param largeurCaseDefinitive la largeur du carré en pixel.
     * @param rangColonne le rang de la ligne du carré.
     * @param rangLigne le rang de la colonne.
     */
    private void ajouterCaseNoireALaSilhouette(FrameLayout parent, int largeurCaseDefinitive, int rangColonne, int rangLigne) {
        int posX = rangColonne * largeurCaseDefinitive;
        int posY = rangLigne * largeurCaseDefinitive;
        View caseNoire = creationCaseNoire(parent, largeurCaseDefinitive, posX, posY);
        parent.addView(caseNoire);
    }

    public SilhouettePlateau(@NonNull Context context, LevelFile levelFile, int largeur, int hauteur, int leftMargin, int topMargin) {
        super(context);

        int marge = 10;
        int largeurSilhouette = largeur - 2 * marge;
        int hauteurSilhouette = hauteur - 2 * marge;
        int leftMarginSilhouette = leftMargin + marge;
        int topMarginSilhouette = topMargin + marge;

        ConstraintLayout.LayoutParams layoutParamsSilhouette = new ConstraintLayout.LayoutParams(largeurSilhouette, hauteurSilhouette);
        layoutParamsSilhouette.leftToLeft = ConstraintLayout.LayoutParams.PARENT_ID;
        layoutParamsSilhouette.topToTop = ConstraintLayout.LayoutParams.PARENT_ID;
        layoutParamsSilhouette.leftMargin = leftMarginSilhouette;
        layoutParamsSilhouette.topMargin = topMarginSilhouette;
        this.setLayoutParams(layoutParamsSilhouette);

        int nbCasesLargeur = 36;
        int[][] codePlateau = gsonToIntArray(levelFile.sequentialCode);

        if(nbCasesLargeur == 0 || codePlateau.length == 0) return;

        int nbCasesHauteur = 36;
        int largeurMaxDesCasesSurSilhouette = largeurSilhouette / nbCasesLargeur;
        int hauteurMaxDesCasesSurSilhouette = hauteurSilhouette / nbCasesHauteur;
        int largeurCaseDefinitive = Math.min(largeurMaxDesCasesSurSilhouette, hauteurMaxDesCasesSurSilhouette);

        if(largeurMaxDesCasesSurSilhouette < hauteurMaxDesCasesSurSilhouette) {
            layoutParamsSilhouette.topMargin += (hauteurSilhouette - nbCasesHauteur * largeurCaseDefinitive) / 2;
        } else if(hauteurMaxDesCasesSurSilhouette < largeurMaxDesCasesSurSilhouette) {
            layoutParamsSilhouette.leftMargin += (largeurSilhouette - nbCasesLargeur * largeurCaseDefinitive) / 2;
        }

        for(int rangLigne = 0; rangLigne < nbCasesHauteur; rangLigne++) {
            for(int rangColonne = 0; rangColonne < nbCasesLargeur; rangColonne++) {
                int[] codeDeLaCase = codePlateau[rangLigne * nbCasesLargeur + rangColonne];
                if(codeDeLaCase.length > 0 && codeDeLaCase[0] >= 0) {
                    ajouterCaseNoireALaSilhouette(this, largeurCaseDefinitive, rangColonne, rangLigne);
                }
            }
        }
    }

    public static int[][] gsonToIntArray(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, int[][].class);
    }
}
