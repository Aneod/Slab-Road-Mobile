package com.example.veritablejeu.Game.Editeur.SelectionElement;

import androidx.annotation.Nullable;

import com.example.veritablejeu.Game.Editeur.SelectionElement.BoutonModele.BoutonModele;

public interface ISelectionElement {

    /**
     * Renvoie le boutonModele actuellement sélectionné.
     * @return un objet BoutonModele.
     */
    @Nullable
    BoutonModele getBoutonModeleSelectionne();

    /**
     * Modifie la sélection du BoutonModele.
     * @param boutonModele le nouveau BoutonModele considéré comme sélectionné.
     *                     null ou égal au BoutonModele actuellement sélectionné
     *                     -> supprime la sélection.
     */
    void setBoutonModeleSelectionne(BoutonModele boutonModele);

}
