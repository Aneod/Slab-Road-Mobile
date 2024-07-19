package com.example.veritablejeu.PopUp.ContenuPopUp.SettingsPanel;

import static org.junit.Assert.*;

import android.graphics.Color;

import androidx.core.util.Consumer;

import com.example.veritablejeu.PopUp.ContenuPopUp.SettingsPanel.RGBPanel.RGBPanel;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import java.util.List;

@RunWith(RobolectricTestRunner.class)
public class RGBPanelTest {

    private final RGBPanel rgbPanel = new RGBPanel(Color.RED, color -> {});

    @Test
    public void testInitialization() {
        Consumer<Integer> consumer = color -> {};
        RGBPanel rgbPanel = new RGBPanel(Color.RED, consumer);
        assertEquals(Color.RED, rgbPanel.getColor());
        assertEquals(rgbPanel.getWhenModify(), consumer);
    }

    @Test
    public void testNumberOfCursors() {
        List<SettingsPanel.SettingComponent> cursors = rgbPanel.getCursors();
        assertEquals(3, cursors.size());
    }
}