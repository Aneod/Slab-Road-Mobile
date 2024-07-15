package com.example.veritablejeu.Tools;

import android.graphics.Point;

import java.util.Random;

public class MathematicTools {

    /**
     * Renvoie une entier aléatoire dans l'intervalle [min, max].
     * @param min le minimum inclus.
     * @param max le maximum inclus.
     * @return un entier.
     */
    public static int random_open(int min, int max) {
        Random random = new Random();
        return min + random.nextInt(max - min + 1);
    }

    /**
     * Revoie un nombre alétoire sous forme de float.
     * @param min la valeur minimale (incluse)
     * @param max la valeur maximale (exclue)
     * @return un float aléatoire dans l'intervalle [min, max[.
     */
    public static float random_float_halfOpen(float min, float max) {
        Random random = new Random();
        return min + random.nextFloat() * (max - min);
    }

    /**
     * Convertie simplement un long en int. Et si la valeur du long dépasse les limites
     * des int, alors on retoure Integer.MIN_VALUE ou Integer.MAX_VALUE.
     * @param longValue le long à convertir.
     * @return un int normalement égal au long.
     */
    public static int long_to_int(long longValue) {
        if(longValue < Integer.MIN_VALUE) {
            return Integer.MIN_VALUE;
        }
        if(longValue > Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        }
        return (int) longValue;
    }

    public static int getDistanceX(Point from, Point to) {
        if(from == null || to == null) return 0;
        return to.x - from.x;
    }

    public static int getDistanceY(Point from, Point to) {
        if(from == null || to == null) return 0;
        return to.y - from.y;
    }

    public static int getDistance(Point from, Point to) {
        if(from == null || to == null) return 0;
        int dx = getDistanceX(from, to);
        int dy = getDistanceY(from, to);
        return (int) Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
    }

    public static double getAngle(Point from, Point to) {
        if(from == null || to == null) return 0.0;
        int dx = getDistanceX(from, to);
        int dy = getDistanceY(from, to);
        double angleRad = Math.atan2(dy, dx);
        double angleDeg = angleRad * 180 / Math.PI;
        if(angleDeg < 0) {
            return angleDeg + 360.0;
        }
        return angleDeg;
    }
}
