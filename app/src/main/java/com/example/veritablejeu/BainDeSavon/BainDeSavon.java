package com.example.veritablejeu.BainDeSavon;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.veritablejeu.BainDeSavon.BulleDeSavon.BulleDeSavon;
import com.example.veritablejeu.Game.PlateauModulaire.StringColorConverter;

import java.util.HashSet;

public class BainDeSavon implements IBainDeSavon {

    private static BainDeSavon instance;
    private final HashSet<BulleDeSavon> toutesLesBulles;
    private boolean visible = true;

    private HashSet<BulleDeSavon> creerBullesDeSavon(@NonNull Activity context) {
        int nbBulles = 45;
        HashSet<BulleDeSavon> nouvelleListe = new HashSet<>();
        for(int i = 0; i < nbBulles; i++) {
            BulleDeSavon bulleDeSavon = new BulleDeSavon(context);
            nouvelleListe.add(bulleDeSavon);
        }
        return nouvelleListe;
    }

    private BainDeSavon(@NonNull AppCompatActivity context) {
        this.toutesLesBulles = creerBullesDeSavon(context);
        setDesignDeBase();
    }

    public static synchronized BainDeSavon getInstance(@NonNull AppCompatActivity context) {
        if (instance == null) {
            instance = new BainDeSavon(context);
        }
        return instance;
    }

    @Override
    public void setContainerDeToutesLesBulles(@NonNull AppCompatActivity activity) {
        for(BulleDeSavon bulleDeSavon : this.toutesLesBulles) {
            bulleDeSavon.setConstraintLayout(activity);
        }
    }

    @Override
    public void setDesignDeBase() {
        for(BulleDeSavon bulleDeSavon : this.toutesLesBulles) {
            int selection = bulleDeSavon.getGroupe();
            int couleur = selection == 0 ? Color.BLACK : Color.WHITE;
            int forme = GradientDrawable.OVAL;
            bulleDeSavon.setDesign(forme, couleur);
        }
    }

    @Override
    public void setDesigns(
            @Nullable Integer formeUne, @Nullable Integer couleur1,
            @Nullable Integer formeDeux, @Nullable Integer couleur2
    ) {
        if(formeUne == null || couleur1 == null || formeDeux == null || couleur2 == null) {
            setDesignDeBase();
            return;
        }
        for(BulleDeSavon bulleDeSavon : this.toutesLesBulles) {
            int selection = bulleDeSavon.getGroupe();
            int couleur = selection == 0 ? couleur1 : couleur2;
            int forme = selection == 0 ? formeUne : formeDeux;
            int formeTraduite = forme == 1 ? GradientDrawable.RECTANGLE : GradientDrawable.OVAL;
            bulleDeSavon.setDesign(formeTraduite, couleur);
        }
    }

    @Override
    public void setDesigns(@NonNull String code) {
        boolean uncorrectCodeSize = code.length() != 14;
        if(uncorrectCodeSize) return;

        String stringShape1 = code.substring(0, 1);
        int shape1 = Integer.parseInt(stringShape1);
        String stringColor1 = code.substring(1, 7);
        int color1 = StringColorConverter.turnIntoColor(stringColor1);
        String stringShape2 = code.substring(7, 8);
        int shape2 = Integer.parseInt(stringShape2);
        String stringColor2 = code.substring(8);
        int color2 = StringColorConverter.turnIntoColor(stringColor2);

        setDesigns(shape1, color1, shape2, color2);
    }

    @Override
    public void resume_animations() {
        for(BulleDeSavon bulleDeSavon : this.toutesLesBulles) {
            bulleDeSavon.resume_animation();
        }
    }

    @Override
    public void pause_animations() {
        for(BulleDeSavon bulleDeSavon : this.toutesLesBulles) {
            bulleDeSavon.pause_animation();
        }
    }

    @Override
    public void show() {
        visible = true;
        for(BulleDeSavon bulleDeSavon : this.toutesLesBulles) {
            bulleDeSavon.show();
        }
    }

    @Override
    public void hide() {
        visible = false;
        for(BulleDeSavon bulleDeSavon : this.toutesLesBulles) {
            bulleDeSavon.hide();
        }
    }

    @Override
    public boolean getBullesVisibles() {
        return visible;
    }
}
