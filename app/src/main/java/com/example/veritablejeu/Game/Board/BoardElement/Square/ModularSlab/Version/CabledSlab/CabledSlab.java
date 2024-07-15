package com.example.veritablejeu.Game.Board.BoardElement.Square.ModularSlab.Version.CabledSlab;

import android.annotation.SuppressLint;
import android.graphics.Color;

import androidx.annotation.NonNull;
import androidx.core.util.Consumer;

import com.example.veritablejeu.Game.Board.BoardElement.Square.ModularSlab.ModularSlab;
import com.example.veritablejeu.Game.Board.BoardElement.Square.ModularSlab.Version.CabledSlab.Cable.Cable;
import com.example.veritablejeu.Game.Board.BoardElement.Square.ModularSquare;
import com.example.veritablejeu.Game.Board.BoardElement.Square.WallsOfSquare.Wall.Versions.ModularDoor;
import com.example.veritablejeu.Tools.CouleurDuJeu;
import com.example.veritablejeu.LittleWindow.LittleWindow;
import com.example.veritablejeu.sequentialCode.Code;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SuppressLint("ViewConstructor")
public class CabledSlab extends ModularSlab {
    private final Set<Cable> connectedCable = new HashSet<>();

    public CabledSlab(@NonNull ModularSquare modularSquare, int fillColor, @NonNull String code) {
        super(modularSquare, fillColor, code);

        String subString = code.substring(2);
        if(!subString.isEmpty()) {
            Code.apply(subString,
                    'c', (Consumer<String>) this::addCable
            );
        }
    }

    public Set<Cable> getConnectedCable() {
        return connectedCable;
    }

    private void addCable(String code) {
        if(code == null || code.isEmpty()) {
            return;
        }
        Cable modularCable2 = new Cable(this, code);
        connectedCable.add(modularCable2);
    }

    public void removeCable(Cable cable) {
        connectedCable.remove(cable);
    }

    public void removeAllCables() {
        Set<Cable> toDelete = new HashSet<>(connectedCable);
        toDelete.forEach(Cable::delete);
    }

    public void printCableOutlines() {
        connectedCable.forEach(completeCable -> completeCable.getMorceauStorage().addBorders());
    }

    public void hideCableOutlines() {
        connectedCable.forEach(completeCable -> completeCable.getMorceauStorage().removeBorders());
    }

    public void verifyDoors() {
        for(Cable cable : connectedCable) {
            ModularDoor door = cable.getComponentsStorage().getDoor();
            if(door != null) {
                door.verify();
            }
        }
    }

    @Override
    public void whenActivation() {
        verifyDoors();
    }

    @Override
    public void whenActivation_onlyFirstTime() {
        if(fillColor == CouleurDuJeu.Rouge.Int()) {
            flash();
        }
    }

    @Override
    public void whenDisactivation() {
        verifyDoors();
    }

    @Override
    public void whenDisactivation_onlyFirstTime() {

    }

    @Override
    public void whenBlobAdded() {

    }

    @Override
    public void whenBlobAdded_onlyFirstTime() {

    }

    @Override
    public void whenBlobGone() {

    }

    @Override
    public void whenBlobGone_onlyFirstTime() {

    }

    @Override
    public List<LittleWindow.StringRunnablePair> getEditPropositions() {
        List<LittleWindow.StringRunnablePair> propositions = new ArrayList<>();
        propositions.add(new LittleWindow.StringRunnablePair("Add cable", () -> {}));

        boolean isBlue = fillColor == CouleurDuJeu.BleuClair.Int() || fillColor == CouleurDuJeu.BleuFonce.Int();
        if(!isBlue) {
            propositions.add(new LittleWindow.StringRunnablePair("Add weight", this::addWeight));
            propositions.add(new LittleWindow.StringRunnablePair("Remove weight", this::removeWeight));
        }
        propositions.add(new LittleWindow.StringRunnablePair("Delete", this::remove, Color.RED, true));
        return propositions;
    }

    @Override
    public void remove() {
        super.remove();
        removeAllCables();
    }
}
