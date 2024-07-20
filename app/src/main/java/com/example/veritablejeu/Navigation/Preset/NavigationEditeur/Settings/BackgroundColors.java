package com.example.veritablejeu.Navigation.Preset.NavigationEditeur.Settings;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.util.Consumer;

import com.example.veritablejeu.Game.Game;
import com.example.veritablejeu.PopUp.InlineComponent.InlineComponents.CursorComponent;
import com.example.veritablejeu.PopUp.ComposedComponents.RGBPanel;
import com.example.veritablejeu.PopUp.PopUp;
import com.example.veritablejeu.Tools.BackgroundColoration;

public class BackgroundColors {

    private static CursorComponent[] getTopColorSettings(@NonNull Game game) {
        ConstraintLayout constraintLayout = game.getContainer();
        Consumer<Integer> whenModify = color -> {
            game.getBackgroundColors().setTopColor(color);
            int[] backgroundColors = game.getBackgroundColors().getColors();
            BackgroundColoration.colorierBackground(constraintLayout, backgroundColors);
        };
        int topColor = game.getBackgroundColors().getTopColor();
        RGBPanel rgbPanel = new RGBPanel(game.getPopUp(), topColor, whenModify);
        return rgbPanel.getCursors();
    }

    private static CursorComponent[] getBottomColorSettings(@NonNull Game game) {
        ConstraintLayout constraintLayout = game.getContainer();
        Consumer<Integer> whenModify = color -> {
            game.getBackgroundColors().setBottomColor(color);
            int[] backgroundColors = game.getBackgroundColors().getColors();
            BackgroundColoration.colorierBackground(constraintLayout, backgroundColors);
        };
        int bottomColor = game.getBackgroundColors().getBottomColor();
        RGBPanel rgbPanel = new RGBPanel(game.getPopUp(), bottomColor, whenModify);
        return rgbPanel.getCursors();
    }

    @NonNull
    public static CursorComponent[] mergeArrays(@NonNull CursorComponent[] array1, @NonNull CursorComponent[] array2) {
        CursorComponent[] mergedArray = new CursorComponent[array1.length + array2.length];
        System.arraycopy(array1, 0, mergedArray, 0, array1.length);
        System.arraycopy(array2, 0, mergedArray, array1.length, array2.length);

        return mergedArray;
    }

    public static void showPanel(Game game) {
        if(game == null) return;
        PopUp popUp = game.getPopUp();
        popUp.setContent("BACKGROUND COLOR", mergeArrays(getTopColorSettings(game), getBottomColorSettings(game)));
    }

}
