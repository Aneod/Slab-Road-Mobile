package com.example.veritablejeu.Game.InGame.ATHFinal.BoutonsFinaux;

import androidx.annotation.NonNull;

import com.example.veritablejeu.Game.InGame.ATHFinal.BoutonsFinaux.BoutonFInal.BoutonFinal;
import com.example.veritablejeu.Game.InGame.InGame;
import com.example.veritablejeu.Navigation.Association_Symbole_Fonction.Association_Symbole_Fonction;
import com.example.veritablejeu.OutilsEnEnum.ScreenUtils;

import java.util.HashSet;
import java.util.List;

public class BoutonsFinaux implements IBoutonsFinaux {

    private final HashSet<BoutonFinal> boutons = new HashSet<>();

    private void creationBouton(@NonNull InGame context, int diametreBoutons, int leftMargin, int topMarginBoutons, Association_Symbole_Fonction association) {
        BoutonFinal boutonFinal = new BoutonFinal(
                context, diametreBoutons, leftMargin, topMarginBoutons);

        Integer symbole = association.getSymbole();
        if (symbole != null) {
            boutonFinal.setImage(symbole, .6f);
        }

        Runnable fonction = association.getRunnable();
        if (fonction != null) {
            boutonFinal.setOnClickListener(v -> {
                boutonFinal.effectuerAnimationDeClick();
                fonction.run();
            });
        }
        boutons.add(boutonFinal);
    }

    private void creationBoutons(@NonNull InGame context, List<Association_Symbole_Fonction> associations) {
        int nombreDeBoutons = associations.size();
        int diametreBoutons = 100;
        int espaceRestant = ScreenUtils.getScreenWidth() - nombreDeBoutons * diametreBoutons;
        int separationEntreLesBoutons = espaceRestant / (nombreDeBoutons + 1);
        int separationTotale = separationEntreLesBoutons + diametreBoutons;
        int topMarginBoutons = (int) (ScreenUtils.getScreenHeight() * 0.8f);

        for(int index = 0; index < nombreDeBoutons; index++) {
            int leftMargin = separationEntreLesBoutons + separationTotale * index;
            Association_Symbole_Fonction association = associations.get(index);
            creationBouton(context, diametreBoutons, leftMargin, topMarginBoutons, association);
        }
    }

    public BoutonsFinaux(@NonNull InGame context, List<Association_Symbole_Fonction> associations) {
        creationBoutons(context, associations);
    }

    @Override
    public void apparition(int startOffSet, long duration) {
        for(BoutonFinal boutonFinal : boutons) {
            boutonFinal.apparition(startOffSet, duration);
        }
    }

    @Override
    public void disparition(long duration) {
        for(BoutonFinal boutonFinal : boutons) {
            boutonFinal.disparition(duration);
        }
    }
}
