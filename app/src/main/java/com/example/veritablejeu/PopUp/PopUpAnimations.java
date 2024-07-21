package com.example.veritablejeu.PopUp;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.view.View;

import androidx.annotation.NonNull;

public class PopUpAnimations {

    private static final int SHOW_ANIMATION_DURATION = 400;
    private static final int HIDE_ANIMATION_DURATION = 300;

    /**
     * Make the given popup visible.
     * <br><br>
     * The start alpha value is 0.0f for each use of this method (and not the current alpha), for
     * change the popup content with a appearition animation even if the popup is already visible.
     */
    public static void show(PopUp popUp) {
        if(popUp == null) return;
        popUp.setVisibility(View.VISIBLE);
        ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(
                popUp,
                PropertyValuesHolder.ofFloat(View.ALPHA, 0.0f, 1.0f)
        );
        objectAnimator.setDuration(SHOW_ANIMATION_DURATION);
        objectAnimator.start();
    }

    /**
     * Make the given popUp invisible.
     */
    public static void hide(PopUp popUp) {
        if(popUp == null) return;
        ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(
                popUp,
                PropertyValuesHolder.ofFloat(View.ALPHA, popUp.getAlpha(), 0.0f)
        );
        objectAnimator.setDuration(HIDE_ANIMATION_DURATION);
        objectAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(@NonNull Animator animation) {
            }

            @Override
            public void onAnimationEnd(@NonNull Animator animation) {
                popUp.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationCancel(@NonNull Animator animation) {}

            @Override
            public void onAnimationRepeat(@NonNull Animator animation) {}
        });
        objectAnimator.start();
    }
}
