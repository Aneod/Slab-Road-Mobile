package com.example.veritablejeu.Game.PlateauModulaire.BoardsMovements;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;

import androidx.core.util.Consumer;

import com.example.veritablejeu.Game.PlateauModulaire.BoardElement.BoardElement;

public abstract class OnTouchForElement {

    private static final int MOVE_SENSIBILITY = 10;
    private static final int LONG_PRESS_DELAY = 750;
    private static final Handler handler = new Handler();

    public OnTouchForElement(BoardElement boardElement) {
        setOnTouchForElement(boardElement);
    }

    public abstract Consumer<MotionEvent> clickEvent();
    public abstract Consumer<MotionEvent> longPressWithoutMoveEvent();
    public abstract Consumer<MotionEvent> longPressAfterMoveEvent();
    public abstract Consumer<MotionEvent> fastMoveEvent();
    public abstract Consumer<MotionEvent> moveAfterLongPressEvent();
    public abstract Consumer<MotionEvent> fastStopTouchWithoutMoveEvent();
    public abstract Consumer<MotionEvent> fastStopTouchWithMoveEvent();
    public abstract Consumer<MotionEvent> stopTouchWithoutMoveAfterLongPressEvent();
    public abstract Consumer<MotionEvent> stopTouchWithMoveAfterLongPressEvent();

    @SuppressLint("ClickableViewAccessibility")
    private void setOnTouchForElement(BoardElement boardElement) {
        if(boardElement == null) {
            return;
        }

        BoardsMovements onToucheListener = boardElement
                .getGame().getOnToucheListenerPlateauModulaire();

        boardElement.setOnTouchListener(new View.OnTouchListener() {
            private boolean moveEffectue = false;
            private boolean longPressTriggered = false;

            private final Runnable longPressRunnable = () -> {
                longPressTriggered = true;
                if (!moveEffectue) {
                    longPressWithoutMoveEvent().accept(null);
                } else {
                    longPressAfterMoveEvent().accept(null);
                }
            };

            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getActionMasked()) {
                    case MotionEvent.ACTION_DOWN:
                        clickEvent().accept(event);
                        onToucheListener.lastTouchX = event.getRawX();
                        onToucheListener.lastTouchY = event.getRawY();
                        moveEffectue = false;
                        longPressTriggered = false;
                        handler.postDelayed(longPressRunnable, LONG_PRESS_DELAY);
                        break;

                    case MotionEvent.ACTION_MOVE:
                        float deltaX = event.getRawX() - onToucheListener.lastTouchX;
                        float deltaY = event.getRawY() - onToucheListener.lastTouchY;

                        if (Math.abs(deltaX) > MOVE_SENSIBILITY || Math.abs(deltaY) > MOVE_SENSIBILITY) {
                            moveEffectue = true;
                            handler.removeCallbacks(longPressRunnable);
                            if (longPressTriggered) {
                                moveAfterLongPressEvent().accept(event);
                            } else {
                                fastMoveEvent().accept(event);
                            }
                        }
                        break;

                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL:
                        handler.removeCallbacks(longPressRunnable);
                        if (longPressTriggered) {
                            if (moveEffectue) {
                                stopTouchWithMoveAfterLongPressEvent().accept(event);
                            } else {
                                stopTouchWithoutMoveAfterLongPressEvent().accept(event);
                            }
                        } else {
                            if (moveEffectue) {
                                fastStopTouchWithMoveEvent().accept(event);
                            } else {
                                fastStopTouchWithoutMoveEvent().accept(event);
                            }
                        }
                        break;
                }
                return !moveEffectue;
            }
        });
    }
}
