package com.example.veritablejeu.PopUp.PopUpInitiale;

public interface IPopUpInitiale {

    /**
     * Renvoie en pixel la largeur totale de la pop-up.
     * @return un int.
     */
    int getLargeur();

    /**
     * Renvoie en pixel la hauteur de la barre supérieure de la pop-up.
     * @return un int.
     */
    int getHauteurInitiale();

    /**
     * Renvoie la largeur en pixel de la bordure de la pop-up.
     * @return un int.
     */
    int getLargeurBordure();

    /**
     * Change la hauteur de la popUp pour la hauteur initiale + celle
     * donnée en paramètre.
     * @param hauteurEnPlus la hauteur qu'on ajoute à la hauteur initiale.
     */
    void setHauteurInitiale_plus_autre(int hauteurEnPlus);

    /**
     * Modifie le string du titre.
     * @param nouveauTitre le nouveau String du titre.
     */
    void setTitre(String nouveauTitre);
}
