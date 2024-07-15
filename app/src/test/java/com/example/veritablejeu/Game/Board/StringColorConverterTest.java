package com.example.veritablejeu.Game.Board;

import static org.junit.Assert.*;

import android.graphics.Color;

import com.example.veritablejeu.Tools.StringColorConverter;

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
}