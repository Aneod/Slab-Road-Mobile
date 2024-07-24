package com.example.veritablejeu.LevelsPanelMVC.LevelsPanel.Scroller.Components.BoutonRedirectionNiveau.BoutonRedirectionNiveauType;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;

import com.example.veritablejeu.BackEnd.LevelFile.LevelFile;
import com.example.veritablejeu.LevelsPanelMVC.LevelsPanel.Scroller.Scroller;
import com.example.veritablejeu.LevelsPanelMVC.LevelsPanel.Scroller.Components.BoutonRedirectionNiveau.BoutonRedirectionNiveau;

@SuppressLint("ViewConstructor")
public class BoutonRedirectionNiveauMondial extends BoutonRedirectionNiveau {

    /**
     * Création d'un bouton de redirection pour choisir un niveau.
     *
     * @param parent     la frameLayout parent.
     * @param topMargin  la marge supérieure.
     * @param levelFile l'id du niveeau vers lequel ce bouton redirige.
     */
    public BoutonRedirectionNiveauMondial(@NonNull Scroller parent, int topMargin, LevelFile levelFile) {
        super(parent, topMargin, levelFile);
        levelFileAOuvrir = levelFile;
        creeSilouhettePlateau();
        afficherLaDate();
        afficherRecordPerso_sansEtoiles();
        afficherObjectif_ou_recordMondial();
    }
}
