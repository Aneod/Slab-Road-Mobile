package com.example.veritablejeu.Menu.PageDeSelection;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;

import com.example.veritablejeu.LevelFile.LevelCategory;
import com.example.veritablejeu.LevelFile.LevelFile;
import com.example.veritablejeu.DataBases.Local.UserData;
import com.example.veritablejeu.Tools.LayoutParams.LayoutParamsDeBase_pourFrameLayout;
import com.example.veritablejeu.Menu.BoutonRedirection.BoutonRedirectionNiveau.BoutonRedirectionNiveau;
import com.example.veritablejeu.Menu.BoutonRedirection.BoutonRedirectionNiveau.BoutonRedirectionNiveauType.BoutonRedirectionNiveauBloque;
import com.example.veritablejeu.Menu.BoutonRedirection.BoutonRedirectionNiveau.BoutonRedirectionNiveauType.BoutonRedirectionNiveauMondial;
import com.example.veritablejeu.Menu.BoutonRedirection.BoutonRedirectionNiveau.BoutonRedirectionNiveauType.BoutonRedirectionNiveauNormal;
import com.example.veritablejeu.Menu.BoutonRedirection.BoutonRedirectionNiveau.BoutonRedirectionNiveauType.BoutonRedirectionNiveauPerso;
import com.example.veritablejeu.Menu.MainActivity;

import java.util.ArrayList;
import java.util.List;

@SuppressLint("ViewConstructor")
public class ListeDefilanteDeNiveaux extends FrameLayout {
    private final PanneauDeNiveaux parent;
    private final ArrayList<BoutonRedirectionNiveau> listeDesBoutons = new ArrayList<>();
    private float currentTranslationY;
    private int hauteurTotalDeLaListe;
    private final int height;
    private final int width;

    public PanneauDeNiveaux getPanneauDeNiveauxParent() {
        return parent;
    }

    public int getTranslationYmax() {
        return height - hauteurTotalDeLaListe;
    }

    /**
     * Effectue une même translation sur tous les éléments de la liste des boutons.
     * @param deltaY la translation relative à effectuer.
     */
    public void setTranslationYListe(float deltaY) {
        int translationYmax = getTranslationYmax();
        if(translationYmax >= 0) return;

        float nouvelleTranslationY = currentTranslationY + deltaY;
        float translationAFaire;
        if(nouvelleTranslationY > 0) translationAFaire = 0;
        else if(nouvelleTranslationY < translationYmax) translationAFaire = translationYmax;
        else translationAFaire = nouvelleTranslationY;

        this.currentTranslationY = translationAFaire;
        for(BoutonRedirectionNiveau boutonRedirectionNiveau : listeDesBoutons) {
            boutonRedirectionNiveau.setTranslationY(currentTranslationY);
        }
    }

    public void effacerLaListe() {
        removeAllViews();
        listeDesBoutons.clear();
    }

    private void afficherBoutonRedirectionNiveau(int hauteurBouton, int hauteur, LevelFile levelFile) {
        int leftMargin = 0;
        int idLevel = levelFile.id;
        int scoreActuel = UserData.getUserScore(getContext().getApplicationContext());
        int dernierNiveauAutorise = scoreActuel + 1;
        boolean estBloque = idLevel > dernierNiveauAutorise;
        boolean estUnNiveauNormal = levelFile.levelCategory == LevelCategory.Normaux;
        boolean estUnNiveauPerso = levelFile.levelCategory == LevelCategory.Perso;
        boolean niveauAutorise = !(estUnNiveauNormal && estBloque);

        BoutonRedirectionNiveau boutonRedirection;
        if(!niveauAutorise) {
            boutonRedirection = new BoutonRedirectionNiveauBloque(
                    getContext(), this, width, hauteurBouton, leftMargin, hauteur, levelFile);
        }
        else if(estUnNiveauNormal) {
            boutonRedirection = new BoutonRedirectionNiveauNormal(
                    getContext(), this, width, hauteurBouton, leftMargin, hauteur, levelFile);
        } else if(estUnNiveauPerso) {
            boutonRedirection = new BoutonRedirectionNiveauPerso(
                    getContext(), this, width, hauteurBouton, leftMargin, hauteur, levelFile);
        } else {
            boutonRedirection = new BoutonRedirectionNiveauMondial(
                    getContext(), this, width, hauteurBouton, leftMargin, hauteur, levelFile);
        }
        this.addView(boutonRedirection);
        listeDesBoutons.add(boutonRedirection);
    }

    public void changerLesNiveauxAffiches(List<LevelFile> nouveauxNumerosNiveauDeLaPage) {
        ((MainActivity) getContext()).runOnUiThread(() -> {
            effacerLaListe();
            this.currentTranslationY = 0;
            int hauteurBouton = 150;
            int ecartYEntreBoutons = 1;
            int ecartTotalEntreBoutons = hauteurBouton + ecartYEntreBoutons;

            int hauteur = 0;
            for (LevelFile levelFile : nouveauxNumerosNiveauDeLaPage) {
                afficherBoutonRedirectionNiveau(hauteurBouton, hauteur, levelFile);
                hauteur += ecartTotalEntreBoutons;
            }

            int nbElements = listeDesBoutons.size();
            hauteurTotalDeLaListe = nbElements * ecartTotalEntreBoutons - ecartYEntreBoutons;
        });
    }

    public ListeDefilanteDeNiveaux(@NonNull Context context, PanneauDeNiveaux parent, int width, int height, int leftMargin, int topMargin) {
        super(context);
        this.setBackgroundColor(Color.LTGRAY);
        this.parent = parent;
        this.width = width;
        this.height = height;
        FrameLayout.LayoutParams layoutParamsSilhouette = new LayoutParamsDeBase_pourFrameLayout(
                width, height, leftMargin, topMargin);
        this.setLayoutParams(layoutParamsSilhouette);
    }
}
