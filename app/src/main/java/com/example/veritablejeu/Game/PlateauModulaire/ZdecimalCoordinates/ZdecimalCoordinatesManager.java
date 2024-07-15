package com.example.veritablejeu.Game.PlateauModulaire.ZdecimalCoordinates;

import androidx.annotation.NonNull;

import com.example.veritablejeu.Game.PlateauModulaire.ZdecimalCoordinates.ZdecimalCharacter.ZdecimalCharacterSequencer;

import org.jetbrains.annotations.Contract;

import java.util.ArrayList;

/**
 * This class is a complete toolbox about zdecimal coordinates.
 */
public class ZdecimalCoordinatesManager {

    /**
     * Returns the ZdecimalCoordinates to the left of the given coordinates.
     * <br>
     * Returns null it the given ZdecimalCoordinates is null.
     * <br>
     * Returns null if the given ZdecimalCoordinates is on the left boundary.
     * @param zdecimalCoordinates the original coordinates.
     * @return the coordinates to the left, or null if the input is null.
     */
    @Contract("null -> null; !null -> new")
    public static ZdecimalCoordinates getLeftOf(ZdecimalCoordinates zdecimalCoordinates) {
        if(zdecimalCoordinates == null) {
            return null;
        }
        if(ZdecimalCharacterSequencer.isMinChar(zdecimalCoordinates.getX())) {
            return null;
        }
        return new ZdecimalCoordinates(
                ZdecimalCharacterSequencer.getPreviousChar(zdecimalCoordinates.getX()),
                zdecimalCoordinates.getY()
        );
    }

    /**
     * Returns the ZdecimalCoordinates to the right of the given coordinates.
     * <br>
     * Returns null it the given ZdecimalCoordinates is null.
     * <br>
     * Returns null if the given ZdecimalCoordinates is on the right boundary.
     * @param zdecimalCoordinates the original coordinates.
     * @return the coordinates to the right, or null if the input is null.
     */
    @Contract("null -> null; !null -> new")
    public static ZdecimalCoordinates getRightOf(ZdecimalCoordinates zdecimalCoordinates) {
        if(zdecimalCoordinates == null) {
            return null;
        }
        if(ZdecimalCharacterSequencer.isMaxChar(zdecimalCoordinates.getX())) {
            return null;
        }
        return new ZdecimalCoordinates(
                ZdecimalCharacterSequencer.getNextChar(zdecimalCoordinates.getX()),
                zdecimalCoordinates.getY()
        );
    }

    /**
     * Returns the ZdecimalCoordinates to the top of the given coordinates.
     * <br>
     * Returns null it the given ZdecimalCoordinates is null.
     * <br>
     * Returns null if the given ZdecimalCoordinates is on the top boundary.
     * @param zdecimalCoordinates the original coordinates.
     * @return the coordinates to the top, or null if the input is null.
     */
    @Contract("null -> null; !null -> new")
    public static ZdecimalCoordinates getTopOf(ZdecimalCoordinates zdecimalCoordinates) {
        if(zdecimalCoordinates == null) {
            return null;
        }
        if(ZdecimalCharacterSequencer.isMinChar(zdecimalCoordinates.getY())) {
            return null;
        }
        return new ZdecimalCoordinates(
                zdecimalCoordinates.getX(),
                ZdecimalCharacterSequencer.getPreviousChar(zdecimalCoordinates.getY())
        );
    }

    /**
     * Returns the ZdecimalCoordinates to the bottom of the given coordinates.
     * <br>
     * Returns null it the given ZdecimalCoordinates is null.
     * <br>
     * Returns null if the given ZdecimalCoordinates is on the bottom boundary.
     * @param zdecimalCoordinates the original coordinates.
     * @return the coordinates to the bottom, or null if the input is null.
     */
    @Contract("null -> null; !null -> new")
    public static ZdecimalCoordinates getBottomOf(ZdecimalCoordinates zdecimalCoordinates) {
        if(zdecimalCoordinates == null) {
            return null;
        }
        if(ZdecimalCharacterSequencer.isMaxChar(zdecimalCoordinates.getY())) {
            return null;
        }
        return new ZdecimalCoordinates(
                zdecimalCoordinates.getX(),
                ZdecimalCharacterSequencer.getNextChar(zdecimalCoordinates.getY())
        );
    }



