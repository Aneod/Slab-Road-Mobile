package com.example.veritablejeu.Game.Board.BoardElement.Square.ModularSlab;

import android.annotation.SuppressLint;
import android.graphics.Color;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.veritablejeu.BackEnd.sequentialCode.CodeBuilder;
import com.example.veritablejeu.Game.Board.BoardElement.BoardElement;
import com.example.veritablejeu.Game.Board.BoardElement.Square.ModularBlob.ModularBlob;
import com.example.veritablejeu.Game.Board.BoardElement.Square.ModularSlab.SlabDesign.SlabDesign;
import com.example.veritablejeu.Game.Board.BoardElement.Square.ModularSlab.Version.CabledSlab.Cable.Cable;
import com.example.veritablejeu.Game.Board.BoardElement.Square.ModularSlab.Version.CabledSlab.CabledSlab;
import com.example.veritablejeu.Game.Board.BoardElement.Square.ModularSlab.Version.GreenSlab;
import com.example.veritablejeu.Game.Board.BoardElement.Square.ModularSlab.Version.OrangeSlab;
import com.example.veritablejeu.Game.Board.BoardElement.Square.ModularSlab.Version.PurpleSlab.PurpleSlab;
import com.example.veritablejeu.Game.Board.BoardElement.Square.ModularSlab.Version.YellowSlab;
import com.example.veritablejeu.Game.Board.BoardElement.Square.ModularSquare;
import com.example.veritablejeu.Game.Board.ZdecimalCoordinates.ZdecimalCharacter.ZdecimalCharacterConverter;
import com.example.veritablejeu.Game.Board.Board;
import com.example.veritablejeu.Game.Board.ZdecimalCoordinates.ZdecimalCoordinates;
import com.example.veritablejeu.Game.Board.ZdecimalCoordinates.ZdecimalCoordinatesManager;
import com.example.veritablejeu.LittleWindow.WindowProposal.WindowProposal;
import com.example.veritablejeu.Tools.CouleurDuJeu;

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
            case '0': return new CabledSlab(modularSquare, CouleurDuJeu.BleuClair.Int(), code);
            case '1': return new CabledSlab(modularSquare, CouleurDuJeu.BleuFonce.Int(), code);
            case '2': return new CabledSlab(modularSquare, CouleurDuJeu.Rouge.Int(), code);
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
        this.slabDesign = new SlabDesign(modularSquare, this);
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
        boolean notEnoughSpace = !enoughSpace(weight);
        if(notEnoughSpace) {
            game.getPopUp().showMessage("WARNING", "Not enough space. Slabs need a floor.");
            return;
        }
        if(weight < MIN_WEIGHT) {
            game.getPopUp().showMessage("WARNING", "A slab weight can't be lower than " + MIN_WEIGHT);
        } else if(weight > MAX_WEIGHT) {
            game.getPopUp().showMessage("WARNING", "A slab weight can't be higher than " + MAX_WEIGHT);
        }
        this.weight = Math.min(Math.max(MIN_WEIGHT, weight), MAX_WEIGHT);
        slabDesign.refresh();
    }

    private boolean enoughSpace(int weight) {
        boolean isYellow = this instanceof YellowSlab;
        int nombreTotalDeCase = weight + (isYellow ? 1 : 0);
        int casesEnLargeur = (int) Math.ceil(Math.sqrt(nombreTotalDeCase));

        for(int x = 0; x < casesEnLargeur; x++) {
            for(int y = 0; y < casesEnLargeur; y++) {
                ZdecimalCoordinates cord = originSquare.getCord();
                for(int xIndex = 0; xIndex < x; xIndex++) {
                    cord = ZdecimalCoordinatesManager.getRightOf(cord);
                }
                for(int yIndex = 0; yIndex < y; yIndex++) {
                    cord = ZdecimalCoordinatesManager.getBottomOf(cord);
                }
                ModularSquare square = originSquare.getBoard().getSquareAt(cord);
                boolean noSquareHere = square == null;
                if(noSquareHere) {
                    return false;
                }
            }
        }
        return true;
    }

    public void addWeight() {
        setWeight(weight + 1);
    }

    public void removeWeight() {
        setWeight(weight - 1);
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
        game.colorFlash(fillColor);
    }

    @Override
    public void enableInGameListeners() {

    }

    @Override
    public List<WindowProposal> getEditPropositions() {
        List<WindowProposal> propositions = new ArrayList<>();
        propositions.add(new WindowProposal("Add weight", this::addWeight));
        propositions.add(new WindowProposal("Remove weight", this::removeWeight));
        propositions.add(new WindowProposal("Delete", this::remove, Color.RED, true));
        return propositions;
    }

    @Override
    public void remove() {
        super.remove();
        board.removeSlab(this);
    }

    public String getCode() {
        char type = getType();
        String onReturn = "" + type + weight;
        if(this instanceof CabledSlab) {
            Set<Cable> cables = ((CabledSlab) this).getConnectedCables();
            for(Cable cable : cables) {
                String cryptedCable = cable.getCode();
                char key = CabledSlab.getKeyCable();
                String codeCable = CodeBuilder.buildKeyValue(key, cryptedCable);
                onReturn = onReturn.concat(codeCable);
            }
        }
        return onReturn;
    }

    public char getType() {
        if(fillColor == CouleurDuJeu.BleuClair.Int()) {
            return '0';
        } else if(fillColor == CouleurDuJeu.BleuFonce.Int()) {
            return '1';
        } else if(fillColor == CouleurDuJeu.Rouge.Int()) {
            return '2';
        } else if(fillColor == CouleurDuJeu.Vert.Int()) {
            return '3';
        } else if(fillColor == CouleurDuJeu.Jaune.Int()) {
            return '4';
        } else if(fillColor == CouleurDuJeu.Orange.Int()) {
            return '5';
        } else if(fillColor == CouleurDuJeu.Violet.Int()) {
            return '6';
        }
        return '0';
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
