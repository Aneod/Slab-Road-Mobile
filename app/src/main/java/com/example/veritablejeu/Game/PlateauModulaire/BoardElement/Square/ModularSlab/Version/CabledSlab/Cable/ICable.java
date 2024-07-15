package com.example.veritablejeu.Game.PlateauModulaire.BoardElement.Square.ModularSlab.Version.CabledSlab.Cable;

import androidx.annotation.Nullable;

import com.example.veritablejeu.Game.PlateauModulaire.Board;
import com.example.veritablejeu.Game.PlateauModulaire.BoardElement.Square.ModularSlab.Version.CabledSlab.Cable.DoorIdentity.DoorIdentity;
import com.example.veritablejeu.Game.PlateauModulaire.BoardElement.Square.ModularSlab.Version.CabledSlab.Cable.CableComponentsStorage.ComponentsStorage;
import com.example.veritablejeu.Game.PlateauModulaire.BoardElement.Square.ModularSlab.Version.CabledSlab.Cable.MorceauStorage.MorceauStorage;

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
     * Delete and remove the cable. Disconnet the door and the slab.
     */
    void delete();
}
