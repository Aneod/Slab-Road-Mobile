package com.example.veritablejeu.MediaPlayerInstance;

import android.content.Context;
import android.media.MediaPlayer;

import androidx.annotation.NonNull;

import com.example.veritablejeu.MediaPlayerInstance.BanqueDeSon.BanqueDeSon;

public class MediaPlayerInstance implements IMediaPlayerInstance {

    private static MediaPlayerInstance instance;
    private static MediaPlayer mediaPlayer;
    private static float volumeActuel = 1.0f;

    private MediaPlayerInstance(){}

    public static MediaPlayerInstance getInstance() {
        if(instance == null) {
            instance = new MediaPlayerInstance();
        }
        return instance;
    }

    @Override
    public float getVolume() {
        return volumeActuel;
    }

    @Override
    public void setVolume(float volume) {
        volumeActuel = Math.min(Math.max(0.0f, volume), 1.0f);
        mediaPlayer.setVolume(volumeActuel, volumeActuel);
    }

    @Override
    public void setMusiqueActuelle(@NonNull Context context, int musique) {
        if(mediaPlayer != null) {
            mediaPlayer.release();
        }
        mediaPlayer = MediaPlayer.create(context, musique);
        mediaPlayer.setVolume(volumeActuel, volumeActuel);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();
    }

    @Override
    public void activerPisteDuNumero(@NonNull Context context, int numero) {
        if(mediaPlayer != null) {
            mediaPlayer.release();
        }
        BanqueDeSon banqueDeSon = BanqueDeSon.getInstance();
        int musique = banqueDeSon.getMusiqueDuNumero(numero);
        mediaPlayer = MediaPlayer.create(context, musique);
        mediaPlayer.setVolume(volumeActuel, volumeActuel);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();
    }

    @Override
    public void activerLaMusiqueDuMenu(@NonNull Context context) {
        BanqueDeSon banqueDeSon = BanqueDeSon.getInstance();
        int musiqueDuMenu = banqueDeSon.getMusiqueDuMenu();
        setMusiqueActuelle(context, musiqueDuMenu);
    }
}
