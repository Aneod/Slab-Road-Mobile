package com.example.veritablejeu.Game.Board.BoardElement.Square.ModularBlob;

import android.graphics.Color;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.veritablejeu.Game.Board.Board;
import com.example.veritablejeu.Game.Board.BoardElement.Square.ModularSquare;

import java.util.HashSet;
import java.util.Set;

public class GroupOfBlobsOfBoard {

    private int RED = 255;
    private int GREEN = 255;
    private int BLUE = 255;

    public int getRED() {
        return RED;
    }

    public void setRED(int RED) {
        this.RED = RED;
    }

    public int getGREEN() {
        return GREEN;
    }

    public void setGREEN(int GREEN) {
        this.GREEN = GREEN;
    }

    public int getBLUE() {
        return BLUE;
    }

    public void setBLUE(int BLUE) {
        this.BLUE = BLUE;
    }




    private final Board board;
    private final Set<ModularBlob> modularBlobSet = new HashSet<>();
    private ModularBlob master;

    public GroupOfBlobsOfBoard(@NonNull Board board) {
        this.board = board;
    }

    public Board getBoard() {
        return board;
    }

    public void add(ModularBlob modularBlob) {
        if(modularBlob != null) {
            modularBlobSet.add(modularBlob);
        }
    }

    public void remove(ModularBlob modularBlob) {
        modularBlobSet.remove(modularBlob);
    }

    public ModularBlob getMaster() {
        return master;
    }

    /**
     * Modify the current blob master of the group.
     * @param modularBlob the new master of the group. Can be <i>null</i> for unmaster all.
     */
    public void setMaster(@Nullable ModularBlob modularBlob) {
        ModularBlob newMaster;
        if(modularBlob == null || modularBlob.equals(master)) {
            newMaster = null;
        } else {
            newMaster = modularBlob;
        }
        if(master != null) master.setMaster(false);
        master = newMaster;
        if(master != null) master.setMaster(true);
        board.setSquaresAccessibilities();
    }

    public int getBlobsColor() {
        return Color.rgb(RED, GREEN, BLUE);
    }

    public void setBlobsColor(int blobsColor) {
        RED = Color.red(blobsColor);
        GREEN = Color.green(blobsColor);
        BLUE = Color.blue(blobsColor);
        refreshBlobColor();
    }

    public void refreshBlobColor() {
        modularBlobSet.forEach(modularBlob -> modularBlob.setColor(getBlobsColor()));
    }

    @Nullable
    public ModularBlob getBlobAtSquare(ModularSquare modularSquare) {
        for(ModularBlob modularBlob : modularBlobSet) {
            if(modularBlob.isOnThisSquare(modularSquare))
                return modularBlob;
        }
        return null;
    }

}
