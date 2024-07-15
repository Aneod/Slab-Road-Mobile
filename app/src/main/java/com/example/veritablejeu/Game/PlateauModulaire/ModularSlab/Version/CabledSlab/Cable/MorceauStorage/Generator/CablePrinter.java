package com.example.veritablejeu.Game.PlateauModulaire.ModularSlab.Version.CabledSlab.Cable.MorceauStorage.Generator;

import android.graphics.Point;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.veritablejeu.Game.PlateauModulaire.Board;
import com.example.veritablejeu.Game.PlateauModulaire.ModularSlab.Version.CabledSlab.Cable.DoorIdentity.DoorIdentity;
import com.example.veritablejeu.Game.PlateauModulaire.ModularSlab.Version.CabledSlab.Cable.Cable;
import com.example.veritablejeu.Game.PlateauModulaire.ModularSlab.Version.CabledSlab.Cable.CableComponentsStorage.ComponentsStorage;
import com.example.veritablejeu.Game.PlateauModulaire.ModularSlab.Version.CabledSlab.Cable.MorceauStorage.Morceau.Morceau;
import com.example.veritablejeu.Game.PlateauModulaire.Square.WallsOfSquare.WallsOfSquare;
import com.example.veritablejeu.Game.PlateauModulaire.ZdecimalCoordinates.ZdecimalCharacter.ZdecimalCharacterConverter;
import com.example.veritablejeu.Game.PlateauModulaire.ZdecimalCoordinates.ZdecimalCoordinates;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * This class exists for print a cable on a board.
 */
public class CablePrinter {

    @NonNull
    public static Set<Morceau> createAllMorceaux(Cable cable, boolean borders) {
        ArrayList<ZdecimalCoordinates> coordinates = getSortedIntersections_ofCable(cable);
        Set<Morceau> finalList = new HashSet<>();
        int color = borders ? Morceau.getOutlineColor() : cable.getComponentsStorage().getSlab().getFillColor();
        for(int index = 0; index < coordinates.size() - 1; index++) {
            ZdecimalCoordinates from = coordinates.get(index);
            ZdecimalCoordinates to = coordinates.get(index + 1);
            Morceau morceau = new Morceau(cable.getMorceauStorage(), from, to, color, borders);
            finalList.add(morceau);
        }
        DoorIdentity doorIdentity = cable.getDoorIdentity();
        Point centerOfDoor = getDoorCenter(doorIdentity);
        if(centerOfDoor != null) {
            ZdecimalCoordinates lastCoordinates = coordinates.get(coordinates.size() - 1);
            Morceau morceau = new Morceau(cable.getMorceauStorage(), lastCoordinates, centerOfDoor, color, borders);
            finalList.add(morceau);
        }
        return finalList;
    }

    @NonNull
    private static ArrayList<ZdecimalCoordinates> getSortedIntersections_ofCable(Cable cable) {
        ArrayList<ZdecimalCoordinates> finalList = new ArrayList<>();
        if(cable == null) {
            return finalList;
        }
        ComponentsStorage componentsStorage = cable.getComponentsStorage();
        if(componentsStorage == null) {
            return finalList;
        }
        ZdecimalCoordinates originSlabCoordinates = componentsStorage.getSlab().getOriginSquare().getCord();
        Set<ZdecimalCoordinates> intersections = componentsStorage.getIntersections();
        return IntersectionsSorter.sort(originSlabCoordinates, intersections);
    }

    @Nullable
    private static Point getDoorCenter(DoorIdentity doorIdentity) {
        if(doorIdentity == null) return null;

        WallsOfSquare.Direction doorDirection = doorIdentity.getDirection();
        ZdecimalCoordinates doorSquareCenterCoordinates = doorIdentity.getZdecimalCoordinates();

        switch (doorDirection) {
            case Top: return getTopCenterOf(doorSquareCenterCoordinates);
            case Bottom: return getBottomCenterOf(doorSquareCenterCoordinates);
            case Left: return getLeftCenterOf(doorSquareCenterCoordinates);
            case Right: return getRightCenterOf(doorSquareCenterCoordinates);
        }
        return getCenterOf(doorSquareCenterCoordinates);
    }

    @NonNull
    private static Point getTopCenterOf(@NonNull ZdecimalCoordinates here) {
        Point center = getCenterOf(here);
        center.y -= Board.SQUARE_SIZE / 2;
        return center;
    }

    @NonNull
    private static Point getBottomCenterOf(@NonNull ZdecimalCoordinates here) {
        Point center = getCenterOf(here);
        center.y += Board.SQUARE_SIZE / 2;
        return center;
    }

    @NonNull
    private static Point getLeftCenterOf(@NonNull ZdecimalCoordinates here) {
        Point center = getCenterOf(here);
        center.x -= Board.SQUARE_SIZE / 2;
        return center;
    }

    @NonNull
    private static Point getRightCenterOf(@NonNull ZdecimalCoordinates here) {
        Point center = getCenterOf(here);
        center.x += Board.SQUARE_SIZE / 2;
        return center;
    }

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

}
