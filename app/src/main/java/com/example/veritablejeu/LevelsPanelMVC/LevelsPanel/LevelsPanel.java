package com.example.veritablejeu.LevelsPanelMVC.LevelsPanel;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.veritablejeu.BackEnd.LevelFile.LevelFile;
import com.example.veritablejeu.LevelsPanelMVC.Controller;
import com.example.veritablejeu.LevelsPanelMVC.LevelsPanel.BottomBar.BottomBar;
import com.example.veritablejeu.LevelsPanelMVC.LevelsPanel.Scroller.Scroller;
import com.example.veritablejeu.R;
import com.example.veritablejeu.Tools.LayoutParams.LayoutParamsDeBase_pourConstraintLayout;
import com.example.veritablejeu.Tools.ScreenUtils;
import com.example.veritablejeu.Tools.SimpleBackground;

import java.util.List;

@SuppressLint("ViewConstructor")
public class LevelsPanel extends FrameLayout implements ILevelsPanel {

    private static final int TOPMARGIN = 130;
    private static final int OTHERS_MARGINS = 50;
    private static final int BORDER_WIDTH = 5;

    public static int getBorderWidth() {
        return BORDER_WIDTH;
    }

    private static LevelsPanel instance;
    private final AppCompatActivity activity;
    private final Controller controller;
    private final Scroller scroller;
    private final BottomBar bottomBar;

    @NonNull
    private ConstraintLayout.LayoutParams get_layoutParams() {
        int width = ScreenUtils.getScreenWidth() - 2 * OTHERS_MARGINS;
        int height = ScreenUtils.getScreenHeight() - (TOPMARGIN + OTHERS_MARGINS);
        int widthPositif = Math.abs(width);
        int heightPositif = Math.abs(height);
        return new LayoutParamsDeBase_pourConstraintLayout(
                widthPositif, heightPositif, OTHERS_MARGINS, TOPMARGIN
        );
    }

    private LevelsPanel(@NonNull AppCompatActivity activity, @NonNull Controller controller){
        super(activity);
        this.activity = activity;
        this.controller = controller;

        ConstraintLayout.LayoutParams layoutParams = get_layoutParams();
        this.setLayoutParams(layoutParams);

        GradientDrawable background = SimpleBackground.create(
                Color.LTGRAY, Color.BLACK, BORDER_WIDTH);
        setBackground(background);

        this.bottomBar = new BottomBar(this);
        this.addView(bottomBar);

        this.scroller = new Scroller(this);
        this.addView(scroller);
    }

    public static LevelsPanel getInstance(@NonNull AppCompatActivity activity, @NonNull Controller controller) {
        if(instance == null) {
            instance = new LevelsPanel(activity, controller);
        }
        return instance;
    }

    @Override
    public void show() {
        if(getParent() == null) {
            ConstraintLayout container = activity.findViewById(R.id.main);
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

    public void get(int from, int to) {
        controller.getLevels(from, to);
    }
}
