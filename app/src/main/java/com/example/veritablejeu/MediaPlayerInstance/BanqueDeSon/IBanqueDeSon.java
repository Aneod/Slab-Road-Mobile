package com.example.veritablejeu.MediaPlayerInstance.BanqueDeSon;

/**
 * Cette classe consiste en la gestion des fichiers sonores de l'application.
 * <p>
 * Valeurs statiques importantes :
 * La musique du menu. Qui est finale.
 */
public interface IBanqueDeSon {

    /**
     * Les musiques du jeu sont nommées pas un numéro. Cette fonction utilise un switch
     * pour récupérer la musique correspondante au numéro donnée en paramètre.
     * @param numero le numéro de la musique que l'on cherche.
     *               Normalement ce numéro est positif et ne dépasse pas (et n'égal pas) le nombre total
     *               de musique dans le switch.
     *               Mais dans le cas contraire, la musique du menu sera renvoyée par défaut.
     * @return la musique correspondante.
     */
    int getMusiqueDuNumero(int numero);

    /**
     * Renvoie la musique jouée dans le menu du jeu.
     * @return la valeur de l'objet int de la musique du menu.
     */
    int getMusiqueDuMenu();

    /**
     * Retourne le nombre total de piste audio.
     * @return un entier.
     */
    int getNombreTotalDePistes();
}
