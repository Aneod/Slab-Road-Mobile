package com.example.veritablejeu.Menu.BoutonRedirection.BoutonRedirectionNiveau.BoutonRedirectionNiveauType;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.example.veritablejeu.BackEnd.LevelFile.LevelFile;
import com.example.veritablejeu.LevelsPanel.Scroller.Scroller;
import com.example.veritablejeu.Menu.BoutonRedirection.BoutonRedirectionNiveau.BoutonRedirectionNiveau;
import com.example.veritablejeu.Menu.PageDeSelection.ListeDefilanteDeNiveaux;
import com.example.veritablejeu.R;

@SuppressLint("ViewConstructor")
public class BoutonRedirectionNiveauBloque extends BoutonRedirectionNiveau {

    /**
     * Création d'un bouton de redirection pour choisir un niveau.
     *
     * @param parent     la frameLayout parent.
     * @param width      la largeur du bouton.
     * @param height     la hauteur du bouton.
     * @param leftMargin la marge gauche.
     * @param topMargin  la marge supérieure.
     * @param levelFile l'id du niveeau vers lequel ce bouton redirige.
     */
    public BoutonRedirectionNiveauBloque(@NonNull Context context, Scroller parent, int width, int height, int leftMargin, int topMargin, LevelFile levelFile) {
        super(context, parent, width, height, leftMargin, topMargin, levelFile);

        levelFileAOuvrir = null;

        setBackgroundColor(Color.DKGRAY);
        ImageView view = new ImageView(context);
        view.setImageResource(R.drawable.cadena_ferme);
        setImage(view, null);
    }
}
