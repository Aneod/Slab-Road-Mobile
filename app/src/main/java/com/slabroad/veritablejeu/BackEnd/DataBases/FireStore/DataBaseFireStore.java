package com.slabroad.veritablejeu.BackEnd.DataBases.FireStore;

import com.google.firebase.firestore.FirebaseFirestore;

public class DataBaseFireStore {

    private static final String LEVELSFILES_COLLECTION_PATH = "levels";
    private static final String PSEUDO_COLLECTION_PATH = "userNames";

    public static String getLevelsfilesCollectionPath() {
        return LEVELSFILES_COLLECTION_PATH;
    }

    public static String getPseudoCollectionPath() {
        return PSEUDO_COLLECTION_PATH;
    }

    private static DataBaseFireStore instance;
    private final FirebaseFirestore firebaseFirestore;

    private DataBaseFireStore() {
        firebaseFirestore = FirebaseFirestore.getInstance();
    }

    public static synchronized DataBaseFireStore getInstance() {
        if (instance == null) {
            instance = new DataBaseFireStore();
        }
        return instance;
    }

    public FirebaseFirestore getFirebaseFirestore() {
        return firebaseFirestore;
    }

}
