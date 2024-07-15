package com.example.veritablejeu.Game.PlateauModulaire.BoardElement.Square.ModularBlob;

import androidx.annotation.NonNull;

import com.example.veritablejeu.Game.PlateauModulaire.Board;
import com.example.veritablejeu.Game.PlateauModulaire.BoardElement.Square.ModularSquare;
import com.example.veritablejeu.Game.PlateauModulaire.BoardElement.Square.WallsOfSquare.Wall.ModularWall;
import com.example.veritablejeu.Game.PlateauModulaire.BoardElement.Square.WallsOfSquare.WallsOfSquare;
import com.example.veritablejeu.Game.PlateauModulaire.ZdecimalCoordinates.ZdecimalCoordinates;
import com.example.veritablejeu.Game.PlateauModulaire.ZdecimalCoordinates.ZdecimalCoordinatesManager;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BestItinerary {

    @NonNull
    public static ArrayList<ZdecimalCoordinates> get(@NonNull Board board, @NonNull ZdecimalCoordinates here, @NonNull ZdecimalCoordinates to) {
        Set<ZdecimalCoordinates> knownLocations = new HashSet<>();
        ArrayList<ZdecimalCoordinates> itinerary = searching(board, here, to, knownLocations);
        itinerary.remove(0);
        return raccourcirItineraire(board, itinerary);
    }

    @NonNull
    private static ArrayList<ZdecimalCoordinates> searching(@NonNull Board board, ZdecimalCoordinates here, ZdecimalCoordinates to, @NonNull Set<ZdecimalCoordinates> knownLocations) {
        ArrayList<ZdecimalCoordinates> itinerary = new ArrayList<>();

        itinerary.add(here);
        knownLocations.add(here);
        if(here.equals(to)) return itinerary;
        ArrayList<ZdecimalCoordinates> moreLogicDirection = ZdecimalCoordinatesManager.getMoreLogicalDirection(here, to);

        for(ZdecimalCoordinates coordinates : moreLogicDirection) {
            boolean isValidDirection = isValidDirection(board, here, coordinates, knownLocations);
            if(isValidDirection) {
                ArrayList<ZdecimalCoordinates> track = searching(board, coordinates, to, knownLocations);
                ZdecimalCoordinates lastOfTrack = track.get(track.size() - 1);
                boolean destinationFound = lastOfTrack.equals(to);
                if(destinationFound) {
                    itinerary.addAll(track);
                    return itinerary;
                }
            }
        }
        return itinerary;
    }

    /**
     * Pour chaque num de l'itinéaire, on regarde si une étape de l'itinéraire est adjacente (excepté celui juste d'après)
     * Pour chaque num (en commencant pas les premiers), on regarde chaque autre num (en partant du plus loin => meilleur raccourci possible)
     */
    public static ArrayList<ZdecimalCoordinates> raccourcirItineraire(Board plateau, @NonNull ArrayList<ZdecimalCoordinates> itineraire) {

        for(int indexActuel = 0; indexActuel < itineraire.size(); indexActuel++) {
            for (int indexInverse = itineraire.size() - 1; indexInverse >= indexActuel + 2; indexInverse--) {
                if(sontAdjacentsSansMur(plateau, itineraire.get(indexActuel), itineraire.get(indexInverse))) {

                    List<ZdecimalCoordinates> aSupp = itineraire.subList(indexActuel + 1, indexInverse);
                    itineraire.removeAll(aSupp);

                    return raccourcirItineraire(plateau, itineraire);
                }
            }
        }
        return itineraire;
    }

    public static boolean sontAdjacentsSansMur(@NonNull Board board, ZdecimalCoordinates here, ZdecimalCoordinates to) {

        ModularSquare hereSquare = board.getSquareAt(here);
        if(hereSquare == null) return false;

        boolean onTop = ZdecimalCoordinatesManager.isAdjacentTopOf(to, here);
        if(onTop) {
            ModularWall wall = hereSquare.getWalls().get(WallsOfSquare.Direction.Top);
            return wall == null || wall.isTraversable();
        }

        boolean onLeft = ZdecimalCoordinatesManager.isAdjacentLeftOf(to, here);
        if(onLeft) {
            ModularWall wall = hereSquare.getWalls().get(WallsOfSquare.Direction.Left);
            return wall == null || wall.isTraversable();
        }

        boolean onBottom = ZdecimalCoordinatesManager.isAdjacentBottomOf(to, here);
        if(onBottom) {
            ModularWall wall = hereSquare.getWalls().get(WallsOfSquare.Direction.Bottom);
            return wall == null || wall.isTraversable();
        }

        boolean onRight = ZdecimalCoordinatesManager.isAdjacentRightOf(to, here);
        if(onRight) {
            ModularWall wall = hereSquare.getWalls().get(WallsOfSquare.Direction.Right);
            return wall == null || wall.isTraversable();
        }
        return false;
    }

    public static boolean isValidDirection(@NonNull Board board, ZdecimalCoordinates here, ZdecimalCoordinates to, @NonNull Set<ZdecimalCoordinates> knownLocations) {

        for(ZdecimalCoordinates coordinates : knownLocations) {
            if(coordinates.equals(to)) {
                return false;
            }
        }

        if(here == null || to == null) {
            return false;
        }

        boolean onTop = ZdecimalCoordinatesManager.isAdjacentTopOf(to, here);
        if(onTop) {
            return hautValide(board, here);
        }

        boolean onLeft = ZdecimalCoordinatesManager.isAdjacentLeftOf(to, here);
        if(onLeft) {
            return gaucheValide(board, here);
        }

        boolean onBottom = ZdecimalCoordinatesManager.isOnBottomOf(to, here);
        if(onBottom) {
            return basValide(board, here);
        }

        boolean onRight = ZdecimalCoordinatesManager.isOnRightOf(to, here);
        if(onRight) {
            return droiteValide(board, here);
        }

        return false;
    }

    public static boolean hautValide(@NonNull Board board, ZdecimalCoordinates here) {

        ModularSquare squareOfHere = board.getSquareAt(here);
        if(squareOfHere == null || (squareOfHere.getWalls().get(WallsOfSquare.Direction.Top) != null && !squareOfHere.getWalls().get(WallsOfSquare.Direction.Top).isTraversable())) return false;

        ModularSquare sideSquare = squareOfHere.isOnTopOfBoard() ? null : squareOfHere.getSquareOfTop();
        return sideSquare != null && sideSquare.isAccessible();
    }

    public static boolean basValide(@NonNull Board board, ZdecimalCoordinates here) {

        ModularSquare squareOfHere = board.getSquareAt(here);
        if(squareOfHere == null || (squareOfHere.getWalls().get(WallsOfSquare.Direction.Bottom) != null && !squareOfHere.getWalls().get(WallsOfSquare.Direction.Bottom).isTraversable())) return false;

        ModularSquare sideSquare = squareOfHere.isOnBottomOfBoard() ? null : squareOfHere.getSquareOfBottom();
        return sideSquare != null && sideSquare.isAccessible();
    }

    public static boolean gaucheValide(@NonNull Board board, ZdecimalCoordinates here) {

        ModularSquare squareOfHere = board.getSquareAt(here);
        if(squareOfHere == null || (squareOfHere.getWalls().get(WallsOfSquare.Direction.Left) != null && !squareOfHere.getWalls().get(WallsOfSquare.Direction.Left).isTraversable())) return false;

        ModularSquare sideSquare = squareOfHere.isOnLeftOfBoard() ? null : squareOfHere.getSquareOfLeft();
        return sideSquare != null && sideSquare.isAccessible();
    }

    public static boolean droiteValide(@NonNull Board board, ZdecimalCoordinates here) {

        ModularSquare squareOfHere = board.getSquareAt(here);
        if(squareOfHere == null || (squareOfHere.getWalls().get(WallsOfSquare.Direction.Right) != null && !squareOfHere.getWalls().get(WallsOfSquare.Direction.Right).isTraversable())) return false;

        ModularSquare sideSquare = squareOfHere.isOnRightOfBoard() ? null : squareOfHere.getSquareOfRight();
        return sideSquare != null && sideSquare.isAccessible();
    }

}
