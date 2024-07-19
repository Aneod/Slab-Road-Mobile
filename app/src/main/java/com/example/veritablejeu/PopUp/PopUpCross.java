package com.example.veritablejeu.PopUp;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.example.veritablejeu.Tools.LayoutParams.LayoutParamsDeBase_pourFrameLayout;
import com.example.veritablejeu.R;

import org.jetbrains.annotations.Contract;

@SuppressLint("ViewConstructor")
public class PopUpCross extends FrameLayout {

    private static final int CROSS_IMAGE = R.drawable.croix;
    private static final int CROSS_COLOR = Color.BLACK;
    private static final float CROSS_HEIGHT_PERCENTAGE = .45f;

    @NonNull
    @Contract("_ -> new")
    private FrameLayout.LayoutParams layoutParamsDeCroix(int hauteur) {
        int hauteurCroix = (int) (hauteur * CROSS_HEIGHT_PERCENTAGE);
        int margeGaucheEtSuperieur = (hauteur - hauteurCroix) / 2;
        return new LayoutParamsDeBase_pourFrameLayout(
                hauteurCroix, hauteurCroix, margeGaucheEtSuperieur, margeGaucheEtSuperieur);
    }

    @NonNull
    private ImageView croix(int hauteur) {
        ImageView croix = new ImageView(getContext());
        croix.setImageResource(CROSS_IMAGE);
        croix.setColorFilter(CROSS_COLOR);
        FrameLayout.LayoutParams layoutParamsDeCroix = layoutParamsDeCroix(hauteur);
        croix.setLayoutParams(layoutParamsDeCroix);
        return croix;
    }

    public PopUpCross(@NonNull PopUp popUp) {
        super(popUp.getContext());

        int size = popUp.getInitialHeight();
        FrameLayout.LayoutParams layoutparamsCroixContainer = new FrameLayout.LayoutParams(size, size);
        this.setLayoutParams(layoutparamsCroixContainer);

        ImageView croix = croix(size);
        this.addView(croix);

        setOnClickListener(v -> popUp.hide());
    }
}
