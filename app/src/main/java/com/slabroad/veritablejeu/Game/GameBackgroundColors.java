package com.slabroad.veritablejeu.Game;

import android.graphics.Color;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.slabroad.veritablejeu.BackEnd.sequentialCode.CodeBuilder;
import com.slabroad.veritablejeu.Tools.BackgroundColoration;
import com.slabroad.veritablejeu.Tools.StringColorConverter;

public class GameBackgroundColors {

    private static final int DEFAULT_COLOR = Color.WHITE;

    public static int getDefaultColor() {
        return DEFAULT_COLOR;
    }

    private final Game game;
    private final int[] backgroundColors = new int[2];

    public GameBackgroundColors(Game game) {
        this.game = game;
        setTopColor(DEFAULT_COLOR);
        setBottomColor(DEFAULT_COLOR);
    }

    public int[] getColors() {
        return backgroundColors;
    }

    public int getTopColor() {
        if(backgroundColors.length < 1) {
            return DEFAULT_COLOR;
        }
        return backgroundColors[0];
    }

    public int getBottomColor() {
        if(backgroundColors.length < 2) {
            return DEFAULT_COLOR;
        }
        return backgroundColors[1];
    }

    public String getBackgroundColorationCode() {
        char key = FirstCodeReader.getKeyBackgroundColoration();
        String code = StringColorConverter.turnIntoCode(backgroundColors);
        return CodeBuilder.buildKeyValue(key, code);
    }

    /**
     * Manages the background colors.
     * @param code who containes some colors like : xxxxxxyyyyyyzzzzzz...
     */
    public void setColors_byCode(String code) {
        int[] colors = StringColorConverter.turnIntoColors(code);
        int topColor;
        int bottomColor;
        if(colors.length < 1) {
            topColor = DEFAULT_COLOR;
        } else {
            topColor = colors[0];
        }
        if(colors.length < 2) {
            bottomColor = DEFAULT_COLOR;
        } else {
            bottomColor = colors[1];
        }
        setColors(topColor, bottomColor);
    }

    public void setColors(int topColor, int bottomColor) {
        backgroundColors[0] = topColor;
        backgroundColors[1] = bottomColor;
        applyColors();
    }

    public void setTopColor(int color) {
        backgroundColors[0] = color;
        applyColors();
    }

    public void setBottomColor(int color) {
        backgroundColors[1] = color;
        applyColors();
    }

    private void applyColors() {
        if(game == null) return;
        ConstraintLayout container = game.getContainer();
        BackgroundColoration.colorierBackground(container, backgroundColors);
    }

}
