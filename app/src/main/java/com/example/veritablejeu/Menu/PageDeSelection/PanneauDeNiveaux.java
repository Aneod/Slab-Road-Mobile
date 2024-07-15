package com.example.veritablejeu.Menu.PageDeSelection;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.veritablejeu.LevelFile.LevelFile;
import com.example.veritablejeu.OutilsEnEnum.LayoutParams.LayoutParamsDeBase_pourConstraintLayout;
import com.example.veritablejeu.Menu.MainActivity;
import com.example.veritablejeu.OutilsEnEnum.ScreenUtils;
import com.example.veritablejeu.PartieInferieureAPageNumerotee.PartieInferieureAPageNumerotee;
import com.example.veritablejeu.R;

import java.util.List;

public class PanneauDeNiveaux extends FrameLayout implements IPanneauDeNiveaux {

    protected final int PAGE_SIZE = 12;
    private final ListeDefilanteDeNiveaux listeDefilanteDeNiveaux;
    private final SymboleDeChargement symboleDeChargement;
    private final PartieInferieureAPageNumerotee partieInferieure;
    protected IndicationPourPanneauDeNiveaux indication;
    private int nombreTotalDePages;
    private int pageActuelle = 1;
    private boolean enChargement = true;

    public PanneauDeNiveaux(@NonNull Context context) {
        this(context, 130);
    }

    public PanneauDeNiveaux(@NonNull Context context, int margeSuperieure) {
        super(context);

        int margesGDB = 50;
        int width = ScreenUtils.getScreenWidth() - 2 * margesGDB;
        int height = ScreenUtils.getScreenHeight() - (margeSuperieure + margesGDB);
        int widthPositif = Math.abs(width);
        int heightPositif = Math.abs(height);

        ConstraintLayout.LayoutParams layoutParams = new LayoutParamsDeBase_pourConstraintLayout(
                widthPositif, heightPositif, margesGDB, margeSuperieure
        );
        this.setLayoutParams(layoutParams);

        int largeurBordure = 5;
        GradientDrawable background = new GradientDrawable();
        background.setColor(Color.LTGRAY);
        background.setStroke(largeurBordure, Color.BLACK);
        setBackground(background);

        int hauteurPartieInferieureDuPanneau = 100;
        int topMarginPartieInferieure = heightPositif - hauteurPartieInferieureDuPanneau;
        this.partieInferieure = new PartieInferieureAPageNumerotee(
                this, widthPositif, hauteurPartieInferieureDuPanneau, topMarginPartieInferieure, largeurBordure, null, null
        );
        this.addView(partieInferieure);

        int largeurSymboleDeChargement = 30;
        int leftMarginSymbole = (widthPositif - largeurSymboleDeChargement) / 2;
        int topMarginSymbole = (heightPositif - largeurSymboleDeChargement) / 2;
        this.symboleDeChargement = new SymboleDeChargement(
                context, largeurSymboleDeChargement, leftMarginSymbole, topMarginSymbole
        );
        symboleDeChargement.setElevation(1);
        addView(symboleDeChargement);

        int widthListeDefilante = widthPositif - 2 * largeurBordure;
        int heightListeDefilante = heightPositif - 2 * largeurBordure - (hauteurPartieInferieureDuPanneau - largeurBordure);
        this.listeDefilanteDeNiveaux = new ListeDefilanteDeNiveaux(context, this, widthListeDefilante, heightListeDefilante, largeurBordure, largeurBordure);
        this.addView(listeDefilanteDeNiveaux);
    }

    @Override
    public boolean getEnChargement() {
        return enChargement;
    }

    @Override
    public FrameLayout getBoutonPagePrecedente() {
        return partieInferieure.getFlecheGauche();
    }

    @Override
    public FrameLayout getBoutonPageSuivante() {
        return partieInferieure.getFlecheDroite();
    }

    @Override
    public int getNumeroDePage() {
        return pageActuelle;
    }

    @Override
    public void setNumeroDePage(int numeroDePage) {
        if(numeroDePage < 1) this.pageActuelle = 1;
        else if (nombreTotalDePages > 0 && numeroDePage > nombreTotalDePages) pageActuelle = nombreTotalDePages;
        else pageActuelle = numeroDePage;

        boolean nombreTotalDePagesInconnu = nombreTotalDePages == 0;
        String affichageDuNumeroDePage;
        if(nombreTotalDePagesInconnu) {
            affichageDuNumeroDePage = Integer.toString(pageActuelle);
        } else {
            affichageDuNumeroDePage = pageActuelle + "/" + nombreTotalDePages;
        }
        partieInferieure.setAffichageNumeroPage(affichageDuNumeroDePage);
    }

    @Override
    public void setNumeroDePage_precedent() {
        setNumeroDePage(--pageActuelle);
    }

    @Override
    public void setNumeroDePage_suivante() {
        setNumeroDePage(++pageActuelle);
    }

    @Override
    public void setNombreTotalDePage(int nombreTotalDePages) {
        this.nombreTotalDePages = Math.max(1, nombreTotalDePages);
        setNumeroDePage(pageActuelle);
    }

    @Override
    public void ajouterListeDeNiveaux(List<LevelFile> listeNiveaux) {
        symboleDeChargement.effacer();
        removeView(indication);
        listeDefilanteDeNiveaux.changerLesNiveauxAffiches(listeNiveaux);
        enChargement = false;

        if(listeNiveaux.isEmpty()) {
            afficherAucunFichier();
        }
    }

    @Override
    public void viderListeDeNiveaux() {
        symboleDeChargement.afficherEtReprendreAnimation();
        removeView(indication);
        listeDefilanteDeNiveaux.effacerLaListe();
        enChargement = true;
    }

    @Override
    public void afficherAucunFichier() {
        Context context = getContext();
        String text = "Aucun niveau dans vos fichiers. Vous pouvez en créer depuis l'éditeur.";
        int img = R.drawable.aucun_fichier;
        if(indication != null) {
            indication.removeAllViews();
        }
        indication = new IndicationPourPanneauDeNiveaux(
                context, this, text, img
        );
        afficherIndication();
    }

    @Override
    public void afficherIndication() {
        symboleDeChargement.effacer();
        if(indication.getParent() == null) {
            ((MainActivity) getContext()).runOnUiThread(() -> addView(indication));
        }
        enChargement = true;
    }
}
