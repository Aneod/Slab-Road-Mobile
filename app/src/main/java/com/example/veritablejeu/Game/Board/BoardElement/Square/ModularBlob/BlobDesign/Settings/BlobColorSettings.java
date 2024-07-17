package com.example.veritablejeu.Game.Board.BoardElement.Square.ModularBlob.BlobDesign.Settings;

import androidx.annotation.NonNull;
import androidx.core.util.Consumer;

import com.example.veritablejeu.Game.Board.Board;
import com.example.veritablejeu.Game.Board.BoardElement.Square.ModularBlob.BlobDesign.ModularBlobDesign;
import com.example.veritablejeu.Game.Board.BoardElement.Square.ModularBlob.ModularBlob;
import com.example.veritablejeu.Game.Editeur.Editeur;
import com.example.veritablejeu.Game.Game;
import com.example.veritablejeu.PopUp.ContenuPopUp.SettingsPanel.SettingsPanel;
import com.example.veritablejeu.PopUp.PopUp;

import java.util.ArrayList;
import java.util.List;

public class BlobColorSettings {

    @NonNull
    private static SettingsPanel.Title_Consumer_Association getREDCursor() {
        Consumer<Float> consumer = value -> {
            if(value != null) {
                int value_on255 = (int) (value * 255);
                ModularBlobDesign.setRED(value_on255);
            }
        };
        float currentRed = (float) ModularBlobDesign.getRED() / 255 * 100;
        return new SettingsPanel.Title_Consumer_Association(
                "RED", consumer, currentRed);
    }

    @NonNull
    private static SettingsPanel.Title_Consumer_Association getGREENCursor() {
        Consumer<Float> consumer = value -> {
            if(value != null) {
                int value_on255 = (int) (value * 255);
                ModularBlobDesign.setGREEN(value_on255);
            }
        };
        float currentGreen = (float) ModularBlobDesign.getGREEN() / 255 * 100;
        return new SettingsPanel.Title_Consumer_Association(
                "GREEN", consumer, currentGreen);
    }

    @NonNull
    private static SettingsPanel.Title_Consumer_Association getBLUECursor() {
        Consumer<Float> consumer = value -> {
            if(value != null) {
                int value_on255 = (int) (value * 255);
                ModularBlobDesign.setBLUE(value_on255);
            }
        };
        float currentBlue = (float) ModularBlobDesign.getBLUE() / 255 * 100;
        return new SettingsPanel.Title_Consumer_Association(
                "BLUE", consumer, currentBlue);
    }

    @NonNull
    private static List<SettingsPanel.Title_Effect_Association> getAllComponents() {
        List<SettingsPanel.Title_Effect_Association> components = new ArrayList<>();
        components.add(getREDCursor());
        components.add(getGREENCursor());
        components.add(getBLUECursor());
        return components;
    }

    public static void showGameSettingsPopUp(Board board) {
        if(board == null) return;
        PopUp popUp = board.getGame().getPopUp();
        SettingsPanel settingsPanel = new SettingsPanel(popUp, getAllComponents());
        popUp.setContenu(settingsPanel);
    }

}
