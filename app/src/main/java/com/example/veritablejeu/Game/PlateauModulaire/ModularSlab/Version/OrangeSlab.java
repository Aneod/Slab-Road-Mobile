package com.example.veritablejeu.Game.PlateauModulaire.ModularSlab.Version;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;

import com.example.veritablejeu.Game.PlateauModulaire.ModularSlab.ModularSlab;
import com.example.veritablejeu.Game.PlateauModulaire.Square.ModularSquare;
import com.example.veritablejeu.Tools.CouleurDuJeu;

@SuppressLint("ViewConstructor")
public class OrangeSlab extends ModularSlab {

    public OrangeSlab(@NonNull ModularSquare modularSquare, @NonNull String code) {
        super(modularSquare, CouleurDuJeu.Orange.Int(), code);
    }

    @Override
    public void whenActivation() {
        game.verifyCompletion();
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
        flash();
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
