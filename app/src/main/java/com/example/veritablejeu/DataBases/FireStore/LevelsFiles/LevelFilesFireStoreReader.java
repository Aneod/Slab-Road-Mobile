package com.example.veritablejeu.DataBases.FireStore.LevelsFiles;

import com.example.veritablejeu.DataBases.FireStore.DataBaseFireStore;
import com.example.veritablejeu.LevelFile.LevelFile;
import com.example.veritablejeu.OutilsEnEnum.OutilsMathematiques;
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
    public void getNumberOfLevels_inDataBase(final CountCallback countCallback) {
        FirebaseFirestore firebaseFirestore = DataBaseFireStore.getInstance().getFirebaseFirestore();
        AggregateQuery countQuery = firebaseFirestore.collection(COLLECTION_PATH).count();
        countQuery.get(AggregateSource.SERVER).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                AggregateQuerySnapshot snapshot = task.getResult();
                long count = snapshot.getCount();
                int count_int = OutilsMathematiques.long_to_int(count);
                countCallback.onCallback(count_int);
            }
        });
    }

    public interface CountCallback {
        void onCallback(int count);
    }

    @Override
    public void clearLevelsList() {
        levelFileList.clear();
        lastVisible = null;
    }

    @Override
    public void loadNextLevels(int numberOfLevelsToLoad) {
        FirebaseFirestore firebaseFirestore = DataBaseFireStore.getInstance().getFirebaseFirestore();
        Query query;
        if (lastVisible == null) {
            query = firebaseFirestore.collection(COLLECTION_PATH)
                    .orderBy("id")
                    .limit(numberOfLevelsToLoad);
        } else {
            query = firebaseFirestore.collection(COLLECTION_PATH)
                    .orderBy("id")
                    .startAfter(lastVisible)
                    .limit(numberOfLevelsToLoad);
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
                }
            }
        });
    }

    @Override
    public List<LevelFile> get(int firstIndex, int lastIndex) {
        if(firstIndex < 0 || firstIndex > lastIndex) {
            return new ArrayList<>();
        }

        int lastKnownIndex = levelFileList.size();
        boolean lastIndexIsKnown = lastKnownIndex >= lastIndex;

        if(!lastIndexIsKnown) {
            int howManyLevelsToResearch = lastIndex - lastKnownIndex;
            loadNextLevels(howManyLevelsToResearch);

            int newLastKnownIndex = levelFileList.size();
            boolean isSuccess = newLastKnownIndex >= lastIndex;

            if(!isSuccess) {
                return new ArrayList<>();
            }
        }
        return levelFileList.subList(firstIndex, lastIndex);
    }
}
