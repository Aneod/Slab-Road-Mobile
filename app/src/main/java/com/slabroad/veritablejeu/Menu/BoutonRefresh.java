package com.slabroad.veritablejeu.Menu;

import android.content.Context;
import android.graphics.Color;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.slabroad.veritablejeu.Tools.LayoutParams.LayoutParamsDeBase_pourFrameLayout;
import com.example.veritablejeu.R;

public class BoutonRefresh extends FrameLayout {

    public BoutonRefresh(@NonNull Context context) {
        super(context);

        ImageView imageView = new ImageView(context);
        imageView.setImageResource(R.drawable.symbole_reset);
        imageView.setColorFilter(Color.BLACK);
        this.addView(imageView);

        LayoutParams layoutParamsExit = new LayoutParamsDeBase_pourFrameLayout(
                BoutonExit.hauteurLargeur, BoutonExit.hauteurLargeur, BoutonExit.hauteurLargeur + 2 * 30, 20
        );
        imageView.setLayoutParams(layoutParamsExit);
    }
}
