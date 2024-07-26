package com.example.veritablejeu.Game.InGame.FeuxArtifice;

import androidx.annotation.NonNull;

import com.example.veritablejeu.BackEnd.LevelFile.LevelFile;
import com.example.veritablejeu.Game.Board.Board;
import com.example.veritablejeu.Game.InGame.FeuxArtifice.FeuArtifice.FeuArtifice;
import com.example.veritablejeu.Game.InGame.InGame;
import com.example.veritablejeu.Tools.MathematicTools;
import com.example.veritablejeu.Tools.ScreenUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class FeuxArtifice implements IFeuxArtifice {

    private static final int FIREWORKS_NUMBER = 30;

    private final HashSet<FeuArtifice> feux = new HashSet<>();

    public FeuxArtifice(@NonNull InGame inGame) {

        ArrayList<Board> boards = inGame.getPlateauModulaireSet();
        if(boards.isEmpty()) return;

        Board board = boards.get(0);
        int blobColor = board.getBlobsColor();
        float margesZoneAutorisee = 0.10f;

        int cordXmin = (int) (ScreenUtils.getScreenWidth() * margesZoneAutorisee);
        int cordXmax = ScreenUtils.getScreenWidth() - cordXmin;
        int cordYmin = (int) (ScreenUtils.getScreenHeight() * margesZoneAutorisee);
        int cordYmax = ScreenUtils.getScreenHeight() - cordYmin;

        int plusGrandeLongueur = Math.max(ScreenUtils.getScreenWidth(), ScreenUtils.getScreenHeight());
        int diametreFeuxFinalMin = plusGrandeLongueur / 4;
        int diametreFeuxFinalMax = plusGrandeLongueur / 2;

        for(int index = 0; index < FIREWORKS_NUMBER; index++) {
            int diametreFeu = MathematicTools.random_open(diametreFeuxFinalMin, diametreFeuxFinalMax);
            int leftMargin = MathematicTools.random_open(cordXmin, cordXmax) - diametreFeu / 2;
            int topMargin = MathematicTools.random_open(cordYmin, cordYmax) - diametreFeu / 2;

            FeuArtifice feuArtifice = new FeuArtifice(
                    inGame, diametreFeu, leftMargin, topMargin, blobColor);
            feux.add(feuArtifice);
        }
    }

    @Override
    public void declencherFeux(int startOffSet) {
        for(FeuArtifice feuArtifice : feux) {
            feuArtifice.declencher(startOffSet);
        }
    }
}
