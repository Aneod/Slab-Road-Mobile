package com.slabroad.veritablejeu.Game.InGame;

import com.slabroad.veritablejeu.BackEnd.DataBases.FireStore.LevelsFiles.LevelFilesFireStoreWriter;
import com.slabroad.veritablejeu.BackEnd.DataBases.Local.PersonalBests.Association_id_record.Association_id_record;
import com.slabroad.veritablejeu.BackEnd.DataBases.Local.PersonalBests.PersonalBests;
import com.slabroad.veritablejeu.BackEnd.DataBases.Local.UserData;

public class SaveRecord {

    public static void saveRecord(InGame inGame) {
        if(inGame == null || inGame.isPersonalLevel()) return;
        String levelId = inGame.getLevelFiles().id;
        long time = inGame.getChronometre().getElapsedTime();
        int numberOfMoves = inGame.getNombreDeCoups();

        Association_id_record association =
                new Association_id_record(levelId, numberOfMoves, time);
        savePersonalBest(association, inGame);
        saveGlobalBest(association, inGame);
    }

    private static void savePersonalBest(Association_id_record association, InGame inGame) {
        if(inGame == null) return;
        PersonalBests repository = PersonalBests.getInstance(inGame.getApplicationContext());
        repository.set_ifBestOf(association, new PersonalBests.BooleanCallback() {
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

    private static void saveGlobalBest(Association_id_record association, InGame inGame) {
        if(inGame == null) return;
        if(inGame.worldRecordBroken()) {
            String pseudoDUNouveauChampion = UserData.getUsername(inGame.getApplicationContext());
            LevelFilesFireStoreWriter.saveNewWorldRecord(pseudoDUNouveauChampion, association);
        }
    }

}
