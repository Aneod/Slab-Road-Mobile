package com.example.veritablejeu.LevelsPanelMVC.LevelFilesStorage;

import androidx.annotation.NonNull;

import com.example.veritablejeu.BackEnd.LevelFile.LevelFile;
import com.example.veritablejeu.Tools.SafeSubList;

import java.util.Arrays;
import java.util.List;

public class NormalLevelsReader extends LevelFilesStorage {

    private static final List<LevelFile> LEVELS = Arrays.asList(
            LevelFile.getFake(),
            LevelFile.getFake(),
            LevelFile.getFake(),
            LevelFile.getFake(),
            LevelFile.getFake(),
            LevelFile.getFake(),
            LevelFile.getFake(),
            LevelFile.getFake(),
            LevelFile.getFake(),
            LevelFile.getFake(),
            LevelFile.getFake(),
            LevelFile.getFake(),

            LevelFile.getFake(),
            LevelFile.getFake(),
            LevelFile.getFake(),
            LevelFile.getFake(),
            LevelFile.getFake(),
            LevelFile.getFake()
    );

    private static NormalLevelsReader instance;

    private NormalLevelsReader() {}

    public static NormalLevelsReader getInstance() {
        if(instance == null) {
            instance = new NormalLevelsReader();
        }
        return instance;
    }

    public static List<LevelFile> get() {
        return LEVELS;
    }

    @Override
    public void get(int from, int to, @NonNull final LevelListCallback callback) {
        List<LevelFile> onReturn = SafeSubList.get(LEVELS, from, to);
        callback.onCallback(onReturn);
    }

    @Override
    public void getSize(@NonNull final CountCallback callback) {
        callback.onCallback(LEVELS.size());
    }
}
