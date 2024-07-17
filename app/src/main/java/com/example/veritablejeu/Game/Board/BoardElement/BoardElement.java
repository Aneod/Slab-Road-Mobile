package com.example.veritablejeu.Game.Board.BoardElement;

import android.annotation.SuppressLint;
import android.graphics.Point;
import android.view.MotionEvent;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.core.util.Consumer;

import com.example.veritablejeu.Game.Editeur.Editeur;
import com.example.veritablejeu.Game.Game;
import com.example.veritablejeu.Game.Board.Board;
import com.example.veritablejeu.Game.Board.BoardsMovements.OnTouchForElement;
import com.example.veritablejeu.LittleWindow.LittleWindow;
import com.example.veritablejeu.LittleWindow.WindowProposal.WindowProposal;

import java.util.List;

public abstract class BoardElement extends FrameLayout {

    protected final Game game;
    protected final Board board;

    public BoardElement(@NonNull Board board) {
        super(board.getContext());
        this.game = board.getGame();
        this.board = board;
        board.getBoardElementSet().add(this);

        if(game instanceof Editeur) {
            boolean isCableEditing = ((Editeur) game).isCableEditing();
            if(!isCableEditing) {
                enableEditorListeners();
            }
        } else {
            enableInGameListeners();
        }
    }

    public Game getGame() {
        return game;
    }

    public void remove() {
        board.removeView(this);
    }

    public abstract void enableInGameListeners();

    public abstract List<WindowProposal> getEditPropositions();

    public void enableEditorListeners() {

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
                return event -> showLittleWindow(event);
            }

            @Override
            public Consumer<MotionEvent> fastStopTouchWithMoveEvent() {
                return event -> {};
            }

            @Override
            public Consumer<MotionEvent> stopTouchWithoutMoveAfterLongPressEvent() {
                return event -> showLittleWindow(event);
            }

            @Override
            public Consumer<MotionEvent> stopTouchWithMoveAfterLongPressEvent() {
                return event -> {};
            }
        };
    }

    private void showLittleWindow(MotionEvent event) {
        if(event == null) return;
        Point point = new Point((int) event.getRawX(), (int) event.getRawY());
        List<WindowProposal> propositions = getEditPropositions();
        LittleWindow littleWindow = game.getLittleWindow();
        littleWindow.setPosition(point);
        littleWindow.set(propositions);
    }

    @SuppressLint("ClickableViewAccessibility")
    public void disableEditorListeners() {
        setOnTouchListener((v, event) -> false);
    }

}
