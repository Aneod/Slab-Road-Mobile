package com.slabroad.veritablejeu.Game.Board.BoardElement.Square.Versions;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;

import androidx.annotation.NonNull;

import com.slabroad.veritablejeu.Game.Board.Board;
import com.slabroad.veritablejeu.Game.Board.BoardElement.Square.ModularSquare;
import com.slabroad.veritablejeu.Tools.SimpleBackground;
import com.slabroad.veritablejeu.Tools.CouleurDuJeu;

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
        return SimpleBackground.create(CouleurDuJeu.Mauve.Int(), Color.TRANSPARENT, 24);
    }

    @Override
    public GradientDrawable getInaccessibleBackground() {
        return SimpleBackground.create(Color.DKGRAY, Color.TRANSPARENT, 24);
    }

    @Override
    public boolean isAccessible() {
        return tangible;
    }
}
