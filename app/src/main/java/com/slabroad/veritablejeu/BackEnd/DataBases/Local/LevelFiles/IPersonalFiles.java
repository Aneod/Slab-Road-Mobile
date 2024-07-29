package com.slabroad.veritablejeu.BackEnd.DataBases.Local.LevelFiles;

import com.slabroad.veritablejeu.BackEnd.LevelFile.LevelFile;

public interface IPersonalFiles {

    /**
     * Add the given {@link LevelFile} in the database.
     * <br>
     * Or update it if it already exist.
     * @param levelFile the level to add in the database.
     */
    void set(LevelFile levelFile, final PersonalFiles.BooleanCallback booleanCallback);

    /**
     * Remove of the database the given level.
     *
     * @param levelFile the level to delete.
     */
    void remove(LevelFile levelFile, final PersonalFiles.BooleanCallback booleanCallback);

    /**
     * Returns the level of the database with the given id.
     * @param id the id of the searched level.
     * @param callback this object returns the levelfile of the id.
     */
    void get(String id, final PersonalFiles.Callback callback);

}
