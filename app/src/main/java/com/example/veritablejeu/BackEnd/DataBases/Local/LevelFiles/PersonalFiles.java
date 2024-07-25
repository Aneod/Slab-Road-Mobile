package com.example.veritablejeu.BackEnd.DataBases.Local.LevelFiles;

import android.content.Context;

import com.example.veritablejeu.BackEnd.DataBases.Local.LevelFiles.DAO.PersonalFilesDao;
import com.example.veritablejeu.BackEnd.DataBases.Local.LevelFiles.DAO.PersonalFilesDatabase;
import com.example.veritablejeu.BackEnd.LevelFile.LevelFile;

public class PersonalFiles implements IPersonalFiles {

    private static PersonalFiles instance;
    private final PersonalFilesDao personalFilesDao;

    private PersonalFiles(Context context){
        personalFilesDao = PersonalFilesDatabase.getInstance(context).personalFilesDao();
    }

    public static PersonalFiles getInstance(Context context) {
        if(instance == null) {
            instance = new PersonalFiles(context);
        }
        return instance;
    }

    @Override
    public void set(LevelFile levelFile, final BooleanCallback booleanCallback) {
        if(levelFile == null) {
            return;
        }
        int id = levelFile.id;
        get(id, callback -> {
            boolean alreadyExists = callback != null;
            if(alreadyExists) {
                personalFilesDao.update(levelFile);
            } else {
                personalFilesDao.insert(levelFile);
            }
            booleanCallback.onSuccess();
        });
    }

    public interface BooleanCallback {
        void onSuccess();
    }

    @Override
    public void remove(LevelFile levelFile) {
        personalFilesDao.delete(levelFile);
    }

    @Override
    public void get(int id, final Callback callback) {
        new Thread(() -> {
            LevelFile levelData = personalFilesDao.get(id);
            callback.onDataLoaded(levelData);
        }).start();
    }

    public interface Callback {
        void onDataLoaded(LevelFile levelFile);
    }

}
