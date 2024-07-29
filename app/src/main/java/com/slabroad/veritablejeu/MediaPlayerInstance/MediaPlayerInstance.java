package com.slabroad.veritablejeu.MediaPlayerInstance;

import android.annotation.SuppressLint;
import android.content.Context;
import android.media.MediaPlayer;

import androidx.annotation.NonNull;

import com.slabroad.veritablejeu.MediaPlayerInstance.BanqueDeSon.BanqueDeSon;

public class MediaPlayerInstance implements IMediaPlayerInstance {

    @SuppressLint("StaticFieldLeak")
    private static MediaPlayerInstance instance;
    private final Context context;
    private MediaPlayer mediaPlayer;
    private float currentVolume = 1.0f;
    private int currentTrack = BanqueDeSon.getMainPageTrack();

    /**
     * Hide
     */
    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

    /**
     * Hide
     */
    public int getCurrentTrack() {
        return currentTrack;
    }

    private MediaPlayerInstance(@NonNull Context context){
        this.context = context;
    }

    public static MediaPlayerInstance getInstance(@NonNull Context context) {
        if(instance == null) {
            instance = new MediaPlayerInstance(context);
        }
        return instance;
    }

    @Override
    public float getVolume() {
        return currentVolume;
    }

    @Override
    public void setVolume(float volume) {
        currentVolume = Math.min(Math.max(0.0f, volume), 1.0f);
        if(mediaPlayer != null) {
            mediaPlayer.setVolume(currentVolume, currentVolume);
        }
    }

    @Override
    public void setTrack(int track) {
        if(mediaPlayer != null) {
            mediaPlayer.release();
        }
        mediaPlayer = MediaPlayer.create(context, track);
        mediaPlayer.setVolume(currentVolume, currentVolume);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();
        currentTrack = track;
    }

    @Override
    public void playNewMusic(int numero) {
        BanqueDeSon banqueDeSon = BanqueDeSon.getInstance();
        int musique = banqueDeSon.getTrackOf(numero);
        setTrack(musique);
    }

    @Override
    public void playMainPageMusic() {
        setTrack(BanqueDeSon.getMainPageTrack());
    }

    @Override
    public void playPrevious() {
        BanqueDeSon banqueDeSon = BanqueDeSon.getInstance();
        int musique = banqueDeSon.getPreviousOf(currentTrack);
        setTrack(musique);
    }

    @Override
    public void playNext() {
        BanqueDeSon banqueDeSon = BanqueDeSon.getInstance();
        int musique = banqueDeSon.getNextOf(currentTrack);
        setTrack(musique);
    }

    @Override
    public int getCurrentTrackNumber() {
        BanqueDeSon banqueDeSon = BanqueDeSon.getInstance();
        return banqueDeSon.getNumberOf(currentTrack);
    }

}
