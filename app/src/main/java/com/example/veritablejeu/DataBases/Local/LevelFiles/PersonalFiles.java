package com.example.veritablejeu.DataBases.Local.LevelFiles;

import android.content.Context;

import com.example.veritablejeu.DataBases.Local.LevelFiles.DAO.PersonalFilesDao;
import com.example.veritablejeu.DataBases.Local.LevelFiles.DAO.PersonalFilesDatabase;
import com.example.veritablejeu.LevelFile.LevelFile;

import java.util.List;

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
    public void set(LevelFile levelFile) {
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
        });
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

    @Override
    public void getAll(final MultipleCallback multipleCallback) {
        new Thread(() -> {
            List<LevelFile> levelFileList = personalFilesDao.getAll();
            multipleCallback.onDataLoaded(levelFileList);
        }).start();
    }

    public interface MultipleCallback {
        void onDataLoaded(List<LevelFile> levelFileList);
    }

    @Override
    public void getSize(CountCallback countCallback) {
        new Thread(() -> {
            int size = personalFilesDao.getSize();
            countCallback.onCallBack(size);
        }).start();
    }

    public interface CountCallback {
        void onCallBack(int count);
    }
}
