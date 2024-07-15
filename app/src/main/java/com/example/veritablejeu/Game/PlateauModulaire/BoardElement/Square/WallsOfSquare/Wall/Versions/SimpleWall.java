package com.example.veritablejeu.Game.PlateauModulaire.BoardElement.Square.WallsOfSquare.Wall.Versions;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;

import androidx.annotation.NonNull;

import com.example.veritablejeu.Game.PlateauModulaire.BoardElement.Square.ModularSquare;
import com.example.veritablejeu.Game.PlateauModulaire.BoardElement.Square.WallsOfSquare.Wall.ModularWall;
import com.example.veritablejeu.Game.PlateauModulaire.BoardElement.Square.WallsOfSquare.WallsOfSquare;
import com.example.veritablejeu.Tools.CreateSimpleBackground;
import com.example.veritablejeu.PetiteFenetreFlottante.PetiteFenetreFlottante2;

import java.util.ArrayList;
import java.util.List;

@SuppressLint("ViewConstructor")
public class SimpleWall extends ModularWall {

    public SimpleWall(@NonNull ModularSquare modularSquare, WallsOfSquare.Direction direction, String code) {
        super(modularSquare, direction, code);

        GradientDrawable normalBackground = CreateSimpleBackground.create(Color.BLACK);
        WallAspect wallAspect = new WallAspect(normalBackground, 4, 1);
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
    public List<PetiteFenetreFlottante2.StringRunnablePair> getEditPropositions() {
        List<PetiteFenetreFlottante2.StringRunnablePair> propositions = new ArrayList<>();
        propositions.add(new PetiteFenetreFlottante2.StringRunnablePair("Delete", this::remove, Color.RED, true));
        return propositions;
    }
}
