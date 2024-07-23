package com.example.veritablejeu.LevelsPanelMVC.LevelsPanel.Scroller.Components;

import android.annotation.SuppressLint;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;

import com.example.veritablejeu.Tools.LayoutParams.LayoutParamsDeBase_pourFrameLayout;

@SuppressLint("ViewConstructor")
public class Indicator extends AppCompatImageView {

    private final FrameLayout parent;

    @NonNull
    private FrameLayout.LayoutParams get_layoutParams(@NonNull FrameLayout parent) {
        int width = 200;
        int leftMargin = (parent.getLayoutParams().width - width) / 2;
        int topMargin = (parent.getLayoutParams().height - width) / 2;
        return new LayoutParamsDeBase_pourFrameLayout(
                width, width, leftMargin, topMargin);
    }

    public Indicator(@NonNull FrameLayout parent) {
        super(parent.getContext());
        this.parent = parent;
        FrameLayout.LayoutParams layoutParams1 = get_layoutParams(parent);
        setLayoutParams(layoutParams1);
        setElevation(1);
        setAlpha(.1f);
    }

    public void setImage_andShow(int res) {
        setImage(res);
        safeShow();
    }

    private void setImage(int res) {
        setImageResource(res);
    }

    private void safeShow() {
        if(getParent() == null) {
            parent.addView(this);
        }
    }

    public void hide() {
        parent.removeView(this);
    }
}
