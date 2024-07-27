package com.example.veritablejeu.BackEnd.DataBases.Local.PersonalBests;

import com.example.veritablejeu.BackEnd.DataBases.Local.PersonalBests.Association_id_record.Association_id_record;

public interface IPersonalBests {

    /**
     * Add a personal record in the local database.
     * <br>
     * If the given id is already known, update the data only if the new record is the best.
     */
    void set_ifBestOf(Association_id_record association, final PersonalBests.BooleanCallback booleanCallback);

    /**
     * Get the record of the board with the given id.
     * @param levelId the id of the searched level.
     * @param callback this object returns the found {@link Association_id_record}, or null if it doesn't exist.
     *                 If the given id is null, the callback will also returns null.
     */
    void get(String levelId, final PersonalBests.Callback callback);

}
