package com.example.veritablejeu.PopUp.ContenuPopUp.SettingsPanel.RGBPanel;

import android.graphics.Color;

import androidx.annotation.NonNull;
import androidx.core.util.Consumer;

import com.example.veritablejeu.PopUp.ContenuPopUp.SettingsPanel.SettingsPanel;

import java.util.ArrayList;
import java.util.List;

public class RGBPanel {

    private int color;
    private final Consumer<Integer> whenModify;

    @NonNull
    private SettingsPanel.CursorComponent getRedCursor() {
        Consumer<Float> consumer = value -> {
            if(value != null) {
                int value_on255 = (int) (value * 255);
                color = ARGBComposer.setRed(color, value_on255);
                whenModify.accept(color);
            }
        };
        float current = Color.red(color) / 255.0f;
        return new SettingsPanel.CursorComponent(
                "RED", consumer, current, Color.RED);
    }

    @NonNull
    private SettingsPanel.CursorComponent getGreenCursor() {
        Consumer<Float> consumer = value -> {
            if(value != null) {
                int value_on255 = (int) (value * 255);
                color = ARGBComposer.setGreen(color, value_on255);
                whenModify.accept(color);
            }
        };
        float current = Color.green(color) / 255.0f;
        return new SettingsPanel.CursorComponent(
                "GREEN", consumer, current, Color.GREEN);
    }

    @NonNull
    private SettingsPanel.CursorComponent getBlueCursor() {
        Consumer<Float> consumer = value -> {
            if(value != null) {
                int value_on255 = (int) (value * 255);
                color = ARGBComposer.setBlue(color, value_on255);
                whenModify.accept(color);
            }
        };
        float current = Color.blue(color) / 255.0f;
        return new SettingsPanel.CursorComponent(
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

    public int getColor() {
        return color;
    }

    public Consumer<Integer> getWhenModify() {
        return whenModify;
    }

    public List<SettingsPanel.SettingComponent> getCursors() {
        List<SettingsPanel.SettingComponent> components = new ArrayList<>();
        components.add(getRedCursor());
        components.add(getGreenCursor());
        components.add(getBlueCursor());
        return components;
    }

}
