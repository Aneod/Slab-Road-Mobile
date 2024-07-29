package com.slabroad.veritablejeu.BackEnd.DataBases.Local;

import android.content.Context;
import android.content.SharedPreferences;

public class UserData {

    private static final String PREFS_NAME = "UserData";
    private static final String KEY_USERNAME = "username";
    private static final String DEFAULT_USERNAME = "indefinite";
    private static final String NEXT_PERSONAL_LEVEL_ID = "nextPersonalLevelId";
    private static final int DEFAULT_LEVEL_ID = 0;

    public static void saveUsername(Context context, String username) {
        if(context == null) return;
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_USERNAME, username);
        editor.apply();
    }

    public static String getUsername(Context context) {
        if(context == null) return DEFAULT_USERNAME;
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_USERNAME, "");
    }

    public static void incrementNextPersonalLevelId(Context context) {
        if(context == null) return;
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        int nextPersonalLevelId = getNextPersonalLevelId(context);
        int newLevelId = nextPersonalLevelId + 1;
        editor.putInt(NEXT_PERSONAL_LEVEL_ID, newLevelId);
        editor.apply();
    }

    public static int getNextPersonalLevelId(Context context) {
        if(context == null) return DEFAULT_LEVEL_ID;
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getInt(NEXT_PERSONAL_LEVEL_ID, DEFAULT_LEVEL_ID);
    }
}
