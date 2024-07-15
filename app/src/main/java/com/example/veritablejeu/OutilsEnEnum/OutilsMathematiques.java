package com.example.veritablejeu.OutilsEnEnum;

import java.util.Random;

public class OutilsMathematiques {

    /**
     * Renvoie une entier aléatoire dans l'intervalle [min, max].
     * @param min le minimum inclus.
     * @param max le maximum inclus.
     * @return un entier.
     */
    public static int random_ouvert(int min, int max) {
        Random random = new Random();
        return min + random.nextInt(max - min + 1);
    }

    /**
     * Revoie un nombre alétoire sous forme de float.
     * @param min la valeur minimale (incluse)
     * @param max la valeur maximale (exclue)
     * @return un float aléatoire dans l'intervalle [min, max[.
     */
    public static float random_float_semiOuvert(float min, float max) {
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
}
