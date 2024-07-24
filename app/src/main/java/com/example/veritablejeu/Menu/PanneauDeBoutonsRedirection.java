package com.example.veritablejeu.Menu;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;

import com.example.veritablejeu.Menu.BoutonRedirection.BoutonRedirectionMenu.BoutonRedirectionMenu;
import com.example.veritablejeu.Tools.CouleurDuJeu;
import com.example.veritablejeu.R;
import com.example.veritablejeu.Tools.ScreenUtils;

@SuppressLint("ViewConstructor")
public class PanneauDeBoutonsRedirection extends FrameLayout {

    private static final int VERTICAL_MARGINS = 30;
    private static final int HORIZONTAL_MARGINS = 50;

    public BoutonRedirectionMenu getBoutonJouer() {
        return boutonJouer;
    }
    public BoutonRedirectionMenu getBoutonMondial() {
        return boutonMondial;
    }
    public BoutonRedirectionMenu getBoutonMesNiveaux() {
        return boutonMesNiveaux;
    }

    private final BoutonRedirectionMenu boutonJouer;
    private final BoutonRedirectionMenu boutonMondial;
    private final BoutonRedirectionMenu boutonMesNiveaux;

    public PanneauDeBoutonsRedirection(@NonNull Context context) {
        super(context);

        // Le panneau ne doit prendre que la moitié de l'écran en hauteur.
        // 4 * MARGINS + bouton1 (2/10) + bouton2 (3/10) + bouton3 (5/10)

        int maxWidth = ScreenUtils.getScreenWidth() - 2 * HORIZONTAL_MARGINS;
        int totalHeight = ScreenUtils.getScreenHeight() / 2;
        int heightForButtons = totalHeight - 4 * VERTICAL_MARGINS;

        int height1 = heightForButtons * 2/10;
        int topMargin1 = ScreenUtils.getScreenHeight() - VERTICAL_MARGINS - height1;
        this.boutonMesNiveaux = new BoutonRedirectionMenu(
                context, "Personal", maxWidth, height1, HORIZONTAL_MARGINS, topMargin1, CouleurDuJeu.Orange.Int()
        );
        boutonMesNiveaux.setImage(R.drawable.dossier);
        this.addView(boutonMesNiveaux);

        int height2 = heightForButtons * 3/10;
        int topMargin2 = ScreenUtils.getScreenHeight() - 2 * VERTICAL_MARGINS - height1 - height2;
        this.boutonMondial = new BoutonRedirectionMenu(
                context, "Global", maxWidth, height2, HORIZONTAL_MARGINS, topMargin2, CouleurDuJeu.Violet.Int()
        );
        boutonMondial.setImage(R.drawable.planete);
        this.addView(boutonMondial);

        int height3 = heightForButtons * 5/10;
        int topMargin3 = ScreenUtils.getScreenHeight() - 3 * VERTICAL_MARGINS - height1 - height2 - height3;
        this.boutonJouer = new BoutonRedirectionMenu(
                context, "Normal levels", maxWidth, height3, HORIZONTAL_MARGINS, topMargin3, CouleurDuJeu.BleuClair.Int()
        );
        boutonJouer.setImage(R.drawable.logo_jouer);
        this.addView(boutonJouer);
    }

}
