package com.example.veritablejeu.Game.PlateauModulaire.ModularSlab.Version.CabledSlab.Cable.CableComponentsStorage;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.veritablejeu.Game.PlateauModulaire.Board;
import com.example.veritablejeu.Game.PlateauModulaire.ModularSlab.Version.CabledSlab.Cable.Cable;
import com.example.veritablejeu.Game.PlateauModulaire.ModularSlab.Version.CabledSlab.Cable.DoorIdentity.DoorIdentity;
import com.example.veritablejeu.Game.PlateauModulaire.ModularSlab.Version.CabledSlab.CabledSlab;
import com.example.veritablejeu.Game.PlateauModulaire.Square.ModularSquare;
import com.example.veritablejeu.Game.PlateauModulaire.Square.WallsOfSquare.Wall.ModularDoor;
import com.example.veritablejeu.Game.PlateauModulaire.Square.WallsOfSquare.Wall.ModularWall;
import com.example.veritablejeu.Game.PlateauModulaire.Square.WallsOfSquare.WallsOfSquare;
import com.example.veritablejeu.Game.PlateauModulaire.ZdecimalCoordinates.ZdecimalCoordinates;

import java.util.HashSet;
import java.util.Set;

public class ComponentsStorage implements IComponentsStorage {

    private final Cable cable;
    private final CabledSlab slab;
    private final Set<ZdecimalCoordinates> intersections = new HashSet<>();
    private ModularDoor door;

    /**
     * Check the interface for more details.
     * @param cabledSlab the only slab to connect.
     * @param code can be null for create a virgin cable.
     * @see IComponentsStorage
     */
    public ComponentsStorage(@NonNull Cable cable, @NonNull CabledSlab cabledSlab) {
        this.cable = cable;
        this.slab = cabledSlab;
    }

    @Override
    public CabledSlab getSlab() {
        return slab;
    }

    @Override
    public Set<ZdecimalCoordinates> getIntersections() {
        return intersections;
    }

    @Override
    public void addIntersection(ZdecimalCoordinates intersection) {
        intersections.add(intersection);
    }

    @Override
    public @Nullable ModularDoor getDoor() {
        return door;
    }

    @Override
    public void connectCorrespondingDoor(DoorIdentity doorIdentity) {
        Board board = getSlab().getBoard();
        ModularDoor correspondingDoor = board.getCorrespondingDoor_OfDoorIdentity(doorIdentity);
        if(correspondingDoor != null) {
            connectDoor(correspondingDoor);
        }
    }

    @Override
    public void connectDoor(@Nullable ModularDoor modularDoor) {
        if(door != null) {
            disconnectDoor();
        }
        if(modularDoor != null) {
            modularDoor.addConnectedCable(this);
            door = modularDoor;
        }
    }

    @Override
    public void disconnectDoor() {
        if(door != null) {
            door.removeConnectedCable(this);
            door = null;
        }
    }

    @Override
    public void deleteCable() {
        cable.delete();
    }
}
