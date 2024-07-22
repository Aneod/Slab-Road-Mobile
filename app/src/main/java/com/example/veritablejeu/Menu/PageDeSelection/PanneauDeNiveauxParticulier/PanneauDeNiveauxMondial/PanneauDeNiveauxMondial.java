package com.example.veritablejeu.Menu.PageDeSelection.PanneauDeNiveauxParticulier.PanneauDeNiveauxMondial;

import android.content.Context;

import androidx.annotation.NonNull;

import com.example.veritablejeu.BackEnd.DataBases.FireStore.LevelsFiles.GlobalLevelsReader;
import com.example.veritablejeu.BackEnd.LevelFile.LevelFile;
import com.example.veritablejeu.Menu.MainActivity;
import com.example.veritablejeu.Menu.PageDeSelection.IndicationPourPanneauDeNiveaux;
import com.example.veritablejeu.Menu.PageDeSelection.PanneauDeNiveaux;
import com.example.veritablejeu.R;

import java.util.List;

public class PanneauDeNiveauxMondial extends PanneauDeNiveaux implements IPanneauDeNiveauxMondial {

    public PanneauDeNiveauxMondial(@NonNull Context context) {
        super(context);

        getNombreDePagesDansFireStore();
        loadLookingPage();

        getBoutonPagePrecedente().setOnClickListener(v -> {
            boolean enChargement = getEnChargement();
            if(enChargement) return;

            setNumeroDePage_precedent();
            viderListeDeNiveaux();
            loadLookingPage();
        });

        getBoutonPageSuivante().setOnClickListener(v -> {
            boolean enChargement = getEnChargement();
            if(enChargement) return;

            setNumeroDePage_suivante();
            viderListeDeNiveaux();
            loadLookingPage();
        });
    }

    public void refresh() {
        getNombreDePagesDansFireStore();
        viderListeDeNiveaux();
        GlobalLevelsReader.getInstance().clearLevelsList();
        setNumeroDePage(1);
        loadLookingPage();
    }

    private void loadLookingPage() {
        afficherProblemeConnexion();
    }

    private void updateUI(List<LevelFile> levelFiles) {
        ajouterListeDeNiveaux(levelFiles);
    }

    private void getNombreDePagesDansFireStore() {
        Context context = getContext();
        ((MainActivity) context).runOnUiThread(() -> setNombreTotalDePage(1));
    }

    @Override
    public void afficherProblemeConnexion() {
        Context context = getContext();
        String text = "Il semblerait que vous ayez un problème de connexion.";
        int img = R.drawable.pas_de_connexion;
        if(indication != null) {
            indication.removeAllViews();
        }
        indication = new IndicationPourPanneauDeNiveaux(
                context, this, text, img
        );
        afficherIndication();
    }
}
