package com.slabroad.veritablejeu.Navigation.BoutonNavigation.BoutonPrincipal;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.slabroad.veritablejeu.Navigation.BoutonNavigation.BoutonSecondaire.BoutonSecondaire;

public interface IBoutonPrincipal {

    /**
     * Ajoute un bouton secondaire aux boutons associés à la classe.
     * @param boutonSecondaire le bouton secondaire à ajouter.
     */
    void boutonsSecondaire_ajouter(@NonNull BoutonSecondaire boutonSecondaire);

    /**
     * Renvoie le diametre en pixel du bouton lorsqu'il est en taille réduite.
     * @return un entier.
     */
    int getDiametreReduit();

    /**
     * Renvoie le temps en ms de la disparition totale des 5 boutons.
     * @return un entier.
     */
    long getTempsDeFermeture();

    /**
     * Effectue une ouverture ou une fermeture de la navigation en fonction de l'état actuel.
     * <p>
     * @param runnableOuverture une fonction sans paramètre ni valeur de retour.
     *                          Cette fonction s'enclenchera à l'ouverture de la
     *                          navigation.
     * @param runnableFermeture une fonction sans paramètre ni valeur de retour.
     *                          Cette fonction s'enclenchera à la fermeture de la
     *                          navigation.
     * Les fonctions en paramètre peuvent êtres nulles pour ne rien effectuer
     * lors de l'ouverture ou de la fermeture.
     */
    void ouvrirFermer(@Nullable Runnable runnableOuverture, @Nullable Runnable runnableFermeture);

    /**
     * Effectue une animation d'agrandissement léger.
     * Pour lorsque les boutons secondaire associés sont montrés.
     * Et tous les boutons secondaires associés apparaissent successivement.
     * Le dernier bouton secondaire commence en premier son animation.
     */
    void ouvrir();

    /**
     * Effectue une animation de réduction légère.
     * Pour lorsque les boutons secondaire associés sont cachés.
     * Et tous les boutons secondaires associés disparaissent successivement.
     * Le premier bouton secondaire commence en premier son animation.
     */
    void fermer();

}
