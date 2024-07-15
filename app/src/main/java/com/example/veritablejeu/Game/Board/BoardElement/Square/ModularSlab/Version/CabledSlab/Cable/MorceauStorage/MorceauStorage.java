package com.example.veritablejeu.Game.Board.BoardElement.Square.ModularSlab.Version.CabledSlab.Cable.MorceauStorage;

import com.example.veritablejeu.Game.Board.Board;
import com.example.veritablejeu.Game.Board.BoardElement.Square.ModularSlab.Version.CabledSlab.Cable.MorceauStorage.Generator.CablePart.CablePart;
import com.example.veritablejeu.Game.Board.BoardElement.Square.ModularSlab.Version.CabledSlab.Cable.MorceauStorage.Generator.MorceauxGenerator;
import com.example.veritablejeu.Game.Board.BoardElement.Square.ModularSlab.Version.CabledSlab.Cable.Cable;

import java.util.HashSet;
import java.util.Set;

public class MorceauStorage implements IMorceauStorage {

    private final Cable cable;
    private final Set<CablePart> fillCablePartSet = new HashSet<>();
    private final Set<CablePart> bordersCablePartSet = new HashSet<>();

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
        bordersCablePartSet.forEach(board::removeView);
    }

    @Override
    public void addBorders() {
        printBorder();
    }

    private void printBorder() {
        if(bordersCablePartSet.isEmpty()) {
            generateBorder();
        }
        bordersCablePartSet.forEach(getBoard()::addView);
    }

    private void printFill() {
        if(fillCablePartSet.isEmpty()) {
            generateFill();
        }
        fillCablePartSet.forEach(getBoard()::addView);
    }

    private void generateFill() {
        Set<CablePart> fillCableParts = MorceauxGenerator.createAllMorceaux(cable, false);
        fillCablePartSet.addAll(fillCableParts);
    }

    private void generateBorder() {
        Set<CablePart> bordersCableParts = MorceauxGenerator.createAllMorceaux(cable, true);
        bordersCablePartSet.addAll(bordersCableParts);
    }

    @Override
    public void deleteCable() {
        cable.delete();
    }

    @Override
    public void deleteMorceaux() {
        remove();
        fillCablePartSet.clear();
        bordersCablePartSet.clear();
    }

    private void remove() {
        Board board = getBoard();
        fillCablePartSet.forEach(board::removeView);
        bordersCablePartSet.forEach(board::removeView);
    }
}
