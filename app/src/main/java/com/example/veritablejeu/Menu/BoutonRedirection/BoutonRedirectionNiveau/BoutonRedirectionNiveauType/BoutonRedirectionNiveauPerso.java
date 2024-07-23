package com.example.veritablejeu.Menu.BoutonRedirection.BoutonRedirectionNiveau.BoutonRedirectionNiveauType;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;

import com.example.veritablejeu.BackEnd.DataBases.Local.LevelFiles.PersonalFiles;
import com.example.veritablejeu.BackEnd.LevelFile.LevelFile;
import com.example.veritablejeu.LevelsPanelMVC.LevelsPanel.Scroller.Scroller;
import com.example.veritablejeu.Menu.BoutonRedirection.BoutonRedirectionNiveau.BoutonRedirectionNiveau;
import com.example.veritablejeu.Menu.MainActivity;
import com.example.veritablejeu.R;

@SuppressLint("ViewConstructor")
public class BoutonRedirectionNiveauPerso extends BoutonRedirectionNiveau {

    private final LevelFile levelFileOriginal;

    private void affichageIconEditeur() {
        Context context = getContext();
        Icons crayon = new Icons(context, 10, 90, R.drawable.crayon);
        crayon.setOnClickListener(v -> ((MainActivity) context).goEditeur(levelFileOriginal));
        this.addView(crayon);
    }

    private void setDeleteEffect(View view) {
        Context context = getContext();
        view.setOnClickListener(v -> new Thread(() -> {
            PersonalFiles personalFiles = PersonalFiles.getInstance(context);
            personalFiles.remove(levelFileOriginal);
            ((MainActivity) context).getTexteMenuHD().afficherNombreDeFichiersPerso();
        }).start());
    }

    private void afficherIconsPoubelle() {
        Context context = getContext();
        Icons poubelle = new Icons(context, 10 + 30 + 40, 90, R.drawable.poubelle);
        setDeleteEffect(poubelle);
        this.addView(poubelle);
    }

    private void affichageIcons_pourNiveauPerso() {
        affichageIconEditeur();
        afficherIconsPoubelle();
    }

    /**
     * Création d'un bouton de redirection pour choisir un niveau.
     *
     * @param topMargin  la marge supérieure.
     * @param levelFile l'id du niveeau vers lequel ce bouton redirige.
     */
    public BoutonRedirectionNiveauPerso(@NonNull Scroller scroller, int topMargin, LevelFile levelFile) {
        super(scroller, topMargin, levelFile);
        levelFileAOuvrir = levelFile;
        levelFileOriginal = levelFile;
        creeSilouhettePlateau();
        afficherLaDate();
        affichageIcons_pourNiveauPerso();
    }
}
