package com.example.veritablejeu.Game.Board.BoardElement.Square.ModularBlob.CadrageMaster;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.veritablejeu.Game.Board.Board;
import com.example.veritablejeu.Game.Board.BoardElement.Square.ModularBlob.ModularBlob;
import com.example.veritablejeu.Tools.LayoutParams.LayoutParamsDeBase_pourConstraintLayout;

public class CadrageMaster extends FrameLayout implements ICadrageMaster {

    private final ObjectAnimator scaleAnimation = getScaleAnimation();

    @NonNull
    private ObjectAnimator getScaleAnimation() {
        ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(
                this,
                PropertyValuesHolder.ofFloat(SCALE_X, 1.0f, 0.95f, 1.0f),
                PropertyValuesHolder.ofFloat(SCALE_Y, 1.0f, 0.95f, 1.0f)
        );
        objectAnimator.setDuration(1000);
        objectAnimator.setRepeatCount(ValueAnimator.INFINITE);
        return objectAnimator;
    }

    @NonNull
    private View getAtomeDeCadrage(@NonNull FrameLayout parent, int width, int height, int leftMargin, int topMargin) {
        View view = new View(parent.getContext());
        view.setBackgroundColor(Color.BLACK);
        ConstraintLayout.LayoutParams layoutParams = new LayoutParamsDeBase_pourConstraintLayout(
                width, height, leftMargin, topMargin);
        view.setLayoutParams(layoutParams);
        return view;
    }

    @NonNull
    private FrameLayout getCoinDeCadrage(int longueurAtomes, int largeurAtomes, int leftMargin, int topMargin, float rotation) {
        Context context = getContext();
        FrameLayout coin = new FrameLayout(context);
        View viewHorizontal = getAtomeDeCadrage(coin, longueurAtomes, largeurAtomes, leftMargin, topMargin);
        View viewVertical = getAtomeDeCadrage(coin, largeurAtomes, longueurAtomes, leftMargin, topMargin);
        coin.addView(viewHorizontal);
        coin.addView(viewVertical);
        coin.setRotation(rotation);
        return coin;
    }

    private void ajouterLesQuatreCoins() {
        int tailleCases = Board.SQUARE_SIZE;
        int margeToutAutour = (int) (tailleCases * 0.05);
        int largeurAutoriseeCadrage = tailleCases - 2 * margeToutAutour;
        int longueurAtomes = largeurAutoriseeCadrage / 3;
        int largeurAtomes = (int) (tailleCases * 0.05);

        for(int rotation = 0; rotation < 360; rotation += 90) {
            FrameLayout coin = getCoinDeCadrage(longueurAtomes, largeurAtomes, margeToutAutour, margeToutAutour, rotation);
            addView(coin);
        }
    }

    public CadrageMaster(@NonNull Context context) {
        super(context);
    }

    public CadrageMaster(@NonNull ModularBlob modularBlob) {
        super(modularBlob.getContext());
        ajouterLesQuatreCoins();
        scaleAnimation.start();
        hide(0);
    }

    @Override
    public void show_and_resume() {
        resume_animation();
        show(200);
    }

    @Override
    public void hide_and_pause() {
        pause_animation();
        hide(150);
    }

    @Override
    public void resume_animation() {
        scaleAnimation.resume();
    }
    @Override
    public void pause_animation() {
        scaleAnimation.pause();
    }

    @Override
    public void show(int duration) {
        doAlphaAnimation(0.0f, 1.0f, duration);
    }

    @Override
    public void hide(int duration) {
        doAlphaAnimation(1.0f, 0.0f, duration);
    }

    private void doAlphaAnimation(float from, float to, int duration) {
        AlphaAnimation alphaAnimation = new AlphaAnimation(from, to);
        alphaAnimation.setDuration(duration);
        alphaAnimation.setFillAfter(true);
        startAnimation(alphaAnimation);
    }

}
