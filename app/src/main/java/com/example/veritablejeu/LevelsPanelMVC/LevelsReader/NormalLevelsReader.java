package com.example.veritablejeu.LevelsPanelMVC.LevelsReader;

import androidx.annotation.NonNull;

import com.example.veritablejeu.BackEnd.LevelFile.LevelFile;
import com.example.veritablejeu.Tools.SafeSubList;

import java.util.Arrays;
import java.util.List;

public class NormalLevelsReader extends LevelsReader {

    private static final LevelFile LVL_1 = new LevelFile(
            "1",
            "Level 1",
            "Slab Road",
            0L,
            0,
            "a210b1cffffffacadadp1e00000000ffffffo11fm110p24lt122sb1650003ds1i011w0s1a01c1501t02s17020w0b0s1a023w0s1251s15010w0s15021w0s15001w0s17000w0b0s1n012w14t110s1a01c1522t22s1a022w14t11as1a002w14t11as15013w0s1a003w0s1251"
    );

    private static final LevelFile LVL_2 = new LevelFile(
            "2",
            "Level 2",
            "Slab Road",
            0L,
            0,
            "a210b1cffffff7a7a7dp1e100c1fe10130ffo11fm111p25it122sb1600007bs1a012w14t11bs15011w0s17013w0b0s1i001w0s1a11c1511t12s17023w0b0s1n002w14t110s1a11c1512t12s1n022w14t110s1a11c1512t12s1a010w0s1251s17003w0b0s1i021w0s1a11c1511t12s1a000w0s1251s1a020w0s1251"
    );

    private static final List<LevelFile> LEVELS = Arrays.asList(
            LVL_1,
            LVL_2
    );

    private static NormalLevelsReader instance;

    private NormalLevelsReader() {}

    public static NormalLevelsReader getInstance() {
        if(instance == null) {
            instance = new NormalLevelsReader();
        }
        return instance;
    }

    @Override
    public void get(int from, int to, @NonNull final LevelListCallback callback) {
        List<LevelFile> onReturn = SafeSubList.get(LEVELS, from, to);
        callback.onCallback(onReturn);
    }

    @Override
    public void getSize(@NonNull final CountCallback callback) {
        callback.onCallback(LEVELS.size());
    }
}
