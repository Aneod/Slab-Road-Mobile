package com.example.veritablejeu.PopUp.BoutonDePopUp;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.veritablejeu.Tools.LayoutParams.LayoutParamsDeBase_pourConstraintLayout;

@SuppressLint("ViewConstructor")
public class BoutonDePopUp extends AppCompatButton {

    /**
     * Change la couleur de fond pour du noir, et le texte pour du blanc.
     */
    public void prendreLaCouleurNoire() {
        this.setBackgroundColor(Color.BLACK);
        this.setTextColor(Color.WHITE);
    }

    /**
     * Change la couleur de fond pour du blanc, et le texte pour du noir.
     */
    public void prendreLaCouleurBlanche() {
        GradientDrawable border = new GradientDrawable();
        border.setColor(Color.WHITE);
        border.setStroke(2, Color.BLACK);
        this.setBackground(border);
        this.setTextColor(Color.BLACK);
    }

    public BoutonDePopUp(@NonNull FrameLayout parent) {
        super(parent.getContext());
    }

    public BoutonDePopUp(@NonNull FrameLayout parent, String texte, int width, int height, int leftMargin, int topMargin, Runnable runnable) {
        this(parent, texte, width, height, leftMargin, topMargin);
        setRunnable(runnable);
    }

    public BoutonDePopUp(@NonNull FrameLayout parent, String texte, int width, int height, int leftMargin, int topMargin) {
        super(parent.getContext());
        setText(texte);
        setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        ConstraintLayout.LayoutParams layoutParams =
                new LayoutParamsDeBase_pourConstraintLayout(width, height, leftMargin, topMargin);
        this.setLayoutParams(layoutParams);
    }

    public void setRunnable(Runnable runnable) {
        setOnClickListener(v -> {
            if(runnable != null) {
                runnable.run();
            }
        });
    }
}
