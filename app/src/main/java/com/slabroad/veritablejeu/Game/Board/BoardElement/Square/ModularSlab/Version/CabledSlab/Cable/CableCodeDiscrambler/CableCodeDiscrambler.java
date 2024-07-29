package com.slabroad.veritablejeu.Game.Board.BoardElement.Square.ModularSlab.Version.CabledSlab.Cable.CableCodeDiscrambler;

import com.slabroad.veritablejeu.Game.Board.BoardElement.Square.ModularSlab.Version.CabledSlab.Cable.Cable;
import com.slabroad.veritablejeu.Game.Board.BoardElement.Square.ModularSlab.Version.CabledSlab.Cable.DoorIdentity.DoorIdentity;
import com.slabroad.veritablejeu.Game.Board.BoardElement.Square.WallsOfSquare.WallsOfSquare;
import com.slabroad.veritablejeu.Game.Board.ZdecimalCoordinates.ZdecimalCoordinates;

public class CableCodeDiscrambler {

    /**
     * The given code can take two shapes :
     * <br>
     * With a door : rrffzzaaDxx
     * <br>
     * Without door : rrffzzaa
     * <br>
     * <p>
     *     In 'rrffzzaaDxx' code, there are two parts :
     *     <br><br>
     *     1. 'rrffzzaa' who can be discompose like 'rr' 'ff' 'zz' 'aa'
     *     <br>
     *     These four characters couples representing some {@link ZdecimalCoordinates}.
     *     These coordinates are the coordinates of all intersections.
     *     <br><br>
     *     2. 'Dxx' represents the {@link DoorIdentity} of the given given {@link Cable}.
     *     <br>
     *     'D' is the {@link WallsOfSquare.Direction} of the door.
     *     <br>
     *     'xx' is the {@link ZdecimalCoordinates} of the door square.
     * </p>
     * <br>
     * So if the code is divisible by 2, there isn't a door.
     * @param cable the cable to share the found informations.
     * @param code the code to descrypt.
     */
    public static void descrypt(Cable cable, String code) {
        if(cable == null || cable.getComponentsStorage() == null || code == null || code.length() <= 1) {
            return;
        }

        boolean thereIsADoor = code.length() % 2 == 1;
        int codeLength_forDoor = thereIsADoor ? 3 : 0;
        int codeLength_forIntersections = code.length() - codeLength_forDoor;

        for(int i = 0; i < codeLength_forIntersections; i += 2) {
            char x = code.charAt(i);
            char y = code.charAt(i + 1);
            ZdecimalCoordinates intersection = new ZdecimalCoordinates(x, y);
            cable.getComponentsStorage().addIntersection(intersection);
        }

        if(thereIsADoor) {
            String doorCode = code.substring(code.length() - codeLength_forDoor);
            if(doorCode.length() != 3) return;

            WallsOfSquare.Direction direction = WallsOfSquare.Direction.getOfChar(doorCode.charAt(0));
            ZdecimalCoordinates zdecimalCoordinates = new ZdecimalCoordinates(doorCode.charAt(1), doorCode.charAt(2));
            DoorIdentity doorIdentity = new DoorIdentity(direction, zdecimalCoordinates);
            cable.setDoorIdentity(doorIdentity);
        }
    }
}
