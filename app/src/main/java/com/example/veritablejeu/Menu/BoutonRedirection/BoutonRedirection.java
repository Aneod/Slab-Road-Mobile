package com.example.veritablejeu.Menu.BoutonRedirection;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.veritablejeu.OutilsEnEnum.LayoutParams.LayoutParamsDeBase_pourConstraintLayout;
import com.example.veritablejeu.OutilsEnEnum.LayoutParams.LayoutParamsDeBase_pourFrameLayout;

public class BoutonRedirection extends FrameLayout implements IBoutonRedirection {

    protected final int width;
    protected final int height;
    protected final TextView textViewTitre;
    private View imageView;

    /**
     * Crée le layoutParams du titre du bouton.
     * @return un ConstraintLayout.LayoutParams.
     */
    protected ConstraintLayout.LayoutParams getLayoutParamsTitre() {
        ConstraintLayout.LayoutParams layoutParamsTitre = new ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.MATCH_PARENT
        );
        layoutParamsTitre.leftToLeft = ConstraintLayout.LayoutParams.PARENT_ID;
        layoutParamsTitre.topToTop = ConstraintLayout.LayoutParams.PARENT_ID;
        layoutParamsTitre.leftMargin = 3 + 20;
        layoutParamsTitre.topMargin = 3;
        return layoutParamsTitre;
    }

    /**
     * Crée le titre du bouton.
     * @param titre le string du titre.
     * @return un textView.
     */
    private AppCompatTextView creationTitreDuBouton(String titre) {
        AppCompatTextView textViewTitre = new AppCompatTextView(getContext());
        textViewTitre.setText(titre);
        textViewTitre.setTextColor(Color.BLACK);
        textViewTitre.setTextSize(26);
        ConstraintLayout.LayoutParams layoutParamsTitre = getLayoutParamsTitre();
        textViewTitre.setLayoutParams(layoutParamsTitre);
        return textViewTitre;
    }

    public BoutonRedirection(@NonNull Context context) {
        this(context, "", 100, 100, 0, 0);
    }

    public BoutonRedirection(@NonNull Context context, String titre, int width, int height, int leftMargin, int topMargin) {
        super(context);

        this.width = Math.abs(width);
        this.height = Math.abs(height);
        FrameLayout.LayoutParams layoutParams = new LayoutParamsDeBase_pourFrameLayout(this.width, this.height, leftMargin, topMargin);
        setLayoutParams(layoutParams);

        textViewTitre = creationTitreDuBouton(titre);
        addView(textViewTitre);
    }

    @Override
    public void setImage(@Nullable View image, @Nullable Integer hauteurMaximale) {
        int hauteurDefinitive = hauteurMaximale == null ? height : hauteurMaximale;
        int plusPetitCote = Math.min(width, hauteurDefinitive);
        int leftMargin = width - plusPetitCote;
        int topMargin = height - plusPetitCote;

        if(imageView != null) this.removeView(imageView);

        if(image == null) return;
        imageView = creationLogo(plusPetitCote, plusPetitCote, leftMargin, topMargin, image);
        this.addView(imageView);
    }

    private View creationLogo(int width, int height, int leftMargin, int topMargin, View logo) {
        int margeLogo = 20;
        int largeurLogo = width - 2 * margeLogo;
        int hauteurLogo = height - 2 * margeLogo;
        int leftMarginLogo = leftMargin + margeLogo;
        int topMarginLogo = topMargin + margeLogo;

        ConstraintLayout.LayoutParams layoutParams = new LayoutParamsDeBase_pourConstraintLayout(
                largeurLogo, hauteurLogo, leftMarginLogo, topMarginLogo
        );
        logo.setLayoutParams(layoutParams);

        return logo;
    }
}
