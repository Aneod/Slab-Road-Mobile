package com.example.veritablejeu.Game.PlateauModulaire.ModularSlab.Version.CabledSlab.Cable.MorceauStorage.Morceau;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.Point;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;

import com.example.veritablejeu.Game.PlateauModulaire.ModularObject;
import com.example.veritablejeu.Game.PlateauModulaire.ModularSlab.Version.CabledSlab.Cable.MorceauStorage.Generator.CablePrinter;
import com.example.veritablejeu.Game.PlateauModulaire.ModularSlab.Version.CabledSlab.Cable.MorceauStorage.MorceauStorage;
import com.example.veritablejeu.Game.PlateauModulaire.ZdecimalCoordinates.ZdecimalCoordinates;
import com.example.veritablejeu.Game.PlateauModulaire.ZdecimalCoordinates.ZdecimalCoordinatesPositionner;
import com.example.veritablejeu.OutilsEnEnum.Elevation;
import com.example.veritablejeu.OutilsEnEnum.LayoutParams.LayoutParamsDeBase_pourFrameLayout;
import com.example.veritablejeu.PetiteFenetreFlottante.PetiteFenetreFlottante2;

import java.util.ArrayList;
import java.util.List;

@SuppressLint("ViewConstructor")
public class Morceau extends ModularObject {

    private static final int TOTAL_HEIGHT = 100;
    private static final int CABLE_HEIGHT = 6;
    private static final int BORDER_HEIGHT = 2;
    private static final int OUTLINE_COLOR = Color.BLACK;
    public static int getOutlineColor() {
        return OUTLINE_COLOR;
    }

    private final MorceauStorage morceauStorage;

    public Morceau(@NonNull MorceauStorage morceauStorage, @NonNull ZdecimalCoordinates from, @NonNull ZdecimalCoordinates to, int couleur, boolean borders) {
        this(morceauStorage, ZdecimalCoordinatesPositionner.getCenterOf(from), ZdecimalCoordinatesPositionner.getCenterOf(to), couleur, borders);
    }

    public Morceau(@NonNull MorceauStorage morceauStorage, @NonNull ZdecimalCoordinates from, @NonNull Point to, int couleur, boolean borders) {
        this(morceauStorage, ZdecimalCoordinatesPositionner.getCenterOf(from), to, couleur, borders);
    }

    public Morceau(@NonNull MorceauStorage morceauStorage, @NonNull Point from, @NonNull Point to, int couleur, boolean borders) {
        super(morceauStorage.getBoard());
        this.morceauStorage = morceauStorage;

        int distance = getDistanceBetween(from, to);
        int largeur = distance + TOTAL_HEIGHT;
        int halfH = TOTAL_HEIGHT / 2;
        int leftMargin = from.x - halfH;
        int topMargin = from.y - halfH;

        FrameLayout.LayoutParams layoutParams = new LayoutParamsDeBase_pourFrameLayout(
                largeur, TOTAL_HEIGHT, leftMargin, topMargin
        );
        setLayoutParams(layoutParams);
        setElevation(Elevation.Cable.getElevation());

        setPivotX(halfH);
        setPivotY(halfH);
        float angleDegPositif = (float) getAngle(from, to);
        setRotation(angleDegPositif);

        addVisualCable(distance, couleur, borders);
    }

    private void addVisualCable(double distance, int color, boolean borders) {
        View visualCable = new View(getContext());

        int height = borders ? CABLE_HEIGHT + BORDER_HEIGHT * 2 : CABLE_HEIGHT;
        int width = (int) (distance + height);
        int margins = (TOTAL_HEIGHT - height) / 2;

        FrameLayout.LayoutParams layoutParams = new LayoutParamsDeBase_pourFrameLayout(
                width, height, margins, margins
        );
        visualCable.setLayoutParams(layoutParams);
        visualCable.setBackgroundColor(color);
        addView(visualCable);
    }

    private int getDeltaXBetween(Point from, Point to) {
        if(from == null || to == null) return 0;
        return to.x - from.x;
    }

    private int getDeltaYBetween(Point from, Point to) {
        if(from == null || to == null) return 0;
        return to.y - from.y;
    }

    private int getDistanceBetween(Point from, Point to) {
        if(from == null || to == null) return 0;
        int dx = getDeltaXBetween(from, to);
        int dy = getDeltaYBetween(from, to);
        return (int) Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
    }

    private double getAngle(Point from, Point to) {
        if(from == null || to == null) return 0.0;
        int dx = getDeltaXBetween(from, to);
        int dy = getDeltaYBetween(from, to);
        double angleRad = Math.atan2(dy, dx);
        double angleDeg = angleRad * 180 / Math.PI;
        if(angleDeg < 0) {
            return angleDeg + 360.0;
        }
        return angleDeg;
    }

    @Override
    public void enableInGameListeners() {

    }

    @Override
    public List<PetiteFenetreFlottante2.StringRunnablePair> getEditPropositions() {
        List<PetiteFenetreFlottante2.StringRunnablePair> propositions = new ArrayList<>();
        propositions.add(new PetiteFenetreFlottante2.StringRunnablePair("Outline", this::enableDisableCableOutline));
        propositions.add(new PetiteFenetreFlottante2.StringRunnablePair("Delete", morceauStorage::delete, Color.RED, true));
        return propositions;
    }

    public void enableDisableCableOutline() {
        morceauStorage.getBoard().getGame().enableDisableCableOutline();
    }
}
