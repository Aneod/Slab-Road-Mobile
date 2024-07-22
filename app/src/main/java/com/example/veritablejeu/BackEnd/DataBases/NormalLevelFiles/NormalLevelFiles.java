package com.example.veritablejeu.BackEnd.DataBases.NormalLevelFiles;

import com.example.veritablejeu.BackEnd.LevelFile.LevelFile;

import java.util.ArrayList;
import java.util.List;

public class NormalLevelFiles {

    private static final List<LevelFile> LEVELS_LIST = new ArrayList<>();

    public static List<LevelFile> get() {
        return LEVELS_LIST;
    }
}
