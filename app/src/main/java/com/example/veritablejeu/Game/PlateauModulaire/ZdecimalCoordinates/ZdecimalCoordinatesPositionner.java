package com.example.veritablejeu.Game.PlateauModulaire.ZdecimalCoordinates;

import android.graphics.Point;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.veritablejeu.Game.PlateauModulaire.Board;
import com.example.veritablejeu.Game.PlateauModulaire.Square.ModularSquare;
import com.example.veritablejeu.Game.PlateauModulaire.Square.WallsOfSquare.WallsOfSquare;
import com.example.veritablejeu.Game.PlateauModulaire.ZdecimalCoordinates.ZdecimalCharacter.ZdecimalCharacterConverter;

/**
 * This class propose some methods to get squares specific positions, but without square.
 * This class permit to get a Point on the board where there isn't {@link ModularSquare}.
 * It's usefull for place on the board an element on none square.
 * <br>
 * With a known square size and a {@link ZdecimalCoordinates}, it's possible to get
 * a specific position on the board.
 */
public class ZdecimalCoordinatesPositionner {

    @NonNull
    public static Point getCenterOf(@NonNull ZdecimalCoordinates here) {
        int squareSize = Board.SQUARE_SIZE;
        int halfHeight = squareSize / 2;
        int xPos = ZdecimalCharacterConverter.zdecimalCharacter_to_intDecimal(here.getX());
        int yPos = ZdecimalCharacterConverter.zdecimalCharacter_to_intDecimal(here.getY());
        int leftMargin = xPos * squareSize + Board.BORDER_WIDTH + halfHeight;
        int topMargin = yPos * squareSize + Board.BORDER_WIDTH + halfHeight;
        return new Point(leftMargin, topMargin);
    }

    @Nullable
    public static Point getCenterSideOfDirection(ZdecimalCoordinates here, WallsOfSquare.Direction direction) {
        if(here == null || direction == null) {
            return null;
        }
        switch (direction) {
            case Top: return getTopCenterOf(here);
            case Bottom: return getBottomCenterOf(here);
            case Left: return getLeftCenterOf(here);
            case Right: return getRightCenterOf(here);
        }
        return getCenterOf(here);
    }

    @NonNull
    public static Point getTopCenterOf(@NonNull ZdecimalCoordinates here) {
        Point center = getCenterOf(here);
        center.y -= Board.SQUARE_SIZE / 2;
        return center;
    }

    @NonNull
    public static Point getBottomCenterOf(@NonNull ZdecimalCoordinates here) {
        Point center = getCenterOf(here);
        center.y += Board.SQUARE_SIZE / 2;
        return center;
    }

    @NonNull
    public static Point getLeftCenterOf(@NonNull ZdecimalCoordinates here) {
        Point center = getCenterOf(here);
        center.x -= Board.SQUARE_SIZE / 2;
        return center;
    }

    @NonNull
    public static Point getRightCenterOf(@NonNull ZdecimalCoordinates here) {
        Point center = getCenterOf(here);
        center.x += Board.SQUARE_SIZE / 2;
        return center;
    }

}
