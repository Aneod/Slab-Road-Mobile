package com.example.veritablejeu.Game.PlateauModulaire.ModularSlab.Version.CabledSlab.Cable.MorceauStorage;

import com.example.veritablejeu.Game.PlateauModulaire.Board;
import com.example.veritablejeu.Game.PlateauModulaire.ModularSlab.Version.CabledSlab.Cable.Cable;
import com.example.veritablejeu.Game.PlateauModulaire.ModularSlab.Version.CabledSlab.Cable.MorceauStorage.Generator.Morceau.Morceau;

/**
 * This class works with two {@link Morceau} set : one for the fill, and another one for the borders.
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
     * Delete the cable.
     */
    void deleteCable();

    /**
     * Delete and remove all {@link Morceau} of cable.
     */
    void deleteMorceaux();
}
