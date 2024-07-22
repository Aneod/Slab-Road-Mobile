package com.example.veritablejeu.BackEnd.LevelFileStorage;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.veritablejeu.BackEnd.DataBases.FireStore.LevelsFiles.LevelFilesFireStoreReader;
import com.example.veritablejeu.BackEnd.DataBases.LevelFilesStorage;
import com.example.veritablejeu.BackEnd.LevelFile.LevelFile;
import com.example.veritablejeu.LevelsPanel.LevelsPanel;
import com.example.veritablejeu.LevelsPanel.Scroller.Scroller;

import org.jetbrains.annotations.Contract;

import java.util.ArrayList;
import java.util.List;

public class GlobalStorage implements ILevelFileStorage {

    private static final String TAG = "GlobalStorage";
    private static final int DEFAULT_PAGES_SIZE = 12;

    private static GlobalStorage instance;
    private final LevelsPanel levelsPanel;
    private final LevelFilesFireStoreReader levelFilesFireStoreReader;
    private int numberOfPages;
    private final int pagesSize;
    private int currentNumberPage;

    private GlobalStorage() {
        this.levelsPanel = LevelsPanel.getInstance();
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
        setLoadingPanel();
        // Recherche le nombre total de niveau (unsynchronized). (ou disconnectedMessage si problème).
        // Seulement alors, recherche la première page.
        setNumberOfPages();
    }

    private void setLoadingPanel() {
        levelsPanel.getScroller().showLoadindIcon();
        levelsPanel.getBottomBar().showLoadingIcon();
        levelsPanel.show();
    }

    private void setNumberOfPages() {
        int min = 1;
        new Thread(() -> levelFilesFireStoreReader.getSize(new LevelFilesStorage.CountCallback() {
            @Override
            public void onCallback(int count) {
                int numberOfPages = (int) Math.ceil((double) count / pagesSize);
                GlobalStorage.this.numberOfPages = Math.max(min, numberOfPages);
                resetCurrentNumberPage();
                // Recherche/Affiche la première page. (ou disconnectedMessage si problème).
                // Affiche le nombre total de page et la page actuelle.
                // Modifie les previous/next listeners du panneau.
                setPanel();
            }

            @Override
            public void onFailure() {
                GlobalStorage.this.numberOfPages = min;
                resetCurrentNumberPage();
                // DisconnectedMessage
                levelsPanel.getScroller().showDisconnectedMessage();
            }
        })).start();
    }

    private void setPanel() {
        showTheFirstPage();
        showTheNumberOfPages();
        setPanelListeners();
        setLevelScrollerCategory();
    }

    private void showTheFirstPage() {
        getLevelsOfCurrentPage();
    }

    private void showTheNumberOfPages() {
        int numberOfPages = getNumberOfPages();
        levelsPanel.getBottomBar().setNumberOfPages(numberOfPages);
    }

    private void setPanelListeners() {
        levelsPanel.getBottomBar().setPreviousPageRunnable(previousRunnable());
        levelsPanel.getBottomBar().setNextPageRunnable(nextRunnable());
    }

    @NonNull
    @Contract(pure = true)
    private Runnable previousRunnable() {
        return this::getPrevious;
    }

    @NonNull
    @Contract(pure = true)
    private Runnable nextRunnable() {
        return this::getNext;
    }

    private void setLevelScrollerCategory() {
        levelsPanel.getScroller().setLevelCategory(Scroller.LevelCategory.Global);
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
        levelFilesFireStoreReader.get(firstIndex, lastIndex, new LevelFilesStorage.LevelListCallback() {
            @Override
            public void onCallback(List<LevelFile> levelFileList) {
                // Affiche dans le panneau la liste obtenue.
                levelsPanel.getScroller().showLevels(levelFileList);
            }

            @Override
            public void onFailure() {
                // DisconnectedMessage
                levelsPanel.getScroller().showDisconnectedMessage();
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
