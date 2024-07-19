package com.example.veritablejeu.Game.Board.BoardElement.Square.ModularBlob.BlobDesign.Settings;

import androidx.annotation.NonNull;
import androidx.core.util.Consumer;

import com.example.veritablejeu.Game.Board.BoardElement.Square.ModularBlob.GroupOfBlobsOfBoard;
import com.example.veritablejeu.PopUp.ContenuPopUp.SettingsPanel.RGBPanel.RGBPanel;
import com.example.veritablejeu.PopUp.ContenuPopUp.SettingsPanel.SettingsPanel;
import com.example.veritablejeu.PopUp.PopUp;

import org.jetbrains.annotations.Contract;

import java.util.List;

public class BlobColorSettings {

    @Contract("_ -> new")
    @NonNull
    private static List<SettingsPanel.SettingComponent> getCursors(@NonNull GroupOfBlobsOfBoard blobsOfBoard) {
        int current = blobsOfBoard.getBlobsColor();
        Consumer<Integer> whenModify = blobsOfBoard::setBlobsColor;
        RGBPanel rgbPanel = new RGBPanel(current, whenModify);
        return rgbPanel.getCursors();
    }

    public static void showPanel(GroupOfBlobsOfBoard groupOfBlobsOfBoard) {
        if(groupOfBlobsOfBoard == null) return;
        PopUp popUp = groupOfBlobsOfBoard.getBoard().getGame().getPopUp();
        SettingsPanel settingsPanel = new SettingsPanel(popUp, getCursors(groupOfBlobsOfBoard));
        popUp.setContent(settingsPanel);
    }

}
