package com.slabroad.veritablejeu.Tools;

import android.annotation.SuppressLint;
import android.content.res.Resources;

import androidx.appcompat.app.AppCompatActivity;

public class StatusBar {

    public static int getHeight(AppCompatActivity activity) {
        if(activity == null) {
            return 0;
        }
        Resources resources = activity.getResources();
        @SuppressLint({"DiscouragedApi", "InternalInsetResource"})
        int resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");

        if (resourceId > 0) {
            return 2 * resources.getDimensionPixelSize(resourceId);
        }
        return  0;
    }

}
