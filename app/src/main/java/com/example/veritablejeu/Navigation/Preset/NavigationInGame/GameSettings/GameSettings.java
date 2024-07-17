package com.example.veritablejeu.Navigation.Preset.NavigationInGame.GameSettings;

import androidx.annotation.NonNull;
import androidx.core.util.Consumer;

import com.example.veritablejeu.BainDeSavon.BainDeSavon;
import com.example.veritablejeu.Game.Board.AccessibleSquaresFinder;
import com.example.veritablejeu.Game.Board.Board;
import com.example.veritablejeu.Game.Board.BoardElement.Square.ModularBlob.ModularBlob;
import com.example.veritablejeu.Game.InGame.InGame;
import com.example.veritablejeu.MediaPlayerInstance.MediaPlayerInstance;
import com.example.veritablejeu.PopUp.ContenuPopUp.SettingsPanel.SettingsPanel;
import com.example.veritablejeu.PopUp.PopUp;
import com.example.veritablejeu.Tools.BackgroundColoration;

import java.util.ArrayList;
import java.util.List;

public class GameSettings {

    @NonNull
    private static SettingsPanel.Title_Consumer_Association getVolumeCursor(InGame inGame) {
        MediaPlayerInstance mediaPlayerInstance = MediaPlayerInstance.getInstance(inGame);
        Consumer<Float> consumerVolume = value -> {
            if(value != null) {
                mediaPlayerInstance.setVolume(value);
            }
        };
        float currentVolume = mediaPlayerInstance.getVolume();
        return new SettingsPanel.Title_Consumer_Association(
                "Music volume", consumerVolume, currentVolume);
    }

    @NonNull
    private static SettingsPanel.Title_Consumer_Association getBlobSpeedCursor() {
        Consumer<Float> speedConsumer = value -> {
            if(value != null) {
                int value_on1000 = (int) (value * 1000);
                int invertedValue = 1000 - value_on1000;
                ModularBlob.setMovesDuration(invertedValue);
            }
        };
        float currentSpeed = 1.0f - (float) ModularBlob.getMovesDuration() / 1000;
        return new SettingsPanel.Title_Consumer_Association(
                "Move speed", speedConsumer, currentSpeed);
    }

    @NonNull
    private static SettingsPanel.Title_Runnables_Association getFlashsOnOff() {
        Runnable activeEffectFlashs = () -> BackgroundColoration.setFlashesEnable(true);
        Runnable disactiveEffectFlashs = () -> BackgroundColoration.setFlashesEnable(false);
        boolean isFlashesEnable = BackgroundColoration.isFlashesEnable();
        return new SettingsPanel.Title_Runnables_Association(
                "Flashes", activeEffectFlashs, disactiveEffectFlashs, isFlashesEnable);
    }

    @NonNull
    private static SettingsPanel.Title_Runnables_Association getBubblesOnOff(InGame inGame) {
        BainDeSavon bainDeSavon = BainDeSavon.getInstance(inGame);
        Runnable activeEffectBulles = bainDeSavon::show_and_resume;
        Runnable disactiveEffectBulles = bainDeSavon::hide_and_pause;
        boolean currentBulles = bainDeSavon.getBullesVisibles();
        return new SettingsPanel.Title_Runnables_Association(
                "Background bubbles", activeEffectBulles, disactiveEffectBulles, currentBulles);
    }

    @NonNull
    private static SettingsPanel.Title_Runnables_Association getMovesHelperOnOff(InGame inGame) {
        Runnable runnableA = () -> AccessibleSquaresFinder.setHelperEnableAndRefresh(true, inGame);
        Runnable runnableB = () -> AccessibleSquaresFinder.setHelperEnableAndRefresh(false, inGame);
        boolean isHelperEnable = AccessibleSquaresFinder.isHelperEnable();
        return new SettingsPanel.Title_Runnables_Association(
                "Travel assistance", runnableA, runnableB, isHelperEnable);
    }

    @NonNull
    private static List<SettingsPanel.Title_Effect_Association> getAllComponents(InGame inGame) {
        List<SettingsPanel.Title_Effect_Association> components = new ArrayList<>();
        components.add(getVolumeCursor(inGame));
        components.add(getBlobSpeedCursor());
        components.add(getFlashsOnOff());
        components.add(getBubblesOnOff(inGame));
        components.add(getMovesHelperOnOff(inGame));
        return components;
    }

    public static void showGameSettingsPopUp(InGame inGame) {
        if(inGame == null) return;
        PopUp popUp = inGame.getPopUp();
        SettingsPanel settingsPanel = new SettingsPanel(popUp, getAllComponents(inGame));
        popUp.setContenu(settingsPanel);
    }

}
