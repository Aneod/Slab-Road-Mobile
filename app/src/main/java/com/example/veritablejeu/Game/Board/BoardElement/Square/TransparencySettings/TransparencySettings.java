package com.example.veritablejeu.Game.Board.BoardElement.Square.TransparencySettings;

import android.graphics.Color;

import androidx.annotation.NonNull;
import androidx.core.util.Consumer;

import com.example.veritablejeu.Game.Board.Board;
import com.example.veritablejeu.Game.Board.BoardElement.Square.ModularSquare;
import com.example.veritablejeu.PopUp.InlineComponent.InlineComponents.CursorComponent;
import com.example.veritablejeu.PopUp.PopUp;

import org.jetbrains.annotations.Contract;

public class TransparencySettings {

    @Contract("_ -> new")
    @NonNull
    private static CursorComponent getTransparencyCursor(@NonNull Board board) {
        Consumer<Float> consumer = value -> {
            if(value != null) {
                board.getBoardTransparency().setTransparency(board, value);
            }
        };
        PopUp popUp = board.getGame().getPopUp();
        float currentTransparency = board.getBoardTransparency().getTransparency();
        return new CursorComponent(
                popUp, "Transparency", currentTransparency, consumer, Color.BLACK);
    }

    public static void showGameSettingsPopUp(ModularSquare square) {
        if(square == null) return;
        PopUp popUp = square.getGame().getPopUp();
        Board board = square.getBoard();
        CursorComponent cursor = getTransparencyCursor(board);
        popUp.setContent("SQUARE", cursor);
    }
}
