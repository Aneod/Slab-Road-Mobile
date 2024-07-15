package com.example.veritablejeu.Game.InGame.ATHFinal.BoutonsFinaux.BoutonFInal;

public interface IBoutonFinal {

    /**
     * Lance l'animation d'apparition.
     * @param startOffSet le délai avant le début de l'animation.
     * @param duration la durée de l'animation.
     */
    void apparition(int startOffSet, long duration);

    /**
     * Lance l'animation de disparition.
     * @param duration la durée de l'animation.
     */
    void disparition(long duration);

    /**
     * Effectue une animation de transparence selon les paramètres donnés.
     * @param from la transparence de départ.
     * @param to la transparence à la fin.
     * @param startOffSet la durée en ms avant le lancement de l'animation.
     * @param duration la durée en ms de l'animation.
     */
    void alphaAnimation(float from, float to, long startOffSet, long duration);

}
