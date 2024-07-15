package com.example.veritablejeu.PopUp.ContenuPopUp;

import android.content.Context;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;

public class ContenuPopUp extends FrameLayout implements IContenuPopUp {

    protected final String titre;

    protected int hauteurTotale = 0;

    public ContenuPopUp(@NonNull Context context) {
        super(context);
        titre = "";
    }

    public ContenuPopUp(@NonNull Context context, String titre) {
        super(context);
        this.titre = titre;
    }

    @Override
    public String getTitre() {
        return titre;
    }

    @Override
    public int getHauteurTotale() {
        return hauteurTotale;
    }
}
