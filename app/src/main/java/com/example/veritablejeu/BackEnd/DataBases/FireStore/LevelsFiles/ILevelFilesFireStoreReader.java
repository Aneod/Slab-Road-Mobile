package com.example.veritablejeu.BackEnd.DataBases.FireStore.LevelsFiles;

import com.example.veritablejeu.BackEnd.LevelFile.LevelFile;

import java.util.List;

public interface ILevelFilesFireStoreReader {

    /**
     * Returns how many there are levels in the FireStore database.
     * @param countCallback this object give the number of levels in the FireStore database.
     */
    void getSize(final LevelFilesFireStoreReader.CountCallback countCallback);

    /**
     * Clear the loaded {@link LevelFile} list of the FireStore database.
     */
    void clearLevelsList();

    /**
     * Load "how many" next {@link LevelFile} in the FireStore database, after the last
     * loaded {@link LevelFile}.
     * @param howManyLevelsToLoad how many {@link LevelFile} must be loaded.
     */
    void loadNextLevels(int howManyLevelsToLoad, final LevelFilesFireStoreReader.BooleanCallback booleanCallback);

    /**
     * Returns all levels between <i>firstIndex</i> (included) and the <i>lastIndex</i> (excluded).
     * <br>
     * If at least one of the searched levels isn't in the <i>LevelList</i>, load the next levels
     * of the Firestore database {@link LevelFile} list for find it.
     * <br>
     * If there is a error, returns an <u>empty list</u>.
     * @param firstIndex the index of the first {@link LevelFile} in the Firestore database list (included).
     *                   Can't be lower than 0.
     * @param lastIndex the index of the last {@link LevelFile} in the Firestore database list (excluded).
     *                  Can't be lower than <i>firstIndex</i>.
     */
    void get(int firstIndex, int lastIndex, final LevelFilesFireStoreReader.LevelsListCallback callback);

}
