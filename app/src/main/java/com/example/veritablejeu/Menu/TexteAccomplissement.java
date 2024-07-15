package com.example.veritablejeu.Menu;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.veritablejeu.DataBases.Local.LevelFiles.PersonalFiles;
import com.example.veritablejeu.DataBases.Local.UserData;

@SuppressLint("ViewConstructor")
public class TexteAccomplissement extends androidx.appcompat.widget.AppCompatTextView {

    private final String versionTexte;

    public void afficherAccomplissementCampagne() {
        Context context = getContext();
        int currentScore = UserData.getUserScore(context.getApplicationContext());
        int nombreDeNiveau = 0;
        String texte = "Niveaux terminés : " + currentScore + "/" + nombreDeNiveau;
        setText(texte);
    }

    public void afficherPseudoUtilisateur() {
        Context context = getContext();
        String userName = UserData.getUsername(context.getApplicationContext());
        setText(userName);
    }

    public void afficherNumeroDeVersion() {
        setText(versionTexte);
    }

    public void afficherNombreDeFichiersPerso() {
        Context context = getContext();
        new Thread(() -> {
            PersonalFiles personalFiles = PersonalFiles.getInstance(context);
            personalFiles.getSize(count -> ((MainActivity) context).runOnUiThread(() -> {
                String texte = count + " fichiers";
                setText(texte);
            }));
        }).start();
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
