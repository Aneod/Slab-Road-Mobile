package com.example.veritablejeu.Game.Board.BoardElement.Square.ModularBlob.BlobDesign.Settings;

import android.graphics.Color;

import androidx.annotation.NonNull;
import androidx.core.util.Consumer;

import com.example.veritablejeu.Game.Board.Board;
import com.example.veritablejeu.Game.Board.BoardElement.Square.ModularBlob.BlobDesign.ModularBlobDesign;
import com.example.veritablejeu.Game.Board.BoardElement.Square.ModularBlob.GroupOfBlobsOfBoard;
import com.example.veritablejeu.Game.Board.BoardElement.Square.ModularBlob.ModularBlob;
import com.example.veritablejeu.Game.Editeur.Editeur;
import com.example.veritablejeu.Game.Game;
import com.example.veritablejeu.PopUp.ContenuPopUp.SettingsPanel.SettingsPanel;
import com.example.veritablejeu.PopUp.PopUp;

import org.jetbrains.annotations.Contract;

import java.util.ArrayList;
import java.util.List;

public class BlobColorSettings {

    @Contract("_ -> new")
    @NonNull
    private static SettingsPanel.Title_Consumer_Association getREDCursor(@NonNull GroupOfBlobsOfBoard blobsOfBoard) {
        Consumer<Float> consumer = value -> {
            if(value != null) {
                int value_on255 = (int) (value * 255);
                blobsOfBoard.setRED(value_on255);
                blobsOfBoard.refreshBlobColor();
            }
        };
        float currentRed = blobsOfBoard.getRED() / 255.0f;
        return new SettingsPanel.Title_Consumer_Association(
                "RED", consumer, currentRed, Color.RED);
    }

    @Contract("_ -> new")
    @NonNull
    private static SettingsPanel.Title_Consumer_Association getGREENCursor(@NonNull GroupOfBlobsOfBoard blobsOfBoard) {
        Consumer<Float> consumer = value -> {
            if(value != null) {
                int value_on255 = (int) (value * 255);
                blobsOfBoard.setGREEN(value_on255);
                blobsOfBoard.refreshBlobColor();
            }
        };
        float currentGreen = blobsOfBoard.getGREEN() / 255.0f;
        return new SettingsPanel.Title_Consumer_Association(
                "GREEN", consumer, currentGreen, Color.GREEN);
    }

    @Contract("_ -> new")
    @NonNull
    private static SettingsPanel.Title_Consumer_Association getBLUECursor(@NonNull GroupOfBlobsOfBoard blobsOfBoard) {
        Consumer<Float> consumer = value -> {
            if(value != null) {
                int value_on255 = (int) (value * 255);
                blobsOfBoard.setBLUE(value_on255);
                blobsOfBoard.refreshBlobColor();
            }
        };
        float currentBlue = blobsOfBoard.getBLUE() / 255.0f;
        return new SettingsPanel.Title_Consumer_Association(
                "BLUE", consumer, currentBlue, Color.BLUE);
    }

    @NonNull
    private static List<SettingsPanel.Title_Effect_Association> getAllComponents(@NonNull Board board) {
        GroupOfBlobsOfBoard blobsOfBoard = board.getBlobs();
        List<SettingsPanel.Title_Effect_Association> components = new ArrayList<>();
        components.add(getREDCursor(blobsOfBoard));
        components.add(getGREENCursor(blobsOfBoard));
        components.add(getBLUECursor(blobsOfBoard));
        return components;
    }

    public static void showGameSettingsPopUp(Board board) {
        if(board == null) return;
        PopUp popUp = board.getGame().getPopUp();
        SettingsPanel settingsPanel = new SettingsPanel(popUp, getAllComponents(board));
        popUp.setContenu(settingsPanel);
    }

}
