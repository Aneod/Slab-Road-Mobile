package com.slabroad.veritablejeu.MediaPlayerInstance;

import com.slabroad.veritablejeu.MediaPlayerInstance.BanqueDeSon.BanqueDeSon;

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
     * Set the music volume. Can't work if no track have been played yet.
     * <br>
     * The volume is in [0.0f, 1.0f].
     * <br>
     * If the given volume is < to lower bound, set volume to 0.0f.
     * <br>
     * If the given volume is > to higher bound, set volume to 1.0f.
     * @param volume the new volume.
     */
    void setVolume(float volume);

    /**
     * Change la musique jouée.
     * @param musique Un des fichiers son du jeu.
     *                L'objet MediaPlayer changera alors de ressource (fichier) sonore.
     */
    void setTrack(int musique);

    /**
     * Change la musique jouée.
     *
     * @param numero Le numéro de la musique que l'on souhaite jouer.
     *               L'objet MediaPlayer changera alors de ressource (fichier) sonore.
     */
    void playNewMusic(int numero);

    /**
     * Change la musique actuellement jouée pour celle du menu.
     */
    void playMainPageMusic();

    /**
     * Play the previous track of the current.
     */
    void playPrevious();

    /**
     * Play the next track of the current.
     */
    void playNext();

    /**
     * @return the number in the jukebox of the current track.
     */
    int getCurrentTrackNumber();
}
