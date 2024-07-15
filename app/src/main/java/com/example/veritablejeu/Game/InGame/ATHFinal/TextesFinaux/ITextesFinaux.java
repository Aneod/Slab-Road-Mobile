package com.example.veritablejeu.Game.InGame.ATHFinal.TextesFinaux;

public interface ITextesFinaux {

    /**
     * Refresh les informations affichées.
     */
    void refreshData();

    /**
     * Lance l'animation d'apparition.
     * @param startOffSet le délai avant le début de l'animation.
     */
    void apparition(int startOffSet, long duration);

    /**
     * Lance l'animation de disparition.
     */
    void disparition(long duration);

}
