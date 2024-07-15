package com.example.veritablejeu.BainDeSavon.BulleDeSavon;

import android.app.Activity;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;

public interface IBulleDeSavon {

    /**
     * Supprime la bulle du ConstraintLayout et l'ajoute à celui en paramètre.
     * @param constraintLayout le ConstraintLayout dans lequel on ajoute la bulle.
     */
    void setConstraintLayout(@NonNull Activity activity);

    /**
     * Obtenir le numéro de groupe de la bulle.
     * <p>
     * Explication : Les bulles sont réparties en deux groupes, de couleur et/ou de forme distinctes.
     * Au moment de leur création, chaque bulle se voit attribuer soit un 0 ou un 1 à la variable final "selection".
     * Ce numéro indique à quel groupe appartient la bulle.
     */
    int getGroupe();

    /**
     * Modifie le design de la bulle. Selon les paramètres donnés :
     * @param forme GradientDrawable.OVAL (ou) GradientDrawable.RECTANGLE.
     * @param couleur la couleur en int d'un des modèles.
     */
    void setDesign(int forme, int couleur);

    /**
     * Résume l'animation.
     */
    void resume_animation();

    /**
     * Met en pause l'animation.
     */
    void pause_animation();

    /**
     * Rend visible la bulle.
     */
    void show();

    /**
     * Rend invisible la bulle.
     */
    void hide();

}
