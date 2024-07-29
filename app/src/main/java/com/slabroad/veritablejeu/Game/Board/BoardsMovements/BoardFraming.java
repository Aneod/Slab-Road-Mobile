package com.slabroad.veritablejeu.Game.Board.BoardsMovements;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.slabroad.veritablejeu.Game.Board.Board;
import com.slabroad.veritablejeu.Tools.ScreenUtils;

import org.jetbrains.annotations.Contract;

/**
 * The goal if this static class is to resize and reposition a {@link Board}.
 */
public class BoardFraming {

    private static final float MAX_WIDTH_PORCENTAGE = .9f;
    private static final float MAX_HEIGHT_PORCENTAGE = .7f;
    private static final float MAX_BOARD_WIDTH = ScreenUtils.getScreenWidth() * MAX_WIDTH_PORCENTAGE;
    private static final float MAX_BOARD_HEIGHT = ScreenUtils.getScreenHeight() * MAX_HEIGHT_PORCENTAGE;
    private static final float HORIZONTAL_AUTORIZED_MARGIN = (ScreenUtils.getScreenWidth() - MAX_BOARD_WIDTH) / 2;
    private static final float VERTICAL_AUTORIZED_MARGIN = (ScreenUtils.getScreenHeight() - MAX_BOARD_HEIGHT) / 2;

    /**
     * Resize and translate a {@link Board} by {@link Preset}.
     * @param board the {@link Board} to resize and translate.
     * @param preset the {@link Preset} who decide how 'board' will be resized and translated.
     */
    public static void sizeAndPositionBoard(Board board, @Nullable Preset preset) {
        if(board == null) {
            return;
        }
        if(preset == null) {
            sizeAndPositionBoard_default(board);
        } else {
            sizeAndPositionBoard(board, preset.screenWidthAutorized, preset.screenHeightAutorized, preset.location);
        }
    }

    /**
     * Resize and translate a {@link Board} at the center of the screen and with the maximal dimensions.
     * @param board the {@link Board} the resize and translate.
     */
    private static void sizeAndPositionBoard_default(Board board) {
        sizeAndPositionBoard(board, MAX_BOARD_WIDTH, MAX_BOARD_HEIGHT, Location.center);
    }

    /**
     * Resize and translate a {@link Board} at a specific {@link Location} and with a specific dimensions.
     * @param board the {@link Board} the resize and translate.
     */
    private static void sizeAndPositionBoard(Board board, float screenWidthAutorized, float screenHeightAutorized, Location location) {
        BoardFraming.resetMargins(board);
        BoardFraming.reScale(board, screenWidthAutorized, screenHeightAutorized);
        BoardFraming.repositionAt_SpecificLocation(board, location);
        BoardFraming.translateToNearestMargins(board);
    }



    /**
     * You must use this method <u>before all others</u> in this class. Because all others methods
     * depends of the originals margins, who must be at 0 for a good use.
     * @param board the {@link Board} to reset margins.
     */
    private static void resetMargins(Board board) {
        if(board == null) {
            return;
        }
        ConstraintLayout.LayoutParams layoutParams = board.getLayoutParams();
        if(layoutParams == null) {
            return;
        }
        layoutParams.leftMargin = 0;
        layoutParams.topMargin = 0;
    }



    /**
     * You must <u>reset margins</u> before to use this method !
     * <p>
     * For reset margins, see : {@link #resetMargins(Board)}
     * </p>
     * <br>
     * Scale a {@link Board} for contain it in the givens dimensions.
     * @param board the {@link Board} to scale.
     * @param newBoardWidth how many horizontal pixels in the screen must contain the {@link Board}.
     * @param newBoardHeight how many verticle pixels in the screen must contain the {@link Board}.
     */
    private static void reScale(Board board, float newBoardWidth, float newBoardHeight) {
        if(board == null) {
            return;
        }
        ConstraintLayout.LayoutParams layoutParams = board.getLayoutParams();
        if(layoutParams == null) {
            return;
        }
        float autorizedWidth = Math.min(newBoardWidth, MAX_BOARD_WIDTH);
        float autorizedHeight = Math.min(newBoardHeight, MAX_BOARD_HEIGHT);
        float xScaleValue = autorizedWidth / board.getWidthToShow();
        float yScaleValue = autorizedHeight / board.getHeightToShow();
        float scaleValue = Math.min(xScaleValue, yScaleValue);
        board.setScaleX(scaleValue);
        board.setScaleY(scaleValue);

        int originalWidth = layoutParams.width;
        float scaledWidth = originalWidth * scaleValue;
        float xTranslationByXScale = (originalWidth - scaledWidth) / 2;

        int originalHeight = layoutParams.height;
        float scaledHeight = originalHeight * scaleValue;
        float yTranslationByYScale = (originalHeight - scaledHeight) / 2;

        layoutParams.leftMargin -= (int) xTranslationByXScale;
        layoutParams.topMargin -= (int) yTranslationByYScale;
        board.setLayoutParams(layoutParams);
    }



