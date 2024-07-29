package com.slabroad.veritablejeu.Game.Board.BoardElement.Square.ModularBlob;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.slabroad.veritablejeu.Game.Board.Board;
import com.slabroad.veritablejeu.Game.Board.BoardElement.Square.ModularSquare;

import java.util.HashSet;
import java.util.Set;

public class GroupOfBlobsOfBoard {

    private final Board board;
    private final Set<ModularBlob> modularBlobSet = new HashSet<>();
    private ModularBlob master;
    private int blobsColor;

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
        return blobsColor;
    }

    public void setBlobsColor(int blobsColor) {
        this.blobsColor = blobsColor;
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
