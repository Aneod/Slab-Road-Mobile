package com.example.veritablejeu.Game.Board.BoardElement.Square.ModularSlab.Version.CabledSlab;

import android.annotation.SuppressLint;
import android.graphics.Color;

import androidx.annotation.NonNull;
import androidx.core.util.Consumer;

import com.example.veritablejeu.Game.Board.BoardElement.Square.ModularSlab.ModularSlab;
import com.example.veritablejeu.Game.Board.BoardElement.Square.ModularSlab.Version.CabledSlab.Cable.Cable;
import com.example.veritablejeu.Game.Board.BoardElement.Square.ModularSquare;
import com.example.veritablejeu.Game.Board.BoardElement.Square.WallsOfSquare.Wall.Versions.ModularDoor;
import com.example.veritablejeu.Game.Editeur.Editeur;
import com.example.veritablejeu.Game.Game;
import com.example.veritablejeu.LittleWindow.WindowProposal.WindowProposal;
import com.example.veritablejeu.Tools.CouleurDuJeu;
import com.example.veritablejeu.BackEnd.sequentialCode.Code;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SuppressLint("ViewConstructor")
public class CabledSlab extends ModularSlab {

    private static final char KEY_CABLE = 'c';

    public static char getKeyCable() {
        return KEY_CABLE;
    }

    private final Set<Cable> connectedCables = new HashSet<>();

    public CabledSlab(@NonNull ModularSquare modularSquare, int fillColor, @NonNull String code) {
        super(modularSquare, fillColor, code);

        String subString = code.substring(2);
        if(!subString.isEmpty()) {
            Code.apply(subString,
                    KEY_CABLE, (Consumer<String>) this::addCable
            );
        }
    }

    public Set<Cable> getConnectedCables() {
        return connectedCables;
    }

    @NonNull
    private Cable addCable(String code) {
        Cable cable = new Cable(this, code);
        connectedCables.add(cable);
        return cable;
    }

    public void removeCable(Cable cable) {
        connectedCables.remove(cable);
    }

    public void removeAllCables() {
        Set<Cable> toDelete = new HashSet<>(connectedCables);
        toDelete.forEach(Cable::delete);
    }

    public void printCableOutlines() {
        connectedCables.forEach(completeCable -> completeCable.getMorceauStorage().addBorders());
    }

    public void hideCableOutlines() {
        connectedCables.forEach(completeCable -> completeCable.getMorceauStorage().removeBorders());
    }

    public void verifyDoors() {
        for(Cable cable : connectedCables) {
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
    public List<WindowProposal> getEditPropositions() {
        List<WindowProposal> propositions = new ArrayList<>();
        propositions.add(new WindowProposal("Add cable", this::addAndEditCable, true));
        boolean isBlue = fillColor == CouleurDuJeu.BleuClair.Int() || fillColor == CouleurDuJeu.BleuFonce.Int();
        if(!isBlue) {
            propositions.add(new WindowProposal("Add weight", this::addWeight));
            propositions.add(new WindowProposal("Remove weight", this::removeWeight));
        }
        propositions.add(new WindowProposal("Delete", this::remove, Color.RED, true));
        return propositions;
    }

    private void addAndEditCable() {
        Cable cable = addCable(null);
        Game game = board.getGame();
        if(game instanceof Editeur) {
            ((Editeur) game).enableCableEditing(cable);
        }
    }

    @Override
    public void remove() {
        super.remove();
        removeAllCables();
    }
}
