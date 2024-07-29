package com.slabroad.veritablejeu.Game.Board.BoardElement.Square.ModularBlob.BlobDesign.Settings;

import androidx.annotation.NonNull;
import androidx.core.util.Consumer;

import com.slabroad.veritablejeu.Game.Board.BoardElement.Square.ModularBlob.GroupOfBlobsOfBoard;
import com.slabroad.veritablejeu.PopUp.InlineComponent.Preset.CursorComponent;
import com.slabroad.veritablejeu.PopUp.ComposedComponents.RGBPanel;
import com.slabroad.veritablejeu.PopUp.PopUp;

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
