package com.example.veritablejeu.Menu;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.veritablejeu.BackEnd.DataBases.Local.LevelFiles.PersonalFiles;
import com.example.veritablejeu.BackEnd.DataBases.Local.UserData;

@SuppressLint("ViewConstructor")
public class TexteAccomplissement extends androidx.appcompat.widget.AppCompatTextView {

    private final String versionTexte;

    public void afficherPseudoUtilisateur() {
        Context context = getContext();
        String userName = UserData.getUsername(context.getApplicationContext());
        setText(userName);
    }

    public void afficherNumeroDeVersion() {
        setText(versionTexte);
    }

    public TexteAccomplissement(@NonNull Context context, String versionTexte) {
        super(context);
        this.versionTexte = versionTexte;
        afficherNumeroDeVersion();
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
