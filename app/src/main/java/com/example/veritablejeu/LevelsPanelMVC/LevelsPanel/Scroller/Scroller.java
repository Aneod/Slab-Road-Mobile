package com.example.veritablejeu.LevelsPanelMVC.LevelsPanel.Scroller;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;

import com.example.veritablejeu.BackEnd.DataBases.Local.UserData;
import com.example.veritablejeu.BackEnd.LevelFile.LevelFile;
import com.example.veritablejeu.LevelsPanelMVC.LevelsPanel.BottomBar.BottomBar;
import com.example.veritablejeu.LevelsPanelMVC.LevelsPanel.LevelsPanel;
import com.example.veritablejeu.LevelsPanelMVC.LevelsPanel.Scroller.Components.Indicator;
import com.example.veritablejeu.Menu.BoutonRedirection.BoutonRedirectionNiveau.BoutonRedirectionNiveau;
import com.example.veritablejeu.Menu.BoutonRedirection.BoutonRedirectionNiveau.BoutonRedirectionNiveauType.BoutonRedirectionNiveauBloque;
import com.example.veritablejeu.Menu.BoutonRedirection.BoutonRedirectionNiveau.BoutonRedirectionNiveauType.BoutonRedirectionNiveauMondial;
import com.example.veritablejeu.Menu.BoutonRedirection.BoutonRedirectionNiveau.BoutonRedirectionNiveauType.BoutonRedirectionNiveauNormal;
import com.example.veritablejeu.Menu.BoutonRedirection.BoutonRedirectionNiveau.BoutonRedirectionNiveauType.BoutonRedirectionNiveauPerso;
import com.example.veritablejeu.Menu.MainActivity;
import com.example.veritablejeu.R;
import com.example.veritablejeu.Tools.LayoutParams.LayoutParamsDeBase_pourFrameLayout;

import java.util.ArrayList;
import java.util.List;

@SuppressLint("ViewConstructor")
public class Scroller extends FrameLayout implements IScroller {

    private final ArrayList<BoutonRedirectionNiveau> listeDesBoutons = new ArrayList<>();
    private float currentTranslationY;
    private int hauteurTotalDeLaListe;
    private final Indicator indicator;
    private LevelCategory levelCategory = LevelCategory.Normal;

    public int getTranslationYmax() {
        return getLayoutParams().height - hauteurTotalDeLaListe;
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

        int width = getLayoutParams().width;
        BoutonRedirectionNiveau boutonRedirection;
        if(!niveauAutorise) {
            boutonRedirection = new BoutonRedirectionNiveauBloque(
                    getContext(), this, width, hauteurBouton, leftMargin, hauteur, levelFile);
        } else if(estUnNiveauNormal) {
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

    @NonNull
    private FrameLayout.LayoutParams get_layoutParams(@NonNull LevelsPanel levelsPanel) {
        int border = LevelsPanel.getBorderWidth();
        int width = levelsPanel.getLayoutParams().width - 2 * border;
        int height = levelsPanel.getLayoutParams().height - 2 * border - (BottomBar.getHEIGHT() - border);
        return new LayoutParamsDeBase_pourFrameLayout(
                width, height, border, border
        );
    }

    public Scroller(@NonNull LevelsPanel levelsPanel) {
        super(levelsPanel.getContext());
        this.setBackgroundColor(Color.LTGRAY);

        FrameLayout.LayoutParams layoutParams = get_layoutParams(levelsPanel);
        this.setLayoutParams(layoutParams);

        indicator = new Indicator(this);
    }

    @Override
    public void setLevelCategory(LevelCategory category) {
        this.levelCategory = category;
    }

    @Override
    public void showLevels(List<LevelFile> levelFiles) {
        indicator.hide();
        if(levelFiles == null || levelFiles.isEmpty()) {
            showNotFilesMessage();
        }
        changerLesNiveauxAffiches(levelFiles);
    }

    @Override
    public void showLoadingIcon() {
        indicator.setImage_andShow(
                R.drawable.effets_scintillant
        );
    }

    @Override
    public void showNotFilesMessage() {
        indicator.setImage_andShow(
                R.drawable.aucun_fichier
        );
    }

    @Override
    public void showLocalDataNotFoundMessage() {
        indicator.setImage_andShow(
                R.drawable.symbole_parametres
        );
    }

    @Override
    public void showDisconnectedMessage() {
        indicator.setImage_andShow(
                R.drawable.pas_de_connexion
        );
    }


    public enum LevelCategory {
        Normal, Personal, Global
    }
}
