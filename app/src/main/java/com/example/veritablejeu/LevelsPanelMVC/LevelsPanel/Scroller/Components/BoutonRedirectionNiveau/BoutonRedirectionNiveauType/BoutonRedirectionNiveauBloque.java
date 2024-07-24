package com.example.veritablejeu.LevelsPanelMVC.LevelsPanel.Scroller.Components.BoutonRedirectionNiveau.BoutonRedirectionNiveauType;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.example.veritablejeu.BackEnd.LevelFile.LevelFile;
import com.example.veritablejeu.LevelsPanelMVC.LevelsPanel.Scroller.Components.BoutonRedirectionNiveau.BoutonRedirectionNiveau;
import com.example.veritablejeu.LevelsPanelMVC.LevelsPanel.Scroller.Scroller;
import com.example.veritablejeu.R;

@SuppressLint("ViewConstructor")
public class BoutonRedirectionNiveauBloque extends BoutonRedirectionNiveau {

    /**
     * Création d'un bouton de redirection pour choisir un niveau.
     *
     * @param scroller     la frameLayout parent.
     * @param topMargin  la marge supérieure.
     * @param levelFile l'id du niveeau vers lequel ce bouton redirige.
     */
    public BoutonRedirectionNiveauBloque(@NonNull Scroller scroller, int topMargin, LevelFile levelFile) {
        super(scroller, topMargin, levelFile);

        levelFileAOuvrir = null;

        setBackgroundColor(Color.DKGRAY);
        ImageView view = new ImageView(getContext());
        view.setImageResource(R.drawable.cadena_ferme);
        setImage(view, null);
    }
}
