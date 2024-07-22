package com.example.veritablejeu.BackEnd.LevelFileStorage;

import com.example.veritablejeu.BackEnd.LevelFile.LevelFile;
import com.example.veritablejeu.LevelsPanel.LevelsPanel;

import java.util.List;

public interface ILevelFileStorage {

    /**
     * Make the necessary if the user arrive to the level panel.
     */
    void getStart();

    /**
     * Return the necessary levels if the user go to the previous page of the level panel.
     */
    List<LevelFile> getPrevious();

    /**
     * Return the necessary levels if the user go to the next page of the level panel.
     */
    List<LevelFile> getNext();

}
