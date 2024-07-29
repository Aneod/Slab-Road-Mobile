package com.slabroad.veritablejeu.PopUp;

import static org.junit.Assert.*;

import android.content.Context;
import android.view.View;

import androidx.test.core.app.ApplicationProvider;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

@RunWith(RobolectricTestRunner.class)
public class PopUpAnimationsTest {

    private final PopUp popUp = PopUp.getInstance((Context) ApplicationProvider.getApplicationContext());

    @Test
    public void show_testAlpha_atStartAnimation() {
        PopUpAnimations.show(popUp);
        float actual = popUp.getAlpha();
        float expected = 0.0f;
        assertEquals(expected, actual, 0);
    }

    @Test
    public void hide_testAlpha_atStartAnimation() {
        PopUpAnimations.hide(popUp);
        float actual = popUp.getAlpha();
        float expected = 0.0f;
        assertEquals(expected, actual, 0);
    }

    @Test
    public void show_testVisibility_atStartAnimation() {
        PopUpAnimations.show(popUp);
        int actual = popUp.getVisibility();
        int expected = View.VISIBLE;
        assertEquals(expected, actual, 0);
    }

    @Test
    public void hide_testVisibility_atStartAnimation() {
        PopUpAnimations.hide(popUp);
        int actual = popUp.getVisibility();
        int expected = View.VISIBLE;
        assertEquals(expected, actual, 0);
    }
}