package com.slabroad.veritablejeu.LittleWindow;

import android.graphics.Point;

import androidx.appcompat.app.AppCompatActivity;

import com.slabroad.veritablejeu.LittleWindow.WindowProposal.WindowProposal;

import java.util.List;

/**
 * Créer de petites fenêtres composées de plusieurs options d'actions à effectuer.
 * Utile pour l'éditeur, lorsque l'utilisateur à le choix entre un grand nombre d'actions
 * différentes à effectuer sur un seul et même objet du plateau. Les options sont alors
 * mieux rangées et plus accessibles.
 */
public interface ILittleWindow {

    /**
     * Ajoute la fenêtre à l'activité donnée, et l'affiche.
     * @param activity l'activité à laquelle on attache la fenêtre.
     */
    void attachToActivity(AppCompatActivity activity);

    /**
     * Hide the window.
     */
    void hide();

    /**
     * Changes the little window position on screen for the given one.
     * <br>
     * The given position will be one of the corners of the window.
     * <br>
     * If the position is on the screen left side, the window will appear to the right of it.
     * <br>
     * Same logic for vertical. That for stay near of the center of the screen.
     * @param position the approximate position of the window.
     */
    void setPosition(Point position);

    /**
     * Réecrit la liste des propositions de la fenêtre pour la liste donnée.
     * Les propositions étants des {@link WindowProposal}.
     * @param liste la liste triée des nouvelles propositions.
     */
    void set(List<WindowProposal> liste);

}
