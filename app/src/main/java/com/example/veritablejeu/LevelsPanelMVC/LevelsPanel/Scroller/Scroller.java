package com.example.veritablejeu.LevelsPanelMVC.LevelsPanel.Scroller;

import android.content.Context;
import android.graphics.Color;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;

import com.example.veritablejeu.BackEnd.DataBases.Local.UserData;
import com.example.veritablejeu.BackEnd.LevelFile.LevelFile;
import com.example.veritablejeu.Menu.BoutonRedirection.BoutonRedirectionNiveau.BoutonRedirectionNiveau;
import com.example.veritablejeu.Menu.BoutonRedirection.BoutonRedirectionNiveau.BoutonRedirectionNiveauType.BoutonRedirectionNiveauBloque;
import com.example.veritablejeu.Menu.BoutonRedirection.BoutonRedirectionNiveau.BoutonRedirectionNiveauType.BoutonRedirectionNiveauMondial;
import com.example.veritablejeu.Menu.BoutonRedirection.BoutonRedirectionNiveau.BoutonRedirectionNiveauType.BoutonRedirectionNiveauNormal;
import com.example.veritablejeu.Menu.BoutonRedirection.BoutonRedirectionNiveau.BoutonRedirectionNiveauType.BoutonRedirectionNiveauPerso;
import com.example.veritablejeu.Menu.MainActivity;
import com.example.veritablejeu.Menu.PageDeSelection.IndicationPourPanneauDeNiveaux;
import com.example.veritablejeu.Menu.PageDeSelection.SymboleDeChargement;
import com.example.veritablejeu.R;
import com.example.veritablejeu.Tools.LayoutParams.LayoutParamsDeBase_pourFrameLayout;

import java.util.ArrayList;
import java.util.List;

public class Scroller extends FrameLayout implements IScroller {

    private LevelCategory levelCategory = LevelCategory.Normal;

    private final FrameLayout parent;
    private final ArrayList<BoutonRedirectionNiveau> listeDesBoutons = new ArrayList<>();
    private float currentTranslationY;
    private int hauteurTotalDeLaListe;
    private final int height;
    private final int width;
    protected IndicationPourPanneauDeNiveaux indication;
    private final SymboleDeChargement symboleDeChargement;

    public FrameLayout getPanneauDeNiveauxParent() {
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
        boolean estUnNiveauNormal = levelCategory == LevelCategory.Normal;
        boolean estUnNiveauPerso = levelCategory == LevelCategory.Personal;
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

    public Scroller(@NonNull Context context, FrameLayout parent, int width, int height, int leftMargin, int topMargin) {
        super(context);
        this.setBackgroundColor(Color.LTGRAY);
        this.parent = parent;
        this.width = width;
        this.height = height;
        FrameLayout.LayoutParams layoutParamsSilhouette = new LayoutParamsDeBase_pourFrameLayout(
                width, height, leftMargin, topMargin);
        this.setLayoutParams(layoutParamsSilhouette);

        int largeurSymboleDeChargement = 30;
        int leftMarginSymbole = (width - largeurSymboleDeChargement) / 2;
        int topMarginSymbole = (height - largeurSymboleDeChargement) / 2;
        this.symboleDeChargement = new SymboleDeChargement(
                context, largeurSymboleDeChargement, leftMarginSymbole, topMarginSymbole
        );
        symboleDeChargement.setElevation(1);
        addView(symboleDeChargement);
    }

    @Override
    public void setLevelCategory(LevelCategory category) {
        this.levelCategory = category;
    }

    @Override
    public void showLevels(List<LevelFile> levelFiles) {
        symboleDeChargement.effacer();
        //removeView(indication);
        if(levelFiles == null || levelFiles.isEmpty()) {
            showNotFilesMessage();
        }
        changerLesNiveauxAffiches(levelFiles);
    }

    @Override
    public void showLoadingIcon() {

    }

    @Override
    public void showNotFilesMessage() {
        Context context = getContext();
        String text = "Aucun niveau dans vos fichiers. Vous pouvez en créer depuis l'éditeur.";
        int img = R.drawable.aucun_fichier;
        if(indication != null) {
            indication.removeAllViews();
        }
        indication = new IndicationPourPanneauDeNiveaux(
                context, this, text, img
        );
        symboleDeChargement.effacer();
        //addView(indication);
    }

    @Override
    public void showDisconnectedMessage() {
        Context context = getContext();
        String text = "Il semblerait que vous ayez un problème de connexion.";
        int img = R.drawable.pas_de_connexion;
        if(indication != null) {
            indication.removeAllViews();
        }
        indication = new IndicationPourPanneauDeNiveaux(
                context, this, text, img
        );
        symboleDeChargement.effacer();
        //addView(indication);
    }


    public enum LevelCategory {
        Normal, Personal, Global
    }
}