    /**
     * Checks if the 'from' coordinates are on adjacent top of the 'to' coordinates.
     * @param from the starting coordinates.
     * @param to the target coordinates.
     * @return true if 'from' is on adjacent top of 'to', false otherwise.
     */
    @Contract("null, null -> false")
    public static boolean isAdjacentTopOf(ZdecimalCoordinates from, ZdecimalCoordinates to) {
        if(from == null || to == null) {
            return false;
        }
        return from.equals(getTopOf(to));
    }

    /**
     * Checks if the 'from' coordinates are on adjacent bottom of the 'to' coordinates.
     * @param from the starting coordinates.
     * @param to the target coordinates.
     * @return true if 'from' is on adjacent bottom of 'to', false otherwise.
     */
    @Contract("null, null -> false")
    public static boolean isAdjacentBottomOf(ZdecimalCoordinates from, ZdecimalCoordinates to) {
        if(from == null || to == null) {
            return false;
        }
        return from.equals(getBottomOf(to));
    }

    /**
     * Checks if the 'from' coordinates are on adjacent left of the 'to' coordinates.
     * @param from the starting coordinates.
     * @param to the target coordinates.
     * @return true if 'from' is on adjacent left of 'to', false otherwise.
     */
    @Contract("null, null -> false")
    public static boolean isAdjacentLeftOf(ZdecimalCoordinates from, ZdecimalCoordinates to) {
        if(from == null || to == null) {
            return false;
        }
        return from.equals(getLeftOf(to));
    }

    /**
     * Checks if the 'from' coordinates are on adjacent right of the 'to' coordinates.
     * @param from the starting coordinates.
     * @param to the target coordinates.
     * @return true if 'from' is on adjacent right of 'to', false otherwise.
     */
    @Contract("null, null -> false")
    public static boolean isAdjacentRightOf(ZdecimalCoordinates from, ZdecimalCoordinates to) {
        if(from == null || to == null) {
            return false;
        }
        return from.equals(getRightOf(to));
    }



    /**
     * Checks if the 'from' coordinates are on top of the 'to' coordinates.
     * @param from the starting coordinates.
     * @param to the target coordinates.
     * @return true if 'from' is on top of 'to', false otherwise.
     */
    public static boolean isOnTopOf(ZdecimalCoordinates from, ZdecimalCoordinates to) {
        if(from == null || to == null) {
            return false;
        }
        return ZdecimalCharacterSequencer.isLowerTo(from.getY(), to.getY());
    }

    /**
     * Checks if the 'from' coordinates are on the left of the 'to' coordinates.
     * @param from the starting coordinates.
     * @param to the target coordinates.
     * @return true if 'from' is on the left of 'to', false otherwise.
     */
    public static boolean isOnLeftOf(ZdecimalCoordinates from, ZdecimalCoordinates to) {
        if(from == null || to == null) {
            return false;
        }
        return ZdecimalCharacterSequencer.isLowerTo(from.getX(), to.getX());
    }

    /**
     * Checks if the 'from' coordinates are on the bottom of the 'to' coordinates.
     * @param from the starting coordinates.
     * @param to the target coordinates.
     * @return true if 'from' is on the bottom of 'to', false otherwise.
     */
    public static boolean isOnBottomOf(ZdecimalCoordinates from, ZdecimalCoordinates to) {
        if(from == null || to == null) {
            return false;
        }
        return ZdecimalCharacterSequencer.isHigherTo(from.getY(), to.getY());
    }

