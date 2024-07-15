package com.example.veritablejeu.Game.PlateauModulaire.ModularSlab.Version.CabledSlab.Cable.MorceauStorage.Generator;

import android.graphics.Point;

import androidx.annotation.NonNull;

import com.example.veritablejeu.Game.PlateauModulaire.ModularSlab.Version.CabledSlab.Cable.DoorIdentity.DoorIdentity;
import com.example.veritablejeu.Game.PlateauModulaire.ModularSlab.Version.CabledSlab.Cable.Cable;
import com.example.veritablejeu.Game.PlateauModulaire.ModularSlab.Version.CabledSlab.Cable.CableComponentsStorage.ComponentsStorage;
import com.example.veritablejeu.Game.PlateauModulaire.ModularSlab.Version.CabledSlab.Cable.MorceauStorage.Generator.Morceau.Morceau;
import com.example.veritablejeu.Game.PlateauModulaire.ZdecimalCoordinates.ZdecimalCoordinates;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class MorceauxGenerator {

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
        if(doorIdentity != null) {
            Point centerOfDoor = doorIdentity.getDoorIdentityCenter();
            if(centerOfDoor != null) {
                ZdecimalCoordinates lastCoordinates = coordinates.get(coordinates.size() - 1);
                Morceau morceau = new Morceau(cable.getMorceauStorage(), lastCoordinates, centerOfDoor, color, borders);
                finalList.add(morceau);
            }
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

}
