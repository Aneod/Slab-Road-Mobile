package com.slabroad.veritablejeu.LevelsPanelMVC;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.slabroad.veritablejeu.LevelsPanelMVC.LevelsReader.GlobalLevelsReader;
import com.slabroad.veritablejeu.LevelsPanelMVC.LevelsReader.LevelsReader;
import com.slabroad.veritablejeu.LevelsPanelMVC.LevelsReader.PersonalLevelsReader;
import com.slabroad.veritablejeu.LevelsPanelMVC.LevelsReader.NormalLevelsReader;
import com.slabroad.veritablejeu.BackEnd.LevelFile.LevelFile;
import com.slabroad.veritablejeu.LevelsPanelMVC.LevelsPanel.LevelsPanel;
import com.slabroad.veritablejeu.LevelsPanelMVC.LevelsPanel.Scroller.Scroller;

import java.util.List;

public class Controller implements IController {

    private static Controller instance;
    private final AppCompatActivity activity;
    private final LevelsPanel levelsPanel;
    private LevelsReader levelsReader;

    private Controller(@NonNull AppCompatActivity activity) {
        this.activity = activity;
        this.levelsPanel = LevelsPanel.getInstance(activity, this);
    }

    public static Controller getInstance(@NonNull AppCompatActivity activity) {
        if(instance == null) {
            instance = new Controller(activity);
        }
        return instance;
    }

    public AppCompatActivity getActivity() {
        return activity;
    }

    @Override
    public void hide() {
        levelsPanel.hide();
    }

    @Override
    public void showNormalLevels(ConstraintLayout container) {
        if(container == null) return;
        levelsReader = NormalLevelsReader.getInstance();
        levelsPanel.getScroller().setLevelCategory(Scroller.LevelCategory.Normal);
        levelsPanel.resetTopMargin();
        prepareLevelsPanel(container);
    }

    @Override
    public void showPersonalLevels(ConstraintLayout container) {
        if(container == null) return;
        levelsReader = PersonalLevelsReader.getInstance(container.getContext());
        levelsPanel.getScroller().setLevelCategory(Scroller.LevelCategory.Personal);
        levelsPanel.setTopMargin(260);
        prepareLevelsPanel(container);
    }

    @Override
    public void showGlobalLevels(ConstraintLayout container) {
        if(container == null) return;
        levelsReader = GlobalLevelsReader.getInstance();
        levelsPanel.getScroller().setLevelCategory(Scroller.LevelCategory.Global);
        levelsPanel.resetTopMargin();
        prepareLevelsPanel(container);
    }

    private void prepareLevelsPanel(ConstraintLayout container) {
        levelsPanel.initialize(container);
        getListSize();
    }

    /**
     * The number of pages is loaded BEFORE to start to first pages loading. Because if it's
     * impossible to give the number of pages, we show a disconnected message and don't try to
     * load the first levels.
     */
    private void getListSize() {
        levelsReader.getSize(new LevelsReader.CountCallback() {
            @Override
            public void onCallback(int count) {
                levelsPanel.setNumberOfPages_withListSize(count);
                levelsPanel.getBottomBar().showPageNumberIndicator();
                levelsPanel.getBottomBar().showLeftArrow();
                getFirstPage();
            }

            @Override
            public void diconnected() {
                makeDisconnectedText();
                levelsPanel.getScroller().showDisconnectedMessage();
                levelsPanel.getBottomBar().hideRightArrow();
                levelsPanel.getBottomBar().disableRightArrowLoadingAnimation();
            }

            @Override
            public void localDataNotFound() {
                makeLoadDataNotFoundText();
                levelsPanel.getScroller().showLocalDataNotFoundMessage();
                levelsPanel.getBottomBar().hideRightArrow();
                levelsPanel.getBottomBar().disableRightArrowLoadingAnimation();
            }
        });
    }

    private void getFirstPage() {
        int pagesSize = LevelsPanel.getPagesSize();
        levelsReader.get(0, pagesSize, new LevelsReader.LevelListCallback() {
            @Override
            public void onCallback(List<LevelFile> list) {
                levelsPanel.setLevels(list);
                levelsPanel.getBottomBar().disableRightArrowLoadingAnimation();
            }

            @Override
            public void diconnected() {
                makeDisconnectedText();
                levelsPanel.getScroller().showDisconnectedMessage();
                levelsPanel.getBottomBar().disableRightArrowLoadingAnimation();
            }

            @Override
            public void localDataNotFound() {
                makeLoadDataNotFoundText();
                levelsPanel.getScroller().showLocalDataNotFoundMessage();
                levelsPanel.getBottomBar().disableRightArrowLoadingAnimation();
            }
        });
    }

    @Override
    public void getLevels(int from, int to, int pageNumber) {
        levelsReader.get(from, to, new LevelsReader.LevelListCallback() {
            @Override
            public void onCallback(List<LevelFile> list) {
                levelsPanel.setLevels(list);
                levelsPanel.getBottomBar().setPageNumber(pageNumber);
                levelsPanel.getBottomBar().disableLeftArrowLoadingAnimation();
                levelsPanel.getBottomBar().disableRightArrowLoadingAnimation();
            }

            @Override
            public void diconnected() {
                makeDisconnectedText();
                levelsPanel.getBottomBar().disableLeftArrowLoadingAnimation();
                levelsPanel.getBottomBar().disableRightArrowLoadingAnimation();
            }

            @Override
            public void localDataNotFound() {
                makeLoadDataNotFoundText();
                levelsPanel.getBottomBar().disableLeftArrowLoadingAnimation();
                levelsPanel.getBottomBar().disableRightArrowLoadingAnimation();
            }
        });
    }

    private void makeDisconnectedText() {
        String text = "Unstable network. Please, retry later.";
        makeText(text);
    }

    private void makeLoadDataNotFoundText() {
        String text = "Personal files not found. Please, retry later.";
        makeText(text);
    }

    private void makeText(String text) {
        Context context = levelsPanel.getContext();
        Toast.makeText(context, text, Toast.LENGTH_LONG).show();
    }

    // This method must be disappear.
    public Scroller.LevelCategory getLevelCategory() {
        return levelsPanel.getScroller().getLevelCategory();
    }
}
