package com.example.veritablejeu.Game.Editeur.SelectionElement;

import android.graphics.Color;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.veritablejeu.Game.Editeur.Editeur;
import com.example.veritablejeu.Game.Editeur.SelectionElement.Association_Image_Modele.Association_Image_Modele;
import com.example.veritablejeu.Game.Editeur.SelectionElement.BoutonModele.BoutonModele;
import com.example.veritablejeu.Game.Editeur.SelectionElement.Categories.Modeles.Modele;
import com.example.veritablejeu.OutilsEnEnum.ScreenUtils;
import com.example.veritablejeu.R;

import java.util.ArrayList;
import java.util.List;

public class SelectionElement implements ISelectionElement {

    private BoutonModele boutonModeleSelectionne;

    public SelectionElement(@NonNull Editeur context) {

        int largeurEcran_max1000 = Math.min(ScreenUtils.getScreenWidth(), 1000);

        float PROPORTION_AUTORISE_DE_ECRAN = 0.9f;
        int largeurAutorisee = (int) (largeurEcran_max1000 * PROPORTION_AUTORISE_DE_ECRAN);
        int margesGBD = (largeurEcran_max1000 - largeurAutorisee) / 2;
        int largeurMaximaleParBouton = largeurAutorisee / 6;

        List<Association_Image_Modele> associations = new ArrayList<>();
        associations.add(new Association_Image_Modele(R.drawable.modele_dalle_violette, Modele.DalleViolette));
        associations.add(new Association_Image_Modele(R.drawable.modele_porte_bc, Modele.PorteBleuClair));
        associations.add(new Association_Image_Modele(R.drawable.modele_porte_bf, Modele.PorteBleuFoncee));
        associations.add(new Association_Image_Modele(R.drawable.modele_porte_rouge, Modele.PorteRouge));
        associations.add(new Association_Image_Modele(R.drawable.modele_mur, Modele.Mur));
        associations.add(new Association_Image_Modele(R.drawable.modele_blob, Modele.Blob));

        associations.add(new Association_Image_Modele(R.drawable.modele_dalle_bc, Modele.DalleBleuClair));
        associations.add(new Association_Image_Modele(R.drawable.modele_dalle_bf, Modele.DalleBleuFoncee));
        associations.add(new Association_Image_Modele(R.drawable.modele_dalle_rouge, Modele.DalleRouge));
        associations.add(new Association_Image_Modele(R.drawable.modele_dalle_orange, Modele.DalleOrange));
        associations.add(new Association_Image_Modele(R.drawable.modele_dalle_verte, Modele.DalleVerte));
        associations.add(new Association_Image_Modele(R.drawable.modele_dalle_jaune, Modele.DalleJaune));

        associations.add(new Association_Image_Modele(R.drawable.modele_square, Modele.Square));
        associations.add(new Association_Image_Modele(R.drawable.modele_fantome, Modele.Fantome));
        associations.add(new Association_Image_Modele(R.drawable.gomme, Modele.Gomme));

        int indexAssociation = 0;
        for(int indexTopMargin = 0; indexTopMargin < 3; indexTopMargin++) {
            for (int index = 0; index < 6; index++) {
                int leftMargin = margesGBD + largeurMaximaleParBouton * index;
                int topMargin = ScreenUtils.getScreenHeight() - (indexTopMargin + 1) * (largeurMaximaleParBouton);
                Association_Image_Modele association = associations.get(indexAssociation);
                BoutonModele boutonNavigation = new BoutonModele(context, association, largeurMaximaleParBouton, leftMargin, topMargin);
                boutonNavigation.setScaleX(.9f);
                boutonNavigation.setScaleY(.9f);

                boutonNavigation.setOnClickListener(v -> {
                    boutonNavigation.effectuerAnimationDeClick();
                    if(boutonModeleSelectionne != null) {
                        boutonModeleSelectionne.setBackground(Color.WHITE);
                    }
                    setBoutonModeleSelectionne(boutonNavigation);
                    if(boutonModeleSelectionne != null) {
                        boutonModeleSelectionne.setBackground(Color.LTGRAY);
                    }
                });

                context.getContainer().addView(boutonNavigation);
                indexAssociation++;
                if(indexAssociation >= 15) return;
            }
        }
    }

    @Nullable
    @Override
    public BoutonModele getBoutonModeleSelectionne() {
        return boutonModeleSelectionne;
    }

    @Override
    public void setBoutonModeleSelectionne(BoutonModele boutonModele) {
        if(boutonModele == boutonModeleSelectionne) {
            boutonModeleSelectionne = null;
        }
        else boutonModeleSelectionne = boutonModele;
    }


}
