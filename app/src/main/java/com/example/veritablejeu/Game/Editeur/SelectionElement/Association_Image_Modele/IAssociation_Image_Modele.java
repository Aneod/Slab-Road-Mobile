package com.example.veritablejeu.Game.Editeur.SelectionElement.Association_Image_Modele;

import androidx.annotation.NonNull;

import com.example.veritablejeu.Game.Editeur.SelectionElement.Categories.Modeles.Modele;

import org.jetbrains.annotations.Nullable;

public interface IAssociation_Image_Modele {

    /**
     * Renvoie l'objet Integer donné lors de l'instanciation.
     * @return un Integer. Qui peut être null.
     */
    @Nullable
    Integer getImage();

    /**
     * Renvoie l'objet Modele donné lors de l'instanciation.
     * @return un Modele.
     */
    @NonNull
    Modele getModele();

}
