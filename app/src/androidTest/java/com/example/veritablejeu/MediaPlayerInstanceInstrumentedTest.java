package com.example.veritablejeu;// Dans src/androidTest/java/com/example/veritablejeu/MediaPlayerInstance
import static org.junit.Assert.*;

import android.content.Context;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.runner.AndroidJUnit4;

import com.example.veritablejeu.MediaPlayerInstance.BanqueDeSon.BanqueDeSon;
import com.example.veritablejeu.MediaPlayerInstance.MediaPlayerInstance;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class MediaPlayerInstanceInstrumentedTest {

    private MediaPlayerInstance mediaPlayerInstance;

    @Before
    public void setUp() {
        Context context = ApplicationProvider.getApplicationContext();
        mediaPlayerInstance = MediaPlayerInstance.getInstance(context);
    }

    @Test
    public void setTrack() {
        int trackNumber = 1;
        BanqueDeSon banqueDeSon = BanqueDeSon.getInstance();
        int track = banqueDeSon.getTrackOf(trackNumber);
        mediaPlayerInstance.setTrack(track);
        assertEquals(track, mediaPlayerInstance.getCurrentTrack());
        assertNotNull(mediaPlayerInstance.getMediaPlayer());
        assertTrue(mediaPlayerInstance.getMediaPlayer().isLooping());
    }

    // Ajoutez d'autres tests instrumentés ici
}
