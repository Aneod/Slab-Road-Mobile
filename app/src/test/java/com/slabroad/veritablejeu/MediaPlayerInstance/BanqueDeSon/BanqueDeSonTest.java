package com.slabroad.veritablejeu.MediaPlayerInstance.BanqueDeSon;

import static org.junit.Assert.*;

import org.junit.Test;

public class BanqueDeSonTest {

    private final BanqueDeSon banqueDeSon = BanqueDeSon.getInstance();

    @Test
    public void getInstance() {
        BanqueDeSon newBank = BanqueDeSon.getInstance();
        assertEquals(banqueDeSon, newBank);
    }

    @Test
    public void getMainPageTrack_exist() {
        int mainPageTrack = BanqueDeSon.getMainPageTrack();
        assertNotEquals(-1, BanqueDeSon.getPistesAudio().indexOf(mainPageTrack));
    }

    @Test
    public void getTrackOf_firstTrack() {
        int number = 0;
        int actualTrack = banqueDeSon.getTrackOf(number);
        int expected = BanqueDeSon.getPistesAudio().get(number);
        assertEquals(expected, actualTrack);
    }

    @Test
    public void getTrackOf_secondTrack() {
        int number = 2;
        int actualTrack = banqueDeSon.getTrackOf(number);
        int expected = BanqueDeSon.getPistesAudio().get(number);
        assertEquals(expected, actualTrack);
    }

    @Test
    public void getTrackOf_last() {
        int number = BanqueDeSon.getNumberOfMusics() - 1;
        int actualTrack = banqueDeSon.getTrackOf(number);
        int expected = BanqueDeSon.getPistesAudio().get(number);
        assertEquals(expected, actualTrack);
    }

    @Test
    public void getTrackOf_outOfLowerBound_toLast() {
        int number = -1;
        int actualTrack = banqueDeSon.getTrackOf(number);
        int lastIndex = BanqueDeSon.getNumberOfMusics() - 1;
        int expected = BanqueDeSon.getPistesAudio().get(lastIndex);
        assertEquals(expected, actualTrack);
    }

    @Test
    public void getTrackOf_hardLowerBound_toLast() {
        int number = Integer.MIN_VALUE;
        int actualTrack = banqueDeSon.getTrackOf(number);
        int lastIndex = BanqueDeSon.getNumberOfMusics() - 1;
        int expected = BanqueDeSon.getPistesAudio().get(lastIndex);
        assertEquals(expected, actualTrack);
    }

    @Test
    public void getTrackOf_outOfHigherBound_toFirst() {
        int number = BanqueDeSon.getNumberOfMusics();
        int actualTrack = banqueDeSon.getTrackOf(number);
        int expected = BanqueDeSon.getPistesAudio().get(0);
        assertEquals(expected, actualTrack);
    }

    @Test
    public void getTrackOf_hardHigherBound_toFirst() {
        int number = Integer.MAX_VALUE;
        int actualTrack = banqueDeSon.getTrackOf(number);
        int expected = BanqueDeSon.getPistesAudio().get(0);
        assertEquals(expected, actualTrack);
    }

    @Test
    public void getNumberOf_first() {
        int number = 0;
        int track = banqueDeSon.getTrackOf(number);
        int actualNumber = banqueDeSon.getNumberOf(track);
        assertEquals(number, actualNumber);
    }

    @Test
    public void getNumberOf_common() {
        int number = 2;
        int track = banqueDeSon.getTrackOf(number);
        int actualNumber = banqueDeSon.getNumberOf(track);
        assertEquals(number, actualNumber);
    }

    @Test
    public void getNumberOf_last() {
        int number = BanqueDeSon.getNumberOfMusics() - 1;
        int track = banqueDeSon.getTrackOf(number);
        int actualNumber = banqueDeSon.getNumberOf(track);
        assertEquals(number, actualNumber);
    }

    @Test
    public void getNumberOf_unknown_negative() {
        int actualNumber = banqueDeSon.getNumberOf(Integer.MIN_VALUE);
        assertEquals(-1, actualNumber);
    }

    @Test
    public void getNumberOf_unknown_positive() {
        int actualNumber = banqueDeSon.getNumberOf(Integer.MAX_VALUE);
        assertEquals(-1, actualNumber);
    }

