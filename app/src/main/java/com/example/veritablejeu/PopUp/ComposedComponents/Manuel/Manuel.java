package com.example.veritablejeu.PopUp.ComposedComponents.Manuel;

import android.annotation.SuppressLint;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.veritablejeu.PopUp.ComposedComponents.Manuel.PageInstruction.PageInstruction;
import com.example.veritablejeu.PopUp.ComposedComponents.Manuel.PartieInferieureAPageNumerotee.ContainerDeFleche;
import com.example.veritablejeu.PopUp.ComposedComponents.Manuel.PartieInferieureAPageNumerotee.PartieInferieureAPageNumerotee;
import com.example.veritablejeu.PopUp.InlineComponent.PopUpComponent;
import com.example.veritablejeu.PopUp.PopUp;
import com.example.veritablejeu.Tools.LayoutParams.LayoutParamsDeBase_pourFrameLayout;
import com.example.veritablejeu.R;

import org.jetbrains.annotations.Contract;

@SuppressLint("ViewConstructor")
public class Manuel extends PopUpComponent {

    private static Manuel instance;
    private final PopUp parent;
    private ContainerDeFleche boutonGauche;
    private ContainerDeFleche boutonDroite;
    private int pageActuelle = 0;
    private final PartieInferieureAPageNumerotee partieInferieureAPageNumerotee;
    private PageInstruction pageInstruction;
    private final int nombreDePages;

    @NonNull
    @Contract(pure = true)
    private String getAnnotationNumeroPage() {
        return (pageActuelle + 1) + "/" + nombreDePages;
    }

    private int getNbPagesNormal() {
        return pageInstruction.getNbDePages();
    }

    /**
     * Modifie le contenu dans la page d'instruction ainsi que le numéro de page indiqué.
     */
    private void changementPage() {
        pageInstruction.setALaPage(pageActuelle + 1);
        partieInferieureAPageNumerotee.setAffichageNumeroPage(getAnnotationNumeroPage());

        if(boutonGauche != null) {
            if (pageActuelle == 0) {
                partieInferieureAPageNumerotee.removeView(boutonGauche);
            } else {
                if (boutonGauche.getParent() == null) partieInferieureAPageNumerotee.addView(boutonGauche);
            }
        }

        if(boutonDroite != null) {
            boolean dernierePageAtteinte = pageActuelle == nombreDePages - 1;
            if (dernierePageAtteinte) {
                boutonDroite.getImage().setImageResource(R.drawable.croix);
                boutonDroite.setOnClickListener(v -> parent.hide());
            } else {
                boutonDroite.getImage().setImageResource(R.drawable.fleche_droite);
                boutonDroite.setOnClickListener(v -> pageSuivante());
            }
        }
    }

    /**
     * Update le manuel pour afficher la page page précédente.
     */
    private void pagePrecedente() {
        pageActuelle = Math.max(0, pageActuelle - 1);
        changementPage();
    }

    /**
     * Update le manuel pour afficher la page page suivante.
     */
    private void pageSuivante() {
        int numeroDernierePage = nombreDePages - 1;
        pageActuelle = Math.min(numeroDernierePage, pageActuelle + 1);
        changementPage();
    }

    /**
     * Création de la partie inférieure ddu manuel.
     * @param width la largeur de la partie inférieure.
     * @param height la hauteur.
     * @param topMargin la marge supérieure.
     * @return un PartieInferieureAPageNumerotee.
     */
    private PartieInferieureAPageNumerotee creationPartieInferieure(int width, int height, int topMargin) {
        PartieInferieureAPageNumerotee partieInferieureAPageNumerotee = new PartieInferieureAPageNumerotee(
                this, width, height, topMargin, parent.getBORDER_WIDTH(), this::pagePrecedente, this::pageSuivante
        );
        partieInferieureAPageNumerotee.setAffichageNumeroPage(getAnnotationNumeroPage());

        boutonGauche = partieInferieureAPageNumerotee.getFlecheGauche();
        boutonDroite = partieInferieureAPageNumerotee.getFlecheDroite();

        return partieInferieureAPageNumerotee;
    }

    /**
     * Création de l'object page instruction. Et initialisation à la première page.
     * @param parent la popUp parente de la classe.
     * @return une PageInstruction.
     */
    private PageInstruction creationPageInstruction(PopUp parent) {
        PageInstruction pageInstruction = new PageInstruction(this, parent.get_width());
        pageInstruction.setALaPage(pageActuelle + 1);
        return pageInstruction;
    }

    /**
     * Création du conteneur pour les instructions.
     * @param parent la popUp parente.
     * @param height la hauteur du conteneur.
     * @param topMargin la marge supérieure.
     * @return un FrameLayout.
     */
    @NonNull
    private FrameLayout creationConteneurInstructions(@NonNull PopUp parent, int height, int topMargin) {
        int margesGDtexte = 60;
        int largeurTexte = parent.get_width() - 2 * margesGDtexte;
        FrameLayout conteneurInstructions = new FrameLayout(this.getContext());
        FrameLayout.LayoutParams layoutParamsTexte = new LayoutParamsDeBase_pourFrameLayout(
                largeurTexte, height, margesGDtexte, topMargin);
        conteneurInstructions.setLayoutParams(layoutParamsTexte);

        this.pageInstruction= creationPageInstruction(parent);
        conteneurInstructions.addView(pageInstruction);
        return conteneurInstructions;
    }

    private Manuel(@NonNull PopUp parent) {
        super(parent);
        this.parent = parent;

        int margesH = 15;
        int margesB = 0;
        int hauteurTexteApproximative = 920;
        int hauteurTexteAvecMarge = hauteurTexteApproximative + margesH + margesB;
        int hauteurPartieInferieure = 100;
        int height = hauteurTexteAvecMarge + hauteurPartieInferieure;

        int topMarginManuel = parent.getInitialHeight();
        FrameLayout.LayoutParams layoutParams = new LayoutParamsDeBase_pourFrameLayout(
                ConstraintLayout.LayoutParams.MATCH_PARENT, height,
                0, topMarginManuel);
        this.setLayoutParams(layoutParams);

        FrameLayout conteneurInstructions = creationConteneurInstructions(
                parent, hauteurTexteApproximative, margesH);
        this.addView(conteneurInstructions);

        this.nombreDePages = getNbPagesNormal();

        int largeurPartieInferieure = parent.get_width();
        this.partieInferieureAPageNumerotee = creationPartieInferieure(
                largeurPartieInferieure, hauteurPartieInferieure, hauteurTexteAvecMarge);
        partieInferieureAPageNumerotee.removeView(boutonGauche);
        this.addView(partieInferieureAPageNumerotee);
    }

    public static Manuel getInstance(@NonNull PopUp parent) {
        if(instance == null) {
            instance = new Manuel(parent);
        }
        return instance;
    }
}
