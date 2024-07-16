package com.example.veritablejeu.BackEnd.DataBases.Local.PersonalBests.Association_id_record;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface Association_id_recordDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Association_id_record levelData);

    @Update
    void update(Association_id_record levelData);

    @Query("SELECT * FROM Association_id_record WHERE id = :levelId")
    Association_id_record get(String levelId);
}
