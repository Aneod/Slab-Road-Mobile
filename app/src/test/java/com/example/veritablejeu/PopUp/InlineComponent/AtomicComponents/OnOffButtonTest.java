package com.example.veritablejeu.PopUp.InlineComponent.AtomicComponents;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import android.content.Context;
import android.graphics.drawable.Drawable;

import androidx.test.core.app.ApplicationProvider;

import com.example.veritablejeu.PopUp.InlineComponent.AtomicComponents.Button.Button;
import com.example.veritablejeu.PopUp.InlineComponent.AtomicComponents.OnOffButton.OnOffButton;
import com.example.veritablejeu.PopUp.InlineComponent.InlineComponent;
import com.example.veritablejeu.PopUp.PopUp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

@RunWith(RobolectricTestRunner.class)
public class OnOffButtonTest {

    private final PopUp popUp = PopUp.getInstance((Context) ApplicationProvider.getApplicationContext());
    private final InlineComponent inlineComponent = new InlineComponent(popUp);
    private int WITNESS = 0;
    private final OnOffButton button = new OnOffButton(
            inlineComponent, 100, 50,
            false,
            "YES", () -> WITNESS = 1,
            "NO", () -> WITNESS = 2
    );

    @Test
    public void testStart_noEffectRunned() {
        assertEquals(0, WITNESS);
    }

    @Test
    public void testStartAppearence_goodBakground() {
        Drawable actual = button.getBackground();
        Drawable expected = Button.getBlackAppearence();
        assertEquals(expected, actual);
    }

    @Test
    public void testStartAppearence_goodTextColor() {
        int actual = button.getCurrentTextColor();
        int expected = Button.getTextColorBlackAppearence();
        assertEquals(expected, actual);
    }

    @Test
    public void testStartAppearence_titleChanged() {
        CharSequence actual = button.getText();
        assertEquals("NO", actual);
    }

    @Test
    public void setAppearance_goodBakground() {
        button.setAppearance(true);
        Drawable actual = button.getBackground();
        Drawable expected = Button.getWhiteAppearence();
        assertEquals(expected, actual);
    }

    @Test
    public void setAppearance_goodTextColor() {
        button.setAppearance(true);
        CharSequence actual = button.getText();
        assertEquals("YES", actual);
    }

    @Test
    public void setAppearance_textColorNoBullShit() {
        button.setAppearance(true);
        int actual = button.getCurrentTextColor();
        int expected = Button.getTextColorBlackAppearence();
        assertNotEquals(expected, actual);
    }

    @Test
    public void disactivation_disactivationEffectRunned() {
        button.disactivation();
        assertEquals(2, WITNESS);
    }

    @Test
    public void disactivation_blackAppearenceApplied() {
        button.disactivation();
        Drawable actual = button.getBackground();
        Drawable expected = Button.getBlackAppearence();
        assertEquals(expected, actual);
    }

    @Test
    public void activation_activationEffectRunned() {
        button.activation();
        assertEquals(1, WITNESS);
    }

    @Test
    public void activation_whiteAppearenceApplied() {
        button.activation();
        Drawable actual = button.getBackground();
        Drawable expected = Button.getWhiteAppearence();
        assertEquals(expected, actual);
    }

    @Test
    public void activation_nullRunnable_noException() {
        OnOffButton button = new OnOffButton(
                inlineComponent, 100, 50,
                false,
                "YES", null,
                "NO", null
        );
        try {
            button.activation();
            assertTrue(true);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void disactivation_nullRunnable_noException() {
        OnOffButton button = new OnOffButton(
                inlineComponent, 100, 50,
                false,
                "YES", null,
                "NO", null
        );
        try {
            button.disactivation();
            assertTrue(true);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void doubleDisactivation_nullRunnable_noException() {
        OnOffButton button = new OnOffButton(
                inlineComponent, 100, 50,
                false,
                "YES", null,
                "NO", null
        );
        try {
            button.disactivation();
            assertTrue(true);
        } catch (Exception e) {
            fail();
        }

        try {
            button.disactivation();
            assertTrue(true);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void takeBlackAppearance_goodTitle() {
        button.takeBlackAppearance();
        CharSequence actual = button.getText();
        assertEquals("NO", actual);
    }

    @Test
    public void takeBlackAppearance_goodTextColor() {
        button.takeBlackAppearance();
        int actual = button.getCurrentTextColor();
        int expected = Button.getTextColorBlackAppearence();
        assertEquals(expected, actual);
    }

    @Test
    public void takeWhiteAppearance_goodTextColor() {
        button.takeWhiteAppearance();
        int actual = button.getCurrentTextColor();
        int expected = Button.getTextColorWhiteAppearence();
        assertEquals(expected, actual);
    }
}