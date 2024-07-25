package com.example.veritablejeu.BainDeSavon;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.veritablejeu.BainDeSavon.BulleDeSavon.BulleDeSavon;

import java.util.HashSet;

public interface IBainDeSavon {

    /**
     * On redéfini le container de toutes les bulles par le container actuel. Et logiquement
     * on les supprime de l'ancien.
     */
    void setContainerDeToutesLesBulles(@NonNull AppCompatActivity activity);

    /**
     * @return all bubbles of the class.
     */
    public HashSet<BulleDeSavon> getToutesLesBulles();

    /**
     * Applique le design par défaut.
     */
    void setDesignDeBase();

    /**
     * Modifie le design des bulles de savon.
     * @param formeUne l'image en Integer d'un des modèles.
     * @param couleur1 la couleur en Integer d'un des modèles.
     * @param formeDeux l'autre image.
     * @param couleur2 l'autre couleur.
     * <p>
     *                 Si un seul des paramètres est null, alors les bulles prendrons le
     *                 design de base.
     * </p>
     */
    void setDesigns(
            @Nullable Integer formeUne, @Nullable Integer couleur1,
            @Nullable Integer formeDeux, @Nullable Integer couleur2
    );

    /**
     * Change the A type bubbles shape.
     * @param circle true for circle shape. False for rectangle.
     */
    void setATypeAsCircle(boolean circle);

    /**
     * Change the B type bubbles shape.
     * @param circle true for circle shape. False for rectangle.
     */
    void setBTypeAsCircle(boolean circle);

    /**
     * Change the A type bubbles color.
     * @param color the new color.
     */
    void setATypeColor(int color);

    /**
     * Change the B type bubbles color.
     * @param color the new color.
     */
    void setBTypeColor(int color);

    /**
     * Manages the particles shapes and colors by a code.
     * @param code something like : SccccccScccccc.
     *             <p>
     *                 S -> The shape (0 : OVAL, 1 : RECTANGLE, default : OVAL).
     *                 <br>
     *                 cccccc -> The hexadecimal RGB color.
     *             </p>
     */
    void setDesigns(String code);

    /**
     * Reprendre l'animation de toutes les bulles.
     */
    void resume_animations();

    /**
     * Arrêter l'animation de toutes les bulles.
     */
    void pause_animations();

    /**
     * Rend visible les bulles.
     */
    void show();

    /**
     * Rend invisible les bulles.
     */
    void hide();

    void show_and_resume();

    void hide_and_pause();

    /**
     * Indique si les bulles sont actuellement visibles.
     * @return la valeur du booléen <i>visible</i>.
     */
    boolean areBubblesVisible();
}
