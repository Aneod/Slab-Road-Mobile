package com.example.veritablejeu.BackEnd.DataBases.FireStore.LevelsFiles;

import com.example.veritablejeu.BackEnd.DataBases.FireStore.DataBaseFireStore;
import com.example.veritablejeu.BackEnd.DataBases.Local.PersonalBests.Association_id_record.Association_id_record;
import com.example.veritablejeu.BackEnd.LevelFile.LevelFile;
import com.google.firebase.firestore.CollectionReference;
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
        CollectionReference collectionReference = firebaseFirestore.collection(COLLECTION_PATH);
        DocumentReference documentReference = collectionReference.document();

        documentReference.set(levelFile)
                .addOnSuccessListener(e -> {
                    levelFile.id = documentReference.getId();
                    documentReference.set(levelFile)
                            .addOnSuccessListener(updateE -> booleanCallback.onCallback(true))
                            .addOnFailureListener(updateE -> booleanCallback.onCallback(false));
                })
                .addOnFailureListener(e -> booleanCallback.onCallback(false));
    }

    public interface BooleanCallback {
        void onCallback(boolean result);
    }

    /**
     * Update the best record and the best player pseudo of a {@link LevelFile}.
     * @param pseudo the new best player pseudo.
     */
    public static void saveNewWorldRecord(String pseudo, Association_id_record association) {
        if(association == null) return;
        FirebaseFirestore firebaseFirestore = DataBaseFireStore.getInstance().getFirebaseFirestore();
        DocumentReference docRef = firebaseFirestore.collection(COLLECTION_PATH).document(association.id);
        docRef.update(
                "bestPlayer", pseudo,
                "time", association.time,
                "movesNumber", association.numberOfMoves
        );
    }
}
