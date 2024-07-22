package com.example.veritablejeu.BackEnd.LevelFileStorage;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;

import com.example.veritablejeu.BackEnd.DataBases.NormalLevelFiles.NormalLevelFiles;
import com.example.veritablejeu.BackEnd.LevelFile.LevelFile;
import com.example.veritablejeu.LevelsPanel.LevelsPanel;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockedStatic;

import java.util.Arrays;
import java.util.List;

public class NormalStorageTest {

    private NormalStorage normalStorage;

    @Before
    public void setUp() {
        normalStorage = NormalStorage.getInstance();
        normalStorage.getStart();
    }

    @Test
    public void testNumberOfPages_calculation() {
        assertEquals(0, (int) Math.ceil((double) 0 / 12));
        assertEquals(1, (int) Math.ceil((double) 1 / 12));
        assertEquals(1, (int) Math.ceil((double) 8 / 12));
        assertEquals(1, (int) Math.ceil((double) 12 / 12));
        assertEquals(2, (int) Math.ceil((double) 13 / 12));
        assertEquals(10, (int) Math.ceil((double) 120 / 12));
        assertEquals(11, (int) Math.ceil((double) 121 / 12));
    }

    @Test
    public void getStart() {
    }

    @Test
    public void getPrevious_noRuntimeException() {
        int numberOfPreviousClic = 1000;
        try {
            for (int index = 0; index < numberOfPreviousClic; index++) {
                normalStorage.getPrevious();
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
                normalStorage.getNext();
            }
        } catch (Exception e) {
            fail();
        }
    }
}