package com.example.veritablejeu.LevelsPanelMVC;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.veritablejeu.BackEnd.LevelFile.LevelFile;
import com.example.veritablejeu.LevelsPanelMVC.LevelsPanel.LevelsPanel;

public interface IController {

    /**
     * Hide the {@link LevelsPanel} of the screen.
     */
    void hide();

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
     * Load and set the showing levels in the panel.
     * @param from the first index in the list of the {@link LevelFile}.
     * @param to the last index in the list of the {@link LevelFile}.
     * @param pageNumber one the levels are loaded, change the indicated page number of the
     *                   level panel. <u>Not before !</u>
     */
    void getLevels(int from, int to, int pageNumber);

}
