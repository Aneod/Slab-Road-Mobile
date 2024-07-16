package com.example.veritablejeu.LittleWindow;

import android.graphics.Point;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

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
    void attachToActivity(@NonNull AppCompatActivity activity);

    /**
     * Hide the window.
     */
    void hide();

    /**
     * Réecrit la liste des propositions de la fenêtre pour la liste donnée.
     * Les propositions étants des {@link LittleWindow.StringRunnablePair}.
     * Et ajuste la position pour celle donnée.
     * @param position les nouvelles coordonées sous forme de {@link Point}.
     *                 En fonction de ces dernières, la fenêtre pourra être en bas à droite,
     *                 en bas à gauche, en haut à gauche, etc...
     *                 L'idée est simple : Si x est sur la gauche de l'écran, la fenêtre sera
     *                 à droite, gauche sinon. Même principe pour y.
     * @param liste la liste triée des nouvelles propositions.
     */
    void set(Point position, List<LittleWindow.StringRunnablePair> liste);

    /**
     * Réecrit la liste des propositions de la fenêtre pour la liste donnée.
     * Les propositions étants des {@link LittleWindow.StringRunnablePair}.
     * @param liste la liste triée des nouvelles propositions.
     */
    void set(List<LittleWindow.StringRunnablePair> liste);

    /**
     * Return <i>ObjectInMemory</i>. This variable can memorize a View.
     * For example, this is useful to check if the class is opening for an same
     * object twice in a row.
     * @return the View of ObjectInMemory.
     */
    @Nullable
    View getObjectInMemory();

    /**
     * Set ObjectInMemory.
     */
    void setObjectInMemory(View objectInMemory);

}
