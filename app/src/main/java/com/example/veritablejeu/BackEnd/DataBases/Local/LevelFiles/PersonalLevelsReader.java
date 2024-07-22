package com.example.veritablejeu.BackEnd.DataBases.Local.LevelFiles;

import android.content.Context;

import com.example.veritablejeu.BackEnd.DataBases.LevelFilesStorage;
import com.example.veritablejeu.BackEnd.DataBases.Local.LevelFiles.DAO.PersonalFilesDao;
import com.example.veritablejeu.BackEnd.DataBases.Local.LevelFiles.DAO.PersonalFilesDatabase;
import com.example.veritablejeu.BackEnd.LevelFile.LevelFile;
import com.example.veritablejeu.Tools.SafeSubList;

import java.util.List;

public class PersonalLevelsReader extends LevelFilesStorage {

    private static PersonalLevelsReader instance;
    private final PersonalFilesDao personalFilesDao;
    private List<LevelFile> levelsList;

    private PersonalLevelsReader(Context context) {
        personalFilesDao = PersonalFilesDatabase.getInstance(context).personalFilesDao();}

    public static PersonalLevelsReader getInstance(Context context) {
        if(instance == null) {
            instance = new PersonalLevelsReader(context);
        }
        return instance;
    }

    @Override
    public void get(int from, int to, final LevelListCallback callback) {
        if(levelsList == null) {
            new Thread(() -> {
                levelsList = personalFilesDao.getAll();
                List<LevelFile> onReturn = SafeSubList.get(levelsList, from, to);
                callback.onCallback(onReturn);
            }).start();
        }
        List<LevelFile> onReturn = SafeSubList.get(levelsList, from, to);
        callback.onCallback(onReturn);
    }

    @Override
    public void getSize(final CountCallback callback) {
        new Thread(() -> {
            int size = personalFilesDao.getSize();
            callback.onCallback(size);
        }).start();
    }
}
