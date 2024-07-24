package com.example.veritablejeu.Menu.PagePrincipale;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.example.veritablejeu.Menu.BoutonRedirection.BoutonRedirectionMenu.BoutonRedirectionMenuType.BoutonRedirectionMenuLeger;
import com.example.veritablejeu.Tools.CouleurDuJeu;
import com.example.veritablejeu.R;

@SuppressLint("ViewConstructor")
public class PanneauDeBoutonsRedirection extends FrameLayout {

    public static final int hauteurGenerale = 850;

    public BoutonRedirectionMenuLeger getBoutonJouer() {
        return boutonJouer;
    }
    public BoutonRedirectionMenuLeger getBoutonMondial() {
        return boutonMondial;
    }
    public BoutonRedirectionMenuLeger getBoutonMesNiveaux() {
        return boutonMesNiveaux;
    }

    private final BoutonRedirectionMenuLeger boutonJouer;
    private final BoutonRedirectionMenuLeger boutonMondial;
    private final BoutonRedirectionMenuLeger boutonMesNiveaux;

    public PanneauDeBoutonsRedirection(@NonNull Context context) {
        super(context);

        int margesCotes = 50;
        this.boutonMesNiveaux = new BoutonRedirectionMenuLeger(context, "Fichiers", 620, 100, margesCotes, hauteurGenerale + 250 + 150 + 2 * 20, CouleurDuJeu.Orange.Int());
        ImageView logoMesNiveaux = new ImageView(getContext());
        logoMesNiveaux.setImageResource(R.drawable.dossier);
        boutonMesNiveaux.setImage(logoMesNiveaux, null);
        this.addView(boutonMesNiveaux);

        this.boutonMondial = new BoutonRedirectionMenuLeger(context, "Mondial", 620, 150, margesCotes, hauteurGenerale + 250 + 20, CouleurDuJeu.Violet.Int());
        ImageView logoMondial = new ImageView(getContext());
        logoMondial.setImageResource(R.drawable.planete);
        boutonMondial.setImage(logoMondial, null);
        this.addView(boutonMondial);

        this.boutonJouer = new BoutonRedirectionMenuLeger(context, "Campagne", 620, 250, margesCotes, hauteurGenerale, CouleurDuJeu.BleuClair.Int());
        ImageView logoJouer = new ImageView(getContext());
        logoJouer.setImageResource(R.drawable.logo_jouer);
        boutonJouer.setImage(logoJouer, null);
        this.addView(boutonJouer);
    }

}
