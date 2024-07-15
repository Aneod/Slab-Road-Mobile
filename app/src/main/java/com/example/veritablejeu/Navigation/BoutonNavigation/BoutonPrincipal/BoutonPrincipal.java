package com.example.veritablejeu.Navigation.BoutonNavigation.BoutonPrincipal;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.veritablejeu.Navigation.BoutonNavigation.BoutonNavigation;
import com.example.veritablejeu.Navigation.BoutonNavigation.BoutonSecondaire.BoutonSecondaire;

import java.util.ArrayList;

public class BoutonPrincipal extends BoutonNavigation implements IBoutonPrincipal {

    private final ArrayList<BoutonSecondaire> boutonsSecondaires = new ArrayList<>();
    private boolean ouvert = false;
    private final float SCALE_QUAND_REDUIT = 0.85f;
    private final long DUREE_ANIMATIONS = 250;
    private final long TEMPS_TOTAL_FERMETURE = 400;

    public BoutonPrincipal(@NonNull Context context) {
        super(context);
    }

    public BoutonPrincipal(@NonNull Context context, int diametre, int leftMargin, int topMargin) {
        super(context, diametre, leftMargin, topMargin);
        reduire(0);
    }

    @Override
    public void boutonsSecondaire_ajouter(@NonNull BoutonSecondaire boutonSecondaire) {
        boutonsSecondaires.add(boutonSecondaire);
    }

    @Override
    public int getDiametreReduit() {
        return (int) (getDiametre() * SCALE_QUAND_REDUIT);
    }

    @Override
    public long getTempsDeFermeture() {
        return TEMPS_TOTAL_FERMETURE;
    }

    @Override
    public void ouvrirFermer(@Nullable Runnable runnableOuverture, @Nullable Runnable runnableFermeture) {
        if(ouvert) {
            fermer();
            if(runnableFermeture != null) {
                runnableFermeture.run();
            }
        }
        else {
            ouvrir();
            if(runnableOuverture != null) {
                runnableOuverture.run();
            }
        }
    }

    private void agrandir() {
        scaleAnimation(SCALE_QUAND_REDUIT, 1.0f, 0, DUREE_ANIMATIONS);
    }

    private void reduire(long dureeAnimation) {
        scaleAnimation(1.0f, SCALE_QUAND_REDUIT, 0, dureeAnimation);
    }

    @Override
    public void ouvrir() {
        ouvert = true;
        agrandir();

        int NOMBRE_BOUTONS_SECONDAIRE = boutonsSecondaires.size();
        long nombreBoutonsPlusUn = NOMBRE_BOUTONS_SECONDAIRE + 1;
        long TEMPS_TOTAL_OUVERTURE = 350;
        long tempsAnimationParBouton = TEMPS_TOTAL_OUVERTURE / nombreBoutonsPlusUn * 2;
        long decalageEntreDeuxDebutDeAnimation = tempsAnimationParBouton / 2;

        for(int index = 0; index < NOMBRE_BOUTONS_SECONDAIRE; index++) {
            long startOffSet = decalageEntreDeuxDebutDeAnimation * index;

            int indexBouton = (NOMBRE_BOUTONS_SECONDAIRE - 1) - index;
            BoutonSecondaire boutonSecondaire = boutonsSecondaires.get(indexBouton);
            boutonSecondaire.montrer(startOffSet, tempsAnimationParBouton);
        }
    }

    @Override
    public void fermer() {
        ouvert = false;
        reduire(DUREE_ANIMATIONS);

        int NOMBRE_BOUTONS_SECONDAIRE = boutonsSecondaires.size();
        long nombreBoutonsPlusUn = NOMBRE_BOUTONS_SECONDAIRE + 1;
        long tempsAnimationParBouton = TEMPS_TOTAL_FERMETURE / nombreBoutonsPlusUn * 2;
        long decalageEntreDeuxDebutDeAnimation = tempsAnimationParBouton / 2;

        for(int index = 0; index < NOMBRE_BOUTONS_SECONDAIRE; index++) {
            long startOffSet = decalageEntreDeuxDebutDeAnimation * index;
            BoutonSecondaire boutonSecondaire = boutonsSecondaires.get(index);
            boutonSecondaire.cacher(startOffSet, tempsAnimationParBouton);
        }
    }

    @Override
    public void activerFocus() {
        int duration = 500;
        float FORCE_DU_CLICK = 1.0f;
        activerCycleDeFocus(duration, FORCE_DU_CLICK);
    }

    private void activerCycleDeFocus(int duration, float FORCE_DU_CLICK) {
        if(!ouvert) {
            scaleAnimation(SCALE_QUAND_REDUIT, FORCE_DU_CLICK, 0, duration);
            postDelayed(() -> {
                if(!ouvert) {
                    scaleAnimation(FORCE_DU_CLICK, SCALE_QUAND_REDUIT, 0, duration);
                }
            }, duration);
        }
        postDelayed(() -> activerCycleDeFocus(duration, FORCE_DU_CLICK), 2L * duration);
    }
}
