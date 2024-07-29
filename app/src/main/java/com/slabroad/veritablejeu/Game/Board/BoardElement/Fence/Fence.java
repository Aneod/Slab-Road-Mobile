package com.slabroad.veritablejeu.Game.Board.BoardElement.Fence;

import androidx.annotation.NonNull;

import com.slabroad.veritablejeu.Game.Board.Board;
import com.slabroad.veritablejeu.Game.Board.BoardElement.Fence.SpecSquare.SpecSquare;
import com.slabroad.veritablejeu.Game.Board.ZdecimalCoordinates.ZdecimalCharacter.ZdecimalCharacter;
import com.slabroad.veritablejeu.Game.Board.ZdecimalCoordinates.ZdecimalCoordinates;

import java.util.HashSet;
import java.util.Set;

public class Fence {

    private final Set<SpecSquare> specSquareSet = new HashSet<>();

    private void createAllSpecSquares(Board board) {
        if(board == null) return;

        int boardSize = Board.getBoardSize();
        for(int x = 0; x < boardSize; x++) {
            for(int y = 0; y < boardSize; y++) {
                ZdecimalCoordinates coordinates = new ZdecimalCoordinates(
                        new ZdecimalCharacter(x),
                        new ZdecimalCharacter(y)
                );
                SpecSquare specSquare = new SpecSquare(board, coordinates);
                specSquareSet.add(specSquare);
            }
        }
    }

    public Fence(@NonNull Board board) {
        createAllSpecSquares(board);
    }

    public void showFence() {
        specSquareSet.forEach(SpecSquare::showFence);
    }

    public void hideFence() {
        specSquareSet.forEach(SpecSquare::hideFence);
    }

}
