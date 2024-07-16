package com.example.veritablejeu.Game.Board.BoardElement.Fence.SpecSquare;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.core.util.Consumer;

import com.example.veritablejeu.Game.Board.Board;
import com.example.veritablejeu.Game.Board.BoardElement.Square.ModularSlab.Version.CabledSlab.Cable.Cable;
import com.example.veritablejeu.Game.Board.BoardsMovements.OnTouchForElement;
import com.example.veritablejeu.Tools.CreateSimpleBackground;
import com.example.veritablejeu.Game.Board.BoardElement.BoardElement;
import com.example.veritablejeu.Game.Board.ZdecimalCoordinates.ZdecimalCharacter.ZdecimalCharacterConverter;
import com.example.veritablejeu.Game.Board.ZdecimalCoordinates.ZdecimalCoordinates;
import com.example.veritablejeu.Tools.Elevation;
import com.example.veritablejeu.Tools.LayoutParams.LayoutParamsDeBase_pourFrameLayout;
import com.example.veritablejeu.LittleWindow.LittleWindow;

import java.util.ArrayList;
import java.util.List;

@SuppressLint("ViewConstructor")
public class SpecSquare extends BoardElement {

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

    @Override
    public void enableInGameListeners() {

    }

    public void enableCableEditorListener(Cable cable) {

        new OnTouchForElement(this) {

            @Override
            public Consumer<MotionEvent> clickEvent() {
                return event -> {};
            }

            @Override
            public Consumer<MotionEvent> longPressWithoutMoveEvent() {
                return event -> {};
            }

            @Override
            public Consumer<MotionEvent> longPressAfterMoveEvent() {
                return event -> {};
            }

            @Override
            public Consumer<MotionEvent> fastMoveEvent() {
                return event -> {};
            }

            @Override
            public Consumer<MotionEvent> moveAfterLongPressEvent() {
                return event -> {};
            }

            @Override
            public Consumer<MotionEvent> fastStopTouchWithoutMoveEvent() {
                return event -> swapIntersectionToCable(cable);
            }

            @Override
            public Consumer<MotionEvent> fastStopTouchWithMoveEvent() {
                return event -> {};
            }

            @Override
            public Consumer<MotionEvent> stopTouchWithoutMoveAfterLongPressEvent() {
                return event -> swapIntersectionToCable(cable);
            }

            @Override
            public Consumer<MotionEvent> stopTouchWithMoveAfterLongPressEvent() {
                return event -> {};
            }
        };
    }

    private void swapIntersectionToCable(Cable cable) {
        if(cable == null) return;
        boolean isIntersectionedCable = cable.getComponentsStorage().getIntersections().contains(coordinates);
        if(isIntersectionedCable) {
            removeFromCableIntersection(cable);
        } else {
            addAsCableIntersection(cable);
        }
    }

    private void addAsCableIntersection(Cable cable) {
        if(cable == null) return;
        cable.addIntersection(coordinates);
    }

    private void removeFromCableIntersection(Cable cable) {
        if(cable == null) return;
        cable.removeIntersection(coordinates);
    }

    @SuppressLint("ClickableViewAccessibility")
    public void disableCableEditorListener() {
        setOnTouchListener((v, event) -> false);
    }

    @Override
    public List<LittleWindow.StringRunnablePair> getEditPropositions() {
        List<LittleWindow.StringRunnablePair> propositions = new ArrayList<>();
        propositions.add(new LittleWindow.StringRunnablePair("Add square", this::createSquareOn, true));
        propositions.add(new LittleWindow.StringRunnablePair("Add ghost", this::createGhostOn, true));
        propositions.add(new LittleWindow.StringRunnablePair("Flash", () -> game.flashDeCouleur(Color.BLACK)));
        return propositions;
    }

    private void createSquareOn() {
        board.createSquare("0" + coordinates.getX().getCharacter() + coordinates.getY().getCharacter());
    }

    private void createGhostOn() {
        board.createSquare("1" + coordinates.getX().getCharacter() + coordinates.getY().getCharacter());
    }

}
