package com.example.veritablejeu.BackEnd.DataBases.Local.LevelFiles;

import android.content.Context;
import android.util.Log;

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
            if(callback != null) {
                personalFilesDao.update(levelFile);
            } else {
                personalFilesDao.insert(levelFile);
            }
            booleanCallback.onSuccess();
        });
    }

    @Override
    public void remove(LevelFile levelFile, BooleanCallback booleanCallback) {
        new Thread(() -> {
            try {
                personalFilesDao.delete(levelFile);
                booleanCallback.onSuccess();
            } catch (Exception ignored) {
                booleanCallback.onFailure();
            }
        }).start();
    }

    public interface BooleanCallback {
        void onSuccess();
        void onFailure();
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
