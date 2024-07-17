package com.example.veritablejeu.Navigation.Preset.NavigationInGame;

import androidx.annotation.NonNull;
import androidx.core.util.Consumer;

import com.example.veritablejeu.BainDeSavon.BainDeSavon;
import com.example.veritablejeu.Game.InGame.InGame;
import com.example.veritablejeu.Navigation.Association_Symbole_Fonction.Association_Symbole_Fonction;
import com.example.veritablejeu.Navigation.Preset.NavigationInGame.GameSettings.GameSettings;
import com.example.veritablejeu.PopUp.ContenuPopUp.Message.Message;
import com.example.veritablejeu.PopUp.ContenuPopUp.SettingsPanel.SettingsPanel;
import com.example.veritablejeu.PopUp.PopUp;
import com.example.veritablejeu.Navigation.Navigation;
import com.example.veritablejeu.PopUp.ContenuPopUp.QuestionFermee.Question;
import com.example.veritablejeu.MediaPlayerInstance.MediaPlayerInstance;
import com.example.veritablejeu.R;

import java.util.ArrayList;
import java.util.List;

public class NavigationInGame extends Navigation {

    private final InGame inGame;

    private void propositionQuitter() {
        PopUp popUp = inGame.getPopUp();
        Runnable runnableA = () -> {
            inGame.retourAuMenu();
            popUp.cacher();
        };
        Runnable runnableB = popUp::cacher;
        Question contenuPopUp = new Question(
                popUp, "EXIT", "Return to main page ? Your progress will be lost.", "YES", runnableA, "NO", runnableB
        );
        popUp.setContenu(contenuPopUp);
    }

    private void propositionReset() {
        PopUp popUp = inGame.getPopUp();
        Runnable runnableA = popUp::cacher;
        Runnable runnableB = popUp::cacher;
        Question contenuPopUp = new Question(
                popUp, "RESET", "Do you want to reset progress ?", "YES", runnableA, "NO", runnableB
        );
        popUp.setContenu(contenuPopUp);
    }

    private void recadrage() {
        PopUp popUp = inGame.getPopUp();
        Message message = new Message(popUp, "SYSTEME", "Le plateau a été correctement recentré.", 1000);
        popUp.setContenu(message);
        recadragePlateauParLeBoutonRecadrage();
    }

    private void recadragePlateauParLeBoutonRecadrage() {
        /*
        Plateau plateau = inGame.getPlateau();
        if(plateau != null) {
            OnToucheListener onToucheListener = inGame.getOnToucheListener();
            if(onToucheListener != null) {
                onToucheListener.setScaleFactor(1.0f);
                plateau.recadrage();
            }
        }*/
    }

    private void manuel() {
        PopUp popUp = inGame.getPopUp();
        popUp.afficherManuel();
    }

    private void parametres() {
        GameSettings.showGameSettingsPopUp(inGame);
    }

    private List<Association_Symbole_Fonction> getAssociations() {
        List<Association_Symbole_Fonction> associations = new ArrayList<>();

        associations.add(new Association_Symbole_Fonction(
                R.drawable.symbole_retour, this::propositionQuitter)
        );
        associations.add(new Association_Symbole_Fonction(
                R.drawable.symbole_reset, this::propositionReset)
        );
        associations.add(new Association_Symbole_Fonction(
                R.drawable.symbole_recadrage, this::recadrage)
        );
        associations.add(new Association_Symbole_Fonction(
                R.drawable.point_interrogation, this::manuel)
        );
        associations.add(new Association_Symbole_Fonction(
                R.drawable.symbole_parametres, this::parametres)
        );

        return associations;
    }

    public NavigationInGame(@NonNull InGame inGame) {
        super(inGame);
        this.inGame = inGame;
        List<Association_Symbole_Fonction> associations = getAssociations();
        setContenu(associations);
    }
}