    /**
     * Checks if the 'from' coordinates are on the right of the 'to' coordinates.
     * @param from the starting coordinates.
     * @param to the target coordinates.
     * @return true if 'from' is on the right of 'to', false otherwise.
     */
    public static boolean isOnRightOf(ZdecimalCoordinates from, ZdecimalCoordinates to) {
        if(from == null || to == null) {
            return false;
        }
        return ZdecimalCharacterSequencer.isHigherTo(from.getX(), to.getX());
    }



    /**
     * Returns the absolute distance between the x-coordinates of two ZdecimalCoordinates.
     * @param from the starting coordinates.
     * @param to the target coordinates.
     * @return the absolute distance between the x-coordinates.
     */
    public static int getXdistanceBetween(ZdecimalCoordinates from, ZdecimalCoordinates to) {
        if(from == null || to == null) {
            return 0;
        }
        return ZdecimalCharacterSequencer.getAbsoluteGapBetween(from.getX(), to.getX());
    }

    /**
     * Returns the absolute distance between the y-coordinates of two ZdecimalCoordinates.
     * @param from the starting coordinates.
     * @param to the target coordinates.
     * @return the absolute distance between the y-coordinates.
     */
    public static int getYdistanceBetween(ZdecimalCoordinates from, ZdecimalCoordinates to) {
        if(from == null || to == null) {
            return 0;
        }
        return ZdecimalCharacterSequencer.getAbsoluteGapBetween(from.getY(), to.getY());
    }

    /**
     * Returns the Euclidean distance between two ZdecimalCoordinates.
     * @param from the starting coordinates.
     * @param to the target coordinates.
     * @return the Euclidean distance between the coordinates.
     */
    public static double getDistanceBetween(ZdecimalCoordinates from, ZdecimalCoordinates to) {
        if(from == null || to == null) {
            return 0;
        }
        int xGap = getXdistanceBetween(from, to);
        int yGap = getYdistanceBetween(from, to);
        return Math.sqrt(Math.pow(xGap, 2) + Math.pow(yGap, 2));
    }



    /**
     * Determines the most logical direction to move from one coordinate to another.
     * <br>
     * It returns an array of coordinates prioritizing vertical or horizontal movements based on the gap.
     *
     * @param from the starting coordinates
     * @param to the target coordinates
     * @return a list of ZdecimalCoordinates representing the logical directions to follow
     */
    @NonNull
    @Contract("_, _ -> new")
    public static ArrayList<ZdecimalCoordinates> getMoreLogicalDirection(ZdecimalCoordinates from, ZdecimalCoordinates to) {
        if(from == null || to == null) {
            return new ArrayList<>();
        }

        boolean isOnTop = isOnTopOf(to, from);
        boolean isOnLeft = isOnLeftOf(to, from);

        ZdecimalCoordinates topCord = getTopOf(from);
        ZdecimalCoordinates bottomCord = getBottomOf(from);
        ZdecimalCoordinates leftCord = getLeftOf(from);
        ZdecimalCoordinates rightCord = getRightOf(from);

        ZdecimalCoordinates verticalPriority = isOnTop ? topCord : bottomCord;
        ZdecimalCoordinates verticalMinority = isOnTop ? bottomCord : topCord;
        ZdecimalCoordinates horizontalPriority = isOnLeft ? leftCord : rightCord;
        ZdecimalCoordinates horizontalMinority = isOnLeft ? rightCord : leftCord;

        int xGap = getXdistanceBetween(from, to);
        int yGap = getYdistanceBetween(from, to);
        boolean priorityToVerticality = yGap >= xGap;

        ZdecimalCoordinates[] intermadiateList;
        if(priorityToVerticality) {
            intermadiateList = new ZdecimalCoordinates[]{ verticalPriority, horizontalPriority, horizontalMinority, verticalMinority };
        } else {
            intermadiateList = new ZdecimalCoordinates[]{ horizontalPriority,verticalPriority, verticalMinority, horizontalMinority };
        }

        ArrayList<ZdecimalCoordinates> finalList = new ArrayList<>();
        for(ZdecimalCoordinates coordinates : intermadiateList) {
            if(coordinates != null) {
                finalList.add(coordinates);
            }
        }
        return finalList;
    }

}
