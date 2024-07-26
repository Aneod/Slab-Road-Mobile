package com.example.veritablejeu.Game.InGame;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.veritablejeu.BackEnd.DataBases.FireStore.LevelsFiles.LevelFilesFireStoreWriter;
import com.example.veritablejeu.BackEnd.DataBases.Local.LevelFiles.PersonalFiles;
import com.example.veritablejeu.Game.Board.Board;
import com.example.veritablejeu.BackEnd.DataBases.Local.UserData;
import com.example.veritablejeu.Game.Game;
import com.example.veritablejeu.Game.InGame.Chronometre.Chronometre;
import com.example.veritablejeu.Game.InGame.FeuxArtifice.FeuxArtifice;
import com.example.veritablejeu.LevelsPanelMVC.Controller;
import com.example.veritablejeu.LevelsPanelMVC.LevelsPanel.Scroller.Scroller;
import com.example.veritablejeu.LevelsPanelMVC.LevelsReader.PersonalLevelsReader;
import com.example.veritablejeu.Navigation.Preset.NavigationInGame.NavigationInGame;
import com.example.veritablejeu.PopUp.PopUp;
import com.example.veritablejeu.Tools.LongToReadableTime;

public class InGame extends Game implements InterfaceInGame {

    private final Chronometre chronometre = new Chronometre();
    public int nombreDeCoups = 0;
    private FeuxArtifice feuxArtifice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new NavigationInGame(this);
        addExternalWalls();
        verifyCompletion();
        chronometre.start();
    }

    public Chronometre getChronometre() {
        return chronometre;
    }

    public int getNombreDeCoups() {
        return nombreDeCoups;
    }

    private void addExternalWalls() {
        plateauModulaireSet.forEach(Board::addExternalWalls);
    }

    public void verifyCompletion() {
        if(isComplete())
            whenCompleted();
    }

    private boolean isComplete() {
        return plateauModulaireSet.stream().allMatch(Board::isComplete);
    }

    public void whenCompleted() {
        chronometre.stop();
        showPopUp();
        fireWorksExplosion();
        SaveRecord.saveRecord(this);
    }

    private void fireWorksExplosion() {
        if(feuxArtifice == null) {
            feuxArtifice = new FeuxArtifice(this);
        }
        feuxArtifice.declencherFeux(500);
    }

    private void showPopUp() {
        if(isPersonalLevel()) {
            showPopUpSendToGlobal();
        } else {
            showPopUpLevelCompleted();
        }
    }

    private void showPopUpSendToGlobal() {
        PopUp popUp = getPopUp();
        popUp.showQuestion("VALIDATED LEVEL", getPopUpSendToGlobalText(),
                "SEND", this::showPopUpCheckLevelName,
                "DON'T", this::showPopUpLevelCompleted);
    }

    @NonNull
    private String getPopUpSendToGlobalText() {
        return "You have validated the level.\n" +
                "Time : " + getReadableTime() + "\n" +
                "Number of moves : " + nombreDeCoups + "\n\n" +
                "You can make your level available to all players by choosing \"send\". " +
                "If you do this, your level will be deleted from your personal files " +
                "and you will not be able to modify its global copy.";
    }

    private void showPopUpCheckLevelName() {
        PopUp popUp = getPopUp();
        popUp.showQuestion("SEND ?", getCheckLevelNameText(),
                "CONTINUE", this::sendToGlobal,
                "DON'T", this::showPopUpSendToGlobal);
    }

    @NonNull
    private String getCheckLevelNameText() {
        return "Your level is called by \"" + levelFile.name + "\". Is it okay for you ? " +
                "After sharing you will not be able to change it";
    }

    private void showPopUpLevelCompleted() {
        PopUp popUp = getPopUp();
        popUp.showQuestion("LEVEL COMPLETED", getPopUpLevelCompletedText(),
                "QUIT", this::goMenu,
                "RESTART", this::restart);
    }

    @NonNull
    private String getPopUpLevelCompletedText() {
        String normalText = "You have completed the level.\n" +
                "Time : " + getReadableTime() + "\n" +
                "Number of moves : " + nombreDeCoups;
        if(worldRecordBroken()) {
            String bonus = "NEW WORLD RECORD !\n\n";
            return bonus + normalText;
        }
        return normalText;
    }

    @NonNull
    private String getReadableTime() {
        long time = chronometre.getElapsedTime();
        return LongToReadableTime.getReadable(time);
    }

    public boolean worldRecordBroken() {
        if(!isGlobalLevel()) return false;
        long recordMondial = levelFile.time;
        long time = chronometre.getElapsedTime();
        return time < recordMondial;
    }

    private boolean isGlobalLevel() {
        return getCategory() == Scroller.LevelCategory.Global;
    }

    public boolean isPersonalLevel() {
        return getCategory() == Scroller.LevelCategory.Personal;
    }

    private Scroller.LevelCategory getCategory() {
        Controller controller = Controller.getInstance(this);
        return controller.getLevelCategory();
    }

    public void sendToGlobal() {
        levelFile.bestPlayer = UserData.getUsername(this.getApplicationContext());
        levelFile.time = chronometre.getElapsedTime();
        levelFile.movesNumber = nombreDeCoups;
        new Thread(() ->
                LevelFilesFireStoreWriter.addLevel(levelFile, isSuccess -> {
                    if(isSuccess) {
                        showToast("Level shared successfully");
                        deleteLevelFromPersonal();
                        goMenu();
                    } else {
                        showToast("Unable to shared level. Please retry later.");
                    }
                })
        ).start();
    }

    private void deleteLevelFromPersonal() {
        PersonalFiles personalFiles = PersonalFiles.getInstance(this);
        personalFiles.remove(levelFile, new PersonalFiles.BooleanCallback() {
            @Override
            public void onSuccess() {
                PersonalLevelsReader.getInstance(InGame.this).refreshLevelList(InGame.this);
            }

            @Override
            public void onFailure() {}
        });
    }

    public void showToast(String text) {
        runOnUiThread(() -> Toast.makeText(
                InGame.this, text, Toast.LENGTH_SHORT).show()
        );
    }

}
