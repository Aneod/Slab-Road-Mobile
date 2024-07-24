package com.example.veritablejeu.LevelsPanelMVC.LevelsPanel.Scroller.Components.LevelLauncher;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.veritablejeu.BackEnd.LevelFile.LevelFile;
import com.example.veritablejeu.BackEnd.DataBases.Local.PersonalBests.PersonalBests;
import com.example.veritablejeu.LevelsPanelMVC.LevelsPanel.Scroller.Scroller;
import com.example.veritablejeu.Menu.BoutonRedirection.BoutonRedirection;
import com.example.veritablejeu.Menu.MainActivity;
import com.example.veritablejeu.Tools.LongToReadableTime;

import org.jetbrains.annotations.Contract;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@SuppressLint("ViewConstructor")
public class LevelLauncher extends BoutonRedirection implements ILevelLauncher {

    private static final int FIRST_TEXT_TOPMARGIN = 85;
    private static final int Y_GAP_BETWEEN_TEXTS = 35;

    public static int getHEIGHT(Scroller.LevelCategory levelCategory) {
        if(levelCategory == Scroller.LevelCategory.Global) {
            return 215;
        }
        return 150;
    }

    protected final Scroller scroller;
    protected LevelFile levelFileAOuvrir;
    private AppCompatTextView textePrimaire;
    private AppCompatTextView texteSecondaire;
    private AppCompatTextView texteTertiaire;

    @SuppressLint("ClickableViewAccessibility")
    private void setOnTouchListener() {
        setOnTouchListener(new OnTouchListener() {
            private boolean moveEffectue = false;
            private float lastTouchY;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getActionMasked()) {
                    case MotionEvent.ACTION_DOWN:
                        lastTouchY = event.getRawY();
                        moveEffectue = false;
                        break;
                    case MotionEvent.ACTION_MOVE:
                        float dy = event.getRawY() - lastTouchY;

                        if (!moveEffectue && Math.abs(dy) > 10) {
                            moveEffectue = true;
                            dy = 0;
                        }
                        if(moveEffectue) {
                            scroller.setTranslationYListe(dy);
                            lastTouchY = event.getRawY();
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                        if(!moveEffectue) goInGame();
                }
                return true;
            }
        });
    }

    private void goInGame() {
        if(levelFileAOuvrir == null) return;
        Context context = getContext();
        if(context instanceof MainActivity) {
            ((MainActivity) context).goInGame(levelFileAOuvrir);
        }
    }

    /**
     * Création d'un bouton de redirection pour choisir un niveau.
     * @param scroller la frameLayout parent.
     * @param topMargin la marge supérieure.
     * @param levelFile l'id du niveeau vers lequel ce bouton redirige.
     */
    public LevelLauncher(@NonNull Scroller scroller, int height, int topMargin, @NonNull LevelFile levelFile) {
        super(scroller.getContext(), levelFile.name,
                scroller.getLayoutParams().width, height, 0, topMargin
        );
        this.scroller = scroller;
        this.levelFileAOuvrir = levelFile;
        setOnTouchListener();
        setBackgroundColor(Color.WHITE);
    }

    public String getDateText() {
        String auteur = levelFileAOuvrir.autor;
        Date date = levelFileAOuvrir.date;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault());
        return "By " + auteur + " at " + dateFormat.format(date);
    }

    public void writePersonalBestText(int lineNumber) {
        PersonalBests repository = PersonalBests.getInstance(getContext().getApplicationContext());
        String levelStringId = String.valueOf(levelFileAOuvrir.id);
        repository.get(levelStringId, recordsPerso -> {
            String recordPerso;
            if (recordsPerso != null) {
                String pseudo = "Personal best";
                String readableTime = LongToReadableTime.getReadable(recordsPerso.time);
                int numberOfMoves = recordsPerso.numberOfMoves;
                recordPerso = recordText(pseudo, readableTime, numberOfMoves);
            } else {
                recordPerso = "No personal best";
            }

            AppCompatActivity activity = scroller.getLevelsPanel()
                    .getController().getActivity();
            switch (lineNumber) {
                case 0:
                    activity.runOnUiThread(() ->
                            writeFirstLine(recordPerso)
                    );
                    break;
                case 1:
                    activity.runOnUiThread(() ->
                            writeSecondLine(recordPerso)
                    );
                    break;
                case 2:
                    activity.runOnUiThread(() ->
                            writeThirdLine(recordPerso)
                    );
                    break;
            }
        });
    }

    public String getBestRecordText() {
        String bestPlayer = levelFileAOuvrir.bestPlayer;
        String readableTime = LongToReadableTime.getReadable(levelFileAOuvrir.time);
        int numberOfMoves = levelFileAOuvrir.movesNumber;
        return recordText(bestPlayer, readableTime, numberOfMoves);
    }

    @NonNull
    @Contract(pure = true)
    private String recordText(String pseudo, String readableTime, int numberOfMoves) {
        return pseudo + " : " + readableTime + " (" + numberOfMoves + " moves)";
    }

    public void writeFirstLine(String text) {
        if(textePrimaire == null) {
            textePrimaire = creeLigne(getTextTopMarginByLineNumber(0));
            addView(textePrimaire);
        }
        textePrimaire.setText(text);
    }

    public void writeSecondLine(String text) {
        if(texteSecondaire == null) {
            texteSecondaire = creeLigne(getTextTopMarginByLineNumber(1));
            addView(texteSecondaire);
        }
        texteSecondaire.setText(text);
    }

    public void writeThirdLine(String text) {
        if(texteTertiaire == null) {
            texteTertiaire = creeLigne(getTextTopMarginByLineNumber(2));
            addView(texteTertiaire);
        }
        texteTertiaire.setText(text);
    }

    private int getTextTopMarginByLineNumber(int lineNUmber) {
        return FIRST_TEXT_TOPMARGIN + lineNUmber * Y_GAP_BETWEEN_TEXTS;
    }

    @NonNull
    private AppCompatTextView creeLigne(int topMargin) {
        AppCompatTextView texteView = new AppCompatTextView(getContext());
        texteView.setTextColor(Color.BLACK);
        texteView.setTextSize(12);
        ConstraintLayout.LayoutParams layoutParamsTitre = getLayoutParamsTitre();
        layoutParamsTitre.topMargin += topMargin;
        texteView.setLayoutParams(layoutParamsTitre);
        return texteView;
    }

    @NonNull
    public static LevelLauncher createNormal(@NonNull Scroller parent, int topMargin, LevelFile levelFile) {
        int height = LevelLauncher.getHEIGHT(Scroller.LevelCategory.Normal);
        LevelLauncher bouton = new LevelLauncher(parent, height, topMargin, levelFile);
        bouton.writePersonalBestText(0);
        return bouton;
    }

    @NonNull
    public static LevelLauncher createPersonal(@NonNull Scroller parent, int topMargin, LevelFile levelFile) {
        int height = LevelLauncher.getHEIGHT(Scroller.LevelCategory.Personal);
        LevelLauncher bouton = new LevelLauncher(parent, height, topMargin, levelFile);
        bouton.writeFirstLine(bouton.getDateText());
        return bouton;
    }

    @NonNull
    public static LevelLauncher createGlobal(@NonNull Scroller parent, int topMargin, LevelFile levelFile) {
        int height = LevelLauncher.getHEIGHT(Scroller.LevelCategory.Global);
        LevelLauncher bouton = new LevelLauncher(parent, height, topMargin, levelFile);
        bouton.writeFirstLine(bouton.getDateText());
        bouton.writePersonalBestText(1);
        bouton.writeThirdLine(bouton.getBestRecordText());
        return bouton;
    }
}
