package com.example.veritablejeu.BainDeSavon;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;

import androidx.appcompat.app.AppCompatActivity;

import com.example.veritablejeu.BainDeSavon.BulleDeSavon.BulleDeSavon;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

@RunWith(RobolectricTestRunner.class)
@Config(sdk = {28})
public class BainDeSavonTest {

    @Mock
    private AppCompatActivity mockActivity;

    private BainDeSavon bainDeSavon;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        when(mockActivity.getApplicationContext()).thenReturn(mock(Activity.class));
        bainDeSavon = BainDeSavon.getInstance(mockActivity);
    }

    @Test
    public void testSingletonInstance() {
        BainDeSavon instance1 = BainDeSavon.getInstance(mockActivity);
        BainDeSavon instance2 = BainDeSavon.getInstance(mockActivity);
        assertSame(instance1, instance2);
    }

    @Test
    public void testSetDesignDeBase() {
        bainDeSavon.setDesignDeBase();
        for (BulleDeSavon bulle : bainDeSavon.getToutesLesBulles()) {
            assertEquals(Color.BLACK, bulle.getColor());
            assertEquals(GradientDrawable.OVAL, bulle.getShape());
        }
    }

    @Test
    public void testSetDesignsWithParams() {
        bainDeSavon.setDesigns(GradientDrawable.RECTANGLE, Color.RED, GradientDrawable.OVAL, Color.BLUE);
        for (BulleDeSavon bulle : bainDeSavon.getToutesLesBulles()) {
            int groupe = bulle.getGroupe();
            if (groupe == 0) {
                assertEquals(Color.RED, bulle.getColor());
                assertEquals(GradientDrawable.RECTANGLE, bulle.getShape());
            } else {
                assertEquals(Color.BLUE, bulle.getColor());
                assertEquals(GradientDrawable.OVAL, bulle.getShape());
            }
        }
    }

    @Test
    public void testPauseAndResumeAnimations() {
        bainDeSavon.pause_animations();
        for (BulleDeSavon bulle : bainDeSavon.getToutesLesBulles()) {
            verify(bulle).pause_animation();
        }

        bainDeSavon.resume_animations();
        for (BulleDeSavon bulle : bainDeSavon.getToutesLesBulles()) {
            verify(bulle).resume_animation();
        }
    }

    @Test
    public void testShowAndHide() {
        bainDeSavon.show();
        assertTrue(bainDeSavon.areBubblesVisible());
        for (BulleDeSavon bulle : bainDeSavon.getToutesLesBulles()) {
            verify(bulle).show();
        }

        bainDeSavon.hide();
        assertFalse(bainDeSavon.areBubblesVisible());
        for (BulleDeSavon bulle : bainDeSavon.getToutesLesBulles()) {
            verify(bulle).hide();
        }
    }
}
