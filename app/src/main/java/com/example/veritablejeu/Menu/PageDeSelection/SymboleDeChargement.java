package com.example.veritablejeu.Menu.PageDeSelection;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.veritablejeu.OutilsEnEnum.LayoutParams.LayoutParamsDeBase_pourConstraintLayout;

@SuppressLint("ViewConstructor")
public class SymboleDeChargement extends View {

    public void effacer() {
        clearAnimation();
        setAlpha(0);
    }

    public void afficherEtReprendreAnimation() {
        RotateAnimation rotate = getRotateAnimation();
        startAnimation(rotate);
        setAlpha(1);
    }

    private RotateAnimation getRotateAnimation() {
        RotateAnimation rotate = new RotateAnimation(0, 360,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);

        rotate.setDuration(2000);
        rotate.setInterpolator(new LinearInterpolator());
        rotate.setRepeatCount(Animation.INFINITE);
        return rotate;
    }

    public SymboleDeChargement(@NonNull Context context, int largeurSymbole, int leftMargin, int topMargin) {
        super(context);

        ConstraintLayout.LayoutParams layoutParams = new LayoutParamsDeBase_pourConstraintLayout(
                largeurSymbole, largeurSymbole, leftMargin, topMargin
        );
        setLayoutParams(layoutParams);
        setBackgroundColor(0xFF999999);

        RotateAnimation rotate = getRotateAnimation();
        startAnimation(rotate);
    }
}
