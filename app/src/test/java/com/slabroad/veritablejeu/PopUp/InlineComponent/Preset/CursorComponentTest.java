package com.slabroad.veritablejeu.PopUp.InlineComponent.Preset;

import static org.junit.Assert.*;

import android.content.Context;
import android.graphics.Color;
import android.view.View;

import androidx.test.core.app.ApplicationProvider;

import com.slabroad.veritablejeu.PopUp.InlineComponent.AtomicComponents.Cursor.Cursor;
import com.slabroad.veritablejeu.PopUp.InlineComponent.AtomicComponents.Text;
import com.slabroad.veritablejeu.PopUp.PopUp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

@RunWith(RobolectricTestRunner.class)
public class CursorComponentTest {

    private final PopUp popUp = PopUp.getInstance((Context) ApplicationProvider.getApplicationContext());
    private final CursorComponent cursorComponent = new CursorComponent(popUp, "TIT", .6f, null, Color.RED);

    @Test
    public void testChildCount() {
        int actual = cursorComponent.getChildCount();
        assertEquals(2, actual);
    }

    @Test
    public void testChildType() {
        View text = cursorComponent.getChildAt(0);
        if(!(text instanceof Text)) {
            fail();
        }

        View cursor = cursorComponent.getChildAt(1);
        if(!(cursor instanceof Cursor)) {
            fail();
        }
    }

    @Test
    public void testHeightMaximizedByText() {
        int actual = cursorComponent.getLayoutParams().height;
        int expected = Integer.MAX_VALUE;
        assertEquals(expected, actual);
    }

    @Test
    public void testTextWidthProportion() {
        View text = cursorComponent.getChildAt(0);
        if(!(text instanceof Text)) {
            fail();
        }
        int actual = text.getLayoutParams().width;
        int expected = (int) (cursorComponent.getLayoutParams().width * CursorComponent.getWidthTitledDistribution());
        assertEquals(expected, actual);
    }

    @Test
    public void getCursor_corresponding() {
        Cursor expected = cursorComponent.getCursor();
        View cursor = cursorComponent.getChildAt(1);
        if(!(cursor instanceof Cursor)) {
            fail();
        }
        assertEquals(expected, cursor);
    }

    @Test
    public void setHeight() {
        cursorComponent.setHeight(1500);
        int actual = cursorComponent.getLayoutParams().height;
        assertEquals(1500, actual);
    }
}