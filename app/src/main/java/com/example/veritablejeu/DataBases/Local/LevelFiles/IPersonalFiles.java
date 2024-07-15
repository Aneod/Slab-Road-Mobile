package com.example.veritablejeu.DataBases.Local.LevelFiles;

import com.example.veritablejeu.LevelFile.LevelFile;

public interface IPersonalFiles {

    /**
     * Add the given {@link LevelFile} in the database.
     * <br>
     * Or update it if it already exist.
     * @param levelFile the level to add in the database.
     */
    void set(LevelFile levelFile);

    /**
     * Remove of the database the given level.
     * @param levelFile the level to delete.
     */
    void remove(LevelFile levelFile);

    /**
     * Returns the level of the database with the given id.
     * @param id the id of the searched level.
     * @param callback this object returns the levelfile of the id.
     */
    void get(int id, final PersonalFiles.Callback callback);

    /**
     * Returns the entire list of the personal levels. This list isn't suppose to be
     * too long to be quickly loaded.
     * @param multipleCallback this object returns the list of levelfile.
     */
    void getAll(final PersonalFiles.MultipleCallback multipleCallback);

    /**
     * Returns the number of levels in the database.
     * @param countCallback this object returns the number of levels.
     */
    void getSize(final PersonalFiles.CountCallback countCallback);

}
