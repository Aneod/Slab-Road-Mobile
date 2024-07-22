package com.example.veritablejeu.LevelsPanel.Scroller;

import com.example.veritablejeu.BackEnd.LevelFile.LevelFile;

import java.util.List;

public class Scroller implements IScroller {

    private LevelCategory levelCategory = LevelCategory.Normal;

    public Scroller() {}

    @Override
    public void setLevelCategory(LevelCategory category) {
        this.levelCategory = category;
    }

    @Override
    public void showLevels(List<LevelFile> levelFiles) {
        if(levelFiles == null || levelFiles.isEmpty()) {
            showNotFilesMessage();
        }
    }

    @Override
    public void showLoadindIcon() {

    }

    @Override
    public void showNotFilesMessage() {

    }

    @Override
    public void showDisconnectedMessage() {

    }


    public enum LevelCategory {
        Normal, Personal, Global
    }
}
