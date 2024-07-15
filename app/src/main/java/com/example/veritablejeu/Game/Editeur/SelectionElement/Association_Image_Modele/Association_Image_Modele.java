package com.example.veritablejeu.Game.Editeur.SelectionElement.Association_Image_Modele;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.veritablejeu.Game.Editeur.SelectionElement.Categories.Modeles.Modele;

public class Association_Image_Modele implements IAssociation_Image_Modele {

    private final Integer image;
    private final Modele modele;

    public Association_Image_Modele(@Nullable Integer image, @NonNull Modele modele) {
        this.image = image;
        this.modele = modele;
    }

    @Override
    public @org.jetbrains.annotations.Nullable Integer getImage() {
        return image;
    }

    @NonNull
    @Override
    public Modele getModele() {
        return modele;
    }
}
