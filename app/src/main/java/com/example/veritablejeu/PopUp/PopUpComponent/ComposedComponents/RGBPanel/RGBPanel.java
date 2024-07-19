package com.example.veritablejeu.PopUp.PopUpComponent.ComposedComponents.RGBPanel;

import android.graphics.Color;

import androidx.annotation.NonNull;
import androidx.core.util.Consumer;

import com.example.veritablejeu.PopUp.PopUpComponent.InlineComponents.CursorComponent.CursorComponent;
import com.example.veritablejeu.PopUp.PopUp;

public class RGBPanel {

    private final PopUp popUp;
    private int color;
    private final Consumer<Integer> whenModify;

    @NonNull
    private CursorComponent getRedCursor() {
        Consumer<Float> consumer = value -> {
            if(value != null) {
                int value_on255 = (int) (value * 255);
                color = ARGBComposer.setRed(color, value_on255);
                whenModify.accept(color);
            }
        };
        float current = Color.red(color) / 255.0f;
        return new CursorComponent(popUp, "RED", 100, current, consumer, Color.RED);
    }

    @NonNull
    private CursorComponent getGreenCursor() {
        Consumer<Float> consumer = value -> {
            if(value != null) {
                int value_on255 = (int) (value * 255);
                color = ARGBComposer.setGreen(color, value_on255);
                whenModify.accept(color);
            }
        };
        float current = Color.green(color) / 255.0f;
        return new CursorComponent(popUp, "GREEN", 100, current, consumer, Color.GREEN);
    }

    @NonNull
    private CursorComponent getBlueCursor() {
        Consumer<Float> consumer = value -> {
            if(value != null) {
                int value_on255 = (int) (value * 255);
                color = ARGBComposer.setBlue(color, value_on255);
                whenModify.accept(color);
            }
        };
        float current = Color.blue(color) / 255.0f;
        return new CursorComponent(popUp, "BLUE", 100, current, consumer, Color.BLUE);
    }

    /**
     * Create three cursor for a RGB color settings.
     * @param initialColor the color at the beginning.
     *                     This value decides the starting cursors position.
     * @param whenModify that what's append for each cursors movements.
     */
    public RGBPanel(PopUp popUp, int initialColor, Consumer<Integer> whenModify) {
        this.popUp = popUp;
        this.color = initialColor;
        this.whenModify = whenModify;
    }

    public int getColor() {
        return color;
    }

    public Consumer<Integer> getWhenModify() {
        return whenModify;
    }

    public CursorComponent[] getCursors() {
        return new CursorComponent[]{getRedCursor(), getGreenCursor(), getBlueCursor()};
    }

}
