package com.example.veritablejeu.PopUp;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.veritablejeu.Tools.LayoutParams.LayoutParamsDeBase_pourConstraintLayout;
import com.example.veritablejeu.Tools.ScreenUtils;

@SuppressLint("ViewConstructor")
public class PopUpTitle extends androidx.appcompat.widget.AppCompatTextView {

    private static final int TEXT_COLOR = Color.BLACK;
    private static final int TEXT_SIZE = 18;

    @SuppressLint("ClickableViewAccessibility")
    public void enableParentTranslationOnSwipe(PopUp popUp) {
        this.setOnTouchListener(new View.OnTouchListener() {
            private float startX;
            private float startY;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        startX = event.getRawX();
                        startY = event.getRawY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        float dx = event.getRawX() - startX;
                        float dy = event.getRawY() - startY;

                        if(popUp.getTranslationY() + dy < 0 && dy < 0) dy = 0;
                        else if (popUp.getTranslationY() + dy > ScreenUtils.getScreenHeight() - 4 * popUp.getInitialHeight() && dy > 0) dy = 0;

                        if(popUp.getTranslationX() + dx < (float) -ScreenUtils.getScreenWidth() / 2 && dx < 0) dx = 0;
                        else if(popUp.getTranslationX() + dx > (float) ScreenUtils.getScreenWidth() / 2 && dx > 0) dx = 0;

                        popUp.setTranslationX(popUp.getTranslationX() + dx);
                        popUp.setTranslationY(popUp.getTranslationY() + dy);

                        startX = event.getRawX();
                        startY = event.getRawY();
                        break;
                }
                return true;
            }
        });
    }

    public PopUpTitle(@NonNull PopUp popUp) {
        super(popUp.getContext());
        setTextColor(TEXT_COLOR);
        setTextSize(TEXT_SIZE);
        ConstraintLayout.LayoutParams layoutParamsDeTitre = new LayoutParamsDeBase_pourConstraintLayout(
                ViewGroup.LayoutParams.MATCH_PARENT, popUp.getInitialHeight(), 0, 0);
        setLayoutParams(layoutParamsDeTitre);
        setGravity(Gravity.CENTER);
        enableParentTranslationOnSwipe(popUp);
    }

    public void setTexte(String nouveauTexte) {
        this.setText(nouveauTexte);
    }
}
