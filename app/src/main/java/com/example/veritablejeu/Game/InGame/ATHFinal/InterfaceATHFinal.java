package com.example.veritablejeu.Game.InGame.ATHFinal;

import android.view.animation.AlphaAnimation;

public interface InterfaceATHFinal {

    /**
     * Lance les animations d'apparitions et de feux d'artifice.
     * @param startOffSet le délai avant le début de l'animation.
     * @param duration la durée de l'animation.
     */
    void apparition(int startOffSet, long duration);

    /**
     * Lance les animations de disparitions.
     * @param duration la durée de l'animation.
     */
    void disparition(long duration);

}
