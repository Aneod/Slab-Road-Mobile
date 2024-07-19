package com.example.veritablejeu.PopUp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public interface IPopUp {

    /**
     * Renvoie en pixel la largeur totale de la pop-up.
     * @return un int.
     */
    int get_width();

    /**
     * Renvoie en pixel la hauteur de la barre supérieure de la pop-up.
     * @return un int.
     */
    int getInitialHeight();

    /**
     * Renvoie la largeur en pixel de la bordure de la pop-up.
     * @return un int.
     */
    int getBORDER_WIDTH();

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
    void show();

    /**
     * Cache la pop-up.
     */
    void hide();

    /**
     * Affiche le manuel.
     */
    void showManual();

}
