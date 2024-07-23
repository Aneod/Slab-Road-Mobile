package com.example.veritablejeu.LevelsPanelMVC.LevelsReader;

import android.content.Context;

import com.example.veritablejeu.BackEnd.DataBases.Local.LevelFiles.DAO.PersonalFilesDao;
import com.example.veritablejeu.BackEnd.DataBases.Local.LevelFiles.DAO.PersonalFilesDatabase;
import com.example.veritablejeu.BackEnd.LevelFile.LevelFile;
import com.example.veritablejeu.Tools.SafeSubList;

import java.util.List;

public class PersonalLevelsReader extends LevelsReader {

    private static PersonalLevelsReader instance;
    private List<LevelFile> levelsList;

    private PersonalLevelsReader(Context context) {
        PersonalFilesDao personalFilesDao = PersonalFilesDatabase.getInstance(context)
                .personalFilesDao();
        new Thread(() ->
                levelsList = personalFilesDao.getAll()
        ).start();
    }

    public static PersonalLevelsReader getInstance(Context context) {
        if(instance == null) {
            instance = new PersonalLevelsReader(context);
        }
        return instance;
    }

    @Override
    public void get(int from, int to, final LevelListCallback callback) {
        if(levelsList == null) {
            callback.localDataNotFound();
        } else {
            List<LevelFile> onReturn = SafeSubList.get(levelsList, from, to);
            callback.onCallback(onReturn);
        }
    }

    @Override
    public void getSize(final CountCallback callback) {
        if(levelsList == null) {
            callback.localDataNotFound();
        } else {
            callback.onCallback(levelsList.size());
        }
    }
}
