package com.example.veritablejeu.Navigation.Preset.NavigationEditeur.Settings;

import androidx.annotation.NonNull;

import com.example.veritablejeu.Game.Game;
import com.example.veritablejeu.MediaPlayerInstance.BanqueDeSon.BanqueDeSon;
import com.example.veritablejeu.MediaPlayerInstance.MediaPlayerInstance;
import com.example.veritablejeu.PopUp.ContenuPopUp.QuestionFermee.Question;
import com.example.veritablejeu.PopUp.PopUp;

public class MusicSettings {

    @NonNull
    private static String getTextPopUp(Game game) {
        MediaPlayerInstance mediaPlayerInstance = MediaPlayerInstance.getInstance(game);
        int currentTrackNumber = mediaPlayerInstance.getCurrentTrackNumber();
        int jukeboxSize = BanqueDeSon.getNumberOfMusics();
        String trackNumberIndicator = (currentTrackNumber + 1) + " / " + jukeboxSize;
        return "Select a music track.\n" + trackNumberIndicator;
    }

    private static void setPrevious(Game game, Question question) {
        if(question == null) return;
        MediaPlayerInstance mediaPlayerInstance = MediaPlayerInstance.getInstance(game);
        mediaPlayerInstance.playPrevious();
        question.setText(getTextPopUp(game));
    }

    private static void setNext(Game game, Question question) {
        if(question == null) return;
        MediaPlayerInstance mediaPlayerInstance = MediaPlayerInstance.getInstance(game);
        mediaPlayerInstance.playNext();
        question.setText(getTextPopUp(game));
    }

    public static void showMusicSettings(Game game) {
        if(game == null) return;
        PopUp popUp = game.getPopUp();
        Question contenuPopUp = new Question(
                popUp, "JUKEBOX", getTextPopUp(game), "PREVIOUS", null, "NEXT", null
        );
        contenuPopUp.setRunnableA(() -> setPrevious(game, contenuPopUp));
        contenuPopUp.setRunnableB(() -> setNext(game, contenuPopUp));
        popUp.setContenu(contenuPopUp);
    }
}
