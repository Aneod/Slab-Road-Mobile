package com.slabroad.veritablejeu.Game.Board.BoardElement.Square.ModularSlab.Version;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;

import com.slabroad.veritablejeu.Game.Board.BoardElement.Square.ModularSlab.ModularSlab;
import com.slabroad.veritablejeu.Game.Board.BoardElement.Square.ModularSquare;
import com.slabroad.veritablejeu.Game.InGame.InGame;
import com.slabroad.veritablejeu.Tools.CouleurDuJeu;

@SuppressLint("ViewConstructor")
public class OrangeSlab extends ModularSlab {

    public OrangeSlab(@NonNull ModularSquare modularSquare, @NonNull String code) {
        super(modularSquare, CouleurDuJeu.Orange.Int(), code);
    }

    @Override
    public void whenActivation() {
        if(game instanceof InGame) {
            ((InGame) game).verifyCompletion();
        }
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
