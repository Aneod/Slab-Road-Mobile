package com.example.veritablejeu.Menu.PageDeSelection.PanneauDeNiveauxParticulier.PanneauDeNiveauxNormaux;

import android.content.Context;

import androidx.annotation.NonNull;

import com.example.veritablejeu.BackEnd.LevelFile.LevelFile;
import com.example.veritablejeu.Menu.PageDeSelection.PanneauDeNiveaux;

import java.util.ArrayList;
import java.util.List;

public class PanneauDeNiveauxNormaux extends PanneauDeNiveaux {

    public PanneauDeNiveauxNormaux(@NonNull Context context) {
        super(context);

        List<LevelFile> liste = new ArrayList<>();
        ajouterListeDeNiveaux(liste);
        int nombreTotalDeNiveaux = 0;
        int nombreTotalDePages = (int) Math.ceil((double) nombreTotalDeNiveaux / PAGE_SIZE);
        setNombreTotalDePage(nombreTotalDePages);

        getBoutonPagePrecedente().setOnClickListener(v -> {
            boolean enChargement = getEnChargement();
            if(enChargement) return;

            setNumeroDePage_precedent();
            int pageActuelle = getNumeroDePage();
            List<LevelFile> nouvelleListe = new ArrayList<>();
            viderListeDeNiveaux();
            ajouterListeDeNiveaux(nouvelleListe);
        });

        getBoutonPageSuivante().setOnClickListener(v -> {
            boolean enChargement = getEnChargement();
            if(enChargement) return;

            setNumeroDePage_suivante();
            int pageActuelle = getNumeroDePage();
            List<LevelFile> nouvelleListe = new ArrayList<>();
            viderListeDeNiveaux();
            ajouterListeDeNiveaux(nouvelleListe);
        });
    }
}
