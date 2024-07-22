package com.example.veritablejeu.LevelsPanel.Scroller;

import com.example.veritablejeu.BackEnd.LevelFile.LevelFile;

import java.util.List;

public interface IScroller {

    /**
     * Print the given list of level files.
     * @param levelFiles all levels files to print.
     */
    void showLevels(List<LevelFile> levelFiles);

    /**
     * Show a loading icon.
     */
    void showLoadindIcon();

    /**
     * Show a message to indicate that there are no files.
     */
    void showNotFilesMessage();

    /**
     * Show a message to indicate that there is a connection problem.
     */
    void showDisconnectedMessage();

}
