package com.example.veritablejeu.Game.Editeur;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.veritablejeu.Navigation.BoutonNavigation.BoutonNavigation;
import com.example.veritablejeu.R;

@SuppressLint("ViewConstructor")
public class BoutonNavigationRetour extends BoutonNavigation {

    private final ConstraintLayout container;

    public BoutonNavigationRetour(@NonNull Editeur editeur) {
        super(editeur, 100, 40, 40);
        container = editeur.getContainer();
        setImage(R.drawable.croix, .6f);
        setOnClickListener(v -> editeur.disableCableEditing());
    }

    public void show() {
        if(getParent() == null) {
            container.addView(this);
        }
    }

    public void hide() {
        container.removeView(this);
    }
}
