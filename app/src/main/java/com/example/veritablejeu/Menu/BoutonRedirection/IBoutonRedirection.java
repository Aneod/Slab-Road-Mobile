package com.example.veritablejeu.Menu.BoutonRedirection;

import androidx.annotation.Nullable;

public interface IBoutonRedirection {

    /**
     * Change l'image donnée en bas à droite du bouton, en respectant la hauteur maximale donnée,
     * qui est la hauteur (et largeur) maximal de l'image dans le bouton.
     * La hauteur donnée peut être null pour que le logo soit de la hauteur du bouton.
     * <p>
     * En l'état actuel, l'image est soit tout à droite en prenant toute la hauteur, soit en
     * bas à droite en prenant une partie de la hauteur.
     * Ce qui signifie qu'en mettant l'image en bas à droite, mais en laissant le choix de la
     * hauteur il est possible de combiner en un système tous les modèles de boutons existants.
     * @param res une View. Ou null pour supprimer l'image actuelle.
     */
    void setImage(int res);

}
