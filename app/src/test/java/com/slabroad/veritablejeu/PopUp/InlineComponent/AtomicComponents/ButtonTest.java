package com.slabroad.veritablejeu.PopUp.InlineComponent.AtomicComponents;

import static org.junit.Assert.*;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.test.core.app.ApplicationProvider;

import com.slabroad.veritablejeu.PopUp.InlineComponent.AtomicComponents.Button.Button;
import com.slabroad.veritablejeu.PopUp.InlineComponent.InlineComponent;
import com.slabroad.veritablejeu.PopUp.PopUp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

@RunWith(RobolectricTestRunner.class)
public class ButtonTest {

    private final PopUp popUp = PopUp.getInstance((Context) ApplicationProvider.getApplicationContext());
    private final InlineComponent inlineComponent = new InlineComponent(popUp);
    private final Button button = new Button(inlineComponent, 100, 0);

    @Test
    public void getHEIGHT() {
        int actual = button.getLayoutParams().height;
        int extected = Button.getHEIGHT();
        assertEquals(extected, actual);
    }

    @Test
    public void takeBlackAppearance_goodBakground() {
        button.takeBlackAppearance("TEST");
        Drawable actual = button.getBackground();
        Drawable expected = Button.getBlackAppearence();
        assertEquals(expected, actual);
    }

    @Test
    public void takeBlackAppearance_goodTextColor() {
        button.takeBlackAppearance("TEST");
        int actual = button.getCurrentTextColor();
        int expected = Button.getTextColorBlackAppearence();
        assertEquals(expected, actual);
    }

    @Test
    public void takeBlackAppearance_titleChanged() {
        button.takeBlackAppearance("TEST");
        CharSequence actual = button.getText();
        assertEquals("TEST", actual);
    }

    @Test
    public void takeBlackAppearance_titleChangedNoBullShit() {
        button.takeBlackAppearance("TEST");
        CharSequence actual = button.getText();
        assertNotEquals("TET", actual);
    }

    @Test
    public void takeWhiteAppearance_goodBakground() {
        button.takeWhiteAppearance("TEST");
        Drawable actual = button.getBackground();
        Drawable expected = Button.getWhiteAppearence();
        assertEquals(expected, actual);
    }

    @Test
    public void takeWhiteAppearance_goodTextColor() {
        button.takeWhiteAppearance("TEST");
        int actual = button.getCurrentTextColor();
        int expected = Button.getTextColorWhiteAppearence();
        assertEquals(expected, actual);
    }

    @Test
    public void takeWhiteAppearance_textColorNoBullShit() {
        button.takeWhiteAppearance("TEST");
        int actual = button.getCurrentTextColor();
        int expected = Button.getTextColorBlackAppearence();
        assertNotEquals(expected, actual);
    }

    @Test
    public void takeWhiteAppearance_titleChanged() {
        button.takeWhiteAppearance("TEST");
        CharSequence actual = button.getText();
        assertEquals("TEST", actual);
    }

    @Test
    public void setRunnable() {
        Button button2 = new Button(inlineComponent, 100, 0);
        button2.takeBlackAppearance("EEE");
        assertEquals(Button.getBlackAppearence(), button2.getBackground());
        assertEquals("EEE", button2.getText());

        Runnable runnable = () -> button2.takeWhiteAppearance("YYY");
        button.setRunnable(runnable);
        button.performClick();

        assertNotEquals(Button.getBlackAppearence(), button2.getBackground());
        assertNotEquals("EEE", button2.getText());
        assertEquals(Button.getWhiteAppearence(), button2.getBackground());
        assertEquals("YYY", button2.getText());
    }

    @Test
    public void testPerformClick_withNullRunnable_noExceptionThrown() {
        button.setRunnable(null);
        try {
            button.performClick();
            assertTrue(true);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void refreshLayoutParams_HeightIsntChangeable() {
        inlineComponent.setHeight(1000);
        button.refreshLayoutParams();
        int actual = button.getLayoutParams().height;
        int extected = Button.getHEIGHT();
        assertEquals(extected, actual);
    }

    @Test
    public void refreshLayoutParams_TopMarginIsAtMidle() {
        inlineComponent.setHeight(1000);
        button.refreshLayoutParams();
        ViewGroup.LayoutParams layoutParams = button.getLayoutParams();
        int actual;
        if(layoutParams instanceof FrameLayout.LayoutParams) {
            actual = ((FrameLayout.LayoutParams) layoutParams).topMargin;
        } else {
            actual = -1;
        }
        int extected = (inlineComponent.getLayoutParams().height - button.getLayoutParams().height) / 2;
        assertEquals(extected, actual);
    }
}