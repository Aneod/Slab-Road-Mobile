package com.example.veritablejeu.PopUp.PopUpButton;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.veritablejeu.PopUp.ContenuPopUp.PopUpComponent;
import com.example.veritablejeu.PopUp.PopUp;
import com.example.veritablejeu.Tools.LayoutParams.ConstraintParams;
import com.example.veritablejeu.Tools.SimpleBackground;
import com.example.veritablejeu.Tools.LayoutParams.LayoutParamsDeBase_pourConstraintLayout;

@SuppressLint("ViewConstructor")
public class PopUpButton extends AppCompatButton {

    private void setLayoutParams(ConstraintParams constraintParams) {
        ConstraintLayout.LayoutParams layoutParams =
                new LayoutParamsDeBase_pourConstraintLayout(constraintParams);
        this.setLayoutParams(layoutParams);
    }

    public PopUpButton(@NonNull PopUp popUp, ConstraintParams constraintParams) {
        super(popUp.getContext());
        setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        setLayoutParams(constraintParams);
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
}
