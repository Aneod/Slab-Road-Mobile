package com.example.veritablejeu.PopUp.InlineComponent.AtomicComponents.Button;

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
public class Button extends AppCompatButton implements IButton {

    private static final int HEIGHT = 75;
    private static final GradientDrawable WHITE_APPEARENCE = SimpleBackground.create(Color.WHITE, Color.BLACK, 2);
    private static final GradientDrawable BLACK_APPEARENCE = SimpleBackground.create(Color.BLACK);
    private static final int TEXT_COLOR_WHITE_APPEARENCE = Color.BLACK;
    private static final int TEXT_COLOR_BLACK_APPEARENCE = Color.WHITE;

    public static int getHEIGHT() {
        return HEIGHT;
    }

    public static GradientDrawable getWhiteAppearence() {
        return WHITE_APPEARENCE;
    }

    public static GradientDrawable getBlackAppearence() {
        return BLACK_APPEARENCE;
    }

    public static int getTextColorWhiteAppearence() {
        return TEXT_COLOR_WHITE_APPEARENCE;
    }

    public static int getTextColorBlackAppearence() {
        return TEXT_COLOR_BLACK_APPEARENCE;
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
    
    @Override
    public void takeBlackAppearance(String text) {
        this.setText(text);
        this.setTextColor(TEXT_COLOR_BLACK_APPEARENCE);
        this.setBackground(BLACK_APPEARENCE);
    }
    
    @Override
    public void takeWhiteAppearance(String text) {
        this.setText(text);
        this.setTextColor(TEXT_COLOR_WHITE_APPEARENCE);
        this.setBackground(WHITE_APPEARENCE);
    }

    @Override
    public void setRunnable(Runnable runnable) {
        setOnClickListener(v -> {
            if(runnable != null) {
                runnable.run();
            }
        });
    }

    @Override
    public void refreshLayoutParams() {
        setLayoutParams();
    }
}
