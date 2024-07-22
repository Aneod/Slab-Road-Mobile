package com.example.veritablejeu.Menu.BoutonRedirection.BoutonRedirectionNiveau.BoutonRedirectionNiveauType;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;

import com.example.veritablejeu.BackEnd.DataBases.Local.LevelFiles.PersonalFiles;
import com.example.veritablejeu.BackEnd.LevelFile.LevelFile;
import com.example.veritablejeu.LevelsPanel.Scroller.Scroller;
import com.example.veritablejeu.Menu.BoutonRedirection.BoutonRedirectionNiveau.BoutonRedirectionNiveau;
import com.example.veritablejeu.Menu.MainActivity;
import com.example.veritablejeu.Menu.PageDeSelection.ListeDefilanteDeNiveaux;
import com.example.veritablejeu.Menu.PageDeSelection.PanneauDeNiveaux;
import com.example.veritablejeu.Menu.PageDeSelection.PanneauDeNiveauxParticulier.PanneauDeNiveauxPersonel.PanneauDeNiveauxPersonel;
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

            FrameLayout panneauDeNiveaux = parent.getPanneauDeNiveauxParent();
            if(panneauDeNiveaux instanceof PanneauDeNiveauxPersonel) {
                ((PanneauDeNiveauxPersonel) panneauDeNiveaux).getLevelFilesPersonalFilesList().remove(levelFileOriginal);
                ((PanneauDeNiveauxPersonel) panneauDeNiveaux).loadLookingPersonalFilesPage();
            }

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
     * @param parent     la frameLayout parent.
     * @param width      la largeur du bouton.
     * @param height     la hauteur du bouton.
     * @param leftMargin la marge gauche.
     * @param topMargin  la marge supérieure.
     * @param levelFile l'id du niveeau vers lequel ce bouton redirige.
     */
    public BoutonRedirectionNiveauPerso(@NonNull Context context, Scroller parent, int width, int height, int leftMargin, int topMargin, LevelFile levelFile) {
        super(context, parent, width, height, leftMargin, topMargin, levelFile);
        levelFileAOuvrir = levelFile;
        levelFileOriginal = levelFile;
        creeSilouhettePlateau();
        afficherLaDate();
        affichageIcons_pourNiveauPerso();
    }
}
