package com.slabroad.veritablejeu.Navigation;

import androidx.annotation.Nullable;

import com.slabroad.veritablejeu.Navigation.Association_Symbole_Fonction.Association_Symbole_Fonction;
import com.slabroad.veritablejeu.Navigation.BoutonNavigation.BoutonPrincipal.BoutonPrincipal;

import java.util.List;

public interface INavigation {

    /**
     * Modifie les associations images/méthode de la navigation.
     * @param associations une List<Association_Symbole_Fonction>.
     */
    void setContenu(List<Association_Symbole_Fonction> associations);

    /**
     * Retourne le bouton principal. Normalement il s'agira toujours du bouton au 6ème rang.
     * @return un BoutonNavigation. Ou null dans le cas où le bouton principal ne serais
     * pas défini.
     */
    @Nullable
    BoutonPrincipal getBoutonPrincipal();

    /**
     * Retourne en pixel la valeur des marges haut, gauche et droite.
     * @return un entier, ou 0 si ces marges ne sont pas encore définies.
     */
    int getMargesHautGaucheDroite();

    /**
     * Modifie les fonctions qui sont enclenchées lors d'une ouverture/fermeture de la
     * navigation. Ceci par un click sur le {@link BoutonPrincipal}.
     * <p>
     * Pour plus de détail : {@link BoutonPrincipal#ouvrirFermer(Runnable, Runnable)}
     */
    void setFonction_pour_ouverture_fermeture(
            @Nullable Runnable runnableOuverture, @Nullable Runnable runnableFermeture
    );

    /**
     * Effectuer des zoom/dézoom sur les boutons suivants :
     * <p>
     * - Bouton manuel.
     * <p>
     * - Bouton principal si le menu est fermé.
     */
    void activerFocus();

    /**
     * Show all buttons.
     */
    void show();

    /**
     * Hide all buttons.
     */
    void hide();
}
