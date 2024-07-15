package com.example.veritablejeu.Game.PlateauModulaire.ModularSlab.Version.CabledSlab.Cable.MorceauStorage;

import com.example.veritablejeu.Game.PlateauModulaire.Board;
import com.example.veritablejeu.Game.PlateauModulaire.ModularSlab.Version.CabledSlab.Cable.MorceauStorage.Generator.CablePrinter;
import com.example.veritablejeu.Game.PlateauModulaire.ModularSlab.Version.CabledSlab.Cable.Cable;
import com.example.veritablejeu.Game.PlateauModulaire.ModularSlab.Version.CabledSlab.Cable.MorceauStorage.Morceau.Morceau;

import java.util.HashSet;
import java.util.Set;

public class MorceauStorage implements IMorceauStorage {

    private final Cable completeCable;
    private final Set<Morceau> fillMorceauSet = new HashSet<>();
    private final Set<Morceau> bordersMorceauSet = new HashSet<>();

    public MorceauStorage(Cable completeCable) {
        this.completeCable = completeCable;
    }

    public Board getBoard() {
        return completeCable.getBoard();
    }

    public void firstPrinting() {
        generate();
        print();
    }

    public void generate() {
        Set<Morceau> fillMorceaux = CablePrinter.createAllMorceaux(completeCable, false);
        fillMorceauSet.addAll(fillMorceaux);
        Set<Morceau> bordersMorceaux = CablePrinter.createAllMorceaux(completeCable, true);
        bordersMorceauSet.addAll(bordersMorceaux);
    }

    public void regenerate() {
        delete();
        generate();
        print();
    }

    public void refresh() {
        remove();
        print();
    }

    public void print() {
        Board board = getBoard();
        boolean borders = getBoard().getGame().isCableOutline();
        if(borders) {
            bordersMorceauSet.forEach(board::addView);
        }
        fillMorceauSet.forEach(board::addView);
    }

    public void remove() {
        Board board = getBoard();
        fillMorceauSet.forEach(board::removeView);
        bordersMorceauSet.forEach(board::removeView);
    }

    public void delete() {
        remove();
        fillMorceauSet.clear();
        bordersMorceauSet.clear();
    }
}
