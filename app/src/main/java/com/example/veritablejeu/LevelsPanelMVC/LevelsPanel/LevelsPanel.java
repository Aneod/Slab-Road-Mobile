package com.example.veritablejeu.LevelsPanelMVC.LevelsPanel;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.veritablejeu.BackEnd.LevelFile.LevelFile;
import com.example.veritablejeu.LevelsPanelMVC.LevelsPanel.BottomBar.BottomBar;
import com.example.veritablejeu.LevelsPanelMVC.LevelsPanel.Scroller.Scroller;
import com.example.veritablejeu.Tools.LayoutParams.LayoutParamsDeBase_pourConstraintLayout;
import com.example.veritablejeu.Tools.ScreenUtils;
import com.example.veritablejeu.Tools.SimpleBackground;

import java.util.List;

public class LevelsPanel extends FrameLayout implements ILevelsPanel {

    private static LevelsPanel instance;
    private final Scroller scroller;
    private final BottomBar bottomBar;

    private LevelsPanel(@NonNull Context context){
        super(context);

        int margeSuperieure = 130;
        int margesGDB = 50;
        int width = ScreenUtils.getScreenWidth() - 2 * margesGDB;
        int height = ScreenUtils.getScreenHeight() - (margeSuperieure + margesGDB);
        int widthPositif = Math.abs(width);
        int heightPositif = Math.abs(height);

        ConstraintLayout.LayoutParams layoutParams = new LayoutParamsDeBase_pourConstraintLayout(
                widthPositif, heightPositif, margesGDB, margeSuperieure
        );
        this.setLayoutParams(layoutParams);

        int largeurBordure = 5;
        GradientDrawable background = SimpleBackground.create(
                Color.LTGRAY, Color.BLACK, largeurBordure);
        setBackground(background);

        int hauteurPartieInferieureDuPanneau = 100;
        int topMarginPartieInferieure = heightPositif - hauteurPartieInferieureDuPanneau;
        this.bottomBar = new BottomBar(
                this, widthPositif, hauteurPartieInferieureDuPanneau, topMarginPartieInferieure, largeurBordure);
        this.addView(bottomBar);

        int widthListeDefilante = widthPositif - 2 * largeurBordure;
        int heightListeDefilante = heightPositif - 2 * largeurBordure - (hauteurPartieInferieureDuPanneau - largeurBordure);
        this.scroller = new Scroller(
                context, this, widthListeDefilante, heightListeDefilante, largeurBordure, largeurBordure);
        this.addView(scroller);
    }

    public static LevelsPanel getInstance(@NonNull Context context) {
        if(instance == null) {
            instance = new LevelsPanel(context);
        }
        return instance;
    }

    @Override
    public void show(ConstraintLayout container) {
        if(getParent() == null) {
            container.addView(this);
        }
    }

    @Override
    public void hide() {
        ConstraintLayout parent = (ConstraintLayout) getParent();
        parent.removeView(this);
    }

    @Override
    public void setLevelsListSize(int size) {
        bottomBar.setNumberOfPages(size);
    }

    @Override
    public void setLevels(List<LevelFile> levels) {
        scroller.showLevels(levels);
    }

    @Override
    public Scroller getScroller() {
        return scroller;
    }

    @Override
    public BottomBar getBottomBar() {
        return bottomBar;
    }
}
