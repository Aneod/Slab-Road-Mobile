package com.example.veritablejeu.PopUp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.veritablejeu.PopUp.ComposedComponents.Manual;
import com.example.veritablejeu.PopUp.ComposedComponents.Question;
import com.example.veritablejeu.PopUp.InlineComponent.InlineComponent;

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
    void show();

    /**
     * Cache la pop-up.
     */
    void hide();

    /**
     * Modify the title and the popUp content.
     * @param title the new title.
     * @param contents all {@link InlineComponent} who will compose the popUp. From top to bottom.
     */
    void setContent(String title, @NonNull InlineComponent... contents);

    /**
     * Add a single {@link InlineComponent} at the bottom of the popUp.
     * @param popUpContent the content to add.
     */
    void addContent(InlineComponent popUpContent);

    /**
     * Remove all {@link InlineComponent} in the popUp, and reset it height.
     */
    void clearContents();

    /**
     * Reverify all components height. Useful if a component changes is height.
     */
    void refreshHeight();

    /**
     * Setup the popup to show a single {@link Question}.
     */
    void showQuestion(String title, String text, String reponseA, @Nullable Runnable runnableA, String reponseB, @Nullable Runnable runnableB);

    /**
     * Setup the popup to show a single text.
     */
    void showMessage(String title, String text);

    /**
     * Setop the popup to show the {@link Manual}.
     */
    void showManual();

}
