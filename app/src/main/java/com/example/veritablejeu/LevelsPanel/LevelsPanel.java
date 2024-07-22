package com.example.veritablejeu.LevelsPanel;

import com.example.veritablejeu.LevelsPanel.BottomBar.BottomBar;
import com.example.veritablejeu.LevelsPanel.Scroller.Scroller;

public class LevelsPanel implements ILevelsPanel {

    private static final int PAGES_SIZE = 12;

    public static int getPagesSize() {
        return PAGES_SIZE;
    }

    private static LevelsPanel instance;
    private final Scroller scroller;
    private final BottomBar bottomBar;

    private LevelsPanel(){
        this.scroller = new Scroller();
        this.bottomBar = new BottomBar();
    }

    public static LevelsPanel getInstance() {
        if(instance == null) {
            instance = new LevelsPanel();
        }
        return instance;
    }

    @Override
    public void show() {

    }

    @Override
    public void hide() {

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
