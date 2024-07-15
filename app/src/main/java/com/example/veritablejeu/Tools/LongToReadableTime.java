package com.example.veritablejeu.Tools;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;

public class LongToReadableTime {

    /**
     * Take a long who containes a number of nano-secondes and transform it on something like :
     * 01h 23m 45s 678ms.
     * <br>
     * If the given number is negative, this method will returns the correct equivalent,
     * even if the result is also negative.
     * @param elapsedTime the number of nano-secondes.
     * @return a readable String.
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