    @Test
    public void getPreviousOf_unknownNegative() {
        int actual = banqueDeSon.getPreviousOf(Integer.MIN_VALUE);
        int lastIndex = BanqueDeSon.getNumberOfMusics() - 1;
        int expected = banqueDeSon.getTrackOf(lastIndex);
        assertEquals(expected, actual);
    }

    @Test
    public void getPreviousOf_lowerBound_toLast() {
        int number = 0;
        int track = banqueDeSon.getTrackOf(number);
        int actual = banqueDeSon.getPreviousOf(track);
        int lastIndex = BanqueDeSon.getNumberOfMusics() - 1;
        int expected = banqueDeSon.getTrackOf(lastIndex);
        assertEquals(expected, actual);
    }

    @Test
    public void getPreviousOf_second_toFirst() {
        int number = 1;
        int track = banqueDeSon.getTrackOf(number);
        int actual = banqueDeSon.getPreviousOf(track);
        int expected = banqueDeSon.getTrackOf(0);
        assertEquals(expected, actual);
    }

    @Test
    public void getPreviousOf_normal() {
        int number = 2;
        int track = banqueDeSon.getTrackOf(number);
        int actual = banqueDeSon.getPreviousOf(track);
        int expected = banqueDeSon.getTrackOf(number - 1);
        assertEquals(expected, actual);
    }

    @Test
    public void getPreviousOf_last_toBeforeLast() {
        int lastIndex = BanqueDeSon.getNumberOfMusics() - 1;
        int lastTrack = BanqueDeSon.getPistesAudio().get(lastIndex);
        int actual = banqueDeSon.getPreviousOf(lastTrack);
        int expected = BanqueDeSon.getPistesAudio().get(lastIndex - 1);
        assertEquals(expected, actual);
    }

    @Test
    public void getPreviousOf_unknownPositive() {
        int actual = banqueDeSon.getPreviousOf(Integer.MAX_VALUE);
        int lastIndex = BanqueDeSon.getNumberOfMusics() - 1;
        int expected = BanqueDeSon.getPistesAudio().get(lastIndex);
        assertEquals(expected, actual);
    }

    @Test
    public void getNextOf_unknownNegative() {
        int actual = banqueDeSon.getNextOf(Integer.MIN_VALUE);
        int expected = banqueDeSon.getTrackOf(0);
        assertEquals(expected, actual);
    }

    @Test
    public void getNextOf_lowerBound_toLast() {
        int number = 0;
        int track = banqueDeSon.getTrackOf(number);
        int actual = banqueDeSon.getNextOf(track);
        int expected = banqueDeSon.getTrackOf(1);
        assertEquals(expected, actual);
    }

    @Test
    public void getNextOf_normal() {
        int number = 2;
        int track = banqueDeSon.getTrackOf(number);
        int actual = banqueDeSon.getNextOf(track);
        int expected = banqueDeSon.getTrackOf(number + 1);
        assertEquals(expected, actual);
    }

    @Test
    public void getNextOf_last_toFirst() {
        int lastIndex = BanqueDeSon.getNumberOfMusics() - 1;
        int lastTrack = BanqueDeSon.getPistesAudio().get(lastIndex);
        int actual = banqueDeSon.getNextOf(lastTrack);
        int expected = BanqueDeSon.getPistesAudio().get(0);
        assertEquals(expected, actual);
    }

    @Test
    public void getNextOf_unknownPositive() {
        int actual = banqueDeSon.getNextOf(Integer.MAX_VALUE);
        int expected = BanqueDeSon.getPistesAudio().get(0);
        assertEquals(expected, actual);
    }

    @Test
    public void getPreviousNextOf_normal() {
        int number = 5;
        int track = banqueDeSon.getTrackOf(number);
        int actual = banqueDeSon.getNextOf(banqueDeSon.getPreviousOf(track));
        assertEquals(track, actual);
    }

    @Test
    public void getDoublePreviousNextOf_normal() {
        int number = 3;
        int track = banqueDeSon.getTrackOf(number);
        int actual = banqueDeSon.getNextOf(banqueDeSon.getNextOf(banqueDeSon.getPreviousOf(banqueDeSon.getPreviousOf(track))));
        assertEquals(track, actual);
    }
}