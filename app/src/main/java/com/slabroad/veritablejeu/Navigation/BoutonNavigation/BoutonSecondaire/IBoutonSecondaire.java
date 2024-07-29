package com.slabroad.veritablejeu.Navigation.BoutonNavigation.BoutonSecondaire;

public interface IBoutonSecondaire {

    /**
     * Effectue une animation d'agrandissement (scales de 0.0f à 1.0f).
     * Et active la possibilité de cliquer dessus.
     * @param startOffSet la durée en ms avant le lancement de l'animation.
     * @param duration la durée en ms de l'animation.
     */
    void montrer(long startOffSet, long duration);

    /**
     * Effectue une animation de rapetissement (scales de 1.0f à 0.0f).
     * Et désactive la possibilité de cliquer dessus.
     * @param startOffSet la durée en ms avant le lancement de l'animation.
     * @param duration la durée en ms de l'animation.
     */
    void cacher(long startOffSet, long duration);

}
