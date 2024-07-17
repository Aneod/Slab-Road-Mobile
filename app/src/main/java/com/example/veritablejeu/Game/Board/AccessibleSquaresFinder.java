package com.example.veritablejeu.Game.Board;

import androidx.annotation.NonNull;

import com.example.veritablejeu.Game.Board.BoardElement.Square.ModularBlob.BestItinerary;
import com.example.veritablejeu.Game.Board.BoardElement.Square.ModularBlob.ModularBlob;
import com.example.veritablejeu.Game.Board.ZdecimalCoordinates.ZdecimalCoordinates;
import com.example.veritablejeu.Game.Board.ZdecimalCoordinates.ZdecimalCoordinatesManager;
import com.example.veritablejeu.Game.InGame.InGame;

import org.jetbrains.annotations.Contract;

import java.util.HashSet;
import java.util.Set;

public class AccessibleSquaresFinder {

    private static boolean HELPER_ENABLE = true;

    public static boolean isHelperEnable() {
        return HELPER_ENABLE;
    }

    public static void setHelperEnableAndRefresh(boolean helperEnable, InGame inGame) {
        HELPER_ENABLE = helperEnable;
        if(inGame != null) {
            inGame.getPlateauModulaireSet().forEach(Board::setSquaresAccessibilities);
        }
    }


    @NonNull
    public static Set<ZdecimalCoordinates> getAllAccessibleSquares_byMaster(@NonNull Board board) {
        Set<ZdecimalCoordinates> knownSquares = new HashSet<>();
        ModularBlob master = board.getMaster();
        if (master == null) {
            return knownSquares;
        }
        return findAdjacentsSquares(board, master.getCurrentLocation().getCord(), knownSquares);
    }

    @NonNull
    @Contract("_, _, _ -> param3")
    private static Set<ZdecimalCoordinates> findAdjacentsSquares(Board plateau, ZdecimalCoordinates numCaseDepart, @NonNull Set<ZdecimalCoordinates> casesConnues) {
        casesConnues.add(numCaseDepart);
        ZdecimalCoordinates caseDuHaut = ZdecimalCoordinatesManager.getTopOf(numCaseDepart);
        ZdecimalCoordinates caseDuBas = ZdecimalCoordinatesManager.getBottomOf(numCaseDepart);
        ZdecimalCoordinates caseDeGauche = ZdecimalCoordinatesManager.getLeftOf(numCaseDepart);
        ZdecimalCoordinates caseDeDroite = ZdecimalCoordinatesManager.getRightOf(numCaseDepart);
        ZdecimalCoordinates[] allNeighbors = new ZdecimalCoordinates[]{ caseDuHaut, caseDuBas, caseDeGauche, caseDeDroite };

        for(ZdecimalCoordinates coordinates : allNeighbors) {
            if(BestItinerary.isValidDirection(plateau, numCaseDepart, coordinates, casesConnues))
                casesConnues.addAll(findAdjacentsSquares(plateau, coordinates, casesConnues));
        }
        return casesConnues;
    }
}
