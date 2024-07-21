package com.example.veritablejeu.PopUp.InlineComponent.Preset;

import static org.junit.Assert.*;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.test.core.app.ApplicationProvider;

import com.example.veritablejeu.PopUp.InlineComponent.AtomicComponents.Button.Button;
import com.example.veritablejeu.PopUp.PopUp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

@RunWith(RobolectricTestRunner.class)
public class DoubleButtonsTest {

    private final PopUp popUp = PopUp.getInstance((Context) ApplicationProvider.getApplicationContext());
    private final DoubleButtons doubleButtons = new DoubleButtons(popUp, "text1", null, "text2", null);
    private int WITNESS = 0;

    @Test
    public void testChildNumber() {
        int number = doubleButtons.getChildCount();
        assertEquals(2, number);
    }

    @Test
    public void testChildType() {
        View button1 = doubleButtons.getChildAt(0);
        if(!(button1 instanceof Button)) {
            fail();
        }

        View button2 = doubleButtons.getChildAt(1);
        if(!(button2 instanceof Button)) {
            fail();
        }
    }

    @Test
    public void testWidthButtons() {
        int disponibleWidth = doubleButtons.getLayoutParams().width;
        float buttonsWidth = disponibleWidth * DoubleButtons.getTotalButtonsWidthOccupationPercentage();
        int buttonWidth = (int) (buttonsWidth / 2);

        View button1 = doubleButtons.getChildAt(0);
        if(!(button1 instanceof Button)) {
            fail();
        }
        assertEquals(buttonWidth, button1.getLayoutParams().width, 0);

        View button2 = doubleButtons.getChildAt(1);
        if(!(button2 instanceof Button)) {
            fail();
        }
        assertEquals(buttonWidth, button2.getLayoutParams().width, 0);
        assertEquals(
                button1.getLayoutParams().width,
                button2.getLayoutParams().width,
                0
        );
    }

    @Test
    public void testButtonsCorresponding() {
        View button1 = doubleButtons.getChildAt(0);
        if(!(button1 instanceof Button)) {
            fail();
        }
        assertEquals(button1, doubleButtons.getBoutonA());

        View button2 = doubleButtons.getChildAt(1);
        if(!(button2 instanceof Button)) {
            fail();
        }
        assertEquals(button2, doubleButtons.getBoutonB());
    }

    @Test
    public void testButton1LeftMargin() {
        View button1 = doubleButtons.getChildAt(0);
        if(!(button1 instanceof Button)) {
            fail();
        }
        int disponibleWidth = doubleButtons.getLayoutParams().width;
        float buttonsWidth = disponibleWidth * DoubleButtons.getTotalButtonsWidthOccupationPercentage();
        float side_margins = (disponibleWidth - buttonsWidth) / 4;
        int leftMarginFirst = (int) side_margins + PopUp.getBORDER_WIDTH();
        ViewGroup.LayoutParams layoutParams = button1.getLayoutParams();
        if(layoutParams instanceof FrameLayout.LayoutParams) {
            int actual = ((FrameLayout.LayoutParams) layoutParams).leftMargin;
            assertEquals(leftMarginFirst, actual);
        } else {
            fail();
        }
    }

    @Test
    public void testButton2LeftMargin() {
        View button2 = doubleButtons.getChildAt(1);
        if(!(button2 instanceof Button)) {
            fail();
        }
        int disponibleWidth = doubleButtons.getLayoutParams().width;
        float buttonsWidth = disponibleWidth * DoubleButtons.getTotalButtonsWidthOccupationPercentage();
        float buttonWidth = buttonsWidth / 2;
        float side_margins = (disponibleWidth - buttonsWidth) / 4;
        float buttonsGap = 2 * side_margins;
        int leftMarginSecond = (int) (side_margins + buttonWidth + buttonsGap + PopUp.getBORDER_WIDTH());
        ViewGroup.LayoutParams layoutParams = button2.getLayoutParams();
        if(layoutParams instanceof FrameLayout.LayoutParams) {
            int actual = ((FrameLayout.LayoutParams) layoutParams).leftMargin;
            assertEquals(leftMarginSecond, actual);
        } else {
            fail();
        }
    }

    @Test
    public void testButtonAppearences() {
        View button1 = doubleButtons.getChildAt(0);
        if(!(button1 instanceof Button)) {
            fail();
        }
        Drawable expected1 = Button.getWhiteAppearence();
        assertEquals(expected1, button1.getBackground());

        View button2 = doubleButtons.getChildAt(1);
        if(!(button2 instanceof Button)) {
            fail();
        }
        Drawable expected2 = Button.getBlackAppearence();
        assertEquals(expected2, button2.getBackground());
    }

    @Test
    public void setRunnableA() {
        doubleButtons.getBoutonA().performClick();
        assertEquals(0, WITNESS);
        doubleButtons.setRunnableA(() -> WITNESS = 100);
        doubleButtons.getBoutonA().performClick();
        assertEquals(100, WITNESS);
    }

    @Test
    public void setRunnableB() {
        doubleButtons.getBoutonB().performClick();
        assertEquals(0, WITNESS);
        doubleButtons.setRunnableB(() -> WITNESS = 200);
        doubleButtons.getBoutonB().performClick();
        assertEquals(200, WITNESS);
    }
}