package com.example.veritablejeu.BackEnd.LevelFileStorage;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.veritablejeu.BackEnd.DataBases.FireStore.LevelsFiles.LevelFilesFireStoreReader;
import com.example.veritablejeu.BackEnd.LevelFile.LevelFile;
import com.example.veritablejeu.LevelsPanel.LevelsPanel;

import java.util.ArrayList;
import java.util.List;

public class GlobalStorage implements ILevelFileStorage {

    private static final String TAG = "GlobalStorage";
    private static final int DEFAULT_PAGES_SIZE = 12;

    private static GlobalStorage instance;
    private final LevelFilesFireStoreReader levelFilesFireStoreReader;
    private int numberOfPages;
    private final int pagesSize;
    private int currentNumberPage;

    private GlobalStorage() {
        this.levelFilesFireStoreReader = LevelFilesFireStoreReader.getInstance();
        this.pagesSize = getPagesSize();
        this.currentNumberPage = 0;
    }

    public static synchronized GlobalStorage getInstance() {
        if(instance == null) {
            instance = new GlobalStorage();
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
        // Recherche le nombre total de niveau (unsynchronized). (ou disconnectedMessage si problème).
        // Seulement alors, recherche la première page.
        setNumberOfPages();
    }

    private void setNumberOfPages() {
        int min = 1;
        new Thread(() -> levelFilesFireStoreReader.getSize(new LevelFilesFireStoreReader.CountCallback() {
            @Override
            public void onCallback(int count) {
                int numberOfPages = (int) Math.ceil((double) count / pagesSize);
                GlobalStorage.this.numberOfPages = Math.max(min, numberOfPages);
                resetCurrentNumberPage();
                // Recherche/Affiche la première page. (ou disconnectedMessage si problème).
                getLevelsOfCurrentPage();
                // Affiche le nombre total de page et la page actuelle.
                // Modifie les previous/next listeners du panneau.
            }

            @Override
            public void onFailure() {
                GlobalStorage.this.numberOfPages = min;
                resetCurrentNumberPage();
                // DisconnectedMessage
            }
        })).start();
    }

    /**
     * This method will returns an empty list. The panel mustn't show this empty list. This
     * method will automatically set the good list on the panel once it is loaded.
     * @return an empty list to NOT SHOW IN THE PANEL.
     */
    @Override
    public List<LevelFile> getPrevious() {
        decrementCurrentNumberPage();
        return getLevelsOfCurrentPage();
    }

    /**
     * This method will returns an empty list. The panel mustn't show this empty list. This
     * method will automatically set the good list on the panel once it is loaded.
     * @return an empty list to NOT SHOW IN THE PANEL.
     */
    @Override
    public List<LevelFile> getNext() {
        incrementCurrentNumberPage();
        return getLevelsOfCurrentPage();
    }

    @NonNull
    private List<LevelFile> getLevelsOfCurrentPage() {
        int MAX_INDEX = numberOfPages * pagesSize;
        int firstIndex = Math.min(Math.max(0, currentNumberPage * pagesSize), MAX_INDEX);
        int lastIndex = Math.min(Math.max(0, firstIndex + pagesSize), MAX_INDEX);
        levelFilesFireStoreReader.get(firstIndex, lastIndex, new LevelFilesFireStoreReader.LevelsListCallback() {
            @Override
            public void onCallback(List<LevelFile> levelFileList) {
                // Affiche dans le panneau la liste obtenue.
            }

            @Override
            public void onFailure() {
                // DisconnectedMessage
            }
        });
        return new ArrayList<>();
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
        try {
            return numberOfPages;
        } catch (Exception e) {
            return 1;
        }
    }
}
