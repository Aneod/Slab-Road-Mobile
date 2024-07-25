package com.example.veritablejeu.Navigation.Preset.NavigationEditeur.Input_NomDuNiveau;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.veritablejeu.Game.Game;
import com.example.veritablejeu.Navigation.BoutonNavigation.BoutonPrincipal.BoutonPrincipal;
import com.example.veritablejeu.Navigation.Preset.NavigationEditeur.NavigationEditeur;
import com.example.veritablejeu.R;

@SuppressLint("ViewConstructor")
public class Input_NomDuNiveau extends AppCompatEditText implements IInput_NomDuNiveau {

    private final ConstraintLayout constraintLayout;
    long TEMPS_APPARITION_DISPARITION = 200;

    @NonNull
    private AlphaAnimation alphaAnimation(float from, float to, long startOffSet, long duration) {
        AlphaAnimation alphaAnimation = new AlphaAnimation(from, to);
        alphaAnimation.setFillAfter(true);
        alphaAnimation.setStartOffset(startOffSet);
        alphaAnimation.setDuration(duration);
        return alphaAnimation;
    }

    @Override
    public void apparition(long startOffSet) {
        if(getParent() == null) {
            constraintLayout.addView(this);
        }
        AlphaAnimation alphaAnimation = alphaAnimation(0.0f, 1.0f, startOffSet, TEMPS_APPARITION_DISPARITION);
        startAnimation(alphaAnimation);
    }

    @Override
    public void disparition(long startOffSet) {
        AlphaAnimation alphaAnimation = alphaAnimation(1.0f, 0.0f, startOffSet, TEMPS_APPARITION_DISPARITION);
        startAnimation(alphaAnimation);

        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                constraintLayout.removeView(Input_NomDuNiveau.this);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    @Override
    public void show() {
        if(getParent() == null) {
            constraintLayout.addView(this);
        }
    }

    @Override
    public void hide() {
        constraintLayout.removeView(this);
    }

    /**
     * Construit un GradientDrawable blanc avec bordure noire et coins arrondis.
     * @return un GradientDrawable.
     */
    @NonNull
    private GradientDrawable getDrawable() {
        GradientDrawable drawable = new GradientDrawable();
        drawable.setColor(Color.WHITE);
        drawable.setStroke(1, Color.BLACK);
        drawable.setCornerRadius(5);
        return drawable;
    }

    public Input_NomDuNiveau(@NonNull Game context, @NonNull NavigationEditeur navigationEditeur) {
        super(context);
        setHint("Nom du niveau...");

        constraintLayout = context.findViewById(R.id.main);
        constraintLayout.addView(this);

        String name = context.getLevelFiles().name;
        boolean noName = name == null || name.isEmpty();
        String text = noName ? null : name;
        setText(text);

        int margesHGD;
        BoutonPrincipal boutonPrincipale = navigationEditeur.getBoutonPrincipal();
        int rightMargin;
        int height;
        if(boutonPrincipale != null) {
            int diametreNormal = boutonPrincipale.getDiametre();
            int diametreReduitBoutonPrincipal = boutonPrincipale.getDiametreReduit();
            int surplusDeMarges = (diametreNormal - diametreReduitBoutonPrincipal) / 2;
            margesHGD = navigationEditeur.getMargesHautGaucheDroite() + surplusDeMarges;
            rightMargin = 2 * margesHGD + diametreReduitBoutonPrincipal;
            height = diametreReduitBoutonPrincipal;
        } else {
            margesHGD = navigationEditeur.getMargesHautGaucheDroite();
            rightMargin = navigationEditeur.getMargesHautGaucheDroite();
            height = ConstraintLayout.LayoutParams.MATCH_PARENT;
        }

        ConstraintLayout.LayoutParams layoutParamsEditText = new ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.MATCH_PARENT, height);
        layoutParamsEditText.leftToLeft = ConstraintLayout.LayoutParams.PARENT_ID;
        layoutParamsEditText.rightToRight = ConstraintLayout.LayoutParams.PARENT_ID;
        layoutParamsEditText.topToTop = ConstraintLayout.LayoutParams.PARENT_ID;
        layoutParamsEditText.setMargins(margesHGD, margesHGD, rightMargin, 0);
        setLayoutParams(layoutParamsEditText);
        setPadding(5, 5, 5, 5);

        GradientDrawable drawable = getDrawable();
        setBackgroundDrawable(drawable);
        setElevation(1);

        long tempsDeFermetureDeLaNavigation;
        BoutonPrincipal boutonPrincipal = navigationEditeur.getBoutonPrincipal();
        if(boutonPrincipale != null) {
            tempsDeFermetureDeLaNavigation = boutonPrincipal.getTempsDeFermeture();
        } else {
            tempsDeFermetureDeLaNavigation = 400;
        }

        Runnable fonctionOuverture = () -> disparition(0);
        Runnable fonctionFermeture = () -> apparition(tempsDeFermetureDeLaNavigation);
        navigationEditeur.setFonction_pour_ouverture_fermeture(fonctionOuverture, fonctionFermeture);
    }
}