    /**
     * You must <u>reset margins</u> and <u>scale the View</u> before to use this method !
     * <p>
     * For reset margins, see : {@link #resetMargins(Board)}
     * <br>
     * For scale the View, see : {@link #reScale(Board, float, float)}
     * </p>
     * <br>
     * By default, the {@link Board} margins are at x and y. But the squares aren't at this coordinates.
     * This method modify the {@link Board} margins for have the firsts horizontal and vertical squares
     * positioned at the x and y margins.
     * @param board the {@link Board} whose margins are moved.
     */
    private static void translateToNearestMargins(Board board) {
        if(board == null) {
            return;
        }
        ConstraintLayout.LayoutParams layoutParams = board.getLayoutParams();
        if(layoutParams == null) {
            return;
        }
        float scaleX = board.getScaleX();
        float scaleY = board.getScaleY();
        float dx = board.getNearestLeftMargin() * scaleX;
        float dy = board.getNearestTopMargin() * scaleY;

        layoutParams.leftMargin -= (int) dx;
        layoutParams.topMargin -= (int) dy;
        board.setLayoutParams(layoutParams);
    }



    /**
     * You must <u>reset margins</u> and <u>scale the View</u> before to use this method !
     * <p>
     * For reset margins, see : {@link #resetMargins(Board)}
     * <br>
     * For scale the View, see : {@link #reScale(Board, float, float)}
     * </p>
     * <br>
     * @param board the {@link Board} to reposition.
     * @param location which {@link Location} to give at the given {@link Board}.
     */
    private static void repositionAt_SpecificLocation(Board board, Location location) {
        if(location == null) return;
        location.positionTheView(board);
    }



    /**
     * This class is designed to position a {@link Board} on many screen emplacement posibilities like
     * top-left, center-right or bottom-center.
     */
    public enum Location {
        topLeft, topCenter, topRight, centerRight, bottomRight, bottomCenter, bottomLeft, centerLeft, center;

        private static final int DEFAULT_NUM_LOCATION = 0;
        private static final Location DEFAULT_LOCATION = getLocationOfNum(DEFAULT_NUM_LOCATION);

        /**
         * Get a {@link Location} object by a single int is useful for compose a String code.
         */
        public static Location getLocationOfNum(int num) {
            switch (num) {
                case 0: return center;
                case 1: return topLeft;
                case 2: return topCenter;
                case 3: return topRight;
                case 4: return centerRight;
                case 5: return bottomRight;
                case 6: return bottomCenter;
                case 7: return bottomLeft;
                case 8: return centerLeft;
            }
            return DEFAULT_LOCATION;
        }

        /**
         * Apply the location to the given {@link Board}.
         * @param board the {@link Board} to position.
         */
        public void positionTheView(Board board) {
            if(board == null) {
                return;
            }
            ConstraintLayout.LayoutParams layoutParams = board.getLayoutParams();
            if(layoutParams == null) {
                return;
            }

            if(this == topLeft || this == bottomLeft || this == centerLeft) {
                layoutParams.leftMargin += getPositionToLeft();
            }
            if(this == topCenter || this == bottomCenter || this == center) {
                layoutParams.leftMargin += getPositionToHorizontalCenter(board);
            }
            if(this == topRight || this == bottomRight || this == centerRight) {
                layoutParams.leftMargin += getPositionToRight(board);
            }

            if(this == topLeft || this == topRight || this == topCenter) {
                layoutParams.topMargin += getPositionToTop();
            }
            if(this == centerLeft || this == centerRight || this == center) {
                layoutParams.topMargin += getPositionToVerticalCenter(board);
            }
            if(this == bottomLeft || this == bottomRight || this == bottomCenter) {
                layoutParams.topMargin += getPositionToBottom(board);
            }
        }

