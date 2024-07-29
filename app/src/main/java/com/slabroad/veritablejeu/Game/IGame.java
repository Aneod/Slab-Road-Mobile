package com.slabroad.veritablejeu.Game;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.slabroad.veritablejeu.BackEnd.LevelFile.LevelFile;

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
    void goMenu();

    /**
     * Restart level.
     */
    void restart();

    /**
     * Effectue un flash de la couleur souhaité.
     * @param color la couleur du flash.
     */
    void colorFlash(int color);

}
