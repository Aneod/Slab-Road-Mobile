package com.example.veritablejeu.OutilsEnEnum;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;

public class LongToReadableTime {

    /**
     * Prend le long en paramètre et en resort un String dans un format lisible tel que :
     * 01h 23m 04s 675ms
     * @param elapsedTime une période de temps en long.
     * @return un String.
     */
    @NonNull
    @SuppressLint("DefaultLocale")
    public static String getElapsedTimeFormatted(long elapsedTime) {

        long hours = elapsedTime / 3_600_000_000_000L;
        long minutes = (elapsedTime % 3_600_000_000_000L) / 60_000_000_000L;
        long seconds = (elapsedTime % 60_000_000_000L) / 1_000_000_000L;
        long milliseconds = (elapsedTime % 1_000_000_000L) / 1_000_000;

        if(hours == 0) {
            if(minutes == 0) {
                if(seconds == 0) {
                    return String.format("%03dms", milliseconds);
                }
                return String.format("%02ds %03dms", seconds, milliseconds);
            }
            return String.format("%02dm %02ds %03dms", minutes, seconds, milliseconds);
        }
        return String.format("%02dh %02dm %02ds %03dms", hours, minutes, seconds, milliseconds);
    }
}
