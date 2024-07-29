package com.slabroad.veritablejeu.BackEnd.DataBases.FireStore.Pseudo;

import com.slabroad.veritablejeu.BackEnd.DataBases.FireStore.DataBaseFireStore;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class DatabaseFirestorePseudo {

    private static final String COLLECTION_PATH = DataBaseFireStore.getPseudoCollectionPath();
    private static final String KEY = "name";


    /**
     * Take a pseudonym and try to add it in the FireStore database.
     * @param pseudonym the pseudonym to add.
     * @param callback who return the results of the operation.
     */
    public static void add(String pseudonym, final Callback callback) {
        if(pseudonym == null) {
            callback.invalidePseudonym();
            return;
        }
        FirebaseFirestore firebaseFirestore = DataBaseFireStore.getInstance().getFirebaseFirestore();
        CollectionReference usersRef = firebaseFirestore.collection(COLLECTION_PATH);

        Map<String, Object> pseudonymData = new HashMap<>();
        pseudonymData.put(KEY, pseudonym);

        usersRef.whereEqualTo(KEY, pseudonym).get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                QuerySnapshot querySnapshot = task.getResult();
                if (querySnapshot.isEmpty()) {
                    usersRef.document().set(pseudonymData)
                            .addOnCompleteListener(setTask -> {
                                if(setTask.isSuccessful()) {
                                    callback.onSuccess();
                                } else {
                                    callback.disconnected();
                                }
                            }
                    );
                } else {
                    callback.alreadyTaken();
                }
            } else {
                callback.disconnected();
            }
        });
    }

    public interface Callback {
        void onSuccess();
        void invalidePseudonym();
        void alreadyTaken();
        void disconnected();
    }

}
