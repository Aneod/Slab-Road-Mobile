package com.example.veritablejeu.Game.InGame.ATHFinal.BoutonsFinaux;

public interface IBoutonsFinaux {

    /**
     * Lance l'animation d'apparition des boutons.
     * @param startOffSet le délai avant le début de l'animation.
     */
    void apparition(int startOffSet, long duration);

    /**
     * Lance l'animation de disparition des boutons.
     */
    void disparition(long duration);

}
