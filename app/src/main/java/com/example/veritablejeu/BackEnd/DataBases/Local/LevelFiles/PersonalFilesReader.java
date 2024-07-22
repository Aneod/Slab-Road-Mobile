package com.example.veritablejeu.BackEnd.DataBases.Local.LevelFiles;

import android.content.Context;

import com.example.veritablejeu.BackEnd.DataBases.LevelFilesStorage;
import com.example.veritablejeu.BackEnd.DataBases.Local.LevelFiles.DAO.PersonalFilesDao;
import com.example.veritablejeu.BackEnd.DataBases.Local.LevelFiles.DAO.PersonalFilesDatabase;
import com.example.veritablejeu.BackEnd.LevelFile.LevelFile;
import com.example.veritablejeu.Tools.SafeSubList;

import java.util.List;

public class PersonalFilesReader extends LevelFilesStorage {

    private static PersonalFilesReader instance;
    private final PersonalFilesDao personalFilesDao;
    private List<LevelFile> levelsList;

    private PersonalFilesReader(Context context) {
        personalFilesDao = PersonalFilesDatabase.getInstance(context).personalFilesDao();}

    public static PersonalFilesReader getInstance(Context context) {
        if(instance == null) {
            instance = new PersonalFilesReader(context);
        }
        return instance;
    }

    @Override
    public void get(int from, int to, final LevelListCallback callback) {
        if(levelsList == null) {
            getAll(new LevelListCallback() {
                @Override
                public void onCallback(List<LevelFile> list) {
                    levelsList = list;
                }

                @Override
                public void onFailure() {
                    callback.onFailure();
                }
            });
        }
        List<LevelFile> onReturn = SafeSubList.get(levelsList, from, to);
        callback.onCallback(onReturn);
    }

    private void getAll(final LevelListCallback callback) {
        new Thread(() -> {
            List<LevelFile> levelFileList = personalFilesDao.getAll();
            callback.onCallback(levelFileList);
        }).start();
    }

    @Override
    public void getSize(final CountCallback callback) {
        new Thread(() -> {
            int size = personalFilesDao.getSize();
            callback.onCallback(size);
        }).start();
    }
}
