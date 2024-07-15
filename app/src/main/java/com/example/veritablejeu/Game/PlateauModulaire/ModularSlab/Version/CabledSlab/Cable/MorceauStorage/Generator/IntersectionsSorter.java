package com.example.veritablejeu.Game.PlateauModulaire.ModularSlab.Version.CabledSlab.Cable.MorceauStorage.Generator;

import androidx.annotation.NonNull;

import com.example.veritablejeu.Game.PlateauModulaire.ZdecimalCoordinates.ZdecimalCoordinates;
import com.example.veritablejeu.Game.PlateauModulaire.ZdecimalCoordinates.ZdecimalCoordinatesManager;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class IntersectionsSorter {

    /**
     * Tri la liste des points d'intersection d'un chemin de câble pour que le câble passe
     * par chaque point mais de proche en proche.
     * @return un liste des points d'intersection triée de proche en proche.
     */
    @NonNull
    public static ArrayList<ZdecimalCoordinates> sort(ZdecimalCoordinates slabOriginSquareCoordintes, Set<ZdecimalCoordinates> squaresSteps) {
        Set<ZdecimalCoordinates> copy = new HashSet<>(squaresSteps);
        ArrayList<ZdecimalCoordinates> finalList = new ArrayList<>();
        finalList.add(slabOriginSquareCoordintes);
        ZdecimalCoordinates currentStep = slabOriginSquareCoordintes;

        while(!copy.isEmpty()) {
            ZdecimalCoordinates nearestStep = trouverLePointLePlusProche(currentStep, copy);
            copy.remove(nearestStep);
            finalList.add(nearestStep);
            currentStep = nearestStep;
        }

        return finalList;
    }

    /**
     * A partir d'un point donné et d'une liste d'autres points, on trouve lequel est le plus
     * proche du point donné.
     * @return le numéro du point de la liste le plus proche du point actuel.
     */
    private static ZdecimalCoordinates trouverLePointLePlusProche(ZdecimalCoordinates current, @NonNull Set<ZdecimalCoordinates> squaresSteps) {
        if(squaresSteps.isEmpty()) return current;

        ZdecimalCoordinates nearestStep = current;
        double currentBestDistance = -1;

        for(ZdecimalCoordinates step : squaresSteps) {
            double distance = ZdecimalCoordinatesManager.getDistanceBetween(current, step);
            boolean bestWay = distance < currentBestDistance;
            if(currentBestDistance < 0 || bestWay)  {
                nearestStep = step;
                currentBestDistance = distance;
            }
        }
        return nearestStep;
    }
}
