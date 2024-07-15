package com.example.veritablejeu.Game.PlateauModulaire.BoardElement.Square.ModularSlab.SlabDesign.CercleDeCompte;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;

import com.example.veritablejeu.Game.PlateauModulaire.BoardElement.Square.ModularSlab.ModularSlab;
import com.example.veritablejeu.Tools.LayoutParams.LayoutParamsDeBase_pourFrameLayout;

import java.util.ArrayList;

public class CercleDeCompte implements ICercleDeCompte {

    private final FrameLayout slab;
    private final ArrayList<PointDeCompte> tousLesPoints = new ArrayList<>();

    @Override
    public void set(int number) {
        tousLesPoints.forEach(slab::removeView);
        if(number <= 1) return;
        double separation = 360.0 / number;
        for(int index = 0; index < number; index++) {
            double rotation = separation * index;
            new PointDeCompte(rotation);
        }
    }

    public CercleDeCompte(@NonNull ModularSlab slab) {
        this.slab = slab;
    }



    private class PointDeCompte extends View {

        public PointDeCompte(double rotation) {
            super(slab.getContext());
            slab.addView(this);
            tousLesPoints.add(this);
            setLayoutParams(rotation);
            setBackground();
        }

        private void setBackground() {
            GradientDrawable gradientDrawable = new GradientDrawable();
            gradientDrawable.setShape(GradientDrawable.OVAL);
            gradientDrawable.setColor(Color.BLACK);
            setBackground(gradientDrawable);
        }

        private void setLayoutParams(double rotation) {
            int slabSize = slab.getLayoutParams().width;
            int leftMarginCercle = (int) (Math.cos(Math.toRadians(rotation + 15.0)) * slabSize/5);
            int topMarginCercle = (int) (Math.sin(Math.toRadians(rotation + 15.0)) * slabSize/5);
            float scaleValue = 0.15f;

            int diameter = (int) (slabSize * scaleValue);
            int halfDiameter = diameter / 2;
            int halfSlabSize = slabSize / 2;
            int leftMargin = leftMarginCercle - halfDiameter + halfSlabSize;
            int topMargin = topMarginCercle - halfDiameter + halfSlabSize;
            FrameLayout.LayoutParams layoutParams = new LayoutParamsDeBase_pourFrameLayout(
                    diameter, diameter, leftMargin, topMargin);
            setLayoutParams(layoutParams);
        }

    }
}
