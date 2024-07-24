package com.example.veritablejeu.LevelsPanelMVC.LevelsPanel;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.veritablejeu.BackEnd.LevelFile.LevelFile;
import com.example.veritablejeu.LevelsPanelMVC.LevelsPanel.BottomBar.BottomBar;
import com.example.veritablejeu.LevelsPanelMVC.LevelsPanel.Scroller.Scroller;

import java.util.List;

public interface ILevelsPanel {

    /**
     * Prepare the level panel for start to load the first page.
     */
    void initialize(ConstraintLayout container);

    void setTopMargin(int topMargin);

    void resetTopMargin();

    /**
     * Show the panel of the screen.
     */
    void show(ConstraintLayout container);

    /**
     * Hide the panel of the screen.
     */
    void hide();

    /**
     * Take the number of levels in the db for determine the number of pages.
     * @param listSize number of levels in the db.
     */
    void setNumberOfPages_withListSize(int listSize);

    /**
     * Modify the printed levels.
     * @param levels the new printed levels.
     */
    void setLevels(List<LevelFile> levels);

    /**
     * @return the scroller of the panel.
     */
    Scroller getScroller();

    /**
     * @return the bottom bar of the panel.
     */
    BottomBar getBottomBar();

}
