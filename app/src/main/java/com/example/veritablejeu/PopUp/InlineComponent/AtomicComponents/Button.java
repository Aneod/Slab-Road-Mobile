package com.example.veritablejeu.PopUp.InlineComponent.AtomicComponents;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;

import com.example.veritablejeu.PopUp.InlineComponent.InlineComponent;
import com.example.veritablejeu.Tools.LayoutParams.LayoutParamsDeBase_pourFrameLayout;
import com.example.veritablejeu.Tools.SimpleBackground;

@SuppressLint("ViewConstructor")
public class Button extends AppCompatButton {

    private static final int HEIGHT = 75;

    public static int getHEIGHT() {
        return HEIGHT;
    }

    private final int width;
    private final int leftMargin;
    private final InlineComponent inlineComponent;

    private void setLayoutParams() {
        int heigtPopUpCOmponent = inlineComponent.getLayoutParams().height;
        int topMargin = (heigtPopUpCOmponent - HEIGHT) / 2;
        FrameLayout.LayoutParams layoutParams =
                new LayoutParamsDeBase_pourFrameLayout(width, HEIGHT, leftMargin, topMargin);
        this.setLayoutParams(layoutParams);
    }

    public Button(@NonNull InlineComponent inlineComponent, int width, int leftMargin) {
        super(inlineComponent.getContext());
        this.inlineComponent = inlineComponent;
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
