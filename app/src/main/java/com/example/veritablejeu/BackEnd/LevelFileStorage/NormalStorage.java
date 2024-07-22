package com.example.veritablejeu.BackEnd.LevelFileStorage;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.veritablejeu.BackEnd.DataBases.NormalLevelFiles.NormalLevelFiles;
import com.example.veritablejeu.BackEnd.LevelFile.LevelFile;
import com.example.veritablejeu.LevelsPanel.LevelsPanel;

import java.util.ArrayList;
import java.util.List;

public class NormalStorage implements ILevelFileStorage {

    private static final String TAG = "NormalStorage";
    private static final int DEFAULT_PAGES_SIZE = 12;

    private static NormalStorage instance;
    private final List<LevelFile> levelsList;
    private final int pagesSize;
    private int currentNumberPage;

    private NormalStorage() {
        this.levelsList = getLevelsList();
        this.pagesSize = getPagesSize();
        this.currentNumberPage = 0;
    }

    public static synchronized NormalStorage getInstance() {
        if(instance == null) {
            instance = new NormalStorage();
        }
        return instance;
    }

    private List<LevelFile> getLevelsList() {
        try {
            return NormalLevelFiles.get();
        } catch (Exception e) {
            Log.e(TAG, "Failed to get list from NormalLevelFiles: ", e);
            return new ArrayList<>();
        }
    }

    private int getPagesSize() {
        try {
            return LevelsPanel.getPagesSize();
        } catch (Exception e) {
            Log.e(TAG, "Failed to get page size from LevelsPanel: ", e);
            return DEFAULT_PAGES_SIZE;
        }
    }

    @Override
    public void getStart() {
        resetCurrentNumberPage();
        // Affiche la première page.
        // Affiche le nombre total de page et la page actuelle.
        // Modifie les previous/next listeners du panneau.
    }

    @Override
    public List<LevelFile> getPrevious() {
        decrementCurrentNumberPage();
        return getLevelsOfCurrentPage();
    }

    @Override
    public List<LevelFile> getNext() {
        incrementCurrentNumberPage();
        return getLevelsOfCurrentPage();
    }

    @NonNull
    private List<LevelFile> getLevelsOfCurrentPage() {
        int MAX_INDEX = levelsList.size();
        int firstIndex = Math.min(Math.max(0, currentNumberPage * pagesSize), MAX_INDEX);
        int lastIndex = Math.min(Math.max(0, firstIndex + pagesSize), MAX_INDEX);
        return levelsList.subList(firstIndex, lastIndex);
    }

    private void resetCurrentNumberPage() {
        setCurrentNumberPage(0);
    }

    private void incrementCurrentNumberPage() {
        setCurrentNumberPage(currentNumberPage + 1);
    }

    private void decrementCurrentNumberPage() {
        setCurrentNumberPage(currentNumberPage - 1);
    }

    private void setCurrentNumberPage(int currentNumberPage) {
        int min = 0;
        int max = getNumberOfPages() - 1;
        this.currentNumberPage = Math.min(Math.max(min, currentNumberPage), max);
    }

    private int getNumberOfPages() {
        int min = 1;
        int numberOfPages = (int) Math.ceil((double) levelsList.size() / pagesSize);
        return Math.max(min, numberOfPages);
    }
}
