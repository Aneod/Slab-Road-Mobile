package com.example.veritablejeu.BackEnd.DataBases.Local;

import android.content.Context;
import android.content.SharedPreferences;

public class UserData {

    private static final String PREFS_NAME = "UserData";
    private static final String KEY_USERNAME = "username";
    private static final String DEFAULT_USERNAME = "indefinite";

    public static void saveUsername(Context context, String username) {
        if(context == null) return;
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_USERNAME, username);
        editor.apply();
    }

    public static String getUsername(Context context) {
        if(context == null) {
            return DEFAULT_USERNAME;
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_USERNAME, "");
    }
}
