package com.example.veritablejeu.Game.Board.BoardElement.Square.ModularBlob.BlobDesign.Settings;

import androidx.annotation.NonNull;
import androidx.core.util.Consumer;

import com.example.veritablejeu.Game.Board.BoardElement.Square.ModularBlob.GroupOfBlobsOfBoard;
import com.example.veritablejeu.PopUp.PopUpComponent.InlineComponents.CursorComponent;
import com.example.veritablejeu.PopUp.PopUpComponent.ComposedComponents.RGBPanel;
import com.example.veritablejeu.PopUp.PopUp;

import org.jetbrains.annotations.Contract;

public class BlobColorSettings {

    @Contract("_ -> new")
    @NonNull
    private static CursorComponent[] getCursors(@NonNull GroupOfBlobsOfBoard blobsOfBoard) {
        PopUp popUp = blobsOfBoard.getBoard().getGame().getPopUp();
        int current = blobsOfBoard.getBlobsColor();
        Consumer<Integer> whenModify = blobsOfBoard::setBlobsColor;
        RGBPanel rgbPanel = new RGBPanel(popUp, current, whenModify);
        return rgbPanel.getCursors();
    }

    public static void showPanel(GroupOfBlobsOfBoard groupOfBlobsOfBoard) {
        if(groupOfBlobsOfBoard == null) return;
        PopUp popUp = groupOfBlobsOfBoard.getBoard().getGame().getPopUp();
        popUp.setContent("BLOBS COLOR", getCursors(groupOfBlobsOfBoard));
    }

}
