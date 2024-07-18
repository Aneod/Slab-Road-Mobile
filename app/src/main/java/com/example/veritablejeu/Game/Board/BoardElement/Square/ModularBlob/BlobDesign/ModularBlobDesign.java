package com.example.veritablejeu.Game.Board.BoardElement.Square.ModularBlob.BlobDesign;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.example.veritablejeu.Game.Board.Board;
import com.example.veritablejeu.Game.Board.BoardElement.Square.ModularBlob.ModularBlob;
import com.example.veritablejeu.Game.Board.BoardElement.Square.ModularSlab.SlabDesign.SlabDesign;
import com.example.veritablejeu.Tools.LayoutParams.LayoutParamsDeBase_pourFrameLayout;
import com.example.veritablejeu.R;

import java.util.Random;

@SuppressLint("ViewConstructor")
public class ModularBlobDesign extends FrameLayout {

    private final ImageView contour;
    private final ImageView coeur;
    private final ImageView blobShape;
    private int color;

    @NonNull
    private View creationOeil(int width, int height, int leftMargin, int topMargin) {
        View oeil = new View(getContext());
        GradientDrawable drawable = new GradientDrawable();
        drawable.setShape(GradientDrawable.OVAL);
        drawable.setColor(Color.WHITE);
        drawable.setStroke(2, Color.BLACK);
        oeil.setBackground(drawable);

        LayoutParams layoutParams = new LayoutParamsDeBase_pourFrameLayout(
                width, height, leftMargin, topMargin
        );
        oeil.setLayoutParams(layoutParams);

        return oeil;
    }

    @NonNull
    private ScaleAnimation getScaleAnimation() {
        Random random = new Random();
        int min = 1500;
        int max = 2000;
        int duration = min + random.nextInt(max - min + 1);

        // Création de l'animation d'échelle en boucle
        ScaleAnimation scaleAnimation = new ScaleAnimation(
                0.95f, 1f,
                1f, 1.05f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f
        );
        scaleAnimation.setDuration(duration); // Durée de l'animation en millisecondes
        scaleAnimation.setRepeatCount(Animation.INFINITE); // Répétition infinie
        scaleAnimation.setRepeatMode(Animation.REVERSE); // Inverser la direction à chaque répétition
        return scaleAnimation;
    }

    public ModularBlobDesign(@NonNull ModularBlob modularBlob) {
        super(modularBlob.getBoard().getContext());
        this.color = modularBlob.getBoard().getBlobsColor();

        this.contour = new ImageView(getContext());
        contour.setImageResource(R.drawable.blob_shape);
        contour.setColorFilter(color);
        contour.setAlpha(0.40f);

        this.coeur = new ImageView(getContext());
        coeur.setImageResource(R.drawable.blob_heart);
        coeur.setColorFilter(color);
        coeur.setScaleX(0.80f);
        coeur.setScaleY(0.80f);
        coeur.setTranslationY((float) (Board.SQUARE_SIZE * 0.10));

        this.blobShape = new ImageView(getContext());
        blobShape.setImageResource(R.drawable.blob_shape);
        blobShape.setColorFilter(color);
        blobShape.setScaleX(0.95f);
        blobShape.setScaleY(0.95f);
        blobShape.setAlpha(0.60f);

        ScaleAnimation scaleAnimation = getScaleAnimation();

        // Démarrage de l'animation
        contour.startAnimation(scaleAnimation);
        coeur.startAnimation(scaleAnimation);
        blobShape.startAnimation(scaleAnimation);

        this.addView(contour);
        this.addView(coeur);
        this.addView(blobShape);

        double occupationEnLongeurDuBlobSurUneCase = .75;
        int diametrePions = (int) Math.floor(Board.SQUARE_SIZE * occupationEnLongeurDuBlobSurUneCase);

        int largeurYeux = (int) (diametrePions * 0.15f);
        int hauteurYeux = (int) (diametrePions * 0.27f);
        int leftMarginOeilGauche = (int) (diametrePions * 0.24f);

        int topMarginYeux = (int) (diametrePions * 0.27f);
        View oeilGauche = creationOeil(largeurYeux, hauteurYeux, leftMarginOeilGauche, topMarginYeux);
        addView(oeilGauche);

        int leftMarginOeilDroit = diametrePions - (leftMarginOeilGauche + largeurYeux);
        View oeilDroit = creationOeil(largeurYeux, hauteurYeux, leftMarginOeilDroit, topMarginYeux);
        addView(oeilDroit);

        int margins = (Board.SQUARE_SIZE - diametrePions) / 2;
        LayoutParams layoutParamsDesign = new LayoutParamsDeBase_pourFrameLayout(diametrePions, diametrePions, margins, margins);
        this.setLayoutParams(layoutParamsDesign);
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
        contour.setColorFilter(color);
        coeur.setColorFilter(color);
        blobShape.setColorFilter(color);
    }
}
