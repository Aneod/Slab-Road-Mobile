package com.example.veritablejeu.Game.PlateauModulaire.ModularSlab.Version.CabledSlab.Cable.DoorIdentity;

import androidx.annotation.NonNull;

import com.example.veritablejeu.Game.PlateauModulaire.Square.WallsOfSquare.Wall.ModularDoor;
import com.example.veritablejeu.Game.PlateauModulaire.Square.WallsOfSquare.WallsOfSquare;
import com.example.veritablejeu.Game.PlateauModulaire.ZdecimalCoordinates.ZdecimalCoordinates;

/**
 * This little class is designed to store the global informations of a door : its direction and coordinates.
 * With this informations it's possible to find the corresponding door on a board.
 */
public class DoorIdentity {

    private final WallsOfSquare.Direction direction;
    private final ZdecimalCoordinates zdecimalCoordinates;

    public DoorIdentity(WallsOfSquare.Direction direction, ZdecimalCoordinates zdecimalCoordinates) {
        this.direction = direction;
        this.zdecimalCoordinates = zdecimalCoordinates;
    }

    public boolean sameOf(ModularDoor modularDoor) {
        if(modularDoor == null) {
            return false;
        }
        return modularDoor.getSquareCoordinates().equals(zdecimalCoordinates) && modularDoor.getDirection().equals(direction);
    }

    @NonNull
    public WallsOfSquare.Direction getDirection() {
        return direction;
    }

    @NonNull
    public ZdecimalCoordinates getZdecimalCoordinates() {
        return zdecimalCoordinates;
    }

}
