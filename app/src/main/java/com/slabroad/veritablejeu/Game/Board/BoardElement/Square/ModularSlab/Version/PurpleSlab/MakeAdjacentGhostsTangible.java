package com.slabroad.veritablejeu.Game.Board.BoardElement.Square.ModularSlab.Version.PurpleSlab;

import android.os.Handler;

import com.slabroad.veritablejeu.Game.Board.Board;
import com.slabroad.veritablejeu.Game.Board.BoardElement.Square.Versions.Ghost;
import com.slabroad.veritablejeu.Game.Board.BoardElement.Square.ModularSquare;
import com.slabroad.veritablejeu.Game.Board.ZdecimalCoordinates.ZdecimalCoordinates;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class MakeAdjacentGhostsTangible {

    public static void make(PurpleSlab purpleSlab) {
        if(purpleSlab == null) return;

        Set<ZdecimalCoordinates> foundSquares = new HashSet<>(purpleSlab.getSquareNums());
        findAdjacentGhosts(purpleSlab.getBoard(), foundSquares);
    }

    private static void findAdjacentGhosts(Board board, Set<ZdecimalCoordinates> foundSquares) {
        if(board == null || foundSquares == null) {
            return;
        }

        int nbAuDebut = foundSquares.size();
        ArrayList<ZdecimalCoordinates> newFoundSquares = new ArrayList<>();

        for(ZdecimalCoordinates zdecimalCoordinates : foundSquares) {
            ModularSquare square = board.getSquareAt(zdecimalCoordinates);
            if(square != null) {

                ModularSquare topSquare = square.getSquareOfTop();
                if(topSquare instanceof Ghost && !foundSquares.contains(topSquare.getCord())) {
                    newFoundSquares.add(topSquare.getCord());
                    ((Ghost) topSquare).setTangible(true);
                }
                ModularSquare leftSquare = square.getSquareOfLeft();
                if(leftSquare instanceof Ghost && !foundSquares.contains(leftSquare.getCord())) {
                    newFoundSquares.add(leftSquare.getCord());
                    ((Ghost) leftSquare).setTangible(true);
                }
                ModularSquare bottomSquare = square.getSquareOfBottom();
                if(bottomSquare instanceof Ghost && !foundSquares.contains(bottomSquare.getCord())) {
                    newFoundSquares.add(bottomSquare.getCord());
                    ((Ghost) bottomSquare).setTangible(true);
                }
                ModularSquare rightSquare = square.getSquareOfRight();
                if(rightSquare instanceof Ghost && !foundSquares.contains(rightSquare.getCord())) {
                    newFoundSquares.add(rightSquare.getCord());
                    ((Ghost) rightSquare).setTangible(true);
                }
            }
        }

        foundSquares.addAll(newFoundSquares);

        Handler handler = new Handler();
        handler.postDelayed(() -> {
            board.setSquaresAccessibilities();
            if(foundSquares.size() != nbAuDebut) findAdjacentGhosts(board, foundSquares);
        }, 30);
    }
}
