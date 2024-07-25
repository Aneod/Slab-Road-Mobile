package com.example.veritablejeu.Game.Board.BoardElement.Square.WallsOfSquare;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.veritablejeu.Game.Board.BoardElement.Square.ModularSquare;
import com.example.veritablejeu.Game.Board.BoardElement.Square.WallsOfSquare.Wall.ModularWall;

import java.util.Collection;
import java.util.EnumMap;

/**
 * This class is designed to manage the group of four walls around a squares.
 * <p>
 *     The <u>three most classical</u> method are SET, GET, and IS.
 *     <br>
 *     SET a wall of {@link ModularSquare} at {@link Direction}. Can override.
 *     <br>
 *     GET the wall of {@link ModularSquare} at {@link Direction}.
 *     <br>
 *     IS there a wall of {@link ModularSquare} at {@link Direction}.
 * </p>
 */
public class WallsOfSquare {

    private final ModularSquare modularSquare;
    private final EnumMap<Direction, ModularWall> wallMap = new EnumMap<>(Direction.class);


    private void addNeighborWall(Direction direction, ModularSquare neighborSquare, Direction oppositeDirection) {
        if (neighborSquare != null) {
            ModularWall neighborWall = neighborSquare.getWalls().get(oppositeDirection);
            if (neighborWall != null) {
                set(direction, neighborWall);
            }
        }
    }

    private void addNeighborWalls() {
        addNeighborWall(Direction.Top, modularSquare.getSquareOfTop(), Direction.Bottom);
        addNeighborWall(Direction.Left, modularSquare.getSquareOfLeft(), Direction.Right);
        addNeighborWall(Direction.Bottom, modularSquare.getSquareOfBottom(), Direction.Top);
        addNeighborWall(Direction.Right, modularSquare.getSquareOfRight(), Direction.Left);
    }

    /**
     * <h3>How to work the squares walls system ?</h3>
     * The idea, is to add two walls for each ones. Because the GET methods are used a lot.
     * And the SET methods are used just at the creation of the board.
     * <br>
     * It's more economic to add ALL walls at the creation.
     * <p>
     * At the intialization, the class add all neighbors walls around the square with {@link #addNeighborWalls()}.
     * <br>
     * These first added walls can't will be overrided.
     * <br><br>
     * After, it's possible to add the walls on the square. In that case, the neighbor, if exists, will also receive the new wall.
     * </p>
     * <br>
     * With this strategy, when the user want to get a wall, only one square is necessary to check.
     *
     * @param modularSquare the {@link ModularSquare} of this group of walls.
     */
    public WallsOfSquare(@NonNull ModularSquare modularSquare) {
        this.modularSquare = modularSquare;
        addNeighborWalls();
    }


    /**
     * Put a couple of <{@link Direction}, {@link ModularWall}> in the EnumMap wallMap.
     * <br>
     * <u>Can't override</u>.
     * <br>
     * Add a wall here and also for the neighbor.
     * @param direction the {@link Direction} of the wall.
     * @param modularWall the {@link ModularWall} to add.
     */
    public void set(Direction direction, @Nullable ModularWall modularWall) {
        if(direction == null || get(direction) != null) {
            return;
        }
        wallMap.put(direction, modularWall);

        ModularSquare neighbor = direction.getNeighbor(modularSquare);
        Direction opposite = direction.getOpposite();
        if(neighbor != null) {
            neighbor.getWalls().set(opposite, modularWall);
        }
    }

    public void setByCode(Direction direction, String code) {
        ModularWall modularWall = ModularWall.create(modularSquare, direction, code);
        set(direction, modularWall);
    }

    public void remove(Direction direction) {
        if(get(direction) == null) {
            return;
        }
        wallMap.remove(direction);
        ModularSquare neighbor = direction.getNeighbor(modularSquare);
        Direction opposite = direction.getOpposite();
        if(neighbor != null) {
            neighbor.getWalls().remove(opposite);
        }
    }

    /**
     * <u>Don't verify if the neighbor have a wall</u>. Just returns the wall of the given {@link Direction}.
     * @param direction the {@link Direction} of the desired wall.
     * @return the only wall of the direction.
     */
    public ModularWall get(Direction direction) {
        return wallMap.get(direction);
    }

    public Collection<ModularWall> getAll() {
        return wallMap.values();
    }


    public enum Direction {
        Top, Left, Bottom, Right;

        private static final Direction DEFAULT = Bottom;

        public static Direction getOfChar(char c) {
            switch (c) {
                case 't' : return Direction.Top;
                case 'l' : return Direction.Left;
                case 'b' : return Direction.Bottom;
                case 'r' : return Direction.Right;
                default: return DEFAULT;
            }
        }

        public char getChar() {
            switch (this) {
                case Top: return 't';
                case Left: return 'l';
                case Bottom: return 'b';
                default: return 'r';
            }
        }

        @Nullable
        public ModularSquare getNeighbor(ModularSquare modularSquare) {
            switch (this) {
                case Top: return modularSquare.getSquareOfTop();
                case Left: return modularSquare.getSquareOfLeft();
                case Bottom: return modularSquare.getSquareOfBottom();
                case Right: return modularSquare.getSquareOfRight();
            }
            return null;
        }

        public Direction getOpposite() {
            switch (this) {
                case Top: return Bottom;
                case Left: return Right;
                case Bottom: return Top;
                case Right: return Left;
                default: return DEFAULT;
            }
        }
    }

}
