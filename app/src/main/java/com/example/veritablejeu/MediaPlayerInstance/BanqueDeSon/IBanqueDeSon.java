package com.example.veritablejeu.MediaPlayerInstance.BanqueDeSon;

/**
 * Cette classe consiste en la gestion des fichiers sonores de l'application.
 * <p>
 * Valeurs statiques importantes :
 * La musique du menu. Qui est finale.
 */
public interface IBanqueDeSon {

    /**
     * Returns the corresponding track in the jukebox of the given number.
     * <br>
     * If the number is < 0, returns the last track. The first if the number is > jukebox.length().
     * @param number the number of the track in the jukebox track order.
     * @return a track of the jukebox.
     */
    int getTrackOf(int number);

    /**
     * Returns the number of the given track in the jukebox.
     * <br>
     * If the given track is unknown, returns -1.
     * @param track the track who we searching the number in jukebox.
     * @return the track number.
     */
    int getNumberOf(int track);

    /**
     * Returns the previous track of the given one.
     * <br>
     * If the given track is the first of the list, returns the last.
     * <br>
     * If the given track is unknown, return the last track.
     * @param track the next track of the searched one.
     * @return a track.
     */
    int getPreviousOf(int track);

    /**
     * Returns the next track of the given one.
     * <br>
     * If the given track is the last of the list, returns the first.
     * <br>
     * If the given track is unknown, return the first track.
     * @param track the previous track of the searched one.
     * @return a track.
     */
    int getNextOf(int track);

}
