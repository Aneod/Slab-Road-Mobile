package com.example.veritablejeu.Navigation.Preset.NavigationInGame;

import androidx.annotation.NonNull;
import androidx.core.util.Consumer;

import com.example.veritablejeu.BainDeSavon.BainDeSavon;
import com.example.veritablejeu.Game.InGame.InGame;
import com.example.veritablejeu.Navigation.Association_Symbole_Fonction.Association_Symbole_Fonction;
import com.example.veritablejeu.PopUp.ContenuPopUp.Message.Message;
import com.example.veritablejeu.PopUp.ContenuPopUp.Parametres.Parametres;
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
                popUp, "QUITTER", "Retourner à la page principale ?", "OUI", runnableA, "NON", runnableB
        );
        popUp.setContenu(contenuPopUp);
    }

    private void propositionReset() {
        /*
        PopUp popUp = inGame.getPopUp();
        Plateau plateau = inGame.getPlateau();
        if(plateau != null) {
            Runnable runnableA = () -> {
                plateau.resetPlateau();
                popUp.cacher();
            };
            Runnable runnableB = popUp::cacher;
            Question contenuPopUp = new Question(
                    popUp, "RECOMMENCER", "Voulez-vous réinitialiser la progression ?", "OUI", runnableA, "NON", runnableB
            );
            popUp.setContenu(contenuPopUp);
        }

         */
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
        PopUp popUp = inGame.getPopUp();

        List<Parametres.Title_Effect_Association> components = new ArrayList<>();

        MediaPlayerInstance mediaPlayerInstance = MediaPlayerInstance.getInstance();
        Consumer<Float> consumerVolume = value -> {
            if(value != null) {
                mediaPlayerInstance.setVolume(value);
            }
        };
        float currentVolume = mediaPlayerInstance.getVolume();
        Parametres.Title_Consumer_Association curseurVolume = new Parametres.Title_Consumer_Association(
                "Volume musiques", consumerVolume, currentVolume);
        components.add(curseurVolume);

        Consumer<Float> speedConsumer = value -> {
            if(value != null) {
                int value_on1000 = (int) (value * 1000);
                int valeurInversee = 1000 - value_on1000;
                //ParametresPlateau.setDureeDeplacement(valeurInversee);
            }
        };
        /*
        float currentSpeed = 1.0f - (float) ParametresPlateau.getDureeDeplacement() / 1000;
        Parametres.Title_Consumer_Association speedCursor = new Parametres.Title_Consumer_Association(
                "Vitesse de déplacements", speedConsumer, currentSpeed);
        components.add(speedCursor);

        Runnable activeEffectFlashs = () -> ParametresPlateau.setFlashsActives(true);
        Runnable disactiveEffectFlashs = () -> ParametresPlateau.setFlashsActives(false);
        boolean currentFlash = ParametresPlateau.isFlashesEnable();
        Parametres.Title_Runnables_Association boutonOnOffFlash = new Parametres.Title_Runnables_Association(
                "Flashs lumineux", activeEffectFlashs, disactiveEffectFlashs, currentFlash);
        components.add(boutonOnOffFlash);
         */

        BainDeSavon bainDeSavon = BainDeSavon.getInstance(inGame);
        Runnable activeEffectBulles = () -> {
            bainDeSavon.show();
            bainDeSavon.resume_animations();
        };
        Runnable disactiveEffectBulles = () -> {
            bainDeSavon.hide();
            bainDeSavon.pause_animations();
        };
        boolean currentBulles = bainDeSavon.getBullesVisibles();
        Parametres.Title_Runnables_Association boutonOnOffBulles = new Parametres.Title_Runnables_Association(
                "Particules d'arrière-plan", activeEffectBulles, disactiveEffectBulles, currentBulles);
        components.add(boutonOnOffBulles);

        /*
        Squares squares = inGame.getPlateau().getSquares();
        Runnable runnableA = () -> squares.setAideAuxDeplacements(true);
        Runnable runnableB = () -> squares.setAideAuxDeplacements(false);
        boolean currentMoveHelper = ParametresPlateau.getAideAuxDeplacement();
        Parametres.Title_Runnables_Association boutonOnOffMoveHelper = new Parametres.Title_Runnables_Association(
                "Aide aux déplacements", runnableA, runnableB, currentMoveHelper);
        components.add(boutonOnOffMoveHelper);

        Parametres parametres = new Parametres(popUp, components);
        popUp.setContenu(parametres);

        popUp.montrer();

         */
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
