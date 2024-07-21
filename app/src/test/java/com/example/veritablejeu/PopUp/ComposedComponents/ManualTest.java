package com.example.veritablejeu.PopUp.ComposedComponents;

import static org.junit.Assert.*;

import android.content.Context;

import androidx.test.core.app.ApplicationProvider;

import com.example.veritablejeu.PopUp.PopUp;
import com.example.veritablejeu.PopUp.TopBarElements.PopUpTitle;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

@RunWith(RobolectricTestRunner.class)
public class ManualTest {

    private final PopUp popUp = PopUp.getInstance((Context) ApplicationProvider.getApplicationContext());
    private final Manual manual = Manual.getInstance(popUp);

    @Test
    public void getInstance() {
        Manual instance = Manual.getInstance(popUp);
        assertEquals(manual, instance);
    }

    @Test
    public void getComonents_notNull() {
        assertNotNull(manual.getComponents());
    }

    @Test
    public void show() {
        manual.show();
        PopUpTitle popUpTitle = popUp.getTitle();
        CharSequence title = popUpTitle.getText();
        String expected = Manual.getPopupTitle();
        assertEquals(expected, title);
    }
}