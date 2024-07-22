package com.example.veritablejeu.BackEnd.LevelFileStorage;

import static org.junit.Assert.fail;

import android.content.Context;

import androidx.test.core.app.ApplicationProvider;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

@RunWith(RobolectricTestRunner.class)
public class PersonalStorageTest {

    private PersonalStorage personalStorage;

    @Before
    public void setUp() {
        Context context = ApplicationProvider.getApplicationContext();
        personalStorage = PersonalStorage.getInstance(context);
        personalStorage.getStart();
    }

    @Test
    public void getStart() {
    }

    @Test
    public void getPrevious_noRuntimeException() {
        int numberOfPreviousClic = 1000;
        try {
            for (int index = 0; index < numberOfPreviousClic; index++) {
                personalStorage.getPrevious();
            }
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void getNext() {
        int numberOfNextClic = 1000;
        try {
            for (int index = 0; index < numberOfNextClic; index++) {
                personalStorage.getNext();
            }
        } catch (Exception e) {
            fail();
        }
    }
}