package com.example.veritablejeu.Menu;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;

@SuppressLint("ViewConstructor")
public class VersionText extends androidx.appcompat.widget.AppCompatTextView {

    private String getVersionText(@NonNull Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            String packageName = context.getPackageName();
            PackageInfo packageInfo = packageManager.getPackageInfo(packageName, 0);
            return packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            return "";
        }
    }

    public VersionText(@NonNull Context context) {
        super(context);
        setText(getVersionText(context));
        setTextSize(20);
        setTextColor(Color.BLACK);

        ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.WRAP_CONTENT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT
        );
        layoutParams.topToTop = ConstraintLayout.LayoutParams.PARENT_ID;
        layoutParams.endToEnd = ConstraintLayout.LayoutParams.PARENT_ID;
        layoutParams.setMargins(0, 20, 20, 0);
        setLayoutParams(layoutParams);
    }
}
