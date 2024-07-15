package com.example.veritablejeu.Game.PlateauModulaire.BoardElement.Square.ModularSlab.Version.CabledSlab.Cable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.veritablejeu.Game.PlateauModulaire.Board;
import com.example.veritablejeu.Game.PlateauModulaire.BoardElement.Square.ModularSlab.Version.CabledSlab.Cable.DoorIdentity.DoorIdentity;
import com.example.veritablejeu.Game.PlateauModulaire.BoardElement.Square.ModularSlab.Version.CabledSlab.Cable.MorceauStorage.MorceauStorage;
import com.example.veritablejeu.Game.PlateauModulaire.BoardElement.Square.ModularSlab.Version.CabledSlab.CabledSlab;
import com.example.veritablejeu.Game.PlateauModulaire.BoardElement.Square.ModularSlab.Version.CabledSlab.Cable.CableCodeDiscrambler.CableCodeDiscrambler;
import com.example.veritablejeu.Game.PlateauModulaire.BoardElement.Square.ModularSlab.Version.CabledSlab.Cable.CableComponentsStorage.ComponentsStorage;

public class Cable implements ICable {

    private final ComponentsStorage componentsStorage;
    private final MorceauStorage morceauStorage = new MorceauStorage(this);

    /**
     * During the board creation, it's possible to create a cable who is connected to a door that does not yet exist.
     * For complete the cable, we must wait that <u>all doors are built</u>.
     * <br>
     * Then, with the door identity, we search the corresponding door, with
     * {@link ComponentsStorage#connectCorrespondingDoor(DoorIdentity)}, and connected it.
     */
    private DoorIdentity doorIdentity;

    /**
     * A cable take a slab, some intersection points, and maybe a door. With these elements, it
     * generate a visual cable on its board, and connects cables and doors.
     * @param cabledSlab the only slab of the cable.
     *                   A cable exists through a {@link CabledSlab}.
     *                   It's impossible to create a cable without giving it one.
     * @param code the strating code of the cable.
     *             <p>
     *             If the cable is create through a <u>existing level</u>, this code permit to rebuild
     *             the entire cable.
     *             <br>
     *             If the user is editing, and if he create a <u>new cable</u>, this code can be null for
     *             create a virign cable.
     *             </p>
     *             <br>
     *             For know by which shape this code must be, check the
     *             {@link CableCodeDiscrambler#descrypt(Cable, String)} documentation.
     */
    public Cable(@NonNull CabledSlab cabledSlab, @Nullable String code){
        this.componentsStorage = new ComponentsStorage(this, cabledSlab);
        CableCodeDiscrambler.descrypt(this, code);
    }

    @Override
    public Board getBoard() {
        return componentsStorage.getSlab().getBoard();
    }

    @Override
    public ComponentsStorage getComponentsStorage() {
        return componentsStorage;
    }

    @Override
    public MorceauStorage getMorceauStorage() {
        return morceauStorage;
    }

    @Override
    public @Nullable DoorIdentity getDoorIdentity() {
        return doorIdentity;
    }

    @Override
    public void setDoorIdentity(DoorIdentity doorIdentity) {
        this.doorIdentity = doorIdentity;
    }

    @Override
    public void delete() {
        componentsStorage.disconnectDoor();
        componentsStorage.getSlab().removeCable(this);
        morceauStorage.deleteMorceaux();
    }

}
