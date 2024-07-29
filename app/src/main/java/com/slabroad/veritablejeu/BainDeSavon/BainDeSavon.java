package com.slabroad.veritablejeu.BainDeSavon;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.slabroad.veritablejeu.BackEnd.sequentialCode.CodeBuilder;
import com.slabroad.veritablejeu.BainDeSavon.BulleDeSavon.BulleDeSavon;
import com.slabroad.veritablejeu.Game.FirstCodeReader;
import com.slabroad.veritablejeu.Tools.StringColorConverter;

import java.util.HashSet;

public class BainDeSavon implements IBainDeSavon {

    private static final int BUBBLE_NUMBER = 45;

    private static BainDeSavon instance;
    private final HashSet<BulleDeSavon> toutesLesBulles;
    private boolean visible = true;
    private boolean ATypeIsCircle = true;
    private boolean BTypeIsCircle = true;
    private int ATypeColor = Color.BLACK;
    private int BTypeColor = Color.WHITE;

    public boolean isATypeCircle() {
        return ATypeIsCircle;
    }

    public boolean isBTypeCircle() {
        return BTypeIsCircle;
    }

    public int getATypeColor() {
        return ATypeColor;
    }

    public int getBTypeColor() {
        return BTypeColor;
    }

    @NonNull
    private HashSet<BulleDeSavon> creerBullesDeSavon(@NonNull AppCompatActivity activity) {
        HashSet<BulleDeSavon> nouvelleListe = new HashSet<>();
        for(int i = 0; i < BUBBLE_NUMBER; i++) {
            BulleDeSavon bulleDeSavon = new BulleDeSavon(activity);
            nouvelleListe.add(bulleDeSavon);
        }
        return nouvelleListe;
    }

    private BainDeSavon(@NonNull AppCompatActivity context) {
        this.toutesLesBulles = creerBullesDeSavon(context);
        setDesignDeBase();
    }

    public static synchronized BainDeSavon getInstance(@NonNull AppCompatActivity activity) {
        if (instance == null) {
            instance = new BainDeSavon(activity);
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
    public HashSet<BulleDeSavon> getToutesLesBulles() {
        return toutesLesBulles;
    }

    @Override
    public void setDesignDeBase() {
        setDesigns(0, Color.BLACK, 0, Color.WHITE);
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
        ATypeIsCircle = formeUne != 1;
        BTypeIsCircle = formeDeux != 1;
        ATypeColor = couleur1;
        BTypeColor = couleur2;
    }

    @Override
    public void setATypeAsCircle(boolean circle) {
        int shape = circle ? GradientDrawable.OVAL : GradientDrawable.RECTANGLE;
        for(BulleDeSavon bulleDeSavon : this.toutesLesBulles) {
            int selection = bulleDeSavon.getGroupe();
            boolean isAGroup = selection == 0;
            if(isAGroup) {
                bulleDeSavon.setShape(shape);
            }
        }
        ATypeIsCircle = circle;
    }

    @Override
    public void setBTypeAsCircle(boolean circle) {
        int shape = circle ? GradientDrawable.OVAL : GradientDrawable.RECTANGLE;
        for(BulleDeSavon bulleDeSavon : this.toutesLesBulles) {
            int selection = bulleDeSavon.getGroupe();
            boolean isBGroup = selection == 1;
            if(isBGroup) {
                bulleDeSavon.setShape(shape);
            }
        }
        BTypeIsCircle = circle;
    }

    @Override
    public void setATypeColor(int color) {
        for(BulleDeSavon bulleDeSavon : this.toutesLesBulles) {
            int selection = bulleDeSavon.getGroupe();
            boolean isAGroup = selection == 0;
            if(isAGroup) {
                bulleDeSavon.setColor(color);
            }
        }
        ATypeColor = color;
    }

    @Override
    public void setBTypeColor(int color) {
        for(BulleDeSavon bulleDeSavon : this.toutesLesBulles) {
            int selection = bulleDeSavon.getGroupe();
            boolean isBGroup = selection == 1;
            if(isBGroup) {
                bulleDeSavon.setColor(color);
            }
        }
        BTypeColor = color;
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
    public void show_and_resume() {
        resume_animations();
        show();
    }

    @Override
    public void hide_and_pause() {
        pause_animations();
        hide();
    }

    @Override
    public boolean areBubblesVisible() {
        return visible;
    }

    public String getCode() {
        char shapeA = isATypeCircle() ? '0' : '1';
        char shapeB = isBTypeCircle() ? '0' : '1';
        String colorA = StringColorConverter.turnIntoCode(ATypeColor);
        String colorB = StringColorConverter.turnIntoCode(BTypeColor);
        String code = shapeA + colorA + shapeB + colorB;
        char key = FirstCodeReader.getKeyBubblesStyle();
        return CodeBuilder.buildKeyValue(key, code);
    }
}
