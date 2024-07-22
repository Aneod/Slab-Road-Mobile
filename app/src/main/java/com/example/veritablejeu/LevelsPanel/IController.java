package com.example.veritablejeu.LevelsPanel;

import android.content.Context;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.veritablejeu.BackEnd.LevelFile.LevelFile;

public interface IController {

    /**
     * Setup the {@link LevelsPanel} for the normal files.
     */
    void showNormalLevels(ConstraintLayout container);

    /**
     * Setup the {@link LevelsPanel} for the personal files.
     */
    void showPersonalLevels(ConstraintLayout container);

    /**
     * Setup the {@link LevelsPanel} for the global files.
     */
    void showGlobalLevels(ConstraintLayout container);

    /**
     * Hide the {@link LevelsPanel} of the screen.
     */
    void hide();

    /**
     * Load and set the showing levels in the panel.
     * @param from the first index in the list of the {@link LevelFile}.
     * @param to the last index in the list of the {@link LevelFile}.
     */
    void getLevels(int from, int to);

}
