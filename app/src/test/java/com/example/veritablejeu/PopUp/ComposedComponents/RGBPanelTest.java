package com.example.veritablejeu.PopUp.ComposedComponents;

import static org.junit.Assert.*;

import android.content.Context;

import androidx.test.core.app.ApplicationProvider;

import com.example.veritablejeu.PopUp.InlineComponent.Preset.CursorComponent;
import com.example.veritablejeu.PopUp.PopUp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

@RunWith(RobolectricTestRunner.class)
public class RGBPanelTest {

    private final PopUp popUp = PopUp.getInstance((Context) ApplicationProvider.getApplicationContext());
    private int color = 0xff663366;
    private final RGBPanel rgbPanel = new RGBPanel(popUp, color, c -> color = c);

    @Test
    public void testNoRunnableAtStarting() {
        assertEquals(0xff663366, color);
    }

    @Test
    public void getCursors_nomberOfCursors() {
        CursorComponent[] cursors = rgbPanel.getCursors();
        assertEquals(3, cursors.length);
    }

    @Test
    public void getCursors_redValues() {
        CursorComponent[] cursors = rgbPanel.getCursors();
        if(cursors.length != 3) {
            fail();
        }
        CursorComponent red = cursors[0];
        float actual = red.getCursor().getValue();
        float expected = .4f;
        assertEquals(expected, actual, 0);
    }

    @Test
    public void getCursors_greenValues() {
        CursorComponent[] cursors = rgbPanel.getCursors();
        if(cursors.length != 3) {
            fail();
        }
        CursorComponent green = cursors[1];
        float actual = green.getCursor().getValue();
        float expected = .2f;
        assertEquals(expected, actual, 0);
    }

    @Test
    public void getCursors_blueValues() {
        CursorComponent[] cursors = rgbPanel.getCursors();
        if(cursors.length != 3) {
            fail();
        }
        CursorComponent blue = cursors[2];
        float actual = blue.getCursor().getValue();
        float expected = .4f;
        assertEquals(expected, actual, 0);
    }
}