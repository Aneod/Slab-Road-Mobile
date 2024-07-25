package com.example.veritablejeu.BackEnd.DataBases.FireStore.LevelsFiles;

import com.example.veritablejeu.BackEnd.DataBases.FireStore.DataBaseFireStore;
import com.example.veritablejeu.BackEnd.LevelFile.LevelFile;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class LevelFilesFireStoreWriter {

    private static final String COLLECTION_PATH = DataBaseFireStore.getLevelsfilesCollectionPath();

    /**
     * Try to add a {@link LevelFile} in the FireStore database.
     * Then the {@link LevelFilesFireStoreWriter.BooleanCallback} return <i>true</i> if is a success, <i>false</i> otherwise.
     * @param levelFile the level to add.
     * @param booleanCallback the {@link LevelFilesFireStoreWriter.BooleanCallback} who return the results of the operation.
     */
    public static void addLevel(LevelFile levelFile, final BooleanCallback booleanCallback) {
        FirebaseFirestore firebaseFirestore = DataBaseFireStore.getInstance().getFirebaseFirestore();
        firebaseFirestore.collection(COLLECTION_PATH).document()
                .set(levelFile)
                .addOnSuccessListener(e -> booleanCallback.onCallback(true))
                .addOnFailureListener(e -> booleanCallback.onCallback(false));
    }

    public interface BooleanCallback {
        void onCallback(boolean result);
    }

    /**
     * Update the best record and the best player pseudo of a {@link LevelFile}.
     * @param levelId the id of the {@link LevelFile} to change.
     * @param pseudo the new best player pseudo.
     * @param numberOfMoves the number of moves to indicates.
     * @param time the time to indicates.
     */
    public static void saveNewWorldRecord(String levelId, String pseudo, int numberOfMoves, long time) {
        FirebaseFirestore firebaseFirestore = DataBaseFireStore.getInstance().getFirebaseFirestore();
        DocumentReference docRef = firebaseFirestore.collection(COLLECTION_PATH).document(levelId);
        docRef.update(
                "bestPlayer", pseudo,
                "time", time,
                "movesNumber", numberOfMoves
        );
    }
}