        /**
         * Get left.
         */
        private int getPositionToLeft() {
            return (int) HORIZONTAL_AUTORIZED_MARGIN;
        }

        /**
         * Get horizontal center.
         */
        private int getPositionToHorizontalCenter(Board board) {
            if(board == null) return 0;
            int visibleWidth = (int) (board.getWidthToShow() * board.getScaleX());
            float leftMargin = (ScreenUtils.getScreenWidth() - visibleWidth) / 2.0f;
            return (int) leftMargin;
        }

        /**
         * Get right.
         */
        private int getPositionToRight(Board board) {
            if(board == null) return 0;
            int visibleWidth = (int) (board.getWidthToShow() * board.getScaleX());
            float leftMargin = ScreenUtils.getScreenWidth() - HORIZONTAL_AUTORIZED_MARGIN - visibleWidth;
            return (int) leftMargin;
        }

        /**
         * Get top.
         */
        private int getPositionToTop() {
            return (int) VERTICAL_AUTORIZED_MARGIN;
        }

        /**
         * Get vertical center.
         */
        private int getPositionToVerticalCenter(Board board) {
            if(board == null) return 0;
            int visibleHeight = (int) (board.getHeightToShow() * board.getScaleY());
            float topMargin = (ScreenUtils.getScreenHeight() - visibleHeight) / 2.0f;
            return (int) topMargin;
        }

        /**
         * Get bottom.
         */
        private int getPositionToBottom(Board board) {
            if(board == null) return 0;
            int visibleHeight = (int) (board.getHeightToShow() * board.getScaleY());
            float topMargin = ScreenUtils.getScreenHeight() - VERTICAL_AUTORIZED_MARGIN - visibleHeight;
            return (int) topMargin;
        }

    }



    /**
     * This class is a data container for the {@link Board} size and {@link Location} choice.
     * <br>
     * In the class who create the {@link Board}, a {@link Preset} is created for decide
     * by which parameters it will be sized and positioned.
     */
    public static class Preset {

        private final float screenWidthAutorized;
        private final float screenHeightAutorized;
        private final Location location;

        public Preset(float widthPorcentage, float heightPorcentage, int numLocation) {
            screenWidthAutorized = ScreenUtils.getScreenWidth() * widthPorcentage;
            screenHeightAutorized = ScreenUtils.getScreenHeight() * heightPorcentage;
            location = BoardFraming.Location.getLocationOfNum(numLocation);
        }

        public Preset() {
            this(MAX_WIDTH_PORCENTAGE, MAX_HEIGHT_PORCENTAGE, Location.DEFAULT_NUM_LOCATION);
        }

        /**
         * The code for compose a Preset must take this shape : wwwhhhL.
         * <p>
         * 'www' -> w.wwf for widthPorcentage.
         * <br>
         * 'hhh' -> h.hhf for heightPorcentage.
         * <br>
         * 'L' -> the numLocation. Check {@link Location#getLocationOfNum(int)}.
         * </p>
         * @param code the code for by which create a {@link Preset}.
         * @return a new {@link Preset} with the paramters given by the code.
         */
        @NonNull
        @Contract("null, null -> new")
        public static Preset createPreset_byCode(Board board, String code) {
            if(board == null || code == null || code.length() != 7) {
                return new Preset();
            }

            String www = code.substring(0, 3);
            String hhh = code.substring(3, 6);
            String L = code.substring(6, 7);

            float widthPorcentage = Integer.parseInt(www) / 100.0f;
            float heightPorcentage = Integer.parseInt(hhh) / 100.0f;
            int numLocation = Integer.parseInt(L);

            return new Preset(widthPorcentage, heightPorcentage, numLocation);
        }

    }

}
