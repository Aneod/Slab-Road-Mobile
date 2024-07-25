package com.example.veritablejeu.Game.InGame;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.veritablejeu.BackEnd.DataBases.FireStore.LevelsFiles.LevelFilesFireStoreWriter;
import com.example.veritablejeu.Game.Board.Board;
import com.example.veritablejeu.BackEnd.LevelFile.LevelFile;
import com.example.veritablejeu.BackEnd.DataBases.Local.PersonalBests.PersonalBests;
import com.example.veritablejeu.BackEnd.DataBases.Local.UserData;
import com.example.veritablejeu.Game.InGame.ATHFinal.ATHFinal;
import com.example.veritablejeu.Game.Game;
import com.example.veritablejeu.Game.InGame.Chronometre.Chronometre;
import com.example.veritablejeu.LevelsPanelMVC.Controller;
import com.example.veritablejeu.LevelsPanelMVC.LevelsPanel.Scroller.Scroller;
import com.example.veritablejeu.Navigation.Preset.NavigationInGame.NavigationInGame;

public class InGame extends Game implements InterfaceInGame {

    private boolean niveauDejaEnvoyeDansLeMondial = false;
    private ATHFinal athFinal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new NavigationInGame(this);
        athFinal = new ATHFinal(this);
        addExternalWalls();
        verifyCompletion();
    }

    private void addExternalWalls() {
        plateauModulaireSet.forEach(Board::addExternalWalls);
    }

    @Override
    public void niveauTermine() {
        enregistrerLeRecord(levelFile);
        popUp.hide();

        /*
        Plateau plateauJouable = getPlateau();
        if(plateauJouable != null) {
            plateauJouable.animationFin();
        }*/

        athFinal.apparition(1000, 200);
    }

    private void enregistrerRecordPerso(String levelId, int numberOfMoves, long time) {
        PersonalBests repository = PersonalBests.getInstance(this.getApplicationContext());
        repository.set(levelId, numberOfMoves, time);
    }

    private void enregistrerRecordMondial(String levelId, int numberOfMoves, long time) {
        String pseudoDUNouveauChampion = UserData.getUsername(this.getApplicationContext());
        LevelFilesFireStoreWriter.saveNewWorldRecord(
                levelId, pseudoDUNouveauChampion, numberOfMoves, time
        );
    }

    private void enregistrerLeRecord(@NonNull LevelFile levelFile) {
        Controller controller = Controller.getInstance(this);
        Scroller.LevelCategory levelCategory = controller.getLevelCategory();
        boolean noRecord = levelCategory == Scroller.LevelCategory.Personal;
        if(noRecord) return;

        String levelId = String.valueOf(levelFile.id);
        int numberOfMoves = 777;
        Chronometre chronometre = new Chronometre();
        long tempsEffectue = chronometre.getElapsedTime();

        enregistrerRecordPerso(levelId, numberOfMoves, tempsEffectue);

        boolean niveauMondial = levelCategory == Scroller.LevelCategory.Global;
        if(niveauMondial) {
            long recordMondial = levelFile.time;
            boolean recordMondialBattu = tempsEffectue < recordMondial;
            if(recordMondialBattu) {
                enregistrerRecordMondial(levelId, numberOfMoves, tempsEffectue);
            }
        }
    }

    public void recommencerPlateauApresAvoirFini() {
        /*
        Plateau plateauJouable = getPlateau();
        if(plateauJouable != null) {
            plateauJouable.resetPlateau();
            plateauJouable.animationDepart(0);
            plateauJouable.setNiveauTermine(false);
        }
        if(athFinal != null) {
            athFinal.disparition(150);
        }*/
    }

    public void envoyerLeNiveauDansLeMondial() {
        if(niveauDejaEnvoyeDansLeMondial) {
            Toast.makeText(this.getApplicationContext(), "Votre niveau est déjà en ligne", Toast.LENGTH_SHORT).show();
            return;
        }
        new Thread(() -> {
            levelFile.bestPlayer = UserData.getUsername(this.getApplicationContext());
            int numberOfMoves = 666;
            Chronometre chronometre = new Chronometre();
            long time = chronometre.getElapsedTime();
            levelFile.movesNumber = numberOfMoves;
            levelFile.time = time;
            LevelFilesFireStoreWriter.addLevel(levelFile, isSuccess -> {
                String textToPrint;
                if(isSuccess) {
                    textToPrint = "Votre niveau est en ligne";
                } else {
                    textToPrint = "Echec de la mise en ligne";
                }
                runOnUiThread(() ->
                        Toast.makeText(this.getApplicationContext(), textToPrint, Toast.LENGTH_SHORT)
                        .show());
            });
            niveauDejaEnvoyeDansLeMondial = true;
        }).start();
    }

}
