package com.example.veritablejeu.BainDeSavon;

import android.view.Gravity;

import androidx.annotation.NonNull;

import com.example.veritablejeu.Game.Game;
import com.example.veritablejeu.PopUp.ComposedComponents.RGBPanel;
import com.example.veritablejeu.PopUp.InlineComponent.InlineComponent;
import com.example.veritablejeu.PopUp.InlineComponent.Preset.CursorComponent;
import com.example.veritablejeu.PopUp.InlineComponent.Preset.OnOffComponent;
import com.example.veritablejeu.PopUp.InlineComponent.Preset.SimpleText;
import com.example.veritablejeu.PopUp.PopUp;

public class BubblesSettings {

    @NonNull
    private static InlineComponent[] getComponents(@NonNull Game game) {
        PopUp popUp = game.getPopUp();
        BainDeSavon bainDeSavon = BainDeSavon.getInstance(game);
        SimpleText AGroupTitle = new SimpleText(popUp, "Type A", Gravity.CENTER);
        OnOffComponent AGroupeShape = new OnOffComponent(popUp, "Shape",
                bainDeSavon.isATypeCircle(),
                "CIRCLE", () -> bainDeSavon.setATypeAsCircle(true),
                "RECTANGLE", () -> bainDeSavon.setATypeAsCircle(false)
        );
        RGBPanel rgbPanelA = new RGBPanel(popUp, bainDeSavon.getATypeColor(), bainDeSavon::setATypeColor);
        CursorComponent[] rgbListA = rgbPanelA.getCursors();

        SimpleText space = new SimpleText(popUp, " ", Gravity.CENTER);
        SimpleText BGroupTitle = new SimpleText(popUp, "Type B", Gravity.CENTER);
        OnOffComponent BGroupeShape = new OnOffComponent(popUp, "Shape",
                bainDeSavon.isBTypeCircle(),
                "CIRCLE", () -> bainDeSavon.setBTypeAsCircle(true),
                "RECTANGLE", () -> bainDeSavon.setBTypeAsCircle(false)
        );
        RGBPanel rgbPanelB = new RGBPanel(popUp, bainDeSavon.getBTypeColor(), bainDeSavon::setBTypeColor);
        CursorComponent[] rgbListB = rgbPanelB.getCursors();

        return new InlineComponent[] {
                AGroupTitle,
                AGroupeShape,
                rgbListA[0],
                rgbListA[1],
                rgbListA[2],
                space,
                BGroupTitle,
                BGroupeShape,
                rgbListB[0],
                rgbListB[1],
                rgbListB[2],
        };
    }

    public static void showPanel(Game game) {
        if(game == null) return;
        PopUp popUp = game.getPopUp();
        popUp.setContent("BUBBLES APPEARENCE", getComponents(game));
    }

}
