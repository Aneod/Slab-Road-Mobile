package com.example.veritablejeu.Game.Editeur.SelectionElement.Categories.Modeles;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.veritablejeu.Tools.LayoutParams.LayoutParamsDeBase_pourConstraintLayout;

import java.util.ArrayList;

public class ModelesDeSquare extends ArrayList<View> {

    public static View getModeleDeSquareDansLeContainer(@NonNull Context context, int largeurHauteurContainer, GradientDrawable gradientDrawable) {
        int largeurModele = (int) (.7f * largeurHauteurContainer);
        int margesAutourDuModel = (largeurHauteurContainer - largeurModele) / 2;

        View modele = new View(context);
        ConstraintLayout.LayoutParams layoutParamsDeDalle = new LayoutParamsDeBase_pourConstraintLayout(
                largeurModele, largeurModele, margesAutourDuModel, margesAutourDuModel
        );
        modele.setLayoutParams(layoutParamsDeDalle);
        modele.setBackground(gradientDrawable);
        return modele;
    }
}
