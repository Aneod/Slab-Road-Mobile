package com.example.veritablejeu.BainDeSavon.BulleDeSavon.AnimationBulle;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.view.View;

import androidx.annotation.NonNull;

import com.example.veritablejeu.Tools.MathematicTools;
import com.example.veritablejeu.Tools.ScreenUtils;
import com.example.veritablejeu.BainDeSavon.BulleDeSavon.BulleDeSavon;

public class AnimationBulle implements IAnimationBulle {

    private final ObjectAnimator animator;
    private final ValueAnimator sinAnimator;

    @NonNull
    private ValueAnimator get_xTranslation_animation(BulleDeSavon bulleDeSavon, int hauteurAParcourir, int dureeTotale) {
        int sens = MathematicTools.random_open(-1, 1);
        int vitesseDeVariationSurX = MathematicTools.random_open(20, 100);

        ValueAnimator sinAnimator = ValueAnimator.ofFloat(0, -hauteurAParcourir);
        sinAnimator.setDuration(dureeTotale);
        sinAnimator.setRepeatCount(ValueAnimator.INFINITE);
        if(sens == 0) return sinAnimator;

        sinAnimator.addUpdateListener(animation -> {
            float newX = (float) Math.sin(bulleDeSavon.getTranslationY() / vitesseDeVariationSurX) * 25 * sens;
            bulleDeSavon.setTranslationX(newX);
        });
        return sinAnimator;
    }

    @NonNull
    private ObjectAnimator get_yTranslation_scale_animation(BulleDeSavon bulleDeSavon, int hauteurAParcourir, int dureeTotale) {
        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(
                bulleDeSavon,
                PropertyValuesHolder.ofFloat(View.TRANSLATION_X, 0, 0),
                PropertyValuesHolder.ofFloat(View.TRANSLATION_Y, 0, -hauteurAParcourir),
                PropertyValuesHolder.ofFloat(View.SCALE_X, 0.0f, 1.0f, 0.0f),
                PropertyValuesHolder.ofFloat(View.SCALE_Y, 0.0f, 1.0f, 0.0f)
        );
        animator.setDuration(dureeTotale);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        return animator;
    }

    public AnimationBulle(@NonNull BulleDeSavon bulle) {
        int hauteurAParcourir = MathematicTools.random_open(ScreenUtils.getScreenHeight() * 2/10, ScreenUtils.getScreenHeight() * 9/10);
        int dureeTotale = MathematicTools.random_open(15000, 25000);

        animator = get_yTranslation_scale_animation(bulle, hauteurAParcourir, dureeTotale);
        sinAnimator = get_xTranslation_animation(bulle, hauteurAParcourir, dureeTotale);
        start_animation();
    }

    @Override
    public void start_animation() {
        animator.start();
        sinAnimator.start();
    }

    @Override
    public void resume_animation() {
        animator.resume();
        sinAnimator.resume();
    }

    @Override
    public void pause_animation() {
        animator.pause();
        sinAnimator.pause();
    }
}
