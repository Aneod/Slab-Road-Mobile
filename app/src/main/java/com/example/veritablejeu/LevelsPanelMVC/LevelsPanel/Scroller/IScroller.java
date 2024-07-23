package com.example.veritablejeu.LevelsPanelMVC.LevelsPanel.Scroller;

import com.example.veritablejeu.BackEnd.LevelFile.LevelFile;

import java.util.List;

public interface IScroller {

    /**
     * Modify the level category of the scroller. That modify the content of the level selectors.
     * @param category the new category.
     */
    void setLevelCategory(Scroller.LevelCategory category);

    /**
     * Print the given list of level files.
     * @param levelFiles all levels files to print.
     */
    void showLevels(List<LevelFile> levelFiles);

    /**
     * Show a loading icon.
     */
    void showLoadingIcon();

    /**
     * Show a message to indicate that there are no files.
     */
    void showNotFilesMessage();

    /**
     * Show a message to indicate that there is a local data problem.
     */
    void showLocalDataNotFoundMessage();

    /**
     * Show a message to indicate that there is a connection problem.
     */
    void showDisconnectedMessage();

}
