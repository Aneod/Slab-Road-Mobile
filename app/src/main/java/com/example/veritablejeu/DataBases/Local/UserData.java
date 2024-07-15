package com.example.veritablejeu.DataBases.Local;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.veritablejeu.LevelFile.LevelCategory;
import com.example.veritablejeu.LevelFile.LevelFile;

public class UserData {

    private static final String PREFS_NAME = "UserData";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_USER_SCORE = "user_score";

    public static void saveUsername(Context context, String username) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_USERNAME, username);
        editor.apply();
    }

    public static String getUsername(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_USERNAME, "");
    }

    public static void saveUserScore(Context context, int score) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(KEY_USER_SCORE, score);
        editor.apply();
    }

    public static int getUserScore(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getInt(KEY_USER_SCORE, 0);
    }

    public static void progressionDansLeScore(Context context, LevelFile levelFile) {
        LevelCategory levelCategory = levelFile.levelCategory;
        boolean estUnNiveauDeLaCampagne = levelCategory == LevelCategory.Normaux;
        if(estUnNiveauDeLaCampagne) {
            int plusGrandIdNormalEffectue = UserData.getUserScore(context.getApplicationContext());
            int idLevelActuel = levelFile.id;
            boolean scoreDepasse = idLevelActuel > plusGrandIdNormalEffectue;
            if(scoreDepasse) {
                UserData.saveUserScore(context.getApplicationContext(), idLevelActuel);
            }
        }
    }
}
