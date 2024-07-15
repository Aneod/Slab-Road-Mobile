package com.example.veritablejeu.MediaPlayerInstance;

import android.content.Context;

import androidx.annotation.NonNull;

import com.example.veritablejeu.MediaPlayerInstance.BanqueDeSon.BanqueDeSon;

/**
 * Dans cette classe, il y a quelques valeurs statiques importantes :
 * L'objet MediaPlayer.
 * Le volume de l'objet MediaPlayer. Sur l'interval [0.0f, 1.0f]. Initialisé à 1.0f.
 * La musique actuellement jouée par le MediaPlayer. Initialisée à la musique du menu.
 * Que l'on récupérera depuis la classe {@link BanqueDeSon}.
 * <p>
 * Certaines fonctions évidentes n'ont pas été crées :
 * Stopper le son : Il y a toujours de la musique dans le jeu.
 * getPisteActuelle : Pas d'intérêt.
 */
public interface IMediaPlayerInstance {

    /**
     * Renvoie le volume actuel du son.
     * @return la valeur de la variable statique du volume.
     * En toute logique, un flottant dans l'interval [0.0f, 1.0f].
     */
    float getVolume();

    /**
     * Modifie le volume du son pour celui donné.
     * @param volume Normalement compris dans l'interval [0.0f, 1.0f].
     *               Le volume sera mis à 0.0f si le paramètre donné est inférieur à l'interval.
     *               1.0f si supérieur.
     */
    void setVolume(float volume);

    /**
     * Change la musique jouée.
     * @param musique Un des fichiers son du jeu.
     *                L'objet MediaPlayer changera alors de ressource (fichier) sonore.
     */
    void setMusiqueActuelle(@NonNull Context context, int musique);

    /**
     * Change la musique jouée.
     * @param numero Le numéro de la musique que l'on souhaite jouer.
     *               L'objet MediaPlayer changera alors de ressource (fichier) sonore.
     */
    void activerPisteDuNumero(@NonNull Context context, int numero);

    /**
     * Change la musique actuellement jouée pour celle du menu.
     */
    void activerLaMusiqueDuMenu(@NonNull Context context);
}
