package com.example.veritablejeu.BackEnd.LevelFileStorage;

import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

public class GlobalStorageTest {

    private GlobalStorage globalStorage;

    @Before
    public void setUp() {
        globalStorage = GlobalStorage.getInstance();
        globalStorage.getStart();
    }

    @Test
    public void getStart() {
    }

    @Test
    public void getPrevious_noRuntimeException() {
        int numberOfPreviousClic = 1000;
        try {
            for (int index = 0; index < numberOfPreviousClic; index++) {
                globalStorage.getPrevious();
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
                globalStorage.getNext();
            }
        } catch (Exception e) {
            fail();
        }
    }
}