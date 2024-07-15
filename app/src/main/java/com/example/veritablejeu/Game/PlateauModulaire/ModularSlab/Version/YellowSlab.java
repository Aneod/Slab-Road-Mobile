package com.example.veritablejeu.Game.PlateauModulaire.ModularSlab.Version;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;

import com.example.veritablejeu.Game.PlateauModulaire.ModularSlab.ModularSlab;
import com.example.veritablejeu.Game.PlateauModulaire.Square.ModularSquare;
import com.example.veritablejeu.Game.PlateauModulaire.ZdecimalCoordinates.ZdecimalCoordinates;
import com.example.veritablejeu.OutilsEnEnum.CouleurDuJeu;

@SuppressLint("ViewConstructor")
public class YellowSlab extends ModularSlab {

    public YellowSlab(@NonNull ModularSquare modularSquare, @NonNull String code) {
        super(modularSquare, CouleurDuJeu.Jaune.Int(), code);
    }

    @Override
    public void whenActivation() {

    }

    @Override
    public void whenDisactivation() {

    }

    @Override
    public void whenBlobAdded() {

    }

    @Override
    public void whenBlobGone() {

    }

    @Override
    public void whenActivation_onlyFirstTime() {
        addBlobs();
        flash();
        slabDesign.turnOff();
    }

    private void addBlobs() {
        int nbBlobCree = 0;
        int weight = getWeight();

        for (ZdecimalCoordinates numCase : squareNums) {
            if (nbBlobCree >= weight) {
                break;
            }

            ModularSquare square = getBoard().getSquareAt(numCase);
            if (square != null && square.getBlobOnSquare() == null) {
                square.createBlob(null);
                nbBlobCree++;
            }
        }
    }

    @Override
    public void whenDisactivation_onlyFirstTime() {

    }

    @Override
    public void whenBlobAdded_onlyFirstTime() {

    }

    @Override
    public void whenBlobGone_onlyFirstTime() {

    }
}
