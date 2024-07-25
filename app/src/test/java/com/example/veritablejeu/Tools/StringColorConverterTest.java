package com.example.veritablejeu.Tools;

import static org.junit.Assert.*;

import android.graphics.Color;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

@RunWith(RobolectricTestRunner.class)
public class StringColorConverterTest {

    @Test
    public void turnIntoColor_WHITE() {
        String toConvert = "ffffff";
        int converted = StringColorConverter.turnIntoColor(toConvert);
        assertEquals(Color.WHITE, converted);
    }

    @Test
    public void turnIntoColor_RED() {
        String toConvert = "ff0000";
        int converted = StringColorConverter.turnIntoColor(toConvert);
        assertEquals(Color.RED, converted);
    }

    @Test
    public void turnIntoColor_null() {
        String toConvert = null;
        int converted = StringColorConverter.turnIntoColor(toConvert);
        assertEquals(StringColorConverter.getDefaultColor(), converted);
    }

    @Test
    public void turnIntoColor_empty() {
        String toConvert = null;
        int converted = StringColorConverter.turnIntoColor(toConvert);
        assertEquals(StringColorConverter.getDefaultColor(), converted);
    }

    @Test
    public void turnIntoColor_5characters() {
        String toConvert = "ffffO";
        int converted = StringColorConverter.turnIntoColor(toConvert);
        assertEquals(StringColorConverter.getDefaultColor(), converted);
    }

    @Test
    public void turnIntoColor_7characters() {
        String toConvert = "ffffffO";
        int converted = StringColorConverter.turnIntoColor(toConvert);
        assertEquals(StringColorConverter.getDefaultColor(), converted);
    }

    @Test
    public void turnIntoColor_uncorrectCharacters() {
        String toConvert = "ff@000";
        int converted = StringColorConverter.turnIntoColor(toConvert);
        assertEquals(StringColorConverter.getDefaultColor(), converted);
    }

    @Test
    public void turnIntoColor_halfUpperCaseLetter() {
        String toConvert = "ccCCcC";
        int converted = StringColorConverter.turnIntoColor(toConvert);
        assertEquals(Color.LTGRAY, converted);
    }

    @Test
    public void turnIntoColor_fullUpperCaseLetter() {
        String toConvert = "CCCCCC";
        int converted = StringColorConverter.turnIntoColor(toConvert);
        assertEquals(Color.LTGRAY, converted);
    }

    @Test
    public void turnIntoColors_justeOne() {
        String toConvert = "CCCCCC";
        int[] converted = StringColorConverter.turnIntoColors(toConvert);
        assertArrayEquals(new int[]{Color.LTGRAY}, converted);
    }

    @Test
    public void turnIntoColors_3Colors() {
        String toConvert = "ff000000ff000000FF";
        int[] converted = StringColorConverter.turnIntoColors(toConvert);
        assertArrayEquals(new int[]{Color.RED, Color.GREEN, Color.BLUE}, converted);
    }

    @Test
    public void turnIntoColors_3Colors_butTheSecondIsUncorrect() {
        String toConvert = "ff00000@ff000000FF";
        int[] converted = StringColorConverter.turnIntoColors(toConvert);
        assertArrayEquals(new int[]{Color.RED, StringColorConverter.getDefaultColor(), Color.BLUE}, converted);
    }

    @Test
    public void turnIntoColors_empty() {
        String toConvert = "";
        int[] converted = StringColorConverter.turnIntoColors(toConvert);
        assertArrayEquals(new int[]{}, converted);
    }

    @Test
    public void turnIntoColors_null() {
        String toConvert = null;
        int[] converted = StringColorConverter.turnIntoColors(toConvert);
        assertArrayEquals(new int[]{}, converted);
    }

    @Test
    public void turnIntoCode_justOne() {
        int theOne = 0xFFFF0000;
        String actual = StringColorConverter.turnIntoCode(theOne);
        String expected = "ff0000";
        assertEquals(expected, actual);
    }

    @Test
    public void turnIntoCode_twoColors() {
        int one = Color.RED;
        int two = Color.BLACK;
        String actual = StringColorConverter.turnIntoCode(one, two);
        String expected = "ff0000000000";
        assertEquals(expected, actual);
    }

    @Test
    public void turnIntoCode_empty() {
        String actual = StringColorConverter.turnIntoCode();
        String expected = "";
        assertEquals(expected, actual);
    }

    @Test
    public void turnIntoCode_null() {
        String actual = StringColorConverter.turnIntoCode(null);
        String expected = "";
        assertEquals(expected, actual);
    }

    @Test
    public void turnIntoCode_single_red() {
        int color = Color.RED;
        String actual = StringColorConverter.turnIntoCode_single(color);
        String expected = "ff0000";
        assertEquals(expected, actual);
    }

    @Test
    public void turnIntoCode_single_black() {
        int color = Color.BLACK;
        String actual = StringColorConverter.turnIntoCode_single(color);
        String expected = "000000";
        assertEquals(expected, actual);
    }

    @Test
    public void turnIntoCode_single_alphaDeleted() {
        int color = 0x55FFFFFF;
        String actual = StringColorConverter.turnIntoCode_single(color);
        String expected = "ffffff";
        assertEquals(expected, actual);
    }

    @Test
    public void turnIntoCode_single_1_turnInto_01() {
        int color = 0xFF010900;
        String actual = StringColorConverter.turnIntoCode_single(color);
        String expected = "010900";
        assertEquals(expected, actual);
    }
}