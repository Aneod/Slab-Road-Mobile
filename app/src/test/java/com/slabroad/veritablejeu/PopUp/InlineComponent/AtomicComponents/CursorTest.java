package com.slabroad.veritablejeu.PopUp.InlineComponent.AtomicComponents;

import static org.junit.Assert.*;

import android.content.Context;
import android.graphics.Color;

import androidx.test.core.app.ApplicationProvider;

import com.slabroad.veritablejeu.PopUp.InlineComponent.AtomicComponents.Cursor.Cursor;
import com.slabroad.veritablejeu.PopUp.InlineComponent.InlineComponent;
import com.slabroad.veritablejeu.PopUp.PopUp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

@RunWith(RobolectricTestRunner.class)
public class CursorTest {

    private final PopUp popUp = PopUp.getInstance((Context) ApplicationProvider.getApplicationContext());
    private final InlineComponent inlineComponent = new InlineComponent(popUp);
    private final int cursorWidth = 100;
    private final Cursor cursor = new Cursor(inlineComponent, cursorWidth, .5f, null, Color.BLACK);

    @Test
    public void getValue() {
        float actual = cursor.getValue();
        float expected = .5f;
        assertEquals(expected, actual, 0);
    }

    @Test
    public void testCursorLineWidth() {
        int actual = cursor.getLayoutParamsLine().width;
        int expected = cursorWidth - Cursor.getCursorDiameter();
        assertEquals(expected, actual);
    }

    @Test
    public void testCursorLineHeight() {
        int actual = cursor.getLayoutParamsLine().height;
        int expected = Cursor.getCursorLineHeight();
        assertEquals(expected, actual);
    }

    @Test
    public void testCursorLineLeftMargin() {
        int actual = cursor.getLayoutParamsLine().leftMargin;
        int expected = inlineComponent.getLayoutParams().width - cursorWidth + Cursor.getCursorDiameter() / 2;
        assertEquals(expected, actual);
    }

    @Test
    public void testCursorLineTopMargin() {
        int actual = cursor.getLayoutParamsLine().topMargin;
        int expected = (inlineComponent.getLayoutParams().height - Cursor.getCursorLineHeight()) / 2;
        assertEquals(expected, actual);
    }

    @Test
    public void getCursor_diameter() {
        int width = cursor.getLayoutParamsCursor().width;
        int height = cursor.getLayoutParamsCursor().height;
        int expected = Cursor.getCursorDiameter();
        assertEquals(expected, width);
        assertEquals(expected, height);
    }

    @Test
    public void getCursor_leftMargin_at0() {
        int leftMargin = cursor.getLayoutParamsCursor().leftMargin;
        float translationX = cursor.getCursor().getTranslationX();
        int expected = cursor.getLayoutParamsLine().leftMargin + cursor.getLayoutParamsLine().width / 2 - Cursor.getCursorDiameter() / 2;
        assertEquals(0, leftMargin);
        assertEquals(expected, translationX, 0);
    }

    @Test
    public void getCursor_topMargin_atCenter() {
        int topMargin = cursor.getLayoutParamsCursor().topMargin;
        int expected = (Cursor.getHEIGHT() + cursor.getLayoutParamsLine().height - Cursor.getCursorDiameter()) / 2;
        //assertEquals(expected, topMargin);
    }

    @Test
    public void refreshHeight() {
        int topMargin1 = cursor.getLayoutParamsLine().topMargin;
        assertEquals(
                topMargin1,
                (inlineComponent.getLayoutParams().height - Cursor.getCursorLineHeight()) / 2
        );
        cursor.getInlineComponent().setHeight(1000);
        cursor.refreshHeight();

        int topMargin2 = cursor.getLayoutParamsLine().topMargin;
        assertEquals(
                topMargin2,
                (inlineComponent.getLayoutParams().height - Cursor.getCursorLineHeight()) / 2
        );
        assertNotEquals(topMargin1, topMargin2);
    }

    @Test
    public void getCorrectValue_outOfLowerBound() {
        float value = -Float.MAX_VALUE;
        float actual = cursor.getCorrectValue(value);
        assertEquals(0.0f, actual, 0);
    }

    @Test
    public void getCorrectValue_lowerBound() {
        float value = 0.0f;
        float actual = cursor.getCorrectValue(value);
        assertEquals(0.0f, actual, 0);
    }

    @Test
    public void getCorrectValue_normal() {
        float value = .7f;
        float actual = cursor.getCorrectValue(value);
        assertEquals(.7f, actual, 0);
    }

    @Test
    public void getCorrectValue_higherBound() {
        float value = 1.0f;
        float actual = cursor.getCorrectValue(value);
        assertEquals(1.0f, actual, 0);
    }

    @Test
    public void getCorrectValue_outOfHigherBound() {
        float value = Float.MAX_VALUE;
        float actual = cursor.getCorrectValue(value);
        assertEquals(1.0f, actual, 0);
    }
}