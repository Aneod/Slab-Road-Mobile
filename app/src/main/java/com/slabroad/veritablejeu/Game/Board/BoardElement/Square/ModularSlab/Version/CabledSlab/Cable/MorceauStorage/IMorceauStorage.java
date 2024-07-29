package com.slabroad.veritablejeu.Game.Board.BoardElement.Square.ModularSlab.Version.CabledSlab.Cable.MorceauStorage;

import com.slabroad.veritablejeu.Game.Board.Board;
import com.slabroad.veritablejeu.Game.Board.BoardElement.Square.ModularSlab.Version.CabledSlab.Cable.Cable;
import com.slabroad.veritablejeu.Game.Board.BoardElement.Square.ModularSlab.Version.CabledSlab.Cable.MorceauStorage.Generator.CablePart.CablePart;

/**
 * This class works with two {@link CablePart} set : one for the fill, and another one for the borders.
 */
public interface IMorceauStorage {

    /**
     * @return the board on which is the parent {@link Cable}.
     */
    Board getBoard();

    /**
     * Delete the current cable. Create and print the new.
     */
    void print();

    /**
     * Remove all borders.
     */
    void removeBorders();

    /**
     * Add all borders, and make them if they don't exist.
     */
    void addBorders();

    /**
     * Delete and remove all {@link CablePart} of cable.
     */
    void deleteMorceaux();
}
