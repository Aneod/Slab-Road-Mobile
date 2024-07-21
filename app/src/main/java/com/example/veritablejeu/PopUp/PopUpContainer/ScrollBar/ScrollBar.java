package com.example.veritablejeu.PopUp.PopUpContainer.ScrollBar;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;

import com.example.veritablejeu.PopUp.PopUpContainer.PopUpContainer;
import com.example.veritablejeu.Tools.LayoutParams.LayoutParamsDeBase_pourFrameLayout;

@SuppressLint("ViewConstructor")
public class ScrollBar extends View {

    private static final int WIDTH = 15;
    private static final int COLOR = Color.LTGRAY;

    private final PopUpContainer popUpContainer;
    private final FrameLayout.LayoutParams layoutParams;

    public ScrollBar(@NonNull PopUpContainer popUpContainer) {
        super(popUpContainer.getContext());
        this.popUpContainer = popUpContainer;
        int height = 0;
        int leftMargin = popUpContainer.getLayoutParams().width - WIDTH;
        int topMargin = 0;
        layoutParams = new LayoutParamsDeBase_pourFrameLayout(
                WIDTH, height, leftMargin, topMargin);
        setLayoutParams(layoutParams);
        setBackgroundColor(COLOR);
        setElevation(1);
    }

    private void show() {
        setVisibility(VISIBLE);
    }

    private void hide() {
        setVisibility(GONE);
    }

    private void setHeight(int height) {
        layoutParams.height = height;
    }

    public void refreshHeight() {
        int maxHeightParent = PopUpContainer.getMaxVisualHeight();
        float howManyPageHeight = (float) popUpContainer.getCurrentHeight() / maxHeightParent;

        if(howManyPageHeight <= 1) {
            hide();
        } else {
            show();
            int newHeight = (int) (maxHeightParent / howManyPageHeight);
            setHeight(newHeight);
        }
        refreshTopMargin();
    }

    public void refreshTopMargin() {
        int heightParent = popUpContainer.getCurrentHeight() - PopUpContainer.getMaxVisualHeight();
        float currentTranslationY = popUpContainer.getTranslationY();
        float pctTraveled = -currentTranslationY / heightParent;
        int maxScrollBarTopMargin = PopUpContainer.getMaxVisualHeight() - layoutParams.height;
        layoutParams.topMargin = (int) (maxScrollBarTopMargin * pctTraveled);
        setLayoutParams(layoutParams);
    }

}
