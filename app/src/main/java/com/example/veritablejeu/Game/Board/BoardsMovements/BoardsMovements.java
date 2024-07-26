package com.example.veritablejeu.Game.Board.BoardsMovements;

import android.view.MotionEvent;
import android.view.ScaleGestureDetector;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.veritablejeu.Game.Game;
import com.example.veritablejeu.Game.Board.Board;
import com.example.veritablejeu.Tools.StatusBar;
import com.example.veritablejeu.Tools.ScreenUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class BoardsMovements {

    private final Game game;
    private final ScaleGestureDetector scaleGestureDetector;
    public float lastTouchX = 0, lastTouchY = 0;
    private boolean zoomEffectue = false;

    public BoardsMovements(@NonNull Game game) {
        this.game = game;
        scaleGestureDetector = new ScaleGestureDetector(game, new ScaleListener());
    }

    public void onTouchEvent(MotionEvent event, @Nullable Board board) {
        if(event == null) {
            return;
        }

        int pointerCount = event.getPointerCount();

        if(pointerCount == 1) {
            handleTranslation(event, board);
        }
        else if(pointerCount == 2) {
            zoomEffectue = true;
            scaleGestureDetector.onTouchEvent(event);
        }
    }

    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        @Override
        public boolean onScale(@NonNull ScaleGestureDetector detector) {

            ArrayList<Board> plateauModulaireSet = game.getPlateauModulaireSet();

            for(Board board : plateauModulaireSet) {

                float scaleFactor = board.getScaleX();
                float diff = Math.abs(scaleFactor - scaleFactor * detector.getScaleFactor());
                float m = (float) Math.pow(detector.getScaleFactor(), 2);
                boolean tooBigGap = diff > 0.15 * scaleFactor;
                boolean problemeDeTaille = sizeProblem(board, m);

                if (!tooBigGap && !problemeDeTaille) {
                    scaleFactor *= m;

                    board.setScaleX(scaleFactor);
                    board.setScaleY(scaleFactor);

                    float multiplicateur = m - 1;

                    float posXCentral = board.getX() + (float) board.getWidth() / 2;
                    float relativeX = posXCentral - (float) ScreenUtils.getScreenWidth() / 2;
                    board.setTranslationX(board.getTranslationX() + relativeX * multiplicateur);

                    float posYCentral = board.getY() + (float) board.getHeight() / 2;
                    float relativeY = posYCentral - (float) ScreenUtils.getScreenHeight() / 2;
                    board.setTranslationY(board.getTranslationY() + relativeY * multiplicateur);
                }
            }
            return true;
        }
    }

    private boolean sizeProblem(Board board, float m) {
        if(board == null) {
            return true;
        }
        int numberOfSquaresPerLines = board.getNumberOf_squarePerLines();
        float nombreMinDeSquareSurLaLargeur = Math.min(numberOfSquaresPerLines, 4);
        float nbCaseSurLaHauteurMin = (float) ScreenUtils.getScreenHeight() / ScreenUtils.getScreenWidth() * nombreMinDeSquareSurLaLargeur;
        float scaleXmax = ScreenUtils.getScreenWidth() / (Board.SQUARE_SIZE * nombreMinDeSquareSurLaLargeur);
        float scaleYmax = ScreenUtils.getScreenHeight() / (Board.SQUARE_SIZE * nbCaseSurLaHauteurMin);
        float scaleMax = Math.min(scaleXmax, scaleYmax);
        float normalScale = board.getNormalScale();
        float scaleMin = .5f * normalScale;

        boolean tropGrandX = board.getScaleX() >= scaleMax && m >= 1;
        boolean tropGrandY = board.getScaleY() >= scaleMax && m >= 1;
        boolean tropPetitX = board.getScaleX() <= scaleMin && m <= 1;
        boolean tropPetitY = board.getScaleY() <= scaleMin && m <= 1;

        boolean tropGrand = tropGrandX && tropGrandY;
        boolean tropPetit = tropPetitX && tropPetitY;
        return tropGrand || tropPetit;
    }

    private void handleTranslation(@NonNull MotionEvent event, Board boardToMove) {

        Set<Board> originalPlateauModulaireSet = new HashSet<>();
        if(boardToMove == null) {
            originalPlateauModulaireSet.addAll(game.getPlateauModulaireSet());
        } else {
            originalPlateauModulaireSet.add(boardToMove);
        }

        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                lastTouchX = event.getRawX();
                lastTouchY = event.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                if (!zoomEffectue) {
                    float deltaX = event.getRawX() - lastTouchX;
                    float deltaY = event.getRawY() - lastTouchY;

                    for(Board board : originalPlateauModulaireSet) {
                        float boardWidth = board.getTotalWidthPlateau() * board.getScaleX();
                        float boardHeight = board.getTotalHeightPlateau() * board.getScaleX();
                        float boardLeftMargin = board.getNearestLeftMargin() * board.getScaleX();
                        float boardTopMargin = board.getNearestTopMargin() * board.getScaleX();

                        int[] location = new int[2];
                        board.getLocationOnScreen(location);
                        int x = location[0];
                        int y = location[1];

                        float decalage = (Board.BORDER_WIDTH + (float) Board.SQUARE_SIZE * 2) * board.getScaleX();
                        if (y + boardTopMargin + boardHeight - decalage - StatusBar.getHeight(board.getGame()) < 0 && deltaY < 0)
                            deltaY = 0;
                        else if (y + boardTopMargin + decalage - StatusBar.getHeight(board.getGame()) > ScreenUtils.getScreenHeight() && deltaY > 0)
                            deltaY = 0;
                        if (x + boardLeftMargin + boardWidth - decalage < 0 && deltaX < 0)
                            deltaX = 0;
                        else if (x + boardLeftMargin + decalage > ScreenUtils.getScreenWidth() && deltaX > 0)
                            deltaX = 0;
                    }

                    for(Board board : originalPlateauModulaireSet) {
                        board.setTranslationX(board.getTranslationX() + deltaX);
                        board.setTranslationY(board.getTranslationY() + deltaY);
                    }

                }
                else zoomEffectue = false;

                lastTouchX = event.getRawX();
                lastTouchY = event.getRawY();
                break;
        }
    }

}
