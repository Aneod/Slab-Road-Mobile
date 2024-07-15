package com.example.veritablejeu.Game.PlateauModulaire.ModularSlab.Version.CabledSlab.Cable.DoorIdentity;

import android.graphics.Point;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.veritablejeu.Game.PlateauModulaire.Square.WallsOfSquare.Wall.ModularDoor;
import com.example.veritablejeu.Game.PlateauModulaire.Square.WallsOfSquare.WallsOfSquare;
import com.example.veritablejeu.Game.PlateauModulaire.ZdecimalCoordinates.ZdecimalCoordinates;

public interface IDoorIdentity {

    /**
     * @return the direction of the searching door.
     */
    @NonNull
    WallsOfSquare.Direction getDirection();

    /**
     * @return the coordinates of the searching door.
     */
    @NonNull
    ZdecimalCoordinates getZdecimalCoordinates();

    /**
     * Checks if the given door and the indicating door by the class match.
     * @param modularDoor the door to verify.
     * @return true if match. False otherwise.
     */
    boolean sameOf(ModularDoor modularDoor);

    /**
     * Returns the supposed graphic center of the searching door.
     * @return a {@link Point} with the door center.
     */
    @Nullable
    Point getDoorIdentityCenter();
}
