package com.example.veritablejeu.BackEnd.DataBases;

import com.example.veritablejeu.BackEnd.LevelFile.LevelFile;

import java.util.List;

public abstract class LevelFilesStorage {

    /**
     * Return a part of the list of files.
     * @param from the first file index in the list.
     * @param to the last file index in the list.
     * @param callback this interface will return the found list, or a error otherwise.
     */
    public abstract void get(int from, int to, final LevelListCallback callback);

    public interface LevelListCallback {
        void onCallback(List<LevelFile> list);
        void onFailure();
    }

    /**
     * Return the size of the list.
     * @param callback this interface will return the size of the list, or a error otherwise.
     */
    public abstract void getSize(final CountCallback callback);

    public interface CountCallback {
        void onCallback(int count);
        void onFailure();
    }

}
