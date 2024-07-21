package com.example.veritablejeu.Navigation.Preset.NavigationInGame.GameSettings;

import android.graphics.Color;

import androidx.annotation.NonNull;
import androidx.core.util.Consumer;

import com.example.veritablejeu.BainDeSavon.BainDeSavon;
import com.example.veritablejeu.Game.Board.AccessibleSquaresFinder;
import com.example.veritablejeu.Game.Board.BoardElement.Square.ModularBlob.ModularBlob;
import com.example.veritablejeu.Game.InGame.InGame;
import com.example.veritablejeu.MediaPlayerInstance.MediaPlayerInstance;
import com.example.veritablejeu.PopUp.InlineComponent.InlineComponent;
import com.example.veritablejeu.PopUp.InlineComponent.Preset.CursorComponent;
import com.example.veritablejeu.PopUp.InlineComponent.Preset.OnOffComponent;
import com.example.veritablejeu.PopUp.PopUp;
import com.example.veritablejeu.Tools.BackgroundColoration;

import org.jetbrains.annotations.Contract;

public class GameSettings {

    @NonNull
    private static CursorComponent getVolumeCursor(@NonNull InGame inGame) {
        PopUp popUp = inGame.getPopUp();
        MediaPlayerInstance mediaPlayerInstance = MediaPlayerInstance.getInstance(inGame);
        Consumer<Float> consumerVolume = value -> {
            if(value != null) {
                mediaPlayerInstance.setVolume(value);
            }
        };
        float currentVolume = mediaPlayerInstance.getVolume();
        return new CursorComponent(
                popUp, "Music volume", currentVolume, consumerVolume);
    }

    @Contract("_ -> new")
    @NonNull
    private static CursorComponent getBlobSpeedCursor(@NonNull InGame inGame) {
        PopUp popUp = inGame.getPopUp();
        Consumer<Float> speedConsumer = value -> {
            if(value != null) {
                int value_on1000 = (int) (value * 1000);
                int invertedValue = 1000 - value_on1000;
                ModularBlob.setMovesDuration(invertedValue);
            }
        };
        float currentSpeed = 1.0f - (float) ModularBlob.getMovesDuration() / 1000;
        return new CursorComponent(
                popUp, "Move speed", currentSpeed, speedConsumer);
    }

    @Contract("_ -> new")
    @NonNull
    private static OnOffComponent getFlashsOnOff(@NonNull InGame inGame) {
        PopUp popUp = inGame.getPopUp();
        Runnable activeEffectFlashs = () -> BackgroundColoration.setFlashesEnable(true);
        Runnable disactiveEffectFlashs = () -> BackgroundColoration.setFlashesEnable(false);
        boolean isFlashesEnable = BackgroundColoration.isFlashesEnable();
        return new OnOffComponent(popUp, "Flashes",
                isFlashesEnable,
                "ACTIVE", activeEffectFlashs,
                "DISACTIVE", disactiveEffectFlashs
        );
    }

    @NonNull
    private static OnOffComponent getBubblesOnOff(@NonNull InGame inGame) {
        PopUp popUp = inGame.getPopUp();
        BainDeSavon bainDeSavon = BainDeSavon.getInstance(inGame);
        Runnable activeEffectBulles = bainDeSavon::show_and_resume;
        Runnable disactiveEffectBulles = bainDeSavon::hide_and_pause;
        boolean currentBulles = bainDeSavon.areBubblesVisible();
        return new OnOffComponent(popUp, "Background bubbles",
                currentBulles,
                "ACTIVE", activeEffectBulles,
                "DISACTIVE", disactiveEffectBulles
        );
    }

    @Contract("_ -> new")
    @NonNull
    private static OnOffComponent getMovesHelperOnOff(@NonNull InGame inGame) {
        PopUp popUp = inGame.getPopUp();
        Runnable runnableA = () -> AccessibleSquaresFinder.setHelperEnableAndRefresh(true, inGame);
        Runnable runnableB = () -> AccessibleSquaresFinder.setHelperEnableAndRefresh(false, inGame);
        boolean isHelperEnable = AccessibleSquaresFinder.isHelperEnable();
        return new OnOffComponent(popUp, "Travel assistance",
                isHelperEnable,
                "ACTIVE", runnableA,
                "DISACTIVE", runnableB
        );
    }

    @NonNull
    private static InlineComponent[] getAllComponents(InGame inGame) {
        return new InlineComponent[]{
            getVolumeCursor(inGame), getBlobSpeedCursor(inGame), getFlashsOnOff(inGame),
            getBubblesOnOff(inGame), getMovesHelperOnOff(inGame)
        };
    }

    public static void showGameSettingsPopUp(InGame inGame) {
        if(inGame == null) return;
        PopUp popUp = inGame.getPopUp();
        popUp.setContent("SETTINGS", getAllComponents(inGame));
    }

}
