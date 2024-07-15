package com.example.veritablejeu.Game;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.veritablejeu.LevelFile.LevelFile;

public interface IGame {

    /**
     * Return the R.id.main of the activity of game.
     * @return an {@link ConstraintLayout}.
     */
    ConstraintLayout getContainer();

    /**
     * Renvoie le fichier niveau associé à la classe.
     * @return un LevelFiles.
     */
    @NonNull
    LevelFile getLevelFiles();

    /**
     * Quitter l'activité pour celle du menu.
     */
    void retourAuMenu();

    /**
     * Effectue un flash de la couleur souhaité.
     * @param couleur la couleur du flash.
     */
    void flashDeCouleur(int couleur);

}
