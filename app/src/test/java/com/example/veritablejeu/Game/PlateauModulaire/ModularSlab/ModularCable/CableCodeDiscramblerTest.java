package com.example.veritablejeu.Game.PlateauModulaire.ModularSlab.ModularCable;

import static org.junit.Assert.*;

import org.junit.Test;

import com.example.veritablejeu.Game.PlateauModulaire.ModularSlab.Version.CabledSlab.Cable.CableComponentsStorage.ComponentsStorage;
import com.example.veritablejeu.Game.PlateauModulaire.Square.WallsOfSquare.WallsOfSquare;
import com.example.veritablejeu.Game.PlateauModulaire.ZdecimalCoordinates.ZdecimalCoordinates;

import java.util.Set;

public class CableCodeDiscramblerTest {

    @Test
    public void testDescryptWithDoor() {
        String codeWithDoor = "010203r45";
        ComponentsStorage componentsStorage = new ComponentsStorage(codeWithDoor);

        Set<ZdecimalCoordinates> intersections = componentsStorage.getIntersections();
        assertEquals(3, intersections.size());

        for(int yCord = 1; yCord < 4; yCord++) {
            boolean results = false;
            char yChar = Character.forDigit(yCord, 36);
            ZdecimalCoordinates coordinate = new ZdecimalCoordinates('0', yChar);
            for (ZdecimalCoordinates coordinates : intersections) {
                if (coordinates.equals(coordinate)) {
                    results = true;
                    break;
                }
            }
            assertTrue(results);
        }

        boolean results = false;
        char yChar = Character.forDigit(4, 36);
        ZdecimalCoordinates coordinate = new ZdecimalCoordinates('0', yChar);
        for (ZdecimalCoordinates coordinates : intersections) {
            if (coordinates.equals(coordinate)) {
                results = true;
                break;
            }
        }
        assertFalse(results);

        assertNotNull(componentsStorage.getDoorIdentity());
        assertEquals(WallsOfSquare.Direction.Right, componentsStorage.getDoorIdentity().getDirection());
        assertEquals(new ZdecimalCoordinates('4', '5'), componentsStorage.getDoorIdentity().getZdecimalCoordinates());
    }

    @Test
    public void testDescryptWithoutDoor() {
        String codeWithDoor = "010203";
        ComponentsStorage componentsStorage = new ComponentsStorage(codeWithDoor);

        Set<ZdecimalCoordinates> intersections = componentsStorage.getIntersections();
        assertEquals(3, intersections.size());

        for(int yCord = 1; yCord < 4; yCord++) {
            boolean results = false;
            char yChar = Character.forDigit(yCord, 36);
            ZdecimalCoordinates coordinate = new ZdecimalCoordinates('0', yChar);
            for (ZdecimalCoordinates coordinates : intersections) {
                if (coordinates.equals(coordinate)) {
                    results = true;
                    break;
                }
            }
            assertTrue(results);
        }

        boolean results = false;
        char yChar = Character.forDigit(4, 36);
        ZdecimalCoordinates coordinate = new ZdecimalCoordinates('0', yChar);
        for (ZdecimalCoordinates coordinates : intersections) {
            if (coordinates.equals(coordinate)) {
                results = true;
                break;
            }
        }
        assertFalse(results);

        assertNull(componentsStorage.getDoorIdentity());
    }

    @Test
    public void testDescryptOnlyDoor() {
        String codeWithDoor = "l45";
        ComponentsStorage componentsStorage = new ComponentsStorage(codeWithDoor);

        Set<ZdecimalCoordinates> intersections = componentsStorage.getIntersections();
        assertEquals(0, intersections.size());

        assertNotNull(componentsStorage.getDoorIdentity());
        assertEquals(WallsOfSquare.Direction.Left, componentsStorage.getDoorIdentity().getDirection());
        assertEquals(new ZdecimalCoordinates('4', '5'), componentsStorage.getDoorIdentity().getZdecimalCoordinates());
    }

    @Test
    public void testDescryptEmpty() {
        String codeWithDoor = "";
        ComponentsStorage componentsStorage = new ComponentsStorage(codeWithDoor);

        Set<ZdecimalCoordinates> intersections = componentsStorage.getIntersections();
        assertEquals(0, intersections.size());
        assertNull(componentsStorage.getDoorIdentity());
    }

    @Test
    public void testDescryptNull() {
        String codeWithDoor = null;
        ComponentsStorage componentsStorage = new ComponentsStorage(codeWithDoor);

        Set<ZdecimalCoordinates> intersections = componentsStorage.getIntersections();
        assertEquals(0, intersections.size());
        assertNull(componentsStorage.getDoorIdentity());
    }

    @Test
    public void testDescryptNotLongerEnough() {
        String codeWithDoor = "1";
        ComponentsStorage componentsStorage = new ComponentsStorage(codeWithDoor);

        Set<ZdecimalCoordinates> intersections = componentsStorage.getIntersections();
        assertEquals(0, intersections.size());
        assertNull(componentsStorage.getDoorIdentity());
    }

    @Test
    public void testDescryptInvalidCode() {
        String codeWithDoor = "@";
        ComponentsStorage componentsStorage = new ComponentsStorage(codeWithDoor);

        Set<ZdecimalCoordinates> intersections = componentsStorage.getIntersections();
        assertEquals(0, intersections.size());
        assertNull(componentsStorage.getDoorIdentity());
    }

    @Test
    public void testDescryptInvalidCode_2() {
        String codeWithDoor = "#è@";
        ComponentsStorage componentsStorage = new ComponentsStorage(codeWithDoor);

        Set<ZdecimalCoordinates> intersections = componentsStorage.getIntersections();
        assertEquals(0, intersections.size());

        ZdecimalCoordinates defaultCoordinates = new ZdecimalCoordinates(
                ZdecimalCoordinates.getDefaultChar().getCharacter(),
                ZdecimalCoordinates.getDefaultChar().getCharacter()
        );
        assertNotNull(componentsStorage.getDoorIdentity());
        assertEquals(WallsOfSquare.Direction.getDEFAULT(), componentsStorage.getDoorIdentity().getDirection());
        assertEquals(defaultCoordinates, componentsStorage.getDoorIdentity().getZdecimalCoordinates());
    }

    @Test
    public void testDescryptInvalidCode_3() {
        String codeWithDoor = "#è)°@";
        ComponentsStorage componentsStorage = new ComponentsStorage(codeWithDoor);

        Set<ZdecimalCoordinates> intersections = componentsStorage.getIntersections();
        assertEquals(1, intersections.size());

        ZdecimalCoordinates defaultCoordinates = new ZdecimalCoordinates(
                ZdecimalCoordinates.getDefaultChar().getCharacter(),
                ZdecimalCoordinates.getDefaultChar().getCharacter()
        );
        for(ZdecimalCoordinates coordinates : intersections) {
            assertEquals(defaultCoordinates, coordinates);
        }
        assertNotNull(componentsStorage.getDoorIdentity());
        assertEquals(WallsOfSquare.Direction.getDEFAULT(), componentsStorage.getDoorIdentity().getDirection());
        assertEquals(defaultCoordinates, componentsStorage.getDoorIdentity().getZdecimalCoordinates());
    }
}