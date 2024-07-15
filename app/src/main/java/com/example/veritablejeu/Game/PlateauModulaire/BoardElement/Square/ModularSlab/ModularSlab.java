package com.example.veritablejeu.Game.PlateauModulaire.BoardElement.Square.ModularSlab;

import android.annotation.SuppressLint;
import android.graphics.Color;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.veritablejeu.Game.PlateauModulaire.BoardElement.BoardElement;
import com.example.veritablejeu.Game.PlateauModulaire.BoardElement.Square.ModularBlob.ModularBlob;
import com.example.veritablejeu.Game.PlateauModulaire.BoardElement.Square.ModularSlab.SlabDesign.SlabDesign;
import com.example.veritablejeu.Game.PlateauModulaire.BoardElement.Square.ModularSlab.Version.CabledSlab.CabledSlab;
import com.example.veritablejeu.Game.PlateauModulaire.BoardElement.Square.ModularSlab.Version.GreenSlab;
import com.example.veritablejeu.Game.PlateauModulaire.BoardElement.Square.ModularSlab.Version.OrangeSlab;
import com.example.veritablejeu.Game.PlateauModulaire.BoardElement.Square.ModularSlab.Version.PurpleSlab.PurpleSlab;
import com.example.veritablejeu.Game.PlateauModulaire.BoardElement.Square.ModularSlab.Version.YellowSlab;
import com.example.veritablejeu.Game.PlateauModulaire.BoardElement.Square.ModularSquare;
import com.example.veritablejeu.Game.PlateauModulaire.ZdecimalCoordinates.ZdecimalCharacter.ZdecimalCharacterConverter;
import com.example.veritablejeu.Game.PlateauModulaire.Board;
import com.example.veritablejeu.Game.PlateauModulaire.ZdecimalCoordinates.ZdecimalCoordinates;
import com.example.veritablejeu.PetiteFenetreFlottante.PetiteFenetreFlottante2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SuppressLint("ViewConstructor")
public abstract class ModularSlab extends BoardElement {

    private static final int MIN_WEIGHT = 1;
    private static final int MAX_WEIGHT = 35;

    protected final ModularSquare originSquare;
    protected final int fillColor;
    protected final Set<ZdecimalCoordinates> squareNums = new HashSet<>();
    protected final Set<ModularBlob> blobs = new HashSet<>();
    protected boolean active = false;
    protected final SlabDesign slabDesign;

    /**
     * The slabs weight <u>can't be higher than 35</u>. Because it's more simple to code it, but
     * also because it's probably too much for a single slab.
     * <br>
     * The code : TW...
     * <br>
     * T : the slab type.
     * <br>
     * W : The weight.
     */
    private int weight;

    @Nullable
    public static ModularSlab create(ModularSquare modularSquare, String code) {
        if(modularSquare == null || code == null || code.isEmpty()) {
            return null;
        }
        char slabType = code.charAt(0);
        switch (slabType) {
            case '0': return CabledSlab.lightBlue(modularSquare, code);
            case '1': return CabledSlab.darkBlue(modularSquare, code);
            case '2': return CabledSlab.red(modularSquare, code);
            case '3': return new GreenSlab(modularSquare, code);
            case '4': return new YellowSlab(modularSquare, code);
            case '5': return new OrangeSlab(modularSquare, code);
            case '6': return new PurpleSlab(modularSquare, code);
        }
        return null;
    }

    public ModularSlab(@NonNull ModularSquare modularSquare, int fillColor, @NonNull String code) {
        super(modularSquare.getBoard());
        this.originSquare = modularSquare;
        this.weight = code.length() < 2 ? 1 : ZdecimalCharacterConverter.charBase36_to_intDecimal(code.charAt(1));
        this.fillColor = fillColor;
        slabDesign = new SlabDesign(modularSquare, this);
    }

    public ModularSquare getOriginSquare() {
        return originSquare;
    }

    public Board getBoard() {
        return originSquare.getBoard();
    }

