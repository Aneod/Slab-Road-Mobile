package com.example.veritablejeu.LevelsPanel;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.veritablejeu.BackEnd.DataBases.FireStore.LevelsFiles.GlobalLevelsReader;
import com.example.veritablejeu.BackEnd.DataBases.LevelFilesStorage;
import com.example.veritablejeu.BackEnd.DataBases.Local.LevelFiles.PersonalLevelsReader;
import com.example.veritablejeu.BackEnd.DataBases.NormalLevelFiles.NormalLevelsFiles;
import com.example.veritablejeu.BackEnd.LevelFile.LevelFile;
import com.example.veritablejeu.LevelsPanel.Scroller.Scroller;

import java.util.List;

public class Controller implements IController {

    private static Controller instance;
    private LevelFilesStorage levelFilesStorage;
    private final LevelsPanel levelsPanel;

    private Controller(@NonNull Context context) {
        this.levelsPanel = LevelsPanel.getInstance(context);
    }

    public static Controller getInstance(@NonNull Context context) {
        if(instance == null) {
            instance = new Controller(context);
        }
        return instance;
    }

    @Override
    public void showNormalLevels(ConstraintLayout container) {
        levelFilesStorage = NormalLevelsFiles.getInstance();
        levelsPanel.getScroller().setLevelCategory(Scroller.LevelCategory.Normal);
        prepareLevelsPanel(container);
    }

    @Override
    public void showPersonalLevels(@NonNull ConstraintLayout container) {
        levelFilesStorage = PersonalLevelsReader.getInstance(container.getContext());
        levelsPanel.getScroller().setLevelCategory(Scroller.LevelCategory.Personal);
        prepareLevelsPanel(container);
    }

    @Override
    public void showGlobalLevels(ConstraintLayout container) {
        levelFilesStorage = GlobalLevelsReader.getInstance();
        levelsPanel.getScroller().setLevelCategory(Scroller.LevelCategory.Global);
        prepareLevelsPanel(container);
    }

    @Override
    public void hide() {
        levelsPanel.hide();
    }

    private void prepareLevelsPanel(ConstraintLayout container) {
        levelsPanel.getScroller().showLoadingIcon();
        levelsPanel.getBottomBar().clear();
        levelsPanel.show(container);
        getListSize();
    }

    /**
     * The number of pages is loaded BEFORE to start to first pages loading. Because if it's
     * impossible to give the number of pages, we show a disconnected message and don't try to
     * load the first levels.
     */
    private void getListSize() {
        levelFilesStorage.getSize(new LevelFilesStorage.CountCallback() {
            @Override
            public void onCallback(int count) {
                levelsPanel.setLevelsListSize(count);
                getFirstPage();
            }

            @Override
            public void onFailure() {
                levelsPanel.getScroller().showDisconnectedMessage();
            }
        });
    }

    private void getFirstPage() {
        int pagesSize = LevelsPanel.getPagesSize();
        getLevels(0, pagesSize);
    }

    @Override
    public void getLevels(int from, int to) {
        levelsPanel.getBottomBar().showLoadingIcon();

        levelFilesStorage.get(from, to, new LevelFilesStorage.LevelListCallback() {
            @Override
            public void onCallback(List<LevelFile> list) {
                levelsPanel.setLevels(list);
                // Seulement a ce moment là que le LevelsPanel modifie son numéro de page actuelle.
            }

            @Override
            public void onFailure() {
                levelsPanel.getScroller().showDisconnectedMessage();
            }
        });
    }
}
