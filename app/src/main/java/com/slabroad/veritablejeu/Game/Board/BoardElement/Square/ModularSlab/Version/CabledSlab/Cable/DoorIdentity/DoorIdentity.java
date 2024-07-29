package com.slabroad.veritablejeu.Game.Board.BoardElement.Square.ModularSlab.Version.CabledSlab.Cable.DoorIdentity;

import android.graphics.Point;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.slabroad.veritablejeu.Game.Board.BoardElement.Square.WallsOfSquare.WallsOfSquare;
import com.slabroad.veritablejeu.Game.Board.BoardElement.Square.WallsOfSquare.Wall.Versions.ModularDoor;
import com.slabroad.veritablejeu.Game.Board.ZdecimalCoordinates.ZdecimalCoordinates;
import com.slabroad.veritablejeu.Game.Board.ZdecimalCoordinates.ZdecimalCoordinatesPositionner;

/**
 * This little class is designed to store the global informations of a door : its direction and coordinates.
 * With this informations it's possible to find the corresponding door on a board.
 */
public class DoorIdentity implements IDoorIdentity {

    private final WallsOfSquare.Direction direction;
    private final ZdecimalCoordinates zdecimalCoordinates;

    public DoorIdentity(WallsOfSquare.Direction direction, ZdecimalCoordinates zdecimalCoordinates) {
        this.direction = direction;
        this.zdecimalCoordinates = zdecimalCoordinates;
    }

    @Override
    @NonNull
    public WallsOfSquare.Direction getDirection() {
        return direction;
    }

    @Override
    @NonNull
    public ZdecimalCoordinates getZdecimalCoordinates() {
        return zdecimalCoordinates;
    }

    @Override
    public boolean sameOf(ModularDoor modularDoor) {
        if(modularDoor == null) {
            return false;
        }
        return modularDoor.getSquareCoordinates().equals(zdecimalCoordinates) && modularDoor.getDirection().equals(direction);
    }

    @Override
    @Nullable
    public Point getDoorIdentityCenter() {
        return ZdecimalCoordinatesPositionner.getCenterSideOfDirection(zdecimalCoordinates, direction);
    }

}
