package com.example.veritablejeu.Game.PlateauModulaire.ModularSlab.Version.CabledSlab.Cable.MorceauStorage.Generator;

import android.graphics.Point;

import androidx.annotation.NonNull;

import com.example.veritablejeu.Game.PlateauModulaire.ModularSlab.Version.CabledSlab.Cable.DoorIdentity.DoorIdentity;
import com.example.veritablejeu.Game.PlateauModulaire.ModularSlab.Version.CabledSlab.Cable.Cable;
import com.example.veritablejeu.Game.PlateauModulaire.ModularSlab.Version.CabledSlab.Cable.CableComponentsStorage.ComponentsStorage;
import com.example.veritablejeu.Game.PlateauModulaire.ModularSlab.Version.CabledSlab.Cable.MorceauStorage.Generator.CablePart.CablePart;
import com.example.veritablejeu.Game.PlateauModulaire.ZdecimalCoordinates.ZdecimalCoordinates;
import com.example.veritablejeu.Game.PlateauModulaire.ZdecimalCoordinates.ZdecimalCoordinatesPositionner;

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
            CablePart cablePart = new CablePart(cable.getMorceauStorage(), from, to, color, borders);
            finalList.add(cablePart);
        }
        DoorIdentity doorIdentity = cable.getDoorIdentity();
        if(doorIdentity != null) {
            Point centerOfDoor = doorIdentity.getDoorIdentityCenter();
            if(centerOfDoor != null) {
                Point lastCoordinates = coordinates.get(coordinates.size() - 1);
                CablePart cablePart = new CablePart(cable.getMorceauStorage(), lastCoordinates, centerOfDoor, color, borders);
                finalList.add(cablePart);
            }
        }
        return finalList;
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
