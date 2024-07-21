package com.example.veritablejeu.PopUp.TopBarElements;

import static org.junit.Assert.*;

import android.content.Context;

import androidx.test.core.app.ApplicationProvider;

import com.example.veritablejeu.PopUp.PopUp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

@RunWith(RobolectricTestRunner.class)
public class PopUpTitleTest {

    private final PopUp popUp = PopUp.getInstance((Context) ApplicationProvider.getApplicationContext());

    @Test
    public void testTextAttributs() {
        PopUpTitle popUpTitle = popUp.getTitle();
        assertEquals(PopUpTitle.getCOLOR(), popUpTitle.getCurrentTextColor());
        assertEquals(PopUpTitle.getSIZE(), (int) popUpTitle.getTextSize());
        assertEquals(PopUpTitle.getGRAVITY(), popUpTitle.getGravity());
    }

    @Test
    public void testTextWidthEqualToPopUpWidth() {
        PopUpTitle popUpTitle = popUp.getTitle();
        int popUpWidth = popUpTitle.getLayoutParams().width;
        int titleWidth = popUpTitle.getLayoutParams().width;
        assertEquals(popUpWidth, titleWidth);
    }

    @Test
    public void testTextHeight() {
        PopUpTitle popUpTitle = popUp.getTitle();
        int popUpTopBarHeight = popUp.getInitialHeight();
        int titleHeight = popUpTitle.getLayoutParams().height;
        assertEquals(popUpTopBarHeight, titleHeight);
    }

    @Test
    public void setTexte() {
        PopUpTitle popUpTitle = popUp.getTitle();
        popUpTitle.setTexte("TE");
        assertEquals("TE", popUpTitle.getText());
    }
}