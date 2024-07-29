package com.slabroad.veritablejeu.Navigation.BoutonNavigation;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.slabroad.veritablejeu.Tools.LayoutParams.LayoutParamsDeBase_pourConstraintLayout;

public class BoutonNavigation extends FrameLayout implements IBoutonNavigation {

    private final int diametre;
    private final int leftMargin;
    private final int topMargin;

    /**
     * Conçoit un GradientDrawable en cercle de couleur avec bordure noire.
     * @param couleurInterne la couleur de remplissage du cercle.
     * @return un GradientDrawable.
     */
    protected GradientDrawable getBackgroundDrawable(int couleurInterne) {
        GradientDrawable drawable = new GradientDrawable();
        drawable.setStroke(1, Color.BLACK);
        drawable.setColor(couleurInterne);
        drawable.setShape(GradientDrawable.OVAL);
        return drawable;
    }

    public BoutonNavigation(@NonNull Context context) {
        this(context, 100, 0, 0);
    }

    public BoutonNavigation(@NonNull Context context, int diametre, int leftMargin, int topMargin) {
        super(context);
        this.diametre = diametre;
        this.leftMargin = leftMargin;
        this.topMargin = topMargin;

        GradientDrawable backgroundDrawable = getBackgroundDrawable(Color.WHITE);
        setBackground(backgroundDrawable);
        setElevation(1);

        ConstraintLayout.LayoutParams layoutParams =
                new LayoutParamsDeBase_pourConstraintLayout(diametre, diametre, leftMargin, topMargin);
        setLayoutParams(layoutParams);
    }

    @Override
    public int getDiametre() {
        return diametre;
    }

    @Override
    public int getLeftMargin() {
        return leftMargin;
    }

    @Override
    public int getTopMargin() {
        return topMargin;
    }

    @Override
    public void effectuerAnimationDeClick() {
        float FORCE_DU_CLICK = .9f;
        scaleAnimation(1.0f, FORCE_DU_CLICK, 0, 30);
        scaleAnimation(FORCE_DU_CLICK, 1.0f, 30, 100);
    }

    @Override
    public void setImage(int image, float proportion) {
        ImageView imageView = new ImageView(this.getContext());
        imageView.setImageResource(image);
        imageView.setScaleX(proportion);
        imageView.setScaleY(proportion);
        addView(imageView);
    }

    @Override
    public void scaleAnimation(float from, float to, long startOffSet, long duration) {
        ScaleAnimation scaleAnimation = new ScaleAnimation(
                from, to,
                from, to,
                Animation.RELATIVE_TO_SELF, .5f,
                Animation.RELATIVE_TO_SELF, .5f
        );
        scaleAnimation.setDuration(duration);
        scaleAnimation.setFillAfter(true);
        scaleAnimation.setStartOffset(startOffSet);
        this.startAnimation(scaleAnimation);
    }

    @Override
    public void activerFocus() {

    }
}
