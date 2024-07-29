package com.slabroad.veritablejeu.BackEnd.DataBases.Local.PersonalBests.Association_id_record;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "association_id_record")
public class Association_id_record {

    @PrimaryKey
    @NonNull
    public String id;
    public int numberOfMoves;

    public long time;

    public Association_id_record(@NonNull String id, int numberOfMoves, long time) {
        this.id = id;
        this.numberOfMoves = numberOfMoves;
        this.time = time;
    }

    public boolean isBestOf(Association_id_record other) {
        if(other == null) {
            return true;
        }
        return time < other.time;
    }
}
