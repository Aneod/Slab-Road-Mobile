package com.slabroad.veritablejeu.BackEnd.DataBases.Local.LevelFiles.DAO;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.slabroad.veritablejeu.BackEnd.LevelFile.LevelFile;

@Database(entities = {LevelFile.class}, version = 1, exportSchema = false)
@TypeConverters(Converters.class)
public abstract class PersonalFilesDatabase extends RoomDatabase {

    private static PersonalFilesDatabase instance;
    public abstract PersonalFilesDao personalFilesDao();

    public static synchronized PersonalFilesDatabase getInstance(@NonNull Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context, PersonalFilesDatabase.class, "PersonalFiles")
                    .build();
        }
        return instance;
    }
}

