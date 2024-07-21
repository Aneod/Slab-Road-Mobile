package com.example.veritablejeu.PopUp.InlineComponent;

import static org.junit.Assert.*;

import android.content.Context;
import android.view.Gravity;

import androidx.test.core.app.ApplicationProvider;

import com.example.veritablejeu.PopUp.InlineComponent.AtomicComponents.Text;
import com.example.veritablejeu.PopUp.PopUp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

@RunWith(RobolectricTestRunner.class)
public class InlineComponentTest {

    private final PopUp popUp = PopUp.getInstance((Context) ApplicationProvider.getApplicationContext());
    private final InlineComponent inlineComponent = new InlineComponent(popUp);

    @Test
    public void testWidth() {
        int actual = inlineComponent.getLayoutParams().width;
        int sideMargins = InlineComponent.getSideMargins();
        int extected = popUp.getLayoutParams().width - 2 * popUp.getBORDER_WIDTH() - 2 * sideMargins;
        assertEquals(extected, actual);
    }

    @Test
    public void testHeight_at0() {
        assertEquals(0, inlineComponent.getLayoutParams().height);
    }

    @Test
    public void testLeftMargin() {
        int actual = inlineComponent.getLayoutParams().leftMargin;
        int expected = InlineComponent.getSideMargins();
        assertEquals(expected, actual);
    }

    @Test
    public void testTopMargin_at0() {
        assertEquals(0, inlineComponent.getLayoutParams().topMargin);
    }

    @Test
    public void setHeight() {
        inlineComponent.setHeight(100);
        assertEquals(100, inlineComponent.getLayoutParams().height);
    }

    @Test
    public void testTextSize() {
        Text text = new Text(inlineComponent, "TEST", 100, 100, Gravity.CENTER);
        int actual = (int) text.getTextSize();
        int expected = InlineComponent.getTextSize();
        assertEquals(expected, actual);
    }
}