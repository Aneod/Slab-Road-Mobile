package com.slabroad.veritablejeu.PopUp.InlineComponent.Preset;

import static org.junit.Assert.*;

import android.content.Context;
import android.view.Gravity;
import android.view.View;

import androidx.test.core.app.ApplicationProvider;

import com.slabroad.veritablejeu.PopUp.InlineComponent.AtomicComponents.Text;
import com.slabroad.veritablejeu.PopUp.PopUp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

@RunWith(RobolectricTestRunner.class)
public class SimpleTextTest {

    private final PopUp popUp = PopUp.getInstance((Context) ApplicationProvider.getApplicationContext());
    private final SimpleText simpleText = new SimpleText(popUp, "TEST TEXT", Gravity.CENTER_HORIZONTAL);

    @Test
    public void testNumberOfChild() {
        int childNumber = simpleText.getChildCount();
        assertEquals(1, childNumber);
    }

    @Test
    public void theOnlyChildCorresponding() {
        View child = simpleText.getChildAt(0);
        if(child == null) {
            fail();
        }
        assertTrue(child instanceof Text);
        assertEquals("TEST TEXT", ((Text) child).getText());
    }

    @Test
    public void testGetTextView() {
        View textView = simpleText.getTextView();
        View child = simpleText.getChildAt(0);
        if(child == null) {
            fail();
        }
        assertEquals(textView, child);
    }

    @Test
    public void testDefaultConstructor() {
        SimpleText simpleText = new SimpleText(popUp, "TEST 2");
        assertEquals("TEST 2", simpleText.getTextView().getText());
    }

    @Test
    public void setText() {
        simpleText.setText("TTTTTTEST");
        CharSequence actual = simpleText.getTextView().getText();
        assertEquals("TTTTTTEST", actual);
    }
}