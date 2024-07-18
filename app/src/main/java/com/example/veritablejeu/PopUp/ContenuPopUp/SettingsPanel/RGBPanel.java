package com.example.veritablejeu.PopUp.ContenuPopUp.SettingsPanel;

import android.graphics.Color;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.util.Consumer;

import java.util.ArrayList;
import java.util.List;

public class RGBPanel {

    private int color;
    private final Consumer<Integer> whenModify;

    @NonNull
    private SettingsPanel.Title_Consumer_Association getRedCursor() {
        Consumer<Float> consumer = value -> {
            if(value != null) {
                int value_on255 = (int) (value * 255);
                color = ARGBComposer.setRed(color, value_on255);
                whenModify.accept(color);
            }
        };
        float current = Color.red(color) / 255.0f;
        return new SettingsPanel.Title_Consumer_Association(
                "RED", consumer, current, Color.RED);
    }

    @NonNull
    private SettingsPanel.Title_Consumer_Association getGreenCursor() {
        Consumer<Float> consumer = value -> {
            if(value != null) {
                int value_on255 = (int) (value * 255);
                color = ARGBComposer.setGreen(color, value_on255);
                whenModify.accept(color);
            }
        };
        float current = Color.green(color) / 255.0f;
        return new SettingsPanel.Title_Consumer_Association(
                "GREEN", consumer, current, Color.GREEN);
    }

    @NonNull
    private SettingsPanel.Title_Consumer_Association getBlueCursor() {
        Consumer<Float> consumer = value -> {
            if(value != null) {
                int value_on255 = (int) (value * 255);
                color = ARGBComposer.setBlue(color, value_on255);
                whenModify.accept(color);
            }
        };
        float current = Color.blue(color) / 255.0f;
        return new SettingsPanel.Title_Consumer_Association(
                "BLUE", consumer, current, Color.BLUE);
    }

    /**
     * Create three cursor for a RGB color settings.
     * @param initialColor the color at the beginning.
     *                     This value decides the starting cursors position.
     * @param whenModify that what's append for each cursors movements.
     * @see SettingsPanel
     */
    public RGBPanel(int initialColor, Consumer<Integer> whenModify) {
        this.color = initialColor;
        this.whenModify = whenModify;
    }

    public List<SettingsPanel.Title_Effect_Association> getCursors() {
        List<SettingsPanel.Title_Effect_Association> components = new ArrayList<>();
        components.add(getRedCursor());
        components.add(getGreenCursor());
        components.add(getBlueCursor());
        return components;
    }

}
