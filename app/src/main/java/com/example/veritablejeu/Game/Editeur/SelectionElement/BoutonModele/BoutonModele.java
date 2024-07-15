package com.example.veritablejeu.Game.Editeur.SelectionElement.BoutonModele;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;

import androidx.annotation.NonNull;

import com.example.veritablejeu.Game.Editeur.SelectionElement.Association_Image_Modele.Association_Image_Modele;
import com.example.veritablejeu.Game.Editeur.SelectionElement.Categories.Modeles.Modele;
import com.example.veritablejeu.Navigation.BoutonNavigation.BoutonNavigation;

public class BoutonModele extends BoutonNavigation implements IBoutonModele {

    private final Modele modele;

    public BoutonModele(@NonNull Context context) {
        super(context);
        this.modele = Modele.Gomme;
    }

    public BoutonModele(@NonNull Context context, @NonNull Association_Image_Modele associationImageModele, int diametre, int leftMargin, int topMargin) {
        super(context, diametre, leftMargin, topMargin);
        this.modele = associationImageModele.getModele();
        Integer image = associationImageModele.getImage();
        if(image != null) {
            setImage(image, .6f);
        }
    }

    @Override
    public Modele getModele() {
        return modele;
    }

    @Override
    public void setBackground(int couleur) {
        GradientDrawable backgroundDrawable = getBackgroundDrawable(couleur);
        setBackground(backgroundDrawable);
    }
}
