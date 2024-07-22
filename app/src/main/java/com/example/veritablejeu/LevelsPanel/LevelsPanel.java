package com.example.veritablejeu.LevelsPanel;

public class LevelsPanel implements ILevelsPanel {

    private static final int PAGES_SIZE = 12;

    public static int getPagesSize() {
        return PAGES_SIZE;
    }

    private static LevelsPanel instance;

    private LevelsPanel(){}

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
}
