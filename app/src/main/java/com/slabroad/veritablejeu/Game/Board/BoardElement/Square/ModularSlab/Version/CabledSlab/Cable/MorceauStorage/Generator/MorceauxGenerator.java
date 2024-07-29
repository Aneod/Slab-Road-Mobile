package com.slabroad.veritablejeu.Game.Board.BoardElement.Square.ModularSlab.Version.CabledSlab.Cable.MorceauStorage.Generator;

import android.graphics.Point;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.slabroad.veritablejeu.Game.Board.BoardElement.Square.ModularSlab.Version.CabledSlab.Cable.DoorIdentity.DoorIdentity;
import com.slabroad.veritablejeu.Game.Board.BoardElement.Square.ModularSlab.Version.CabledSlab.Cable.MorceauStorage.Generator.CablePart.CablePart;
import com.slabroad.veritablejeu.Game.Board.BoardElement.Square.ModularSlab.Version.CabledSlab.Cable.Cable;
import com.slabroad.veritablejeu.Game.Board.BoardElement.Square.ModularSlab.Version.CabledSlab.Cable.CableComponentsStorage.ComponentsStorage;
import com.slabroad.veritablejeu.Game.Board.ZdecimalCoordinates.ZdecimalCoordinates;
import com.slabroad.veritablejeu.Game.Board.ZdecimalCoordinates.ZdecimalCoordinatesPositionner;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class MorceauxGenerator {

    @NonNull
    public static Set<CablePart> createAllMorceaux(Cable cable, boolean borders) {
        ArrayList<Point> coordinates = getSortedIntersections_ofCable(cable);
        Set<CablePart> finalList = new HashSet<>();
        int color = borders ? CablePart.getOutlineColor() : cable.getComponentsStorage().getSlab().getFillColor();
        for(int index = 0; index < coordinates.size() - 1; index++) {
            Point from = coordinates.get(index);
            Point to = coordinates.get(index + 1);
            CablePart cablePart = new CablePart(cable, from, to, color, borders);
            finalList.add(cablePart);
        }

        Point lastCoordinates = coordinates.get(coordinates.size() - 1);
        CablePart lastCablePart = createCablePartsOfDoor(cable, lastCoordinates, color, borders);
        if(lastCablePart != null) {
            finalList.add(lastCablePart);
        }
        return finalList;
    }

    @Nullable
    private static CablePart createCablePartsOfDoor(Cable cable, Point lastCoordinate, int color, boolean borders) {
        if(noDoorToConnect(cable)) return null;
        Point centerOfDoor = getDoorIdentityCenter(cable);
        if(centerOfDoor == null) return null;
        return new CablePart(cable, lastCoordinate, centerOfDoor, color, borders);
    }

    private static boolean noDoorToConnect(Cable cable) {
        if(cable == null) return true;
        return !cable.getComponentsStorage().isConnectedToADoor();
    }

    @Nullable
    private static Point getDoorIdentityCenter(Cable cable) {
        if(cable == null) return null;
        DoorIdentity doorIdentity = cable.getDoorIdentity();
        if(doorIdentity == null) return null;
        return doorIdentity.getDoorIdentityCenter();
    }

    @NonNull
    private static ArrayList<Point> getSortedIntersections_ofCable(Cable cable) {
        ArrayList<Point> finalList = new ArrayList<>();
        if(cable == null) {
            return finalList;
        }
        ComponentsStorage componentsStorage = cable.getComponentsStorage();
        if(componentsStorage == null) {
            return finalList;
        }
        ZdecimalCoordinates originSlabCoordinates = componentsStorage.getSlab().getOriginSquare().getCord();
        Set<ZdecimalCoordinates> intersections = componentsStorage.getIntersections();
        ArrayList<ZdecimalCoordinates> sortedIntersections = IntersectionsSorter.sort(originSlabCoordinates, intersections);
        for(ZdecimalCoordinates coordinates : sortedIntersections) {
            Point equivalent = ZdecimalCoordinatesPositionner.getCenterOf(coordinates);
            finalList.add(equivalent);
        }
        return finalList;
    }

}
