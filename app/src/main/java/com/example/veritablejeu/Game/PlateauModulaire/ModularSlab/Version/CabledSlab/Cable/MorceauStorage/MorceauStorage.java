package com.example.veritablejeu.Game.PlateauModulaire.ModularSlab.Version.CabledSlab.Cable.MorceauStorage;

import com.example.veritablejeu.Game.PlateauModulaire.Board;
import com.example.veritablejeu.Game.PlateauModulaire.ModularSlab.Version.CabledSlab.Cable.MorceauStorage.Generator.MorceauxGenerator;
import com.example.veritablejeu.Game.PlateauModulaire.ModularSlab.Version.CabledSlab.Cable.Cable;
import com.example.veritablejeu.Game.PlateauModulaire.ModularSlab.Version.CabledSlab.Cable.MorceauStorage.Generator.Morceau.Morceau;

import java.util.HashSet;
import java.util.Set;

public class MorceauStorage implements IMorceauStorage {

    private final Cable cable;
    private final Set<Morceau> fillMorceauSet = new HashSet<>();
    private final Set<Morceau> bordersMorceauSet = new HashSet<>();

    public MorceauStorage(Cable cable) {
        this.cable = cable;
    }

    @Override
    public Board getBoard() {
        return cable.getBoard();
    }

    @Override
    public void print() {
        deleteMorceaux();
        boolean borders = getBoard().getGame().isCableOutline();
        if(borders) {
            printBorder();
        }
        printFill();
    }

    @Override
    public void removeBorders() {
        Board board = getBoard();
        bordersMorceauSet.forEach(board::removeView);
    }

    @Override
    public void addBorders() {
        printBorder();
    }

    private void printBorder() {
        if(bordersMorceauSet.isEmpty()) {
            generateBorder();
        }
        bordersMorceauSet.forEach(getBoard()::addView);
    }

    private void printFill() {
        if(fillMorceauSet.isEmpty()) {
            generateFill();
        }
        fillMorceauSet.forEach(getBoard()::addView);
    }

    private void generateFill() {
        Set<Morceau> fillMorceaux = MorceauxGenerator.createAllMorceaux(cable, false);
        fillMorceauSet.addAll(fillMorceaux);
    }

    private void generateBorder() {
        Set<Morceau> bordersMorceaux = MorceauxGenerator.createAllMorceaux(cable, true);
        bordersMorceauSet.addAll(bordersMorceaux);
    }

    @Override
    public void deleteCable() {
        cable.delete();
    }

    @Override
    public void deleteMorceaux() {
        remove();
        fillMorceauSet.clear();
        bordersMorceauSet.clear();
    }

    private void remove() {
        Board board = getBoard();
        fillMorceauSet.forEach(board::removeView);
        bordersMorceauSet.forEach(board::removeView);
    }
}
