package com.example.veritablejeu.Game.PlateauModulaire.BoardElement.Square.Versions;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;

import androidx.annotation.NonNull;

import com.example.veritablejeu.Game.PlateauModulaire.Board;
import com.example.veritablejeu.Game.PlateauModulaire.BoardElement.Square.ModularSquare;
import com.example.veritablejeu.Tools.CreateSimpleBackground;
import com.example.veritablejeu.Tools.CouleurDuJeu;

@SuppressLint("ViewConstructor")
public class Ghost extends ModularSquare {

    private boolean tangible = false;

    public Ghost(@NonNull Board board, @NonNull String code) {
        super(board, code);
    }

    public void setTangible(boolean tangible) {
        this.tangible = tangible;
    }

    @Override
    public boolean acceptAutomaticWalls() {
        return false;
    }

    @Override
    public GradientDrawable getAccessibleBackground() {
        return CreateSimpleBackground.create(CouleurDuJeu.Mauve.Int(), Color.TRANSPARENT, 24);
    }

    @Override
    public GradientDrawable getInaccessibleBackground() {
        return CreateSimpleBackground.create(Color.DKGRAY, Color.TRANSPARENT, 24);
    }

    @Override
    public boolean isAccessible() {
        return tangible;
    }
}
