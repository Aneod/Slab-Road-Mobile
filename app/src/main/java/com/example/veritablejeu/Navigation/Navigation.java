package com.example.veritablejeu.Navigation;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.veritablejeu.Navigation.Association_Symbole_Fonction.Association_Symbole_Fonction;
import com.example.veritablejeu.Navigation.BoutonNavigation.BoutonNavigation;
import com.example.veritablejeu.Navigation.BoutonNavigation.BoutonPrincipal.BoutonPrincipal;
import com.example.veritablejeu.Navigation.BoutonNavigation.BoutonSecondaire.BoutonSecondaire;
import com.example.veritablejeu.Tools.ScreenUtils;
import com.example.veritablejeu.R;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Navigation implements INavigation {

    private static final int BOUTONS_SECONDAIRES_NOMBRE = 5;
    private static final float PROPORTION_AUTORISE_DE_ECRAN = 0.9f;
    private static final float COEF_LARGEUR_BOUTON_PRINCIPAL = 1.0f;
    private static final float COEF_LARGEUR_BOUTONS_SECONDAIRES = 0.8f;


    private final AppCompatActivity context;
    private final ConstraintLayout container;
    protected BoutonPrincipal boutonPrincipal;
    private final Set<BoutonSecondaire> boutonSecondaireSet = new HashSet<>();
    private BoutonSecondaire boutonManuel;
    private int margesHGD = 0;

    private void creationDesBoutons(List<Association_Symbole_Fonction> associations) {
        if(container == null) return;
        int largeurAutorisee = (int) (ScreenUtils.getScreenWidth() * PROPORTION_AUTORISE_DE_ECRAN);
        this.margesHGD = (ScreenUtils.getScreenWidth() - largeurAutorisee) / 2;
        int largeurBoutons_enPixel = largeurAutorisee / 6;
        int largeurBoutonPrincipal_enPixel = (int) (largeurBoutons_enPixel * COEF_LARGEUR_BOUTON_PRINCIPAL);
        int largeurBoutonsSecondaire_enPixel = (int) (largeurBoutons_enPixel * COEF_LARGEUR_BOUTONS_SECONDAIRES);
        int topMarginBoutonsSecondaires = margesHGD + (largeurBoutonPrincipal_enPixel - largeurBoutonsSecondaire_enPixel) / 2;
        int espaceOccupeParLesBoutons_enPixel = largeurBoutonPrincipal_enPixel + largeurBoutonsSecondaire_enPixel * BOUTONS_SECONDAIRES_NOMBRE;
        int espaceRestant = largeurAutorisee - espaceOccupeParLesBoutons_enPixel;
        int espaceRestant_minorePar0 = Math.max(0, espaceRestant);
        int espaceEntreLesBoutons_enPixel = espaceRestant_minorePar0 / BOUTONS_SECONDAIRES_NOMBRE;
        int distanceEntreLesBoutons = espaceEntreLesBoutons_enPixel + largeurBoutonsSecondaire_enPixel;

        int leftMargin = margesHGD + distanceEntreLesBoutons * 5;
        this.boutonPrincipal = new BoutonPrincipal(context, largeurBoutonPrincipal_enPixel, leftMargin, margesHGD);
        boutonPrincipal.setImage(R.drawable.ring, 0.7f);
        boutonPrincipal.setOnClickListener(v -> boutonPrincipal.ouvrirFermer(null, null));
        container.addView(boutonPrincipal);

        for(int index = 0; index < BOUTONS_SECONDAIRES_NOMBRE; index++) {
            int leftMarginBoutonSecondaire = margesHGD + distanceEntreLesBoutons * index;
            BoutonSecondaire boutonNavigation = new BoutonSecondaire(
                    context, largeurBoutonsSecondaire_enPixel, leftMarginBoutonSecondaire, topMarginBoutonsSecondaires
            );
            boutonPrincipal.boutonsSecondaire_ajouter(boutonNavigation);
            boutonSecondaireSet.add(boutonNavigation);
            container.addView(boutonNavigation);

            if(index < associations.size()) {
                Association_Symbole_Fonction association = associations.get(index);

                Integer symbole = association.getSymbole();
                if(symbole != null) {
                    boutonNavigation.setImage(symbole, .6f);
                    boolean boutonPourManuel = symbole.equals(R.drawable.point_interrogation);
                    if(boutonPourManuel) boutonManuel = boutonNavigation;
                }

                Runnable runnable = association.getRunnable();
                boutonNavigation.setOnClickListener(v -> {
                    if(runnable != null) {
                        runnable.run();
                    }
                    boutonNavigation.effectuerAnimationDeClick();
                });
            }
        }
    }

    public Navigation(@NonNull AppCompatActivity context) {
        this.context = context;
        container = context.findViewById(R.id.main);
    }

    @Override
    public void setContenu(List<Association_Symbole_Fonction> associations) {
        creationDesBoutons(associations);
    }

    @Nullable
    @Override
    public BoutonPrincipal getBoutonPrincipal() {
        return boutonPrincipal;
    }

    @Override
    public int getMargesHautGaucheDroite() {
        return margesHGD;
    }

    @Override
    public void setFonction_pour_ouverture_fermeture(@Nullable Runnable runnableOuverture, @Nullable Runnable runnableFermeture) {
        boutonPrincipal.setOnClickListener(v -> boutonPrincipal.ouvrirFermer(runnableOuverture, runnableFermeture));
    }

    @Override
    public void activerFocus() {
        boutonPrincipal.activerFocus();
        if(boutonManuel != null) {
            boutonManuel.activerFocus();
        }
    }

    @Override
    public void show() {
        if(boutonPrincipal.getParent() == null) {
            container.addView(boutonPrincipal);
            boutonPrincipal.reduire(0);
        }
        for(BoutonSecondaire boutonSecondaire : boutonSecondaireSet) {
            if(boutonSecondaire.getParent() == null) {
                container.addView(boutonSecondaire);
                boutonSecondaire.cacher(0, 0);
            }
        }
    }

    @Override
    public void hide() {
        container.removeView(boutonPrincipal);
        boutonSecondaireSet.forEach(container::removeView);
    }
}
