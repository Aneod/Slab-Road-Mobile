package com.example.veritablejeu.Game.PlateauModulaire.Square;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;

import androidx.annotation.NonNull;

import com.example.veritablejeu.Game.PlateauModulaire.Board;
import com.example.veritablejeu.Game.PlateauModulaire.CreateSimpleBackground;

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
        return CreateSimpleBackground.create(Color.WHITE, Color.LTGRAY, 1);
    }

    @Override
    public GradientDrawable getInaccessibleBackground() {
        return CreateSimpleBackground.create(Color.DKGRAY, Color.BLACK, 1);
    }

    @Override
    public boolean isAccessible() {
        return true;
    }
}
