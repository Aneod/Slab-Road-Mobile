package com.example.veritablejeu.Game.PlateauModulaire.BoardElement.Square.ModularSlab.Version.PurpleSlab;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;

import com.example.veritablejeu.Game.PlateauModulaire.BoardElement.Square.ModularSquare;
import com.example.veritablejeu.Game.PlateauModulaire.BoardElement.Square.ModularSlab.ModularSlab;
import com.example.veritablejeu.Tools.CouleurDuJeu;

@SuppressLint("ViewConstructor")
public class PurpleSlab extends ModularSlab {

    public PurpleSlab(@NonNull ModularSquare modularSquare, @NonNull String code) {
        super(modularSquare, CouleurDuJeu.Violet.Int(), code);
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
        MakeAdjacentGhostsTangible.make(this);
        flash();
        slabDesign.turnOff();
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
