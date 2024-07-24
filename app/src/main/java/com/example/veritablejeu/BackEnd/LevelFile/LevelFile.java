package com.example.veritablejeu.BackEnd.LevelFile;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.veritablejeu.BackEnd.DataBases.Local.LevelFiles.DAO.Converters;
import com.example.veritablejeu.Menu.BoutonRedirection.BoutonRedirectionMenu.BoutonNouveauNiveau;

import org.jetbrains.annotations.Contract;

import java.util.Date;

@Entity(tableName = "levelFile")
@TypeConverters(Converters.class)
public class LevelFile {

    @PrimaryKey(autoGenerate = true)
    public int id;
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
    public LevelFile(int id, String name, String autor, long time, int movesNumber, String code) {
        this.id = id;
        this.name = name;
        this.autor = autor;
        this.date = new Date();
        this.bestPlayer = "Objectif";
        this.time = time;
        this.movesNumber = movesNumber;
        this.sequentialCode = code;
    }

    /**
     * Génération d'un LevelFiles avec un id aléatoire.
     */
    public LevelFile(String name, String autor, long time, int movesNumber, String code) {
        this.name = name;
        this.autor = autor;
        this.date = new Date();
        this.bestPlayer = autor;
        this.time = time;
        this.movesNumber = movesNumber;
        this.sequentialCode = code;
    }

    @NonNull
    @Contract(" -> new")
    public static LevelFile getFake() {
        return new LevelFile(
                "Name",
                "Autor",
                1_000_000_000_000L,
                5,
                BoutonNouveauNiveau.codeBidon
        );
    }
}