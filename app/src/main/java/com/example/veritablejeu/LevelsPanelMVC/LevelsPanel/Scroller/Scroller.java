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
import com.example.veritablejeu.R;
import com.example.veritablejeu.Tools.LayoutParams.LayoutParamsDeBase_pourFrameLayout;

import java.util.ArrayList;
import java.util.List;

@SuppressLint("ViewConstructor")
public class Scroller extends FrameLayout implements IScroller {

    private final LevelsPanel levelsPanel;
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
        listeDesBoutons.forEach(this::removeView);
        listeDesBoutons.clear();
    }

    private void afficherBoutonRedirectionNiveau(int hauteur, LevelFile levelFile) {
        if(levelFile == null) return;

        int idLevel = levelFile.id;
        int scoreActuel = UserData.getUserScore(getContext().getApplicationContext());
        int dernierNiveauAutorise = scoreActuel + 1;
        boolean estBloque = idLevel > dernierNiveauAutorise;
        boolean estUnNiveauNormal = levelCategory == LevelCategory.Normal;
        boolean estUnNiveauPerso = levelCategory == LevelCategory.Personal;
        boolean niveauAutorise = !(estUnNiveauNormal && estBloque);

        BoutonRedirectionNiveau boutonRedirection;
        if(!niveauAutorise) {
            boutonRedirection = new BoutonRedirectionNiveauBloque(this, hauteur, levelFile);
        } else if(estUnNiveauNormal) {
            boutonRedirection = new BoutonRedirectionNiveauNormal(this, hauteur, levelFile);
        } else if(estUnNiveauPerso) {
            boutonRedirection = new BoutonRedirectionNiveauPerso(this, hauteur, levelFile);
        } else {
            boutonRedirection = new BoutonRedirectionNiveauMondial(this, hauteur, levelFile);
        }
        this.addView(boutonRedirection);
        listeDesBoutons.add(boutonRedirection);
    }

    public void changerLesNiveauxAffiches(List<LevelFile> levelFileList) {
        if(levelFileList == null) return;
        this.currentTranslationY = 0;
        int hauteurBouton = BoutonRedirectionNiveau.getHEIGHT();
        int ecartYEntreBoutons = 1;
        int ecartTotalEntreBoutons = hauteurBouton + ecartYEntreBoutons;

        int hauteur = 0;
        for (LevelFile levelFile : levelFileList) {
            afficherBoutonRedirectionNiveau(hauteur, levelFile);
            hauteur += ecartTotalEntreBoutons;
        }

        int nbElements = listeDesBoutons.size();
        hauteurTotalDeLaListe = nbElements * ecartTotalEntreBoutons - ecartYEntreBoutons;
    }

    @NonNull
    private FrameLayout.LayoutParams get_layoutParams() {
        int border = LevelsPanel.getBorderWidth();
        int width = levelsPanel.getLayoutParams().width - 2 * border;
        int height = levelsPanel.getLayoutParams().height - 2 * border - (BottomBar.getHEIGHT() - border);
        return new LayoutParamsDeBase_pourFrameLayout(
                width, height, border, border
        );
    }

    public Scroller(@NonNull LevelsPanel levelsPanel) {
        super(levelsPanel.getContext());
        this.levelsPanel = levelsPanel;
        this.setBackgroundColor(Color.LTGRAY);
        refreshLayoutParams();
        indicator = new Indicator(this);
    }

    public void refreshLayoutParams() {
        FrameLayout.LayoutParams layoutParams = get_layoutParams();
        setLayoutParams(layoutParams);
        if(indicator != null) {
            indicator.refreshLayoutParams();
        }
    }

    @Override
    public void setLevelCategory(LevelCategory category) {
        this.levelCategory = category;
    }

    @Override
    public void showLevels(List<LevelFile> levelFiles) {
        effacerLaListe();
        if(levelFiles == null || levelFiles.isEmpty()) {
            showNotFilesMessage();
            return;
        }
        indicator.hide();
        changerLesNiveauxAffiches(levelFiles);
    }

    @Override
    public void showLoadingIcon() {
        indicator.setImage_andShow(
                R.drawable.three_points
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
