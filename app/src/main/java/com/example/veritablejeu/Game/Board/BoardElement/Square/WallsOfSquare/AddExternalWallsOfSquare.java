package com.example.veritablejeu.Game.Board.BoardElement.Square.WallsOfSquare;

import androidx.annotation.NonNull;

import com.example.veritablejeu.Game.Board.BoardElement.Square.ModularSquare;
import com.example.veritablejeu.Game.Board.ZdecimalCoordinates.ZdecimalCoordinates;
import com.example.veritablejeu.Game.Board.ZdecimalCoordinates.ZdecimalCoordinatesManager;

public class AddExternalWallsOfSquare {

    private static final char WALL_TYPE = '1';

    public static void add(@NonNull ModularSquare modularSquare) {
        boolean acceptAutomaticWalls = modularSquare.acceptAutomaticWalls();
        if(!acceptAutomaticWalls) return;
        addExternalTopWall(modularSquare);
        addExternalBottomWall(modularSquare);
        addExternalLeftWall(modularSquare);
        addExternalRightWall(modularSquare);
    }

    private static void addExternalTopWall(@NonNull ModularSquare modularSquare) {
        ZdecimalCoordinates sideCord = ZdecimalCoordinatesManager.getTopOf(modularSquare.getCord());
        ModularSquare squareOfSideCord = modularSquare.getBoard().getSquareAt(sideCord);
        if(squareOfSideCord == null || !squareOfSideCord.acceptAutomaticWalls()) modularSquare.addWalls("t11" + WALL_TYPE);
    }

    private static void addExternalBottomWall(@NonNull ModularSquare modularSquare) {
        ZdecimalCoordinates sideCord = ZdecimalCoordinatesManager.getBottomOf(modularSquare.getCord());
        ModularSquare squareOfSideCord = modularSquare.getBoard().getSquareAt(sideCord);
        if(squareOfSideCord == null || !squareOfSideCord.acceptAutomaticWalls()) modularSquare.addWalls("b11" + WALL_TYPE);
    }

    private static void addExternalLeftWall(@NonNull ModularSquare modularSquare) {
        ZdecimalCoordinates sideCord = ZdecimalCoordinatesManager.getLeftOf(modularSquare.getCord());
        ModularSquare squareOfSideCord = modularSquare.getBoard().getSquareAt(sideCord);
        if(squareOfSideCord == null || !squareOfSideCord.acceptAutomaticWalls()) modularSquare.addWalls("l11" + WALL_TYPE);
    }

    private static void addExternalRightWall(@NonNull ModularSquare modularSquare) {
        ZdecimalCoordinates sideCord = ZdecimalCoordinatesManager.getRightOf(modularSquare.getCord());
        ModularSquare squareOfSideCord = modularSquare.getBoard().getSquareAt(sideCord);
        if(squareOfSideCord == null || !squareOfSideCord.acceptAutomaticWalls()) modularSquare.addWalls("r11" + WALL_TYPE);
    }

}
