package com.example.veritablejeu.Menu.BoutonRedirection;

import android.content.Context;
import android.graphics.Color;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.veritablejeu.Tools.LayoutParams.LayoutParamsDeBase_pourConstraintLayout;
import com.example.veritablejeu.Tools.LayoutParams.LayoutParamsDeBase_pourFrameLayout;

public class BoutonRedirection extends FrameLayout implements IBoutonRedirection {

    private static final int LOGO_MARGINS = 20;

    protected final TextView textViewTitre;
    private ImageView imageView;

    /**
     * Crée le layoutParams du titre du bouton.
     * @return un ConstraintLayout.LayoutParams.
     */
    protected ConstraintLayout.LayoutParams getLayoutParamsTitre() {
        return new LayoutParamsDeBase_pourConstraintLayout(
                ConstraintLayout.LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.MATCH_PARENT, 3 + 20, 3
        );
    }

    /**
     * Crée le titre du bouton.
     * @param titre le string du titre.
     * @return un textView.
     */
    @NonNull
    private AppCompatTextView creationTitreDuBouton(String titre) {
        AppCompatTextView textViewTitre = new AppCompatTextView(getContext());
        textViewTitre.setText(titre);
        textViewTitre.setTextColor(Color.BLACK);
        textViewTitre.setTextSize(26);
        textViewTitre.setSingleLine();
        ConstraintLayout.LayoutParams layoutParamsTitre = getLayoutParamsTitre();
        textViewTitre.setLayoutParams(layoutParamsTitre);
        return textViewTitre;
    }

    public BoutonRedirection(@NonNull Context context) {
        this(context, "", 100, 100, 0, 0);
    }

    public BoutonRedirection(@NonNull Context context, String titre, int width, int height, int leftMargin, int topMargin) {
        super(context);

        FrameLayout.LayoutParams layoutParams = new LayoutParamsDeBase_pourFrameLayout(
                width, height, leftMargin, topMargin);
        setLayoutParams(layoutParams);

        textViewTitre = creationTitreDuBouton(titre);
        addView(textViewTitre);
    }

    @Override
    public void setImage(int res) {
        if(imageView == null) {
            imageView = new ImageView(getContext());
            ConstraintLayout.LayoutParams layoutParams = getLayoutParamsLogo();
            imageView.setLayoutParams(layoutParams);
            addView(imageView);
        }
        imageView.setImageResource(res);
    }

    @NonNull
    private ConstraintLayout.LayoutParams getLayoutParamsLogo() {
        int size = getLayoutParams().height - 2 * LOGO_MARGINS;
        int leftMarginLogo = getLayoutParams().width - getLayoutParams().height + LOGO_MARGINS;
        return new LayoutParamsDeBase_pourConstraintLayout(
                size, size, leftMarginLogo, LOGO_MARGINS
        );
    }
}
