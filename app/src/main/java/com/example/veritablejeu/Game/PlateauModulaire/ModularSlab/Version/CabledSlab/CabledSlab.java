package com.example.veritablejeu.Game.PlateauModulaire.ModularSlab.Version.CabledSlab;

import android.annotation.SuppressLint;
import android.graphics.Color;

import androidx.annotation.NonNull;
import androidx.core.util.Consumer;

import com.example.veritablejeu.Game.PlateauModulaire.ModularSlab.Version.CabledSlab.Cable.Cable;
import com.example.veritablejeu.Game.PlateauModulaire.ModularSlab.ModularSlab;
import com.example.veritablejeu.Game.PlateauModulaire.Square.ModularSquare;
import com.example.veritablejeu.Game.PlateauModulaire.Square.WallsOfSquare.Wall.ModularDoor;
import com.example.veritablejeu.Tools.CouleurDuJeu;
import com.example.veritablejeu.PetiteFenetreFlottante.PetiteFenetreFlottante2;
import com.example.veritablejeu.sequentialCode.Code;

import org.jetbrains.annotations.Contract;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SuppressLint("ViewConstructor")
public class CabledSlab extends ModularSlab {
    private final Set<Cable> connectedCable = new HashSet<>();

    @NonNull
    @Contract("_, _ -> new")
    public static CabledSlab lightBlue(@NonNull ModularSquare modularSquare, @NonNull String code) {
        return new CabledSlab(modularSquare, CouleurDuJeu.BleuClair.Int(), code);
    }

    @NonNull
    @Contract("_, _ -> new")
    public static CabledSlab darkBlue(@NonNull ModularSquare modularSquare, @NonNull String code) {
        return new CabledSlab(modularSquare, CouleurDuJeu.BleuFonce.Int(), code);
    }

    @NonNull
    @Contract("_, _ -> new")
    public static CabledSlab red(@NonNull ModularSquare modularSquare, @NonNull String code) {
        return new CabledSlab(modularSquare, CouleurDuJeu.Rouge.Int(), code);
    }

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
    public List<PetiteFenetreFlottante2.StringRunnablePair> getEditPropositions() {
        List<PetiteFenetreFlottante2.StringRunnablePair> propositions = new ArrayList<>();
        propositions.add(new PetiteFenetreFlottante2.StringRunnablePair("Add cable", this::addWeight));

        boolean isBlue = fillColor == CouleurDuJeu.BleuClair.Int() || fillColor == CouleurDuJeu.BleuFonce.Int();
        if(!isBlue) {
            propositions.add(new PetiteFenetreFlottante2.StringRunnablePair("Add weight", this::addWeight));
            propositions.add(new PetiteFenetreFlottante2.StringRunnablePair("Remove weight", this::removeWeight));
        }
        propositions.add(new PetiteFenetreFlottante2.StringRunnablePair("Delete", this::remove, Color.RED, true));
        return propositions;
    }

    @Override
    public void remove() {
        super.remove();
        removeAllCables();
    }
}
