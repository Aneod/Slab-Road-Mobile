package com.example.veritablejeu.Game.Board.BoardElement.Square.ModularSlab.Version.CabledSlab.Cable;

import androidx.annotation.Nullable;

import com.example.veritablejeu.Game.Board.Board;
import com.example.veritablejeu.Game.Board.BoardElement.Square.ModularSlab.Version.CabledSlab.Cable.DoorIdentity.DoorIdentity;
import com.example.veritablejeu.Game.Board.BoardElement.Square.ModularSlab.Version.CabledSlab.Cable.CableComponentsStorage.ComponentsStorage;
import com.example.veritablejeu.Game.Board.BoardElement.Square.ModularSlab.Version.CabledSlab.Cable.MorceauStorage.MorceauStorage;
import com.example.veritablejeu.Game.Board.BoardElement.Square.WallsOfSquare.Wall.Versions.ModularDoor;
import com.example.veritablejeu.Game.Board.ZdecimalCoordinates.ZdecimalCoordinates;

public interface ICable {

    /**
     * @return the board which the cable is.
     */
    Board getBoard();

    /**
     * @return the {@link ComponentsStorage} of the cable.
     */
    ComponentsStorage getComponentsStorage();

    /**
     * @return the {@link MorceauStorage} of the cable.
     */
    MorceauStorage getMorceauStorage();

    /**
     * @return the {@link DoorIdentity} of the cable.
     */
    @Nullable
    DoorIdentity getDoorIdentity();

    /**
     * Set the {@link DoorIdentity} of the cable.
     * @param doorIdentity the new {@link DoorIdentity} of the cable.
     */
    void setDoorIdentity(DoorIdentity doorIdentity);

    /**
     * Add a coordinate, and refresh the print.
     * @param coordinates the coordinate to add.
     */
    void addIntersection(ZdecimalCoordinates coordinates);

    /**
     * Remove a coordinate, and refresh the print.
     * @param coordinates the coordinate to remove.
     */
    void removeIntersection(ZdecimalCoordinates coordinates);

    /**
     * Connect a door to the cable, and refresh the print.
     * @param door the door to add.
     */
    void connectDoor(ModularDoor door);

    /**
     * Try to connect the door indicates by the {@link DoorIdentity}, and refresh the print.
     * Useful once all board doors are created.
     */
    void connectCorrespondingDoor();

    /**
     * Remove the current door, if it exists, and refresh the print.
     */
    void disconnectDoor();

    /**
     * Delete and remove the cable. Disconnet the door and the slab.
     */
    void delete();
}
