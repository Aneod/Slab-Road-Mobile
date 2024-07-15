package com.example.veritablejeu.PopUp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.veritablejeu.PopUp.ContenuPopUp.ContenuPopUp;

public interface IPopUp {

    /**
     * Change le context où est affichée la pop-up :
     * <p>- Supprime la pop-up de l'ancion context.</p>
     * <p>- Ajoute la pop-up au context donné.</p>
     * @param activity l'activité dans laquelle on met la pop-up.
     */
    void setConstraintLayout(@NonNull AppCompatActivity activity);

    /**
     * Affiche la pop-up.
     */
    void montrer();

    /**
     * Cache la pop-up.
     */
    void cacher();

    /**
     * Renvoie true si la pop-up est actuellement visible.
     */
    boolean isVisible();

    /**
     * Renvoie le contenu actuel de la pop-up.
     * @return un {@link ContenuPopUp}.
     */
    ContenuPopUp getContenu();

    /**
     * Efface l'ancien contenu de la pop-up et le remplaçe par celui donné.
     * @param contenuPopUp le nouveau contenu de la pop-up.
     */
    void setContenu(@NonNull ContenuPopUp contenuPopUp);

    /**
     * Affiche le manuel.
     */
    void afficherManuel();

}
