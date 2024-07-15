package com.example.veritablejeu.Menu.PageDeSelection;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.veritablejeu.OutilsEnEnum.LayoutParams.LayoutParamsDeBase_pourConstraintLayout;
import com.example.veritablejeu.OutilsEnEnum.LayoutParams.LayoutParamsDeBase_pourFrameLayout;

@SuppressLint("ViewConstructor")
public class IndicationPourPanneauDeNiveaux extends FrameLayout {

    public IndicationPourPanneauDeNiveaux(@NonNull Context context, PanneauDeNiveaux panneauDeNiveaux, String texte, int img) {
        super(context);

        int decalageVersLeHaut = 100;
        int largeurSymboleAucunFichier = 200;
        int width = panneauDeNiveaux.getLayoutParams().width;
        int height = panneauDeNiveaux.getLayoutParams().height;
        int leftMarginSymboleAucunFichier = (width - largeurSymboleAucunFichier) / 2;
        int topMarginSymboleAucunFichier = (height - largeurSymboleAucunFichier) / 2 - decalageVersLeHaut;
        ImageView symboleAucunFichier = new ImageView(context);
        symboleAucunFichier.setImageResource(img);
        symboleAucunFichier.setAlpha(.1f);
        FrameLayout.LayoutParams layoutParams1 = new LayoutParamsDeBase_pourFrameLayout(
                largeurSymboleAucunFichier, largeurSymboleAucunFichier,
                leftMarginSymboleAucunFichier, topMarginSymboleAucunFichier
        );
        symboleAucunFichier.setLayoutParams(layoutParams1);
        symboleAucunFichier.setElevation(1);
        addView(symboleAucunFichier);

        TextView textView = new TextView(context);
        textView.setText(texte);
        textView.setTextColor(Color.BLACK);
        textView.setAlpha(.3f);
        textView.setGravity(Gravity.CENTER_HORIZONTAL);
        textView.setTextSize(20);
        int distanceSymbole_texte = 20;
        int widthText = (int) (largeurSymboleAucunFichier * 2.5);
        int leftMarginText = (width - widthText) / 2;
        int topMarginText = topMarginSymboleAucunFichier + largeurSymboleAucunFichier + distanceSymbole_texte;
        ConstraintLayout.LayoutParams layoutParams2 = new LayoutParamsDeBase_pourConstraintLayout(
                widthText, largeurSymboleAucunFichier, leftMarginText, topMarginText
        );
        textView.setLayoutParams(layoutParams2);
        textView.setElevation(1);
        addView(textView);
    }
}
