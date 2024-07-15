package com.example.veritablejeu.Menu.PageDeSelection;

import android.widget.FrameLayout;

import com.example.veritablejeu.LevelFile.LevelFile;

import java.util.List;

public interface IPanneauDeNiveaux {

    /**
     * Renvoie la valeur de la variable enChargement. Par définition cette variable est true
     * si la liste des niveaux affichés est vide et que le symbole de chargement en affiché.
     * Utiliser cette valeur est utile pour emêcher d'allez à la page suivante/précédente
     * alors que la page actuelle n'est pas encore chargée. Ce qui peut causer des problèmes
     * si la récupération des pages est asynchrone.
     * @return un booléen.
     */
    boolean getEnChargement();

    /**
     * Renvoie le bouton "précédent".
     * @return un FrameLayout
     */
    FrameLayout getBoutonPagePrecedente();

    /**
     * Renvoie le bouton "suivant".
     * @return un FrameLayout
     */
    FrameLayout getBoutonPageSuivante();

    /**
     * Renvoie le numéro de page actuelle.
     * @return un int
     */
    int getNumeroDePage();

    /**
     * Modifie le numéro de page actuelle en bas du panneau.
     * Quel que soit le numéro donné, il restera dans l'intervalle [1, nombreTotalDePage].
     * Si le nombreTotalDePage n'est pas défini, cette limite est alors infini.
     * @param numeroDePage le nouveau numéro de page.
     */
    void setNumeroDePage(int numeroDePage);

    /**
     * Modifie le numéro de page actuel en retirant 1.
     */
    void setNumeroDePage_precedent();

    /**
     * Modifie le numéro de page actuel en ajoutant 1.
     */
    void setNumeroDePage_suivante();

    /**
     * Défini le nombre total de pages du panneau. Ne pouvant être inférieur à 1. Car même
     * s'il n'y a aucun niveau (et donc 0 au calcul de ce nombre), on considère au moins une
     * première page qui est vide.
     * Et Refresh l'affichage de la page actuelle en bas de page, notamment en diminuant la
     * valeur de la page actuelle si celle-ci est désormais trop haute.
     * Tant que ce nombre n'est pas affiché. Le bas de page ne montrera que le numéro de
     * page actuel.
     * @param nombreTotalDePage le nombre total de pages.
     */
    void setNombreTotalDePage(int nombreTotalDePage);

    /**
     * Ajout au panneau les niveaux en paramètre.
     * Et met fin à l'animation de chargement.
     * Pas besoin d'effacer les niveaux précédement affichés, car ils sont déjà
     * effacés lorque l'on relance l'animation de chargement. Donc juste avant chaque ajout
     * de niveaux dans la liste.
     * @param listeNiveaux la liste des niveaux à afficher.
     */
    void ajouterListeDeNiveaux(List<LevelFile> listeNiveaux);

    /**
     * Vide la liste des niveaux sur le panneau. Et lance l'animation de chargement.
     */
    void viderListeDeNiveaux();

    /**
     * Modifie l'indication selon laquelle il n'y a aucun niveaux dans la liste.
     * Et affiche cette indication
     */
    void afficherAucunFichier();

    /**
     * Affiche l'indication actuelle.
     */
    void afficherIndication();
}
