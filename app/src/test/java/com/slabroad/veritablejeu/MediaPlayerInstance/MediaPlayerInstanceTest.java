package com.slabroad.veritablejeu.MediaPlayerInstance;

import static org.junit.Assert.*;

import android.content.Context;

import androidx.test.core.app.ApplicationProvider;

import com.slabroad.veritablejeu.MediaPlayerInstance.BanqueDeSon.BanqueDeSon;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

@RunWith(RobolectricTestRunner.class)
public class MediaPlayerInstanceTest {

    private MediaPlayerInstance mediaPlayerInstance;
    private BanqueDeSon banqueDeSon;

    @Before
    public void setUp() {
        Context context = ApplicationProvider.getApplicationContext();
        mediaPlayerInstance = MediaPlayerInstance.getInstance(context);
        banqueDeSon = BanqueDeSon.getInstance();
    }

    @Test
    public void getInstance() {
        Context context = ApplicationProvider.getApplicationContext();
        MediaPlayerInstance newMedia = MediaPlayerInstance.getInstance(context);
        assertEquals(mediaPlayerInstance, newMedia);
    }

    @Test
    public void setVolume_minFloat_to0() {
        mediaPlayerInstance.setVolume(-Float.MAX_VALUE);
        assertEquals(0.0f, mediaPlayerInstance.getVolume(), 0);
    }

    @Test
    public void setVolume_outOfLowerBound_to0() {
        mediaPlayerInstance.setVolume(-16896296);
        assertEquals(0.0f, mediaPlayerInstance.getVolume(), 0);
    }

    @Test
    public void setVolume_lowerBound_to0() {
        mediaPlayerInstance.setVolume(0.0f);
        assertEquals(0.0f, mediaPlayerInstance.getVolume(), 0);
    }

    @Test
    public void setVolume_normal() {
        mediaPlayerInstance.setVolume(0.4f);
        assertEquals(0.4f, mediaPlayerInstance.getVolume(), 0);
    }

    @Test
    public void setVolume_higherBound() {
        mediaPlayerInstance.setVolume(1.0f);
        assertEquals(1.0f, mediaPlayerInstance.getVolume(), 0);
    }

    @Test
    public void setVolume_outOfHigherBound_to1() {
        mediaPlayerInstance.setVolume(16537);
        assertEquals(1.0f, mediaPlayerInstance.getVolume(), 0);
    }

    @Test
    public void setVolume_maxFloat_to1() {
        mediaPlayerInstance.setVolume(Float.MAX_VALUE);
        assertEquals(1.0f, mediaPlayerInstance.getVolume(), 0);
    }

    @Test
    public void getCurrentTrackNumber_afterInitialize_mainPageTrack() {
        int currentTrackNumber = mediaPlayerInstance.getCurrentTrackNumber();
        int mainPageTrack = BanqueDeSon.getMainPageTrack();
        int mainPageTrackNumber = banqueDeSon.getNumberOf(mainPageTrack);
        assertEquals(mainPageTrackNumber, currentTrackNumber);
    }
}