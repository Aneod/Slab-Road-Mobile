package com.example.veritablejeu.Game.Board.ZdecimalCoordinates;

import androidx.annotation.NonNull;

import com.example.veritablejeu.Game.Board.ZdecimalCoordinates.ZdecimalCharacter.ZdecimalCharacter;

import java.util.Objects;

public class ZdecimalCoordinates {

    private static final ZdecimalCharacter DEFAULT_CHAR = new ZdecimalCharacter('0');
    public static ZdecimalCharacter getDefaultChar() {
        return DEFAULT_CHAR;
    }

    private final ZdecimalCharacter x;
    private final ZdecimalCharacter y;

    /**
     * Constructs a new ZdecimalCoordinates with specified x and y values.
     * @param x the x coordinate, must be a valid ZdecimalCharacter
     * @param y the y coordinate, must be a valid ZdecimalCharacter
     */
    public ZdecimalCoordinates(ZdecimalCharacter x, ZdecimalCharacter y) {
        this.x = x == null ? DEFAULT_CHAR : x;
        this.y = y == null ? DEFAULT_CHAR : y;
    }

    /**
     * Constructs a new ZdecimalCoordinates with specified x and y characters.
     *
     * @param x the x character
     * @param y the y character
     */
    public ZdecimalCoordinates(char x, char y) {
        this(new ZdecimalCharacter(x), new ZdecimalCharacter(y));
    }

    /**
     * Constructs a new ZdecimalCoordinates with specified x and y characters.
     *
     * @param x the x int
     * @param y the y int
     */
    public ZdecimalCoordinates(int x, int y) {
        this(new ZdecimalCharacter(x), new ZdecimalCharacter(y));
    }

    /**
     * Get the horizontal cordinate, in zdecimal.
     * @return a zdecimal char.
     */
    public ZdecimalCharacter getX() {
        return x;
    }

    /**
     * Get the vertical cordinate, in zdecimal.
     * @return a zdecimal char.
     */
    public ZdecimalCharacter getY() {
        return y;
    }

    @NonNull
    @Override
    public String toString() {
        return "ZdecimalCoordinates{x=" + x.getCharacter() + ", y=" + y.getCharacter() + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ZdecimalCoordinates that = (ZdecimalCoordinates) o;
        return x.equals(that.x) && y.equals(that.y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

}
