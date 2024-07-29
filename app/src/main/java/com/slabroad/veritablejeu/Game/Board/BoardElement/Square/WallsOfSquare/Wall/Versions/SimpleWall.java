package com.slabroad.veritablejeu.Game.Board.BoardElement.Square.WallsOfSquare.Wall.Versions;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;

import androidx.annotation.NonNull;

import com.slabroad.veritablejeu.Game.Board.BoardElement.Square.ModularSquare;
import com.slabroad.veritablejeu.Game.Board.BoardElement.Square.WallsOfSquare.Wall.ModularWall;
import com.slabroad.veritablejeu.Game.Board.BoardElement.Square.WallsOfSquare.WallsOfSquare;
import com.slabroad.veritablejeu.LittleWindow.WindowProposal.WindowProposal;
import com.slabroad.veritablejeu.Tools.SimpleBackground;
import com.slabroad.veritablejeu.Tools.Elevation;

import java.util.ArrayList;
import java.util.List;

@SuppressLint("ViewConstructor")
public class SimpleWall extends ModularWall {

    private static final int THINKNESS = 4;
    private static final int ELEVATION = Elevation.NormalWall.getElevation();

    public SimpleWall(@NonNull ModularSquare modularSquare, WallsOfSquare.Direction direction) {
        super(modularSquare, direction);

        GradientDrawable normalBackground = SimpleBackground.create(Color.BLACK);
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
    public List<WindowProposal> getEditPropositions() {
        List<WindowProposal> propositions = new ArrayList<>();
        propositions.add(new WindowProposal("Delete", this::remove, Color.RED, true));
        return propositions;
    }
}
