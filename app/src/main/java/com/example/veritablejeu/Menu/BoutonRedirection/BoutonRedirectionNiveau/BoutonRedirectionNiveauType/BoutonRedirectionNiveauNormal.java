package com.example.veritablejeu.Menu.BoutonRedirection.BoutonRedirectionNiveau.BoutonRedirectionNiveauType;

import android.annotation.SuppressLint;
import android.content.Context;

import androidx.annotation.NonNull;

import com.example.veritablejeu.BackEnd.LevelFile.LevelFile;
import com.example.veritablejeu.Menu.BoutonRedirection.BoutonRedirectionNiveau.BoutonRedirectionNiveau;
import com.example.veritablejeu.Menu.PageDeSelection.ListeDefilanteDeNiveaux;

@SuppressLint("ViewConstructor")
public class BoutonRedirectionNiveauNormal extends BoutonRedirectionNiveau {

    /**
     * Création d'un bouton de redirection pour choisir un niveau.
     *
     * @param parent     la frameLayout parent.
     * @param width      la largeur du bouton.
     * @param height     la hauteur du bouton.
     * @param leftMargin la marge gauche.
     * @param topMargin  la marge supérieure.
     * @param levelFile l'id du niveeau vers lequel ce bouton redirige.
     */
    public BoutonRedirectionNiveauNormal(@NonNull Context context, ListeDefilanteDeNiveaux parent, int width, int height, int leftMargin, int topMargin, LevelFile levelFile) {
        super(context, parent, width, height, leftMargin, topMargin, levelFile);
        levelFileAOuvrir = levelFile;
        creeSilouhettePlateau();

        afficherRecordPerso_avecEtoiles();
        afficherObjectif_ou_recordMondial();
    }
}
