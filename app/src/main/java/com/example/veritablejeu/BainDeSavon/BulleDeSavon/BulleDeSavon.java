package com.example.veritablejeu.BainDeSavon.BulleDeSavon;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.drawable.GradientDrawable;
import android.view.View;
import android.view.ViewParent;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.veritablejeu.Tools.LayoutParams.LayoutParamsDeBase_pourConstraintLayout;
import com.example.veritablejeu.Tools.Elevation;
import com.example.veritablejeu.Tools.MathematicTools;
import com.example.veritablejeu.Tools.ScreenUtils;
import com.example.veritablejeu.BainDeSavon.BulleDeSavon.AnimationBulle.AnimationBulle;
import com.example.veritablejeu.R;

@SuppressLint("ViewConstructor")
public class BulleDeSavon extends View implements IBulleDeSavon {
    private final int groupe;
    private GradientDrawable gradientDrawable;
    private final AnimationBulle animationBulle = new AnimationBulle(this);

    private void setOpaciteRandom() {
        float opacity =  MathematicTools.random_float_halfOpen(.1f, .3f);
        this.setAlpha(opacity);
    }

    private int getPositionXAleatoire() {
        return MathematicTools.random_open(0, ScreenUtils.getScreenWidth());
    }

    private int getPositionYAleatoire() {
        return MathematicTools.random_open(ScreenUtils.getScreenHeight() * 3/10, ScreenUtils.getScreenHeight() * 12/10);
    }

    private void setLayoutParam() {
        int diametre = 25;
        int leftMargin = getPositionXAleatoire();
        int topMargin = getPositionYAleatoire();
        ConstraintLayout.LayoutParams layoutParams = new LayoutParamsDeBase_pourConstraintLayout(
                diametre, diametre, leftMargin, topMargin
        );
        setLayoutParams(layoutParams);
    }

    public BulleDeSavon(@NonNull Activity activity) {
        super(activity);
        ConstraintLayout constraintLayout = activity.findViewById(R.id.main);
        constraintLayout.addView(this);

        groupe = MathematicTools.random_open(0, 1);
        setLayoutParam();
        setOpaciteRandom();
        setElevation(Elevation.Bubble.getElevation());
    }

    @Override
    public void setConstraintLayout(@NonNull Activity activity) {
        ViewParent parent = getParent();
        ConstraintLayout constraintLayout = activity.findViewById(R.id.main);
        if(parent instanceof ConstraintLayout) {
            ((ConstraintLayout) parent).removeView(this);
            constraintLayout.addView(this);
        }
    }

    @Override
    public int getGroupe() {
        return groupe;
    }

    @Override
    public void setDesign(int forme, int couleur) {
        if(gradientDrawable == null) {
            gradientDrawable = new GradientDrawable();
            setBackground(gradientDrawable);
        }
        gradientDrawable.setShape(forme);
        gradientDrawable.setColor(couleur);
    }

    @Override
    public void resume_animation() {
        animationBulle.resume_animation();
    }

    @Override
    public void pause_animation() {
        animationBulle.pause_animation();
    }

    @Override
    public void show() {
        setVisibility(VISIBLE);
    }

    @Override
    public void hide() {
        setVisibility(INVISIBLE);
    }
}
