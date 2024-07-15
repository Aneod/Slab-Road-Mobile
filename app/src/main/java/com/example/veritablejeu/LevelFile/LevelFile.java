package com.example.veritablejeu.LevelFile;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.veritablejeu.DataBases.Local.LevelFiles.DAO.Converters;

import java.util.Date;

@Entity(tableName = "levelFile")
@TypeConverters(Converters.class)
public class LevelFile {

    @PrimaryKey(autoGenerate = true)
    public int id;
    public LevelCategory levelCategory;

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
    public LevelFile(int id, LevelCategory levelCategory, String name, String autor, long time, int movesNumber, String code) {
        this.id = id;
        this.levelCategory = levelCategory;
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
    public LevelFile(LevelCategory levelCategory, String name, String autor, long time, int movesNumber, String code) {
        this.levelCategory = levelCategory;
        this.name = name;
        this.autor = autor;
        this.date = new Date();
        this.bestPlayer = autor;
        this.time = time;
        this.movesNumber = movesNumber;
        this.sequentialCode = code;
    }
}