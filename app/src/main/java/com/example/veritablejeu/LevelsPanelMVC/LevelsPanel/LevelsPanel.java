package com.example.veritablejeu.LevelsPanelMVC.LevelsPanel;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.ViewParent;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.veritablejeu.BackEnd.LevelFile.LevelFile;
import com.example.veritablejeu.LevelsPanelMVC.Controller;
import com.example.veritablejeu.LevelsPanelMVC.LevelsPanel.BottomBar.BottomBar;
import com.example.veritablejeu.LevelsPanelMVC.LevelsPanel.Scroller.Scroller;
import com.example.veritablejeu.Tools.LayoutParams.LayoutParamsDeBase_pourConstraintLayout;
import com.example.veritablejeu.Tools.ScreenUtils;
import com.example.veritablejeu.Tools.SimpleBackground;

import java.util.List;

@SuppressLint("ViewConstructor")
public class LevelsPanel extends FrameLayout implements ILevelsPanel {

    private static final int PAGES_SIZE = 12;
    private static final int NORMAL_TOPMARGIN = 130;
    private static final int OTHERS_MARGINS = 50;
    private static final int BORDER_WIDTH = 5;

    public static int getPagesSize() {
        return PAGES_SIZE;
    }

    public static int getBorderWidth() {
        return BORDER_WIDTH;
    }

    private static LevelsPanel instance;
    private final Controller controller;
    private final Scroller scroller;
    private final BottomBar bottomBar;

    @NonNull
    private ConstraintLayout.LayoutParams get_layoutParams(int topMargin) {
        int width = ScreenUtils.getScreenWidth() - 2 * OTHERS_MARGINS;
        int height = ScreenUtils.getScreenHeight() - (topMargin + OTHERS_MARGINS);
        int widthPositif = Math.abs(width);
        int heightPositif = Math.abs(height);
        return new LayoutParamsDeBase_pourConstraintLayout(
                widthPositif, heightPositif, OTHERS_MARGINS, topMargin
        );
    }

    private LevelsPanel(@NonNull Context context, @NonNull Controller controller){
        super(context);
        this.controller = controller;

        ConstraintLayout.LayoutParams layoutParams = get_layoutParams(NORMAL_TOPMARGIN);
        this.setLayoutParams(layoutParams);

        GradientDrawable background = SimpleBackground.create(
                Color.LTGRAY, Color.BLACK, BORDER_WIDTH);
        setBackground(background);

        this.bottomBar = new BottomBar(this);
        this.addView(bottomBar);

        this.scroller = new Scroller(this);
        this.addView(scroller);
    }

    public static LevelsPanel getInstance(@NonNull Context context, @NonNull Controller controller) {
        if(instance == null) {
            instance = new LevelsPanel(context, controller);
        }
        return instance;
    }

    @Override
    public void initialize(ConstraintLayout container) {
        getScroller().showLoadingIcon();
        getScroller().effacerLaListe();
        getBottomBar().setPageNumber(1);
        getBottomBar().justShowTheRightLoadingWheel();
        show(container);
    }

    @Override
    public void setTopMargin(int topMargin) {
        ConstraintLayout.LayoutParams layoutParams = get_layoutParams(topMargin);
        setLayoutParams(layoutParams);
        scroller.refreshLayoutParams();
        bottomBar.refreshLayoutParams();
    }

    @Override
    public void resetTopMargin() {
        setTopMargin(NORMAL_TOPMARGIN);
    }

    @Override
    public void show(ConstraintLayout container) {
        ViewParent viewParent = getParent();
        if(viewParent instanceof ConstraintLayout) {
            ((ConstraintLayout) viewParent).removeView(this);
        }
        if(getParent() == null) {
            container.addView(this);
        }
    }

    @Override
    public void hide() {
        ViewParent parent = getParent();
        if(parent instanceof ConstraintLayout) {
            ((ConstraintLayout) parent).removeView(this);
        }
    }

    @Override
    public void setNumberOfPages_withListSize(int listSize) {
        int min = 1;
        int numberOfPages = (int) Math.ceil((double) listSize / PAGES_SIZE);
        int reducedNumberOfPages = Math.max(min, numberOfPages);
        bottomBar.setNumberOfPages(reducedNumberOfPages);
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

    public void get(int from, int to, int pageNumber) {
        controller.getLevels(from, to, pageNumber);
    }
}
