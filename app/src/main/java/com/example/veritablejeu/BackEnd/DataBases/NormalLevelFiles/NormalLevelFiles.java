package com.example.veritablejeu.BackEnd.DataBases.NormalLevelFiles;

import androidx.annotation.NonNull;

import com.example.veritablejeu.BackEnd.DataBases.LevelFilesStorage;
import com.example.veritablejeu.BackEnd.LevelFile.LevelFile;
import com.example.veritablejeu.Tools.SafeSubList;

import java.util.ArrayList;
import java.util.List;

public class NormalLevelFiles extends LevelFilesStorage {

    private static final List<LevelFile> LEVELS = new ArrayList<>();

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