    public int getFillColor() {
        return fillColor;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        if(weight < MIN_WEIGHT) {
            board.getGame().getPopUp().showMessage("WARNING", "A slab weight can't be lower than " + MIN_WEIGHT, 1500);
        } else if(weight > MAX_WEIGHT) {
            board.getGame().getPopUp().showMessage("WARNING", "A slab weight can't be higher than " + MAX_WEIGHT, 1500);
        }
        this.weight = Math.min(Math.max(MIN_WEIGHT, weight), MAX_WEIGHT);
        slabDesign.refresh();
    }

    public void addWeight() {
        setWeight(++weight);
    }

    public void removeWeight() {
        setWeight(--weight);
    }

    public void addBlob(ModularBlob blob) {
        blobs.add(blob);
        verifyActivation();
        whenBlobAdded();
        if(!blobAddedFirstTimeConsume) {
            whenBlobAdded_onlyFirstTime();
            blobAddedFirstTimeConsume = true;
        }
    }

    public void removeBlob(ModularBlob blob) {
        blobs.remove(blob);
        verifyActivation();
        whenBlobGone();
        if(!blobGoneFirstTimeConsume) {
            whenBlobGone_onlyFirstTime();
            blobGoneFirstTimeConsume = true;
        }
    }

    private void verifyActivation() {
        int howManyBlobs = blobs.size();
        boolean canBeActive = howManyBlobs >= (this instanceof YellowSlab ? 1 : weight);
        if(active != canBeActive) {
            setActive(canBeActive);
        }
    }

    public void setActive(boolean active) {
        this.active = active;
        if(this.active) {
            whenActivation();
            if(!activationFirstTimeConsume) {
                whenActivation_onlyFirstTime();
                activationFirstTimeConsume = true;
            }
        } else {
            whenDisactivation();
            if(!disactivationFirstTimeConsume) {
                whenDisactivation_onlyFirstTime();
                disactivationFirstTimeConsume = true;
            }
        }
    }

    public boolean isActive() {
        return active;
    }

    public boolean isActive_withoutMaster() {
        long howManyBlobs = blobs.stream()
                .filter(modularBlob -> !modularBlob.isMaster())
                .count();
        return howManyBlobs >= weight;
    }

    public void addNums(ZdecimalCoordinates zdecimalCoordinates) {
        if(zdecimalCoordinates == null) {
            return;
        }
        squareNums.add(zdecimalCoordinates);
    }

    public boolean haveNum(ZdecimalCoordinates zdecimalCoordinates) {
        if(zdecimalCoordinates == null) {
            return false;
        }
        return squareNums.contains(zdecimalCoordinates);
    }

    public Set<ZdecimalCoordinates> getSquareNums() {
        return squareNums;
    }

    public void clearNums() {
        squareNums.clear();
    }

    public void flash() {
        game.flashDeCouleur(fillColor);
    }

    @Override
    public void enableInGameListeners() {

    }

    @Override
    public List<PetiteFenetreFlottante2.StringRunnablePair> getEditPropositions() {
        List<PetiteFenetreFlottante2.StringRunnablePair> propositions = new ArrayList<>();
        propositions.add(new PetiteFenetreFlottante2.StringRunnablePair("Add weight", this::addWeight));
        propositions.add(new PetiteFenetreFlottante2.StringRunnablePair("Remove weight", this::removeWeight));
        propositions.add(new PetiteFenetreFlottante2.StringRunnablePair("Delete", this::remove, Color.RED, true));
        return propositions;
    }

    public abstract void whenActivation();
    public abstract void whenDisactivation();
    public abstract void whenBlobAdded();
    public abstract void whenBlobGone();
    public abstract void whenActivation_onlyFirstTime();
    public abstract void whenDisactivation_onlyFirstTime();
    public abstract void whenBlobAdded_onlyFirstTime();
    public abstract void whenBlobGone_onlyFirstTime();
    private boolean activationFirstTimeConsume = false;
    private boolean disactivationFirstTimeConsume = false;
    private boolean blobAddedFirstTimeConsume = false;
    private boolean blobGoneFirstTimeConsume = false;

}
