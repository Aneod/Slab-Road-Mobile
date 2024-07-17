package com.example.veritablejeu.MediaPlayerInstance.BanqueDeSon;

import com.example.veritablejeu.R;

import java.util.Arrays;
import java.util.List;

public class BanqueDeSon implements IBanqueDeSon {

    private static BanqueDeSon instance;
    private static final int MAIN_PAGE_TRACK = R.raw.son_trois;
    private static final List<Integer> pistesAudio = Arrays.asList(
            R.raw.son_un,
            R.raw.son_deux,
            R.raw.son_trois,
            R.raw.son_quatre,
            R.raw.son_cinq,
            R.raw.son_six,
            R.raw.son_sept,
            R.raw.son_huit,
            R.raw.son_neuf,
            R.raw.son_dix,
            R.raw.son_onze,
            R.raw.son_douze,
            R.raw.son_treize,
            R.raw.son_quatorze,
            R.raw.son_quize
    );

    public static List<Integer> getPistesAudio() {
        return pistesAudio;
    }
    public static int getMainPageTrack() {
        return MAIN_PAGE_TRACK;
    }



    private BanqueDeSon(){}

    public static BanqueDeSon getInstance() {
        if(instance == null) instance = new BanqueDeSon();
        return instance;
    }

    public static int getNumberOfMusics() {
        return pistesAudio.size();
    }

    @Override
    public int getTrackOf(int number) {
        int inBoundsNumber;
        int minTrack = 0;
        int maxTrack = pistesAudio.size() - 1;
        if(number < minTrack) inBoundsNumber = maxTrack;
        else if (number > maxTrack) inBoundsNumber = minTrack;
        else inBoundsNumber = number;
        return pistesAudio.get(inBoundsNumber);
    }

    @Override
    public int getNumberOf(int track) {
        return pistesAudio.indexOf(track);
    }

    @Override
    public int getPreviousOf(int track) {
        int indexGiven = pistesAudio.indexOf(track);
        return getTrackOf(indexGiven - 1);
    }

    @Override
    public int getNextOf(int track) {
        int indexGiven = pistesAudio.indexOf(track);
        return getTrackOf(indexGiven + 1);
    }
}
