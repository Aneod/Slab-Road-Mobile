package com.example.veritablejeu.Game.PlateauModulaire;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;

import com.example.veritablejeu.Game.PlateauModulaire.ZdecimalCoordinates.ZdecimalCharacter.ZdecimalCharacterConverter;
import com.example.veritablejeu.Game.PlateauModulaire.ZdecimalCoordinates.ZdecimalCoordinates;
import com.example.veritablejeu.Tools.Elevation;
import com.example.veritablejeu.Tools.LayoutParams.LayoutParamsDeBase_pourFrameLayout;
import com.example.veritablejeu.PetiteFenetreFlottante.PetiteFenetreFlottante2;

import java.util.ArrayList;
import java.util.List;

@SuppressLint("ViewConstructor")
public class SpecSquare extends ModularObject {

    private static final GradientDrawable fenceVisual = CreateSimpleBackground.create(Color.TRANSPARENT, Color.BLACK, 1);

    private final Board board;
    private final ZdecimalCoordinates coordinates;

    /**
     * Create and apply a basic square layoutParams, with the good positions and size.
     * @return the custom layoutParams.
     */
    @NonNull
    private FrameLayout.LayoutParams setLayoutParams_byCords() {
        int xPos = ZdecimalCharacterConverter.zdecimalCharacter_to_intDecimal(coordinates.getX());
        int yPos = ZdecimalCharacterConverter.zdecimalCharacter_to_intDecimal(coordinates.getY());

        int leftMargin = xPos * Board.SQUARE_SIZE + Board.BORDER_WIDTH;
        int topMargin = yPos * Board.SQUARE_SIZE + Board.BORDER_WIDTH;

        return new LayoutParamsDeBase_pourFrameLayout(
                Board.SQUARE_SIZE, Board.SQUARE_SIZE, leftMargin, topMargin
        );
    }

    public SpecSquare(@NonNull Board board, @NonNull ZdecimalCoordinates zdecimalCoordinates) {
        super(board);
        this.board = board;
        coordinates = zdecimalCoordinates;
        FrameLayout.LayoutParams layoutParams = setLayoutParams_byCords();
        setLayoutParams(layoutParams);
        setElevation(Elevation.SpecSquare.getElevation());
        board.addView(this);
        hideFence();
        enableEditorListeners();
    }

    public Board getBoard() {
        return board;
    }

    public void showFence() {
        setBackground(fenceVisual);
    }

    public void hideFence() {
        setBackground(null);
    }

    private void createSquareOn() {
        board.createSquare("0" + coordinates.getX().getCharacter() + coordinates.getY().getCharacter());
    }

    @Override
    public void enableInGameListeners() {

    }

    @Override
    public List<PetiteFenetreFlottante2.StringRunnablePair> getEditPropositions() {
        List<PetiteFenetreFlottante2.StringRunnablePair> propositions = new ArrayList<>();
        propositions.add(new PetiteFenetreFlottante2.StringRunnablePair("Add square", this::createSquareOn, true));
        propositions.add(new PetiteFenetreFlottante2.StringRunnablePair("Flash", () -> game.flashDeCouleur(Color.BLACK)));
        return propositions;
    }

}
