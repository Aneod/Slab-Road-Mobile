package com.example.veritablejeu.BackEnd.DataBases.FireStore.Pseudo;

import com.example.veritablejeu.BackEnd.DataBases.FireStore.DataBaseFireStore;
import com.example.veritablejeu.Menu.DemanderUnUserName;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class DatabaseFirestorePseudo {

    private static final String COLLECTION_PATH = DataBaseFireStore.getPseudoCollectionPath();


    /**
     * Take a pseudonym and try to add it in the FireStore database.
     * Then the {@link BooleanCallback} return <i>true</i> if is a success, <i>false</i> otherwise.
     * @param pseudonym the pseudonym to add. It's a {@link DemanderUnUserName.Pseudonym} object.
     * @param booleanCallback the {@link BooleanCallback} who return the results of the operation.
     */
    public static void add(DemanderUnUserName.Pseudonym pseudonym, final BooleanCallback booleanCallback) {
        if(pseudonym == null) {
            booleanCallback.onCallback(false);
            return;
        }
        FirebaseFirestore firebaseFirestore = DataBaseFireStore.getInstance().getFirebaseFirestore();
        CollectionReference usersRef = firebaseFirestore.collection(COLLECTION_PATH);
        String pseudo = pseudonym.getPseudo();
        usersRef.whereEqualTo(COLLECTION_PATH, pseudo).get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                QuerySnapshot querySnapshot = task.getResult();
                if (querySnapshot.isEmpty()) {
                    usersRef.document().set(pseudonym).addOnCompleteListener(setTask -> booleanCallback.onCallback(setTask.isSuccessful()));
                } else {
                    booleanCallback.onCallback(false);
                }
            } else {
                booleanCallback.onCallback(false);
            }
        });
    }

    public interface BooleanCallback {
        void onCallback(boolean result);
    }

}
