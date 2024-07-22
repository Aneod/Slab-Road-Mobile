package com.example.veritablejeu.BackEnd.LevelFileStorage;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.veritablejeu.BackEnd.DataBases.Local.LevelFiles.PersonalFiles;
import com.example.veritablejeu.BackEnd.LevelFile.LevelFile;
import com.example.veritablejeu.LevelsPanel.LevelsPanel;

import java.util.Collections;
import java.util.List;

public class PersonalStorage implements ILevelFileStorage {

    private static final String TAG = "PersonalStorage";
    private static final int DEFAULT_PAGES_SIZE = 12;

    private static PersonalStorage instance;
    private final PersonalFiles personalFiles;
    private List<LevelFile> levelsList = Collections.emptyList();
    private final int pagesSize;
    private int currentNumberPage;

    private PersonalStorage(Context context) {
        this.personalFiles = PersonalFiles.getInstance(context);
        this.pagesSize = getPagesSize();
        this.currentNumberPage = 0;
    }

    public static synchronized PersonalStorage getInstance(Context context) {
        if(instance == null) {
            instance = new PersonalStorage(context);
        }
        return instance;
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
        // Affiche le panneau vide et lance le loading icon.
        new Thread(() -> personalFiles.getAll(levelList -> {
            setLevelsList(levelList);
            resetCurrentNumberPage();
            // Affiche la première page.
            // Affiche le nombre total de page et la page actuelle.
            // Modifie les previous/next listeners du panneau.
        })).start();
    }

    private void setLevelsList(List<LevelFile> levelsList) {
        if(levelsList != null) {
            this.levelsList = levelsList;
        } else {
            this.levelsList = Collections.emptyList();
        }
    }

    @Override
    public List<LevelFile> getPrevious() {
        decrementCurrentNumberPage();
        return getLevelsOfPage(currentNumberPage);
    }

    @Override
    public List<LevelFile> getNext() {
        incrementCurrentNumberPage();
        return getLevelsOfPage(currentNumberPage);
    }

    @NonNull
    private List<LevelFile> getLevelsOfPage(int numberPage) {
        int MAX_INDEX = levelsList.size();
        int firstIndex = Math.min(Math.max(0, numberPage * pagesSize), MAX_INDEX);
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
