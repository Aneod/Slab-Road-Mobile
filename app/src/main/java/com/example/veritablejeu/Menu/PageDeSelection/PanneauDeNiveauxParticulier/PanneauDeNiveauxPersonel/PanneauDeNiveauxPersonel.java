package com.example.veritablejeu.Menu.PageDeSelection.PanneauDeNiveauxParticulier.PanneauDeNiveauxPersonel;

import android.content.Context;

import androidx.annotation.NonNull;

import com.example.veritablejeu.BackEnd.DataBases.Local.LevelFiles.PersonalFiles;
import com.example.veritablejeu.BackEnd.DataBases.Local.LevelFiles.PersonalFilesReader;
import com.example.veritablejeu.BackEnd.LevelFile.LevelFile;
import com.example.veritablejeu.Menu.MainActivity;
import com.example.veritablejeu.Menu.PageDeSelection.PanneauDeNiveaux;
import com.google.firebase.FirebaseApp;

import java.util.ArrayList;
import java.util.List;

public class PanneauDeNiveauxPersonel extends PanneauDeNiveaux {

    private final List<LevelFile> levelFilesPersonalFileList = new ArrayList<>();

    public List<LevelFile> getLevelFilesPersonalFilesList() {
        return levelFilesPersonalFileList;
    }

    public PanneauDeNiveauxPersonel(@NonNull Context context) {
        this(context, 130);
    }

    public PanneauDeNiveauxPersonel(@NonNull Context context, int margeSuperieure) {
        super(context, margeSuperieure);
        FirebaseApp.initializeApp(context.getApplicationContext());

        new Thread(() -> {
            PersonalFiles personalFiles = PersonalFiles.getInstance(context);
            personalFiles.getAll(levelFileList -> {
                levelFilesPersonalFileList.addAll(levelFileList);
                ((MainActivity) context).runOnUiThread(() -> {
                    getNombreDePagesDansPersonalFiles();
                    loadLookingPersonalFilesPage();
                });
            });
        }).start();

        getBoutonPagePrecedente().setOnClickListener(v -> {
            boolean enChargement = getEnChargement();
            if(enChargement) return;

            setNumeroDePage_precedent();
            viderListeDeNiveaux();
            loadLookingPersonalFilesPage();
        });

        getBoutonPageSuivante().setOnClickListener(v -> {
            boolean enChargement = getEnChargement();
            if(enChargement) return;

            setNumeroDePage_suivante();
            viderListeDeNiveaux();
            loadLookingPersonalFilesPage();
        });
    }

    public void loadLookingPersonalFilesPage() {
        int numeroDePage = getNumeroDePage();
        int premierIndexVoulu = PAGE_SIZE * (numeroDePage - 1);
        int dernierIndexVoulu = premierIndexVoulu + PAGE_SIZE;
        int dernierIndex = Math.min(dernierIndexVoulu, levelFilesPersonalFileList.size());
        List<LevelFile> levelsList = levelFilesPersonalFileList.subList(premierIndexVoulu, dernierIndex);
        ajouterListeDeNiveaux(levelsList);
    }

    private void getNombreDePagesDansPersonalFiles() {
        setNombreTotalDePage(1);
    }
}
