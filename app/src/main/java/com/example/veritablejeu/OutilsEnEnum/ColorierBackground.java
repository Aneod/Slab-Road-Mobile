package com.example.veritablejeu.OutilsEnEnum;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.veritablejeu.R;

import java.util.List;

public class ColorierBackground {

    /**
     * Change immédiatement la couleur du background du container.
     * @param context l'activité dont on change la couleur.
     * @param couleurs les couleurs (haute et basse) qu'on applique.
     */
    public static void colorierBackground(@NonNull AppCompatActivity context, int ... couleurs) {
        GradientDrawable gradientDrawable = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, couleurs);
        ConstraintLayout container = context.findViewById(R.id.main);
        if(container != null) {
            container.setBackground(gradientDrawable);
        }
    }

    /**
     * Change immédiatement la couleur du background du container.
     * @param container la container dont on change la couleur.
     * @param couleurs les couleurs (haute et basse) qu'on applique.
     */
    public static void colorierBackground(ConstraintLayout container, int[] couleurs) {
        GradientDrawable gradientDrawable = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, couleurs);
        container.setBackground(gradientDrawable);
    }

    /**
     * Change immédiatement la couleur du background du container.
     * @param container la container dont on change la couleur.
     * @param couleursEnList les couleurs (haute et basse) qu'on applique.
     */
    public static void colorierBackground(ConstraintLayout container, List<Integer> couleursEnList) {
        if(couleursEnList.size() < 2) return;
        int[] couleurs = new int[]{couleursEnList.get(0), couleursEnList.get(1)};
        GradientDrawable gradientDrawable = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, couleurs);
        container.setBackground(gradientDrawable);
    }
}
