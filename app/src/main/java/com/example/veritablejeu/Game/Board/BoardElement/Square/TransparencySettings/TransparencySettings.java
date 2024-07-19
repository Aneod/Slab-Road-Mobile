package com.example.veritablejeu.Game.Board.BoardElement.Square.TransparencySettings;

import androidx.annotation.NonNull;
import androidx.core.util.Consumer;

import com.example.veritablejeu.Game.Board.Board;
import com.example.veritablejeu.Game.Board.BoardElement.Square.ModularSquare;
import com.example.veritablejeu.PopUp.ContenuPopUp.SettingsPanel.SettingsPanel;
import com.example.veritablejeu.PopUp.PopUp;

import org.jetbrains.annotations.Contract;

import java.util.ArrayList;
import java.util.List;

public class TransparencySettings {

    @Contract("_ -> new")
    @NonNull
    private static SettingsPanel.CursorComponent getTransparencyCursor(@NonNull Board board) {
        Consumer<Float> consumer = value -> {
            if(value != null) {
                board.getBoardTransparency().setTransparency(board, value);
            }
        };
        float currentTransparency = board.getBoardTransparency().getTransparency();
        return new SettingsPanel.CursorComponent(
                "Transparency", consumer, currentTransparency);
    }

    @NonNull
    private static List<SettingsPanel.SettingComponent> getAllComponents(@NonNull ModularSquare square) {
        Board board = square.getBoard();
        List<SettingsPanel.SettingComponent> components = new ArrayList<>();
        components.add(getTransparencyCursor(board));
        return components;
    }

    public static void showGameSettingsPopUp(ModularSquare square) {
        if(square == null) return;
        PopUp popUp = square.getGame().getPopUp();
        SettingsPanel settingsPanel = new SettingsPanel(popUp, getAllComponents(square));
        popUp.setContent(settingsPanel);
    }

}
