package com.example.veritablejeu.PopUp.ContenuPopUp.SettingsPanel;

import static org.junit.Assert.*;

import android.graphics.Color;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

@RunWith(RobolectricTestRunner.class)
public class ARGBComposerTest {

    @Test
    public void setAlpha_outOfLowerBound() {
        int value = ARGBComposer.getMinValue() - 100;
        int color = Color.argb(50, 50, 50, 50);
        int modified = ARGBComposer.setAlpha(color, value);
        assertEquals(ARGBComposer.getMinValue(), Color.alpha(modified));
    }

    @Test
    public void setAlpha_lowerBound() {
        int value = ARGBComposer.getMinValue();
        int color = Color.argb(50, 50, 50, 50);
        int modified = ARGBComposer.setAlpha(color, value);
        assertEquals(value, Color.alpha(modified));
    }

    @Test
    public void setAlpha_normal() {
        int value = ARGBComposer.getMinValue() + 10;
        int color = Color.argb(50, 50, 50, 50);
        int modified = ARGBComposer.setAlpha(color, value);
        assertEquals(value, Color.alpha(modified));
    }

    @Test
    public void setAlpha_highBound() {
        int value = ARGBComposer.getMaxValue();
        int color = Color.argb(50, 50, 50, 50);
        int modified = ARGBComposer.setAlpha(color, value);
        assertEquals(value, Color.alpha(modified));
    }

    @Test
    public void setAlpha_outOfHighBound() {
        int value = ARGBComposer.getMaxValue() + 100;
        int color = Color.argb(50, 50, 50, 50);
        int modified = ARGBComposer.setAlpha(color, value);
        assertEquals(ARGBComposer.getMaxValue(), Color.alpha(modified));
    }

    @Test
    public void setRed() {
        int value = ARGBComposer.getMinValue() + 10;
        int color = Color.argb(50, 50, 50, 50);
        int modified = ARGBComposer.setRed(color, value);
        assertEquals(value, Color.red(modified));
    }

    @Test
    public void setGreen() {
        int value = ARGBComposer.getMinValue() + 10;
        int color = Color.argb(50, 50, 50, 50);
        int modified = ARGBComposer.setGreen(color, value);
        assertEquals(value, Color.green(modified));
    }

    @Test
    public void setBlue() {
        int value = ARGBComposer.getMinValue() + 10;
        int color = Color.argb(50, 50, 50, 50);
        int modified = ARGBComposer.setBlue(color, value);
        assertEquals(value, Color.blue(modified));
    }
}