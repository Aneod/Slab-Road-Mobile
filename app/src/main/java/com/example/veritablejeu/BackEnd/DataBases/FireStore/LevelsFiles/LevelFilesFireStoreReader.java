package com.example.veritablejeu.BackEnd.DataBases.FireStore.LevelsFiles;

import android.util.Log;

import com.example.veritablejeu.BackEnd.DataBases.FireStore.DataBaseFireStore;
import com.example.veritablejeu.BackEnd.LevelFile.LevelFile;
import com.example.veritablejeu.Tools.MathematicTools;
import com.google.firebase.firestore.AggregateQuery;
import com.google.firebase.firestore.AggregateQuerySnapshot;
import com.google.firebase.firestore.AggregateSource;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class LevelFilesFireStoreReader implements ILevelFilesFireStoreReader {

    private static final String TAG = "LevelFilesFireStoreReader";
    private static final String COLLECTION_PATH = DataBaseFireStore.getLevelsfilesCollectionPath();

    private static LevelFilesFireStoreReader instance;
    private final List<LevelFile> levelFileList = new ArrayList<>();
    private DocumentSnapshot lastVisible;

    private LevelFilesFireStoreReader(){}

    public static LevelFilesFireStoreReader getInstance() {
        if(instance == null) {
            instance = new LevelFilesFireStoreReader();
        }
        return instance;
    }

    @Override
    public void getSize(final CountCallback countCallback) {
        FirebaseFirestore firebaseFirestore = DataBaseFireStore.getInstance().getFirebaseFirestore();
        AggregateQuery countQuery = firebaseFirestore.collection(COLLECTION_PATH).count();
        countQuery.get(AggregateSource.SERVER).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                AggregateQuerySnapshot snapshot = task.getResult();
                long count = snapshot.getCount();
                int count_int = MathematicTools.long_to_int(count);
                countCallback.onCallback(count_int);
            } else {
                Exception exception = task.getException();
                Log.e(TAG, "Error fetching document count", exception);
                countCallback.onFailure();
            }
        });
    }

    public interface CountCallback {
        void onCallback(int count);
        void onFailure();
    }

    @Override
    public void clearLevelsList() {
        levelFileList.clear();
        lastVisible = null;
    }

    @Override
    public void loadNextLevels(int howManyLevelsToLoad, final BooleanCallback booleanCallback) {
        FirebaseFirestore firebaseFirestore = DataBaseFireStore.getInstance().getFirebaseFirestore();
        Query query;
        if (lastVisible == null) {
            query = firebaseFirestore.collection(COLLECTION_PATH)
                    .orderBy("id")
                    .limit(howManyLevelsToLoad);
        } else {
            query = firebaseFirestore.collection(COLLECTION_PATH)
                    .orderBy("id")
                    .startAfter(lastVisible)
                    .limit(howManyLevelsToLoad);
        }

        query.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                QuerySnapshot querySnapshot = task.getResult();
                if (querySnapshot != null && !querySnapshot.isEmpty()) {
                    List<LevelFile> levelFiles = new ArrayList<>();
                    for (DocumentSnapshot document : querySnapshot.getDocuments()) {
                        LevelFile levelFile = document.toObject(LevelFile.class);
                        levelFiles.add(levelFile);
                    }
                    lastVisible = querySnapshot.getDocuments().get(querySnapshot.size() - 1);
                    levelFileList.addAll(levelFiles);
                    booleanCallback.onSuccess();
                } else {
                    booleanCallback.onFailure();
                }
            } else {
                booleanCallback.onFailure();
            }
        });
    }

    public interface BooleanCallback {
        void onSuccess();
        void onFailure();
    }

    @Override
    public void get(int firstIndex, int lastIndex, final LevelsListCallback callback) {
        if(firstIndex < 0 || firstIndex > lastIndex) {
            callback.onFailure();
            return;
        }

        int lastKnownIndex = levelFileList.size();
        boolean lastIndexIsKnown = lastKnownIndex >= lastIndex;

        if(!lastIndexIsKnown) {
            int howManyLevelsToResearch = lastIndex - lastKnownIndex;
            loadNextLevels(howManyLevelsToResearch, new BooleanCallback() {
                @Override
                public void onSuccess() {
                    int lastKnownIndex = levelFileList.size();
                    boolean lastIndexIsKnown = lastKnownIndex >= lastIndex;
                    if(!lastIndexIsKnown) {
                        callback.onFailure();
                    }
                }

                @Override
                public void onFailure() {
                    callback.onFailure();
                }
            });
        }
        List<LevelFile> list = levelFileList.subList(firstIndex, lastIndex);
        callback.onCallback(list);
    }

    public interface LevelsListCallback {
        void onCallback(List<LevelFile> levelFileList);
        void onFailure();
    }
}
