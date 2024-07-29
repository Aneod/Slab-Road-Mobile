package com.slabroad.veritablejeu.BackEnd.DataBases.Local.PersonalBests;

import android.content.Context;

import com.slabroad.veritablejeu.BackEnd.DataBases.Local.PersonalBests.Association_id_record.Association_id_record;
import com.slabroad.veritablejeu.BackEnd.DataBases.Local.PersonalBests.Association_id_record.Association_id_recordDao;
import com.slabroad.veritablejeu.BackEnd.DataBases.Local.PersonalBests.Association_id_record.Association_id_recordDatabase;

public class PersonalBests implements IPersonalBests {

    private static PersonalBests instance;
    private final Association_id_recordDao associationidrecordDao;


    private PersonalBests(Context context) {
        associationidrecordDao = Association_id_recordDatabase.getInstance(context).levelDataDao();
    }

    public static PersonalBests getInstance(Context context) {
        if(instance == null) {
            instance = new PersonalBests(context);
        }
        return instance;
    }


    private void add(Association_id_record levelData) {
        new Thread(() -> associationidrecordDao.insert(levelData)).start();
    }

    private void update(Association_id_record levelData) {
        new Thread(() -> associationidrecordDao.update(levelData)).start();
    }

    @Override
    public void set_ifBestOf(Association_id_record association, final BooleanCallback booleanCallback) {
        if(association == null) {
            booleanCallback.onFailure();
            return;
        }
        get(association.id, callback -> {
            boolean alreadyExists = callback != null;
            if(alreadyExists) {
                if(association.isBestOf(callback)) {
                    update(association);
                    booleanCallback.onSuccess();
                }
            } else {
                add(association);
                booleanCallback.onSuccess();
            }
        });
    }

    public interface BooleanCallback {
        void onSuccess();
        void onFailure();
    }

    @Override
    public void get(String levelId, final Callback callback) {
        if(levelId == null) {
            callback.onDataLoaded(null);
            return;
        }
        new Thread(() -> {
            Association_id_record levelData = associationidrecordDao.get(levelId);
            callback.onDataLoaded(levelData);
        }).start();
    }

    public interface Callback {
        void onDataLoaded(Association_id_record levelData);
    }
}
