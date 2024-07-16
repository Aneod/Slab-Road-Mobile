package com.example.veritablejeu.Game.InGame.ATHFinal;

import androidx.annotation.NonNull;

import com.example.veritablejeu.BackEnd.LevelFile.LevelFile;
import com.example.veritablejeu.Game.InGame.ATHFinal.BoutonsFinaux.BoutonsFinaux;
import com.example.veritablejeu.Game.InGame.ATHFinal.FeuxArtifice.FeuxArtifice;
import com.example.veritablejeu.Game.InGame.ATHFinal.TextesFinaux.TextesFinaux;
import com.example.veritablejeu.Game.InGame.InGame;
import com.example.veritablejeu.Navigation.Association_Symbole_Fonction.Association_Symbole_Fonction;
import com.example.veritablejeu.R;

import java.util.ArrayList;
import java.util.List;

public class ATHFinal implements InterfaceATHFinal {

    private final FeuxArtifice feuxArtifice;
    private final BoutonsFinaux boutonsFinaux;
    private final TextesFinaux textesFinaux;

    private List<Association_Symbole_Fonction> getAssociationsBoutonsFinaux(@NonNull InGame context, @NonNull LevelFile levelFile) {
        List<Association_Symbole_Fonction> associations = new ArrayList<>();
        associations.add(new Association_Symbole_Fonction(R.drawable.symbole_retour, context::retourAuMenu));
        associations.add(new Association_Symbole_Fonction(R.drawable.symbole_reset, context::recommencerPlateauApresAvoirFini));

        boolean possibiliteDeMiseEnLigne = false;
        if(possibiliteDeMiseEnLigne) {
            associations.add(new Association_Symbole_Fonction(R.drawable.upload, context::envoyerLeNiveauDansLeMondial));
        }

        return associations;
    }

    public ATHFinal(@NonNull InGame context) {
        List<Association_Symbole_Fonction> associations = getAssociationsBoutonsFinaux(context, context.getLevelFiles());
        boutonsFinaux = new BoutonsFinaux(context, associations);
        feuxArtifice = new FeuxArtifice(context);
        textesFinaux = new TextesFinaux(context);
    }

    @Override
    public void apparition(int startOffSet, long duration) {
        feuxArtifice.declencherFeux(startOffSet);
        boutonsFinaux.apparition(startOffSet, duration);
        textesFinaux.refreshData();
        textesFinaux.apparition(startOffSet, duration);
    }

    @Override
    public void disparition(long duration) {
        boutonsFinaux.disparition(duration);
        textesFinaux.disparition(duration);
    }
}
