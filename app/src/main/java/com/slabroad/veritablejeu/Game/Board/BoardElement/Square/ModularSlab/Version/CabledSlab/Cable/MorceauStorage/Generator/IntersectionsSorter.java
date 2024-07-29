package com.slabroad.veritablejeu.Game.Board.BoardElement.Square.ModularSlab.Version.CabledSlab.Cable.MorceauStorage.Generator;

import androidx.annotation.NonNull;

import com.slabroad.veritablejeu.Game.Board.ZdecimalCoordinates.ZdecimalCoordinates;
import com.slabroad.veritablejeu.Game.Board.ZdecimalCoordinates.ZdecimalCoordinatesManager;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class IntersectionsSorter {

    /**
     * Sort the given {@link ZdecimalCoordinates} for create a coherent path, starting with
     * the first given parameter, who is also a coordinate.
     * @param firstCoordinate the starting point of the path.
     * @param squaresSteps all others coordinates to sort.
     * @return a sorted list of givens coordinates.
     */
    @NonNull
    public static ArrayList<ZdecimalCoordinates> sort(ZdecimalCoordinates firstCoordinate, Set<ZdecimalCoordinates> squaresSteps) {
        Set<ZdecimalCoordinates> copy = new HashSet<>(squaresSteps);
        ArrayList<ZdecimalCoordinates> finalList = new ArrayList<>();
        finalList.add(firstCoordinate);
        ZdecimalCoordinates currentStep = firstCoordinate;

        while(!copy.isEmpty()) {
            ZdecimalCoordinates nearestStep = findNearestCoordinate(currentStep, copy);
            copy.remove(nearestStep);
            finalList.add(nearestStep);
            currentStep = nearestStep;
        }

        return finalList;
    }

    /**
     * Among some coordinates, find the nearest one of another specific coordinate.
     * @param current the specific coordinate.
     * @param squaresSteps all coordinates among which one is the nearest the 'current'.
     * @return the nearest coordinates among the given ones.
     */
    private static ZdecimalCoordinates findNearestCoordinate(ZdecimalCoordinates current, @NonNull Set<ZdecimalCoordinates> squaresSteps) {
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
