package com.example.veritablejeu.Navigation.Preset.NavigationEditeur.Settings;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.util.Consumer;

import com.example.veritablejeu.Game.Game;
import com.example.veritablejeu.PopUp.ContenuPopUp.SettingsPanel.RGBPanel;
import com.example.veritablejeu.PopUp.ContenuPopUp.SettingsPanel.SettingsPanel;
import com.example.veritablejeu.PopUp.PopUp;
import com.example.veritablejeu.Tools.BackgroundColoration;

import java.util.ArrayList;
import java.util.List;

public class BackgroundColors {

    private static List<SettingsPanel.Title_Effect_Association> getTopColorSettings(@NonNull Game game) {
        ConstraintLayout constraintLayout = game.getContainer();
        Consumer<Integer> whenModify = color -> {
            game.getBackgroundColors().setTopColor(color);
            int[] backgroundColors = game.getBackgroundColors().getBackgroundColors();
            BackgroundColoration.colorierBackground(constraintLayout, backgroundColors);
        };
        int topColor = game.getBackgroundColors().getTopColor();
        RGBPanel rgbPanel = new RGBPanel(topColor, whenModify);
        return rgbPanel.getCursors();
    }

    private static List<SettingsPanel.Title_Effect_Association> getBottomColorSettings(@NonNull Game game) {
        ConstraintLayout constraintLayout = game.getContainer();
        Consumer<Integer> whenModify = color -> {
            game.getBackgroundColors().setBottomColor(color);
            int[] backgroundColors = game.getBackgroundColors().getBackgroundColors();
            BackgroundColoration.colorierBackground(constraintLayout, backgroundColors);
        };
        int bottomColor = game.getBackgroundColors().getBottomColor();
        RGBPanel rgbPanel = new RGBPanel(bottomColor, whenModify);
        return rgbPanel.getCursors();
    }

    @NonNull
    private static List<SettingsPanel.Title_Effect_Association> getAllComponents(@NonNull Game game) {
        List<SettingsPanel.Title_Effect_Association> cursors = new ArrayList<>();
        cursors.addAll(getTopColorSettings(game));
        cursors.addAll(getBottomColorSettings(game));
        return cursors;
    }

    public static void showPanel(Game game) {
        if(game == null) return;
        PopUp popUp = game.getPopUp();
        SettingsPanel settingsPanel = new SettingsPanel(popUp, getAllComponents(game));
        popUp.setContenu(settingsPanel);
    }

}
