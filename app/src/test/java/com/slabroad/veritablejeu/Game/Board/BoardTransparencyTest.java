package com.slabroad.veritablejeu.Game.Board;

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

    @Test
    public void convertStringToPorcentage_simple() {
        String toConvert = "10";
        float actual = boardTransparency.convertStringToPorcentage(toConvert);
        float expected = .36f;
        assertEquals(expected, actual, 0);
        float noBullShit = .35f;
        assertNotEquals(noBullShit, actual, 0);
    }

    @Test
    public void convertStringToPorcentage_notValid_longLength() {
        String toConvert = "-10"; // More than 2 => 1.0f
        float actual = boardTransparency.convertStringToPorcentage(toConvert);
        float expected = 1.0f;
        assertEquals(expected, actual, 0);
    }

    @Test
    public void convertStringToPorcentage_notValid() {
        String toConvert = "-1"; // '-' => 0
        float actual = boardTransparency.convertStringToPorcentage(toConvert);
        float expected = .01f;
        assertEquals(expected, actual, 0);
        float noBullShit = .0f;
        assertNotEquals(noBullShit, actual, 0);
        float noBullShit2 = .02f;
        assertNotEquals(noBullShit2, actual, 0);
    }

    @Test
    public void convertStringToPorcentage_lowerBound() {
        String toConvert = "0";
        float actual = boardTransparency.convertStringToPorcentage(toConvert);
        float expected = .0f;
        assertEquals(expected, actual, 0);
    }

    @Test
    public void convertStringToPorcentage_higherBound() {
        String toConvert = "2s";
        float actual = boardTransparency.convertStringToPorcentage(toConvert);
        float expected = 1.0f;
        assertEquals(expected, actual, 0);
    }

    @Test
    public void convertStringToPorcentage_outOfHigherBound_toMaxValue() {
        String toConvert = "2t";
        float actual = boardTransparency.convertStringToPorcentage(toConvert);
        float expected = BoardTransparency.getMaxValue();
        assertEquals(expected, actual, 0);
    }

    @Test
    public void convertStringToPorcentage_null_defaultValue() {
        String toConvert = null;
        float actual = boardTransparency.convertStringToPorcentage(toConvert);
        float expected = BoardTransparency.getDefaultValue();
        assertEquals(expected, actual, 0);
    }

    @Test
    public void convertStringToPorcentage_empty_isLike0() {
        String toConvert = "";
        float actual = boardTransparency.convertStringToPorcentage(toConvert);
        float expected = BoardTransparency.getMinValue();
        assertEquals(expected, actual, 0);
    }

    @Test
    public void getTransparencyToTwoChar_36() {
        float value = .36f;
        boardTransparency.setTransparency(null, value);
        String actual = boardTransparency.getTransparencyToTwoChar();
        String expected = "10";
        assertEquals(expected, actual);
    }

    @Test
    public void getTransparencyToTwoChar_10_just_a() {
        float value = .10f;
        boardTransparency.setTransparency(null, value);
        String actual = boardTransparency.getTransparencyToTwoChar();
        String expected = "a";
        assertEquals(expected, actual);
    }

    @Test
    public void getTransparencyToTwoChar_52() {
        float value = .52f;
        boardTransparency.setTransparency(null, value);
        String actual = boardTransparency.getTransparencyToTwoChar();
        String expected = "1g";
        assertEquals(expected, actual);
    }

    @Test
    public void getTransparencyToTwoChar_53() {
        // IMPRECISION : 1g au lieu de 1h.
        // L'explication vient de la methode qui passe de float a int.
        // C'est normal et pas significatif comme changement.
        float value = .53f;
        boardTransparency.setTransparency(null, value);
        String actual = boardTransparency.getTransparencyToTwoChar();
        String expected = "1g";
        assertEquals(expected, actual);
    }

    @Test
    public void getTransparencyToTwoChar_54() {
        float value = .54f;
        boardTransparency.setTransparency(null, value);
        String actual = boardTransparency.getTransparencyToTwoChar();
        String expected = "1i";
        assertEquals(expected, actual);
    }

    @Test
    public void getTransparencyToTwoChar_100() {
        float value = 1.0f;
        boardTransparency.setTransparency(null, value);
        String actual = boardTransparency.getTransparencyToTwoChar();
        String expected = "2s";
        assertEquals(expected, actual);
    }

    @Test
    public void getTransparencyToTwoChar_99() {
        float value = .99f;
        boardTransparency.setTransparency(null, value);
        String actual = boardTransparency.getTransparencyToTwoChar();
        String expected = "2r";
        assertEquals(expected, actual);
    }

    @Test
    public void getTransparencyToTwoChar_0_andNot_00() {
        float value = 0.0f;
        boardTransparency.setTransparency(null, value);
        String actual = boardTransparency.getTransparencyToTwoChar();
        String expected = "0";
        assertEquals(expected, actual);
    }
}