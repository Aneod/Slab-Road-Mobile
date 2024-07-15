package com.example.veritablejeu.Game.InGame.ATHFinal.TextesFinaux.TexteFinal;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.animation.AlphaAnimation;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.veritablejeu.Game.InGame.InGame;
import com.example.veritablejeu.OutilsEnEnum.LayoutParams.LayoutParamsDeBase_pourConstraintLayout;
import com.example.veritablejeu.OutilsEnEnum.ScreenUtils;
import com.example.veritablejeu.R;

public class TexteFinal extends AppCompatTextView implements ITexteFinal {

    public TexteFinal(@NonNull Context context) {
        super(context);
    }

    public TexteFinal(@NonNull InGame context, int topMargin) {
        super(context);
        setTextColor(Color.BLACK);
        setTextSize(20);
        setGravity(Gravity.CENTER_HORIZONTAL);

        ConstraintLayout.LayoutParams layoutParamsTemps = new LayoutParamsDeBase_pourConstraintLayout(
                ScreenUtils.getScreenWidth(), ScreenUtils.getScreenHeight(), 0, topMargin);
        setLayoutParams(layoutParamsTemps);

        ConstraintLayout constraintLayout = context.findViewById(R.id.main);
        constraintLayout.addView(this);
    }

    @Override
    public void apparition(int startOffSet, long duration) {
        alphaAnimation(0.0f, 1.0f, startOffSet, duration);
    }

    @Override
    public void disparition(long duration) {
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
