package com.example.veritablejeu.Game.InGame.ATHFinal.BoutonsFinaux.BoutonFInal;

import android.content.Context;
import android.view.animation.AlphaAnimation;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.veritablejeu.Game.InGame.InGame;
import com.example.veritablejeu.Navigation.BoutonNavigation.BoutonNavigation;
import com.example.veritablejeu.R;

public class BoutonFinal extends BoutonNavigation implements IBoutonFinal {

    public BoutonFinal(@NonNull Context context) {
        super(context);
    }

    public BoutonFinal(@NonNull InGame context, int diametre, int leftMargin, int topMargin) {
        super(context, diametre, leftMargin, topMargin);
        disparition(0);

        ConstraintLayout constraintLayout = context.findViewById(R.id.main);
        constraintLayout.addView(this);
    }

    @Override
    public void apparition(int startOffSet, long duration) {
        setEnabled(true);
        alphaAnimation(0.0f, 1.0f, startOffSet, duration);
    }

    @Override
    public void disparition(long duration) {
        setEnabled(false);
        alphaAnimation(1.0f, 0.0f, 0, duration);
    }

    @Override
    public void alphaAnimation(float from, float to, long startOffSet, long duration) {
        AlphaAnimation alphaAnimation = new AlphaAnimation(from, to);
        alphaAnimation.setDuration(duration);
        alphaAnimation.setFillAfter(true);
        alphaAnimation.setStartOffset(startOffSet);
        this.startAnimation(alphaAnimation);
    }
}
