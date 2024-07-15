package com.example.veritablejeu.Game.PlateauModulaire.ModularSlab.Version.CabledSlab.Cable.CableComponentsStorage;

import androidx.annotation.Nullable;

import com.example.veritablejeu.Game.PlateauModulaire.ModularSlab.Version.CabledSlab.Cable.DoorIdentity.DoorIdentity;
import com.example.veritablejeu.Game.PlateauModulaire.ModularSlab.Version.CabledSlab.CabledSlab;
import com.example.veritablejeu.Game.PlateauModulaire.Square.WallsOfSquare.Wall.ModularDoor;
import com.example.veritablejeu.Game.PlateauModulaire.ZdecimalCoordinates.ZdecimalCoordinates;

import java.util.Set;

/**
 * This class stores the cable informations.
 * <br>
 * The connected slab can't be removed of the cable. And can't be changed.
 * <br>
 * A connected door can be added, removed and changed.
 * <br>
 * This class is designed for manage these elements. Not for print cables.
 */
public interface IComponentsStorage {

    /**
     * @return the connect slab.
     */
    CabledSlab getSlab();

    /**
     * @return intersections.
     */
    Set<ZdecimalCoordinates> getIntersections();

    /**
     * Add a intersection.
     * @param intersection the intersection to add.
     */
    void addIntersection(ZdecimalCoordinates intersection);

    /**
     * @return the connected door.
     */
    @Nullable
    ModularDoor getDoor();

    /**
     * Try to add the indicates door by the given {@link DoorIdentity}. This method works once
     * the board have all their doors.
     */
    void connectCorrespondingDoor(DoorIdentity doorIdentity);

    /**
     * Add (or change) the door of in the storage.
     * <br>
     * If there is a door in the storage, disconnect it with {@link #disconnectDoor()}, and remove it.
     * <br>
     * Add the given door in the storage, and connect it.
     * @param modularDoor the door to connect.
     *                    <br>
     *                    Can be null for just disconnect the connected door. Use {@link #disconnectDoor()} instead.
     */
    void connectDoor(@Nullable ModularDoor modularDoor);

    /**
     * Disconnect the connected door and remove it from the storage.
     */
    void disconnectDoor();

}
