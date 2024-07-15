package com.example.veritablejeu.Game.Board.BoardElement.Square.WallsOfSquare.Wall.Versions;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;

import androidx.annotation.NonNull;

import com.example.veritablejeu.Game.Board.BoardElement.Square.ModularSquare;
import com.example.veritablejeu.Game.Board.BoardElement.Square.WallsOfSquare.Wall.ModularWall;
import com.example.veritablejeu.Game.Board.BoardElement.Square.WallsOfSquare.WallsOfSquare;
import com.example.veritablejeu.Tools.CreateSimpleBackground;
import com.example.veritablejeu.LittleWindow.LittleWindow;
import com.example.veritablejeu.Tools.Elevation;

import java.util.ArrayList;
import java.util.List;

@SuppressLint("ViewConstructor")
public class SimpleWall extends ModularWall {

    private static final int THINKNESS = 4;
    private static final int ELEVATION = Elevation.NormalWall.getElevation();

    public SimpleWall(@NonNull ModularSquare modularSquare, WallsOfSquare.Direction direction) {
        super(modularSquare, direction);

        GradientDrawable normalBackground = CreateSimpleBackground.create(Color.BLACK);
        WallAspect wallAspect = new WallAspect(normalBackground, THINKNESS, ELEVATION);
        buildVisual(wallAspect);
    }

    @Override
    public boolean isTraversable() {
        return false;
    }

    @Override
    public void enableInGameListeners() {

    }

    @Override
    public List<LittleWindow.StringRunnablePair> getEditPropositions() {
        List<LittleWindow.StringRunnablePair> propositions = new ArrayList<>();
        propositions.add(new LittleWindow.StringRunnablePair("Delete", this::remove, Color.RED, true));
        return propositions;
    }
}
