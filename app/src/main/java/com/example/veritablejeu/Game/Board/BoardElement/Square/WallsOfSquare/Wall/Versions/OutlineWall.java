package com.example.veritablejeu.Game.Board.BoardElement.Square.WallsOfSquare.Wall.Versions;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;

import androidx.annotation.NonNull;

import com.example.veritablejeu.Game.Board.BoardElement.Square.WallsOfSquare.Wall.ModularWall;
import com.example.veritablejeu.Tools.CreateSimpleBackground;
import com.example.veritablejeu.Game.Board.BoardElement.Square.ModularSquare;
import com.example.veritablejeu.Game.Board.BoardElement.Square.WallsOfSquare.WallsOfSquare;
import com.example.veritablejeu.LittleWindow.LittleWindow;

import java.util.List;

@SuppressLint("ViewConstructor")
public class OutlineWall extends ModularWall {

    public OutlineWall(@NonNull ModularSquare modularSquare, WallsOfSquare.Direction direction, String code) {
        super(modularSquare, direction, code);

        GradientDrawable normalBackground = CreateSimpleBackground.create(Color.LTGRAY);
        WallAspect wallAspect = new WallAspect(normalBackground, 4, 0);
        buildVisual(wallAspect);
    }

    @Override
    public boolean isTraversable() {
        return true;
    }

    @Override
    public void enableInGameListeners() {

    }

    @Override
    public List<LittleWindow.StringRunnablePair> getEditPropositions() {
        return null;
    }
}
