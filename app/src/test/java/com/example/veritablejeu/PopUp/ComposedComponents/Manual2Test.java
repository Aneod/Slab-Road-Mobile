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
public class Manual2Test {

    private final PopUp popUp = PopUp.getInstance((Context) ApplicationProvider.getApplicationContext());
    private final Manual2 manual2 = Manual2.getInstance(popUp);

    @Test
    public void getInstance() {
        Manual2 instance = Manual2.getInstance(popUp);
        assertEquals(manual2, instance);
    }

    @Test
    public void getComonents_notNull() {
        assertNotNull(manual2.getComponents());
    }

    @Test
    public void show() {
        manual2.show();
        PopUpTitle popUpTitle = popUp.getTitle();
        CharSequence title = popUpTitle.getText();
        String expected = Manual2.getPopupTitle();
        assertEquals(expected, title);
    }
}