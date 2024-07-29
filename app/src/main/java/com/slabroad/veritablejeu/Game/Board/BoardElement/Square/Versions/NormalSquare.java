package com.slabroad.veritablejeu.Game.Board.BoardElement.Square.Versions;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;

import androidx.annotation.NonNull;

import com.slabroad.veritablejeu.Game.Board.Board;
import com.slabroad.veritablejeu.Game.Board.BoardElement.Square.ModularSquare;
import com.slabroad.veritablejeu.Tools.SimpleBackground;

@SuppressLint("ViewConstructor")
public class NormalSquare extends ModularSquare {

    public NormalSquare(@NonNull Board board, @NonNull String code) {
        super(board, code);
    }

    @Override
    public boolean acceptAutomaticWalls() {
        return true;
    }

    @Override
    public GradientDrawable getAccessibleBackground() {
        return SimpleBackground.create(Color.WHITE, Color.LTGRAY, 1);
    }

    @Override
    public GradientDrawable getInaccessibleBackground() {
        return SimpleBackground.create(Color.DKGRAY, Color.BLACK, 1);
    }

    @Override
    public boolean isAccessible() {
        return true;
    }
}
