package com.example.veritablejeu.LevelsPanelMVC;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.veritablejeu.LevelsPanelMVC.LevelsReader.GlobalLevelsReader;
import com.example.veritablejeu.LevelsPanelMVC.LevelsReader.LevelsReader;
import com.example.veritablejeu.LevelsPanelMVC.LevelsReader.PersonalLevelsReader;
import com.example.veritablejeu.LevelsPanelMVC.LevelsReader.NormalLevelsReader;
import com.example.veritablejeu.BackEnd.LevelFile.LevelFile;
import com.example.veritablejeu.LevelsPanelMVC.LevelsPanel.BottomBar.BottomBar;
import com.example.veritablejeu.LevelsPanelMVC.LevelsPanel.LevelsPanel;
import com.example.veritablejeu.LevelsPanelMVC.LevelsPanel.Scroller.Scroller;

import java.util.List;

public class Controller implements IController {

    private static Controller instance;
    private LevelsReader levelsReader;
    private final LevelsPanel levelsPanel;

    private Controller(@NonNull AppCompatActivity activity) {
        this.levelsPanel = LevelsPanel.getInstance(activity, this);
    }

    public static Controller getInstance(@NonNull AppCompatActivity activity) {
        if(instance == null) {
            instance = new Controller(activity);
        }
        return instance;
    }

    @Override
    public void hide() {
        levelsPanel.hide();
    }

    @Override
    public void showNormalLevels(ConstraintLayout container) {
        levelsReader = NormalLevelsReader.getInstance();
        levelsPanel.getScroller().setLevelCategory(Scroller.LevelCategory.Normal);
        prepareLevelsPanel();
    }

    @Override
    public void showPersonalLevels(@NonNull ConstraintLayout container) {
        levelsReader = PersonalLevelsReader.getInstance(container.getContext());
        levelsPanel.getScroller().setLevelCategory(Scroller.LevelCategory.Personal);
        prepareLevelsPanel();
    }

    @Override
    public void showGlobalLevels() {
        levelsReader = GlobalLevelsReader.getInstance();
        levelsPanel.getScroller().setLevelCategory(Scroller.LevelCategory.Global);
        prepareLevelsPanel();
    }

    private void prepareLevelsPanel() {
        levelsPanel.getScroller().showLoadingIcon();
        levelsPanel.getScroller().effacerLaListe();
        levelsPanel.getBottomBar().clear();
        levelsPanel.show();
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
                levelsPanel.setLevelsListSize(count);
                getFirstPage();
            }

            @Override
            public void diconnected() {
                levelsPanel.getScroller().showDisconnectedMessage();
            }

            @Override
            public void localDataNotFound() {
                levelsPanel.getScroller().showLocalDataNotFoundMessage();
            }
        });
    }

    private void getFirstPage() {
        int pagesSize = BottomBar.getPagesSize();
        levelsReader.get(0, pagesSize, new LevelsReader.LevelListCallback() {
            @Override
            public void onCallback(List<LevelFile> list) {
                levelsPanel.setLevels(list);
            }

            @Override
            public void diconnected() {
                levelsPanel.getScroller().showDisconnectedMessage();
            }

            @Override
            public void localDataNotFound() {
                levelsPanel.getScroller().showLocalDataNotFoundMessage();
            }
        });
    }

    @Override
    public void getLevels(int from, int to) {
        levelsReader.get(from, to, new LevelsReader.LevelListCallback() {
            @Override
            public void onCallback(List<LevelFile> list) {
                levelsPanel.setLevels(list);
                // Seulement a ce moment là que le LevelsPanel modifie son numéro de page actuelle.
            }

            @Override
            public void diconnected() {
                Context context = levelsPanel.getContext();
                String text = "Unstable network. Please, retry later.";
                Toast.makeText(context, text, Toast.LENGTH_LONG).show();
            }

            @Override
            public void localDataNotFound() {
                Context context = levelsPanel.getContext();
                String text = "Personal files not found. Please, retry later.";
                Toast.makeText(context, text, Toast.LENGTH_LONG).show();
            }
        });
    }
}
