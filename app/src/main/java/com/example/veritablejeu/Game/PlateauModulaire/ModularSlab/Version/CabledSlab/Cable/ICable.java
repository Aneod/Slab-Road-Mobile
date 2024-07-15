package com.example.veritablejeu.Game.PlateauModulaire.ModularSlab.Version.CabledSlab.Cable;

import androidx.annotation.Nullable;

import com.example.veritablejeu.Game.PlateauModulaire.ModularSlab.Version.CabledSlab.Cable.DoorIdentity.DoorIdentity;
import com.example.veritablejeu.Game.PlateauModulaire.Square.WallsOfSquare.WallsOfSquare;
import com.example.veritablejeu.Game.PlateauModulaire.ZdecimalCoordinates.ZdecimalCoordinates;

public interface ICable {

    /**
     * @return the door identity.
     */
    @Nullable
    DoorIdentity getDoorIdentity();

    /**
     * Add a door identity.
     * @param direction the direction of the door.
     * @param zdecimalCoordinates the coordinates of the door.
     */
    void setDoorIdentity(DoorIdentity doorIdentity);

}
