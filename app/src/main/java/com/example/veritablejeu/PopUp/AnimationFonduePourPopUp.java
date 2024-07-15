package com.example.veritablejeu.PopUp;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.view.View;

import androidx.annotation.NonNull;

public class AnimationFonduePourPopUp {

    private final ObjectAnimator objectAnimator;

    public ObjectAnimator getObjectAnimator() {
        return objectAnimator;
    }

    /**
     *
     * @param popUp
     * @param deltaY la translation Y agit différement en fonction de si
     *               deltaY est positif ou non.
     *               Si c'est le cas, alors la pop-up ira de sa position à un peu plus bas.
     *               Sinon elle partira d'un peu en bas pour remonter jusqu'à sa position.
     *               Autrement dit : positif pour effectuer une descente de x pixels.
     *               Et négatif pour effectuer une montée (depuis le dessous de la pop-up) de
     *               x pixels.
     * @param fromAlpha
     * @param toAlpha
     * @param duration
     * @param startEffect
     * @param endEffect
     */
    public AnimationFonduePourPopUp(@NonNull PopUp popUp, int deltaY, float fromAlpha, float toAlpha, int duration, Runnable startEffect, Runnable endEffect) {

        float dyStart;
        float dyEnd;
        boolean animationDeDescente = deltaY >= 0;
        if(animationDeDescente) {
            dyStart = 0;
            dyEnd = deltaY;
        } else {
            dyStart = -deltaY;
            dyEnd = 0;
        }

        objectAnimator = ObjectAnimator.ofPropertyValuesHolder(
                popUp,
                PropertyValuesHolder.ofFloat(View.TRANSLATION_Y, dyStart, dyEnd),
                PropertyValuesHolder.ofFloat(View.ALPHA, fromAlpha, toAlpha)
        );
        objectAnimator.setDuration(duration);
        objectAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(@NonNull Animator animation) {
                startEffect.run();
            }

            @Override
            public void onAnimationEnd(@NonNull Animator animation) {
                endEffect.run();
            }

            @Override
            public void onAnimationCancel(@NonNull Animator animation) {}

            @Override
            public void onAnimationRepeat(@NonNull Animator animation) {}
        });
    }
}
