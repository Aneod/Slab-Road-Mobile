package com.example.veritablejeu.Menu.BoutonRedirection.BoutonRedirectionNiveau.BoutonRedirectionNiveauType;

import android.content.Context;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.example.veritablejeu.OutilsEnEnum.LayoutParams.LayoutParamsDeBase_pourFrameLayout;

public class Icons extends FrameLayout {

    private void ajouterLogo(@NonNull Context context, int longueurCote, int image) {
        ImageView logo = new ImageView(context);
        logo.setImageResource(image);
        int longueurCoteLogo = 40;
        int leftTopMargins = (longueurCote - longueurCoteLogo) / 2;
        LayoutParams layoutParamsLogo = new LayoutParamsDeBase_pourFrameLayout(
                longueurCoteLogo, longueurCoteLogo, leftTopMargins, leftTopMargins
        );
        logo.setLayoutParams(layoutParamsLogo);
        this.addView(logo);
    }

    public Icons(@NonNull Context context, int leftMargin, int topMargin, int image) {
        super(context);

        int longueurCote = 60;
        LayoutParams layoutParams = new LayoutParamsDeBase_pourFrameLayout(
                longueurCote, longueurCote, leftMargin, topMargin
        );
        setLayoutParams(layoutParams);
        ajouterLogo(context, longueurCote, image);
    }

    public Icons(@NonNull Context context) {
        super(context);
    }
}
