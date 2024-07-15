package com.example.veritablejeu.Menu.PageDeSelection.PanneauDeNiveauxParticulier.PanneauDeNiveauxMondial;

import android.content.Context;

import androidx.annotation.NonNull;

import com.example.veritablejeu.DataBases.FireStore.LevelsFiles.LevelFilesFireStoreReader;
import com.example.veritablejeu.LevelFile.LevelFile;
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
        LevelFilesFireStoreReader.getInstance().clearLevelsList();
        setNumeroDePage(1);
        loadLookingPage();
    }

    private void loadLookingPage() {
        int numeroDePage = getNumeroDePage();
        int premierIndexVoulu = PAGE_SIZE * (numeroDePage - 1);
        int dernierIndexVoulu = premierIndexVoulu + PAGE_SIZE;

        LevelFilesFireStoreReader reader = LevelFilesFireStoreReader.getInstance();
        List<LevelFile> levelFileList = reader.get(premierIndexVoulu, dernierIndexVoulu);

        if(levelFileList.isEmpty()) {
            // Pour le moment, on affiche un prblm de connexion si la page renvoyée est vide.
            // Et non pas lors d'un véritable problème de connexion. Même si la conséquence d'un
            // problème de connexion est le renvoi d'une liste vide.
            afficherProblemeConnexion();
        } else {
            Context context = getContext();
            ((MainActivity) context).runOnUiThread(() -> updateUI(levelFileList));
        }
    }

    private void updateUI(List<LevelFile> levelFiles) {
        ajouterListeDeNiveaux(levelFiles);
    }

    private void getNombreDePagesDansFireStore() {
        Context context = getContext();
        LevelFilesFireStoreReader levelFilesFireStoreReader = LevelFilesFireStoreReader.getInstance();
        levelFilesFireStoreReader.getNumberOfLevels_inDataBase((nombreFinal) -> {
            int nombreTotalDePages = (int) Math.ceil((double) nombreFinal / PAGE_SIZE);
            ((MainActivity) context).runOnUiThread(() -> setNombreTotalDePage(nombreTotalDePages));
        });
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
