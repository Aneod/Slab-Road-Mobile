package com.example.veritablejeu.BackEnd.DataBases.Local.LevelFiles.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.veritablejeu.BackEnd.LevelFile.LevelFile;

import java.util.List;

@Dao
public interface PersonalFilesDao {

    @Query("SELECT * FROM LevelFile")
    List<LevelFile> getAll();

    @Query("SELECT * FROM LevelFile WHERE id = :id")
    LevelFile get(String id);

    @Query("SELECT COUNT(*) FROM LevelFile")
    int getSize();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(LevelFile levelFile);

    @Update
    void update(LevelFile levelFile);

    @Delete
    void delete(LevelFile levelFile);
}
