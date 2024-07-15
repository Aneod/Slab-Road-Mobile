package com.example.veritablejeu.Game.Editeur.SelectionElement.BoutonModele;

import com.example.veritablejeu.Game.Editeur.SelectionElement.Categories.Modeles.Modele;

public interface IBoutonModele {

    /**
     * Renvoie l'objet Modele, unique et final, attaché à la classe.
     * @return un Modele.
     */
    Modele getModele();

    /**
     * Modifie la couleur intérieur du bouton.
     * @param couleur la nouvelle couleur intérieur.
     */
    void setBackground(int couleur);
}
