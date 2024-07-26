package com.example.veritablejeu.BackEnd.LevelFile;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.veritablejeu.BackEnd.DataBases.Local.LevelFiles.DAO.Converters;
import com.example.veritablejeu.BackEnd.DataBases.Local.UserData;
import com.example.veritablejeu.Menu.BoutonRedirection.BoutonRedirectionMenu.BoutonNouveauNiveau;

import org.jetbrains.annotations.Contract;

import java.util.Date;

@Entity(tableName = "levelFile")
@TypeConverters(Converters.class)
public class LevelFile {

    @NonNull
    @PrimaryKey
    public String id = "0";

    public String name;
    public String autor;
    public Date date;
    public String bestPlayer;
    public long time;
    public int movesNumber;
    public String sequentialCode;

    public LevelFile(){}

    /**
     * Génération d'un LevelFiles avec un id présélectionné.
     */
    public LevelFile(@NonNull String id, String name, String autor, long time, int movesNumber, String code) {
        this.id = id;
        this.name = name;
        this.autor = autor;
        this.date = new Date();
        this.bestPlayer = "Objectif";
        this.time = time;
        this.movesNumber = movesNumber;
        this.sequentialCode = code;
    }

    @NonNull
    @Contract(" -> new")
    public static LevelFile getFake() {
        return new LevelFile(
                "0",
                "Name",
                "Autor",
                1_000_000_000_000L,
                5,
                BoutonNouveauNiveau.codeBidon
        );
    }

    @NonNull
    public static LevelFile getNew(Context context) {
        String userName = UserData.getUsername(context);
        return new LevelFile(
                "0",
                null,
                userName,
                0L,
                0,
                "p0" // For can create fences and then add board elements, there must is a board.
        );
    }
}