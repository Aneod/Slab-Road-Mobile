package com.example.veritablejeu.Game.PlateauModulaire.BoardElement.Square.ModularSlab.Version.CabledSlab.Cable.MorceauStorage.Generator.CablePart;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.Point;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;

import com.example.veritablejeu.Game.PlateauModulaire.BoardElement.BoardElement;
import com.example.veritablejeu.Game.PlateauModulaire.BoardElement.Square.ModularSlab.Version.CabledSlab.Cable.MorceauStorage.MorceauStorage;
import com.example.veritablejeu.Tools.Elevation;
import com.example.veritablejeu.Tools.LayoutParams.LayoutParamsDeBase_pourFrameLayout;
import com.example.veritablejeu.PetiteFenetreFlottante.PetiteFenetreFlottante2;
import com.example.veritablejeu.Tools.MathematicTools;

import java.util.ArrayList;
import java.util.List;

@SuppressLint("ViewConstructor")
public class CablePart extends BoardElement {

    private static final int TOTAL_HEIGHT = 100;
    private static final int CABLE_HEIGHT = 6;
    private static final int BORDER_HEIGHT = 2;
    private static final int OUTLINE_COLOR = Color.BLACK;
    public static int getOutlineColor() {
        return OUTLINE_COLOR;
    }

    private final MorceauStorage morceauStorage;

    public CablePart(@NonNull MorceauStorage morceauStorage, Point from, Point to, int color, boolean borders) {
        super(morceauStorage.getBoard());
        this.morceauStorage = morceauStorage;
        setLayoutParams(from, to);
        applyRotation(from, to);
        applyElevation(borders);
        addVisualCable(from, to, color, borders);
    }

    private void setLayoutParams(Point from, Point to) {
        int distance = MathematicTools.getDistance(from, to);
        int largeur = distance + TOTAL_HEIGHT;
        int leftMargin = from.x - TOTAL_HEIGHT / 2;
        int topMargin = from.y - TOTAL_HEIGHT / 2;
        FrameLayout.LayoutParams layoutParams = new LayoutParamsDeBase_pourFrameLayout(
                largeur, TOTAL_HEIGHT, leftMargin, topMargin
        );
        setLayoutParams(layoutParams);
    }

    private void applyRotation(Point from, Point to) {
        setPivotX((float) TOTAL_HEIGHT / 2);
        setPivotY((float) TOTAL_HEIGHT / 2);
        float angleDegPositif = (float) MathematicTools.getAngle(from, to);
        setRotation(angleDegPositif);
    }

    private void applyElevation(boolean borders) {
        Elevation elevation = borders ? Elevation.BorderCable : Elevation.FillCable;
        setElevation(elevation.getElevation());
    }

    private void addVisualCable(Point from, Point to, int color, boolean borders) {
        View visualCable = new View(getContext());
        FrameLayout.LayoutParams layoutParams = getVisualCableLayoutParams(from, to, borders);
        visualCable.setLayoutParams(layoutParams);
        visualCable.setBackgroundColor(color);
        addView(visualCable);
    }

    @NonNull
    private FrameLayout.LayoutParams getVisualCableLayoutParams(Point from, Point to, boolean borders) {
        int distance = MathematicTools.getDistance(from, to);
        int height = borders ? CABLE_HEIGHT + BORDER_HEIGHT * 2 : CABLE_HEIGHT;
        int width = (int) (distance + height);
        int margins = (TOTAL_HEIGHT - height) / 2;
        return new LayoutParamsDeBase_pourFrameLayout(
                width, height, margins, margins
        );
    }

    @Override
    public void enableInGameListeners() {

    }

    @Override
    public List<PetiteFenetreFlottante2.StringRunnablePair> getEditPropositions() {
        List<PetiteFenetreFlottante2.StringRunnablePair> propositions = new ArrayList<>();
        propositions.add(new PetiteFenetreFlottante2.StringRunnablePair("Outline", this::swapCableOutline));
        propositions.add(new PetiteFenetreFlottante2.StringRunnablePair("Delete", morceauStorage::deleteCable, Color.RED, true));
        return propositions;
    }

    private void swapCableOutline() {
        morceauStorage.getBoard().getGame().swapCableOutline();
    }
}
