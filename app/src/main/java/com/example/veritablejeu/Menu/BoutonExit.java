package com.example.veritablejeu.Menu;

import android.content.Context;
import android.graphics.Color;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.example.veritablejeu.Tools.LayoutParams.LayoutParamsDeBase_pourFrameLayout;
import com.example.veritablejeu.R;

public class BoutonExit extends FrameLayout {
    public static final int hauteurLargeur = 60;

    public BoutonExit(@NonNull Context context) {
        super(context);

        ImageView imageView = new ImageView(context);
        imageView.setImageResource(R.drawable.symbole_retour);
        imageView.setColorFilter(Color.BLACK);
        this.addView(imageView);

        FrameLayout.LayoutParams layoutParamsExit = new LayoutParamsDeBase_pourFrameLayout(
                hauteurLargeur, hauteurLargeur, 30, 20
        );
        imageView.setLayoutParams(layoutParamsExit);
    }
}
