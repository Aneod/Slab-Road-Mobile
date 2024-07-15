package com.example.veritablejeu.Game.PlateauModulaire.ModularSlab.ModularCable;

import static org.junit.Assert.*;

import com.example.veritablejeu.Game.PlateauModulaire.BoardElement.Square.ModularSlab.Version.CabledSlab.Cable.DoorIdentity.DoorIdentity;
import com.example.veritablejeu.Game.PlateauModulaire.BoardElement.Square.WallsOfSquare.WallsOfSquare;
import com.example.veritablejeu.Game.PlateauModulaire.ZdecimalCoordinates.ZdecimalCoordinates;

import org.junit.Test;

public class DoorIdentityTest {

    @Test
    public void testDoorIdentityCreation() {
        WallsOfSquare.Direction direction = WallsOfSquare.Direction.Top;
        ZdecimalCoordinates coordinates = new ZdecimalCoordinates(5, 10);
        DoorIdentity doorIdentity = new DoorIdentity(direction, coordinates);

        assertEquals(direction, doorIdentity.getDirection());
        assertEquals(coordinates, doorIdentity.getZdecimalCoordinates());
    }

    // Quleques tests resterons impossibles tant que les classes utilisées dans les tests ne seront pas elles-même testables.
    @Test
    public void testSameOfWithMatchingDoor() {
        WallsOfSquare.Direction direction = WallsOfSquare.Direction.Top;
        ZdecimalCoordinates coordinates = new ZdecimalCoordinates(5, 10);
        DoorIdentity doorIdentity = new DoorIdentity(direction, coordinates);

        //ModularDoor modularDoor = new ModularDoor(coordinates, direction);

        //assertTrue(doorIdentity.sameOf(modularDoor));
    }

    @Test
    public void testSameOfWithNonMatchingDoor() {
        WallsOfSquare.Direction direction = WallsOfSquare.Direction.Top;
        ZdecimalCoordinates coordinates = new ZdecimalCoordinates(5, 10);
        DoorIdentity doorIdentity = new DoorIdentity(direction, coordinates);

        //ModularDoor modularDoorDifferentDirection = new ModularDoor(coordinates, WallsOfSquare.Direction.Bottom);
        //ModularDoor modularDoorDifferentCoordinates = new ModularDoor(new ZdecimalCoordinates(6, 11), direction);

        //assertFalse(doorIdentity.sameOf(modularDoorDifferentDirection));
        //assertFalse(doorIdentity.sameOf(modularDoorDifferentCoordinates));
    }

    @Test
    public void testSameOfWithNullDoor() {
        WallsOfSquare.Direction direction = WallsOfSquare.Direction.Top;
        ZdecimalCoordinates coordinates = new ZdecimalCoordinates(5, 10);
        DoorIdentity doorIdentity = new DoorIdentity(direction, coordinates);

        assertFalse(doorIdentity.sameOf(null));
    }
}