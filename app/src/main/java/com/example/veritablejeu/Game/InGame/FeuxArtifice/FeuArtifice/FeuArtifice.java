package com.example.veritablejeu.Game.InGame.FeuxArtifice.FeuArtifice;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.annotation.SuppressLint;
import android.graphics.drawable.GradientDrawable;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.veritablejeu.Game.InGame.InGame;
import com.example.veritablejeu.Tools.LayoutParams.LayoutParamsDeBase_pourConstraintLayout;
import com.example.veritablejeu.Tools.MathematicTools;

@SuppressLint("ViewConstructor")
public class FeuArtifice extends View implements IFeuArtifice {

    private final InGame context;

    private void setVisuel(int couleur) {
        GradientDrawable background = new GradientDrawable();
        background.setColor(couleur);
        background.setShape(GradientDrawable.OVAL);
        setBackground(background);
    }

    public FeuArtifice(@NonNull InGame context, int diametre, int leftMargin, int topMargin, int couleur) {
        super(context);
        this.context = context;

        ConstraintLayout.LayoutParams layoutParams = new LayoutParamsDeBase_pourConstraintLayout(
                diametre, diametre, leftMargin, topMargin
        );
        setLayoutParams(layoutParams);
        setVisuel(couleur);
    }

    @Override
    public void declencher(int startOffSet) {
        int dureeAnimation = MathematicTools.random_open(750, 1750);
        int latenceAnimation = MathematicTools.random_open(0, 1500);

        ObjectAnimator boomAnimation = ObjectAnimator.ofPropertyValuesHolder(
                this,
                PropertyValuesHolder.ofFloat(View.ALPHA, 1.0f, 0.0f),
                PropertyValuesHolder.ofFloat(View.SCALE_X, 0.0f, 1.0f),
                PropertyValuesHolder.ofFloat(View.SCALE_Y, 0.0f, 1.0f)
        );
        boomAnimation.setInterpolator(new DecelerateInterpolator());
        boomAnimation.setDuration(dureeAnimation);
        boomAnimation.setStartDelay(latenceAnimation + startOffSet);

        boomAnimation.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(@NonNull Animator animation) {
                if(getParent() == null) {
                    context.getContainer().addView(FeuArtifice.this);
                }
            }

            @Override
            public void onAnimationEnd(@NonNull Animator animation) {
                context.getContainer().removeView(FeuArtifice.this);
            }

            @Override
            public void onAnimationCancel(@NonNull Animator animation) {}

            @Override
            public void onAnimationRepeat(@NonNull Animator animation) {}
        });

        boomAnimation.start();
    }
}
