package com.example.veritablejeu.Game.InGame.ATHFinal.FeuxArtifice;

import androidx.annotation.NonNull;

import com.example.veritablejeu.Game.InGame.ATHFinal.FeuxArtifice.FeuArtifice.FeuArtifice;
import com.example.veritablejeu.Game.InGame.InGame;

import java.util.HashSet;

public class FeuxArtifice implements IFeuxArtifice {

    private final HashSet<FeuArtifice> feux = new HashSet<>();

    public FeuxArtifice(@NonNull InGame context) {
        /*
        Plateau plateauJouable = context.getPlateau();
        LevelFiles levelFiles = plateauJouable.getLevelFiles();
        int couleurBlob = levelFiles.blobColor;
        float margesZoneAutorisee = 0.10f;

        int cordXmin = (int) (ScreenUtils.getScreenWidth() * margesZoneAutorisee);
        int cordXmax = ScreenUtils.getScreenWidth() - cordXmin;
        int cordYmin = (int) (ScreenUtils.getScreenHeight() * margesZoneAutorisee);
        int cordYmax = ScreenUtils.getScreenHeight() - cordYmin;

        int plusGrandeLongueur = Math.max(ScreenUtils.getScreenWidth(), ScreenUtils.getScreenHeight());
        int diametreFeuxFinalMin = plusGrandeLongueur / 4;
        int diametreFeuxFinalMax = plusGrandeLongueur / 2;

        int nbDeFeux = 30;
        for(int index = 0; index < nbDeFeux; index++) {
            int diametreFeu = OutilsMathematiques.random_ouvert(diametreFeuxFinalMin, diametreFeuxFinalMax);
            int leftMargin = OutilsMathematiques.random_ouvert(cordXmin, cordXmax) - diametreFeu / 2;
            int topMargin = OutilsMathematiques.random_ouvert(cordYmin, cordYmax) - diametreFeu / 2;

            FeuArtifice feuArtifice = new FeuArtifice(
                    context, diametreFeu, leftMargin, topMargin, couleurBlob);
            feux.add(feuArtifice);
        }

         */
    }

    @Override
    public void declencherFeux(int startOffSet) {
        for(FeuArtifice feuArtifice : feux) {
            feuArtifice.declencher(startOffSet);
        }
    }
}
