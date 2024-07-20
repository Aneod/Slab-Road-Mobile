package com.example.veritablejeu.PopUp.PopUpComponent.AtomicComponents;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.veritablejeu.PopUp.PopUpComponent.PopUpComponent;
import com.example.veritablejeu.Tools.LayoutParams.LayoutParamsDeBase_pourFrameLayout;
import com.example.veritablejeu.Tools.SimpleBackground;
import com.example.veritablejeu.Tools.LayoutParams.LayoutParamsDeBase_pourConstraintLayout;

@SuppressLint("ViewConstructor")
public class PopUpButton extends AppCompatButton {

    private static final int HEIGHT = 75;

    public static int getHEIGHT() {
        return HEIGHT;
    }

    private final int width;
    private final int leftMargin;
    private final PopUpComponent popUpComponent;

    private void setLayoutParams() {
        int heigtPopUpCOmponent = popUpComponent.getLayoutParams().height;
        int topMargin = (heigtPopUpCOmponent - HEIGHT) / 2;
        FrameLayout.LayoutParams layoutParams =
                new LayoutParamsDeBase_pourFrameLayout(width, HEIGHT, leftMargin, topMargin);
        this.setLayoutParams(layoutParams);
    }

    public PopUpButton(@NonNull PopUpComponent popUpComponent, int width, int leftMargin) {
        super(popUpComponent.getContext());
        this.popUpComponent = popUpComponent;
        this.width = width;
        this.leftMargin = leftMargin;
        setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        setLayoutParams();
    }
    
    public void takeBlackAppearance(String text) {
        this.setBackgroundColor(Color.BLACK);
        this.setText(text);
        this.setTextColor(Color.WHITE);
    }
    
    public void takeWhiteAppearance(String text) {
        GradientDrawable border = SimpleBackground.create(Color.WHITE, Color.BLACK, 2);
        this.setBackground(border);
        this.setText(text);
        this.setTextColor(Color.BLACK);
    }

    public void setRunnable(Runnable runnable) {
        setOnClickListener(v -> {
            if(runnable != null) {
                runnable.run();
            }
        });
    }

    public void refreshHeight() {
        setLayoutParams();
    }
}
