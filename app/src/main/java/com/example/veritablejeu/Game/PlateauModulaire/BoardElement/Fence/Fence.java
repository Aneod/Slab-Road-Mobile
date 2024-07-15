package com.example.veritablejeu.Game.PlateauModulaire.BoardElement.Fence;

import androidx.annotation.NonNull;

import com.example.veritablejeu.Game.PlateauModulaire.Board;
import com.example.veritablejeu.Game.PlateauModulaire.BoardElement.Fence.SpecSquare.SpecSquare;
import com.example.veritablejeu.Game.PlateauModulaire.ZdecimalCoordinates.ZdecimalCharacter.ZdecimalCharacter;
import com.example.veritablejeu.Game.PlateauModulaire.ZdecimalCoordinates.ZdecimalCharacter.ZdecimalCharacterSequencer;
import com.example.veritablejeu.Game.PlateauModulaire.ZdecimalCoordinates.ZdecimalCoordinates;

import java.util.HashSet;
import java.util.Set;

public class Fence {

    private final Set<SpecSquare> specSquareSet = new HashSet<>();

    private void createAllSpecSquares(Board board) {
        if(board == null) return;

        char firstChar = ZdecimalCharacter.getMinValidChar();
        ZdecimalCharacter firstZdecimalCharacter = new ZdecimalCharacter(firstChar);

        for(ZdecimalCharacter x = firstZdecimalCharacter; !ZdecimalCharacterSequencer.isMaxChar(x); x = ZdecimalCharacterSequencer.getNextChar(x)) {
            for(ZdecimalCharacter y = firstZdecimalCharacter; !ZdecimalCharacterSequencer.isMaxChar(y); y = ZdecimalCharacterSequencer.getNextChar(y)) {
                ZdecimalCoordinates coordinates = new ZdecimalCoordinates(x, y);
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
