package com.example.veritablejeu.PopUp.InlineComponent.Preset;

import static org.junit.Assert.*;

import android.content.Context;
import android.view.View;

import androidx.test.core.app.ApplicationProvider;

import com.example.veritablejeu.PopUp.InlineComponent.AtomicComponents.Image;
import com.example.veritablejeu.PopUp.PopUp;
import com.example.veritablejeu.R;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

@RunWith(RobolectricTestRunner.class)
public class SimpleImageTest {

    private final PopUp popUp = PopUp.getInstance((Context) ApplicationProvider.getApplicationContext());
    private final SimpleImage simpleImage = new SimpleImage(popUp, R.drawable.img10);

    @Test
    public void testNumberOfChild() {
        int childNumber = simpleImage.getChildCount();
        assertEquals(1, childNumber);
    }

    @Test
    public void theOnlyChildCorresponding() {
        View child = simpleImage.getChildAt(0);
        if(child == null) {
            fail();
        }
        assertTrue(child instanceof Image);
    }

}