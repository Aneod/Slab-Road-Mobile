package com.example.veritablejeu.LevelsPanelMVC.LevelsReader;

import android.util.Log;

import com.example.veritablejeu.BackEnd.DataBases.FireStore.DataBaseFireStore;
import com.example.veritablejeu.BackEnd.LevelFile.LevelFile;
import com.example.veritablejeu.Tools.MathematicTools;
import com.example.veritablejeu.Tools.SafeSubList;
import com.google.firebase.firestore.AggregateQuery;
import com.google.firebase.firestore.AggregateQuerySnapshot;
import com.google.firebase.firestore.AggregateSource;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GlobalLevelsReader extends LevelsReader {

    private static final String TAG = "LevelFilesFireStoreReader";
    private static final String COLLECTION_PATH = DataBaseFireStore.getLevelsfilesCollectionPath();

    private static GlobalLevelsReader instance;
    private final List<LevelFile> levelFilesList = new ArrayList<>();
    private int listSize = -1;
    private DocumentSnapshot lastVisible;

    private GlobalLevelsReader(){}

    public static GlobalLevelsReader getInstance() {
        if(instance == null) {
            instance = new GlobalLevelsReader();
        }
        return instance;
    }

    public void clearLevelsList() {
        levelFilesList.clear();
        listSize = -1;
        lastVisible = null;
    }

    @Override
    public void get(int from, int to, final LevelListCallback callback) {
        int lastKnownIndex = levelFilesList.size();
        boolean lastIndexIsKnown = lastKnownIndex >= Math.min(to, listSize);
        if(!lastIndexIsKnown) {
            int howManyLevelsToResearch = to - lastKnownIndex;
            loadNextLevels(howManyLevelsToResearch, new LevelListCallback() {
                @Override
                public void onCallback(List<LevelFile> allFoundLevels) {
                    List<LevelFile> list = SafeSubList.get(levelFilesList, from, to);
                    callback.onCallback(list);
                }

                @Override
                public void diconnected() {
                    callback.diconnected();
                }

                @Override
                public void localDataNotFound() {
                }
            });
        } else {
            List<LevelFile> list = SafeSubList.get(levelFilesList, from, to);
            callback.onCallback(list);
        }
    }

    private void loadNextLevels(int howManyLevelsToLoad, final LevelListCallback callback) {
        FirebaseFirestore firebaseFirestore = DataBaseFireStore.getInstance().getFirebaseFirestore();
        Query query;
        if (lastVisible == null) {
            query = firebaseFirestore.collection(COLLECTION_PATH)
                    .orderBy("date", Query.Direction.DESCENDING)
                    .limit(howManyLevelsToLoad);
        } else {
            query = firebaseFirestore.collection(COLLECTION_PATH)
                    .orderBy("date", Query.Direction.DESCENDING)
                    .startAfter(lastVisible)
                    .limit(howManyLevelsToLoad);
        }

        query.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                QuerySnapshot querySnapshot = task.getResult();
                // The querySnapshot is empty even if there is a connection problem !
                if (querySnapshot != null && !querySnapshot.isEmpty()) {
                    List<LevelFile> levelFiles = new ArrayList<>();
                    for (DocumentSnapshot document : querySnapshot.getDocuments()) {
                        LevelFile levelFile = document.toObject(LevelFile.class);
                        levelFiles.add(levelFile);
                    }
                    lastVisible = querySnapshot.getDocuments().get(querySnapshot.size() - 1);
                    levelFilesList.addAll(levelFiles);
                    callback.onCallback(levelFiles);
                } else {
                    callback.diconnected();
                }
            } else {
                callback.diconnected();
            }
        });
    }

    @Override
    public void getSize(final LevelsReader.CountCallback callback) {
        if(listSize != -1) {
            callback.onCallback(listSize);
            return;
        }
        FirebaseFirestore firebaseFirestore = DataBaseFireStore.getInstance().getFirebaseFirestore();
        AggregateQuery countQuery = firebaseFirestore.collection(COLLECTION_PATH).count();
        countQuery.get(AggregateSource.SERVER).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                AggregateQuerySnapshot snapshot = task.getResult();
                long count = snapshot.getCount();
                int count_int = MathematicTools.long_to_int(count);
                listSize = count_int;
                callback.onCallback(count_int);
            } else {
                Exception exception = task.getException();
                Log.e(TAG, "Error fetching document count", exception);
                callback.diconnected();
            }
        });
    }
}
