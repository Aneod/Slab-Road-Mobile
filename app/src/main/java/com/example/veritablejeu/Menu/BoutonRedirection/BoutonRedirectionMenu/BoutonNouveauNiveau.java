package com.example.veritablejeu.Menu.BoutonRedirection.BoutonRedirectionMenu;

import android.content.Context;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;

import com.example.veritablejeu.BackEnd.LevelFile.LevelFile;
import com.example.veritablejeu.Menu.MainActivity;
import com.example.veritablejeu.Tools.CouleurDuJeu;
import com.example.veritablejeu.R;

public class BoutonNouveauNiveau extends FrameLayout {

    public static final String codeBidon = "a1wp1e00B66230000000b1cFFFFFFaaaaaam115" +
            "p26b" +
            "b16990000" +
            "s1k000w1el110t110b13ac0" +
            "s15001b0" +
            "s1a002w14r110" +
            "s13003" +
            "s18010s1251" +
            "s1e011w18t110l110" +
            "s1c012w14r110b0" +
            "s1s013s1m01c1503t01c19142421r31" +
            "s18020s1231" +
            "s13122" +
            "s13123" +
            "s13030" +
            "s1a031w14r11a" +
            "s18032s1261" +
            "s13133" +
            "s13004" +
            "s18005s1243" +
            "s13015" +
            "s13006" +
            "s13016";

    public static final LevelFile levelFileBidonPourSimulerUnFichier = new LevelFile(
            0,
            "TestName",
            "TestAutor",
            1000000000L,
            7,
            codeBidon
    );

    public BoutonNouveauNiveau(@NonNull Context context) {
        super(context);
    }

    public BoutonNouveauNiveau(@NonNull MainActivity mainActivity, int width, int height, int leftMargin, int topMargin) {
        super(mainActivity);
        BoutonRedirectionMenu boutonNouveau = new BoutonRedirectionMenu(
                mainActivity, "Create new", width, height, leftMargin, topMargin, CouleurDuJeu.Vert.Int()
        );
        boutonNouveau.setImage(R.drawable.plus);
        boutonNouveau.setOnClickListener(v -> {
            mainActivity.goEditeur(levelFileBidonPourSimulerUnFichier); // null en temps normal.
        });
        addView(boutonNouveau);
    }
}
