package com.example.veritablejeu.Game.Board.BoardElement.Square.ModularSlab.SlabDesign;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;

import com.example.veritablejeu.Game.Board.BoardElement.Square.ModularSlab.ModularSlab;
import com.example.veritablejeu.Game.Board.BoardElement.Square.ModularSlab.SlabDesign.CercleDeCompte.CercleDeCompte;
import com.example.veritablejeu.Game.Board.BoardElement.Square.ModularSlab.Version.YellowSlab;
import com.example.veritablejeu.Game.Board.BoardElement.Square.ModularSquare;
import com.example.veritablejeu.Game.Board.Board;
import com.example.veritablejeu.Tools.CreateSimpleBackground;
import com.example.veritablejeu.Game.Board.ZdecimalCoordinates.ZdecimalCoordinates;
import com.example.veritablejeu.Game.Board.ZdecimalCoordinates.ZdecimalCoordinatesManager;
import com.example.veritablejeu.Tools.Elevation;
import com.example.veritablejeu.Tools.LayoutParams.LayoutParamsDeBase_pourFrameLayout;

public class SlabDesign {

    private static final GradientDrawable OFF_BACKGROUND = CreateSimpleBackground.create(Color.LTGRAY, Color.BLACK, 1);

    private final ModularSlab dalle;
    private final ModularSquare originSquare;
    private final CercleDeCompte cercleDeCompte;

    public SlabDesign(@NonNull ModularSquare origin, @NonNull ModularSlab modularSlab) {
        dalle = modularSlab;
        cercleDeCompte = new CercleDeCompte(dalle);
        originSquare = origin;
        display_onlyFirstTime();
        display();
    }

    private void display_onlyFirstTime() {
        dalle.setElevation(Elevation.Slab.getElevation());
        dalle.getBoard().addView(dalle);

        GradientDrawable background = CreateSimpleBackground.create(dalle.getFillColor(), Color.BLACK, 1);
        dalle.setBackground(background);
    }

    public void display() {
        int weight = dalle.getWeight();
        boolean isYellow = dalle instanceof YellowSlab;
        int nombreTotalDeCase = weight + (isYellow ? 1 : 0);
        int casesEnLargeur = (int) Math.ceil(Math.sqrt(nombreTotalDeCase));

        dalle.clearNums();
        for(int x = 0; x < casesEnLargeur; x++) {
            for(int y = 0; y < casesEnLargeur; y++) {
                ZdecimalCoordinates cord = originSquare.getCord();
                for(int xIndex = 0; xIndex < x; xIndex++) {
                    cord = ZdecimalCoordinatesManager.getRightOf(cord);
                }
                for(int yIndex = 0; yIndex < y; yIndex++) {
                    cord = ZdecimalCoordinatesManager.getBottomOf(cord);
                }
                dalle.addNums(cord);
            }
        }
        float occupationLargeurDalle = .8f;
        int tailleCases = Board.SQUARE_SIZE;
        int tailleNormale = (int) Math.floor(tailleCases * occupationLargeurDalle);
        int decalage = (tailleCases - tailleNormale) / 2;
        int tailleDeLaCase = tailleNormale + tailleCases * (casesEnLargeur - 1);
        int leftMargin = originSquare.getLayoutParams().leftMargin + decalage;
        int topMargin = originSquare.getLayoutParams().topMargin + decalage;
        FrameLayout.LayoutParams layoutParams = new LayoutParamsDeBase_pourFrameLayout(
                tailleDeLaCase, tailleDeLaCase, leftMargin, topMargin
        );
        dalle.setLayoutParams(layoutParams);
        cercleDeCompte.set(weight);
    }

    public void refresh() {
        display();
    }

    public void turnOff() {
        dalle.setBackground(OFF_BACKGROUND);
    }

}
