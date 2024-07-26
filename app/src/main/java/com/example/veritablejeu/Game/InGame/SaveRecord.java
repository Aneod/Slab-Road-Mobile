package com.example.veritablejeu.Game.InGame;

import com.example.veritablejeu.BackEnd.DataBases.FireStore.LevelsFiles.LevelFilesFireStoreWriter;
import com.example.veritablejeu.BackEnd.DataBases.Local.PersonalBests.PersonalBests;
import com.example.veritablejeu.BackEnd.DataBases.Local.UserData;

public class SaveRecord {

    public static void saveRecord(InGame inGame) {
        if(inGame == null || inGame.isPersonalLevel()) return;
        String levelId = String.valueOf(inGame.getLevelFiles().id);
        long time = inGame.getChronometre().getElapsedTime();
        int numberOfMoves = inGame.getNombreDeCoups();

        savePersonalBest(levelId, numberOfMoves, time, inGame);
        saveGlobalBest(levelId, numberOfMoves, time, inGame);
    }

    private static void savePersonalBest(String levelId, int numberOfMoves, long time, InGame inGame) {
        if(inGame == null) return;
        PersonalBests repository = PersonalBests.getInstance(inGame.getApplicationContext());
        repository.set_ifBestOf(levelId, numberOfMoves, time, new PersonalBests.BooleanCallback() {
            @Override
            public void onSuccess() {
                inGame.showToast("New record saved successfully");
            }

            @Override
            public void onFailure() {
                inGame.showToast("Unable to saved record");
            }
        });
    }

    private static void saveGlobalBest(String levelId, int numberOfMoves, long time, InGame inGame) {
        if(inGame == null) return;
        if(inGame.worldRecordBroken()) {
            String pseudoDUNouveauChampion = UserData.getUsername(inGame.getApplicationContext());
            LevelFilesFireStoreWriter.saveNewWorldRecord(
                    levelId, pseudoDUNouveauChampion, numberOfMoves, time
            );
        }
    }

}
