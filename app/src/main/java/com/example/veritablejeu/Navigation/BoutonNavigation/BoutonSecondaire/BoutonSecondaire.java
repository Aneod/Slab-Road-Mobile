package com.example.veritablejeu.Navigation.BoutonNavigation.BoutonSecondaire;

import android.content.Context;

import androidx.annotation.NonNull;

import com.example.veritablejeu.Navigation.BoutonNavigation.BoutonNavigation;

public class BoutonSecondaire extends BoutonNavigation implements IBoutonSecondaire {

    private boolean estVisible = false;

    public BoutonSecondaire(@NonNull Context context) {
        super(context);
    }

    public BoutonSecondaire(@NonNull Context context, int diametre, int leftMargin, int topMargin) {
        super(context, diametre, leftMargin, topMargin);
        cacher(0, 0);
    }

    @Override
    public void montrer(long startOffSet, long duration) {
        setEnabled(true);
        setClickable(true);
        scaleAnimation(0.0f, 1.0f, startOffSet, duration);
        estVisible = true;
    }

    @Override
    public void cacher(long startOffSet, long duration) {
        setEnabled(false);
        setClickable(false);
        scaleAnimation(1.0f, 0.0f, startOffSet, duration);
        estVisible = false;
    }

    @Override
    public void activerFocus() {
        int duration = 500;
        float FORCE_DU_CLICK = .9f;
        activerCycleDeFocus(duration, FORCE_DU_CLICK);
    }

    private void activerCycleDeFocus(int duration, float FORCE_DU_CLICK) {
        if(estVisible) {
            scaleAnimation(1.0f, FORCE_DU_CLICK, 0, duration);
            postDelayed(() -> {
                if(estVisible) {
                    scaleAnimation(FORCE_DU_CLICK, 1.0f, 0, duration);
                }
            }, duration);
        }
        postDelayed(() -> activerCycleDeFocus(duration, FORCE_DU_CLICK), 2L * duration);
    }
}