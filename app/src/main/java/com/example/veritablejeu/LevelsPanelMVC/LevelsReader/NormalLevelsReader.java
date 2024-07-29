package com.example.veritablejeu.LevelsPanelMVC.LevelsReader;

import androidx.annotation.NonNull;

import com.example.veritablejeu.BackEnd.DataBases.NormalLevelFiles.NormalFiles;
import com.example.veritablejeu.BackEnd.LevelFile.LevelFile;
import com.example.veritablejeu.Tools.SafeSubList;

import java.util.Arrays;
import java.util.List;

public class NormalLevelsReader extends LevelsReader {

    private static NormalLevelsReader instance;

    private NormalLevelsReader() {}

    public static NormalLevelsReader getInstance() {
        if(instance == null) {
            instance = new NormalLevelsReader();
        }
        return instance;
    }

    @Override
    public void get(int from, int to, @NonNull final LevelListCallback callback) {
        List<LevelFile> onReturn = SafeSubList.get(NormalFiles.LEVELS, from, to);
        callback.onCallback(onReturn);
    }

    @Override
    public void getSize(@NonNull final CountCallback callback) {
        callback.onCallback(NormalFiles.LEVELS.size());
    }
}
