package com.slabroad.veritablejeu.BackEnd.DataBases.Local.PersonalBests.Association_id_record;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.slabroad.veritablejeu.BackEnd.LevelFile.LevelFile;

@Database(entities = {Association_id_record.class, LevelFile.class}, version = 1, exportSchema = false)
public abstract class Association_id_recordDatabase extends RoomDatabase {

    // Si tu veux changer la base de données, ne pas faire comme moi à galérer pour rien. Voici ce qu'il faut faire :
    // 1. Supprimer les fichiers _Impl.
    // 2. Supprimer les données locales de l'application directement depuis le téléphone.

    private static Association_id_recordDatabase instance;
    public abstract Association_id_recordDao levelDataDao();

    public static synchronized Association_id_recordDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), Association_id_recordDatabase.class, "app_database")
                    .build();
        }
        return instance;
    }
}
