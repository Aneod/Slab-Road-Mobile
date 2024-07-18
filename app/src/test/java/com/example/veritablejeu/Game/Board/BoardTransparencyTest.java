package com.example.veritablejeu.Game.Board;

import static org.junit.Assert.*;

import org.junit.Test;

public class BoardTransparencyTest {

    BoardTransparency boardTransparency = new BoardTransparency();

    @Test
    public void getTransparency_default() {
        float actual = boardTransparency.getTransparency();
        float expected = BoardTransparency.getDefaultValue();
        assertEquals(expected, actual, 0);
    }

    @Test
    public void setTransparency_normal() {
        float value = .5f;
        boardTransparency.setTransparency(null, value);
        assertEquals(value, boardTransparency.getTransparency(), 0);
    }

    @Test
    public void setTransparency_outOfLowerBound() {
        float value = BoardTransparency.getMinValue() - 1;
        boardTransparency.setTransparency(null, value);
        assertEquals(BoardTransparency.getMinValue(), boardTransparency.getTransparency(), 0);
    }

    @Test
    public void setTransparency_lowerBound() {
        float value = BoardTransparency.getMinValue();
        boardTransparency.setTransparency(null, value);
        assertEquals(value, boardTransparency.getTransparency(), 0);
    }

    @Test
    public void setTransparency_higherBound() {
        float value = BoardTransparency.getMaxValue();
        boardTransparency.setTransparency(null, value);
        assertEquals(value, boardTransparency.getTransparency(), 0);
    }

    @Test
    public void setTransparency_outOfHigherBound() {
        float value = BoardTransparency.getMaxValue() + 1;
        boardTransparency.setTransparency(null, value);
        assertEquals(BoardTransparency.getMaxValue(), boardTransparency.getTransparency(), 0);
    }
}