package com.example.veritablejeu.Navigation.BoutonNavigation;

public interface IBoutonNavigation {

    /**
     * Renvoie le diametre en pixel du bouton.
     * @return un entier.
     */
    int getDiametre();

    /**
     * Renvoie la marge gauche du bouton.
     * @return un entier.
     */
    int getLeftMargin();

    /**
     * Renvoie la marge droite du bouton.
     * @return un entier.
     */
    int getTopMargin();

    /**
     * Effectue immédiatement une animation de click.
     */
    void effectuerAnimationDeClick();

    /**
     * Ajoute l'image donnée au centre du bouton.
     * @param image la ressource de l'image que l'on souhaite affichée à l'intérieur du bouton.
     * @param proportion un float qui représente la taille de l'image par rapport à celle
     *                   du bouton.
     */
    void setImage(int image, float proportion);

    /**
     * Effectue une animation de scale central selon les paramètres donnés.
     * @param from le scale de départ.
     * @param to le scale à la fin de l'animation.
     * @param startOffSet la durée en ms avant le lancement de l'animation.
     * @param duration la durée en ms de l'animation.
     */
    void scaleAnimation(float from, float to, long startOffSet, long duration);

    /**
     * Active le zoom/dézoom continu du bouton. Utile pour attirer le regard dessus.
     */
    void activerFocus();

}
