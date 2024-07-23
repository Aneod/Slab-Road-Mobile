package com.example.veritablejeu.LevelsPanelMVC.LevelsPanel;

import com.example.veritablejeu.BackEnd.LevelFile.LevelFile;
import com.example.veritablejeu.LevelsPanelMVC.LevelsPanel.BottomBar.BottomBar;
import com.example.veritablejeu.LevelsPanelMVC.LevelsPanel.Scroller.Scroller;

import java.util.List;

public interface ILevelsPanel {

    /**
     * Show the panel of the screen.
     */
    void show();

    /**
     * Hide the panel of the screen.
     */
    void hide();

    /**
     * Take the number of levels in the db. This is important for determine the number of pages.
     * @param size number of levels in the db.
     */
    void setLevelsListSize(int size);

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
