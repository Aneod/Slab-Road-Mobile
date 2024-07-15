package com.example.veritablejeu.Game.InGame.ATHFinal.TextesFinaux;

import android.content.Context;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;

import com.example.veritablejeu.Game.InGame.Chronometre.Chronometre;
import com.example.veritablejeu.Game.InGame.ATHFinal.TextesFinaux.TexteFinal.TexteFinal;
import com.example.veritablejeu.Game.InGame.InGame;
import com.example.veritablejeu.OutilsEnEnum.LongToReadableTime;

public class TextesFinaux extends FrameLayout implements ITextesFinaux {

    private TexteFinal temps;
    private TexteFinal nbCoups;

    public TextesFinaux(@NonNull Context context){
        super(context);
    }

    public TextesFinaux(@NonNull InGame context) {
        super(context);

        int topMarginTemps = 200;
        temps = new TexteFinal(context, topMarginTemps);

        int topMarginNbCoups = 260;
        nbCoups = new TexteFinal(context, topMarginNbCoups);
    }

    private void refreshTemps() {
        Chronometre chronometre = new Chronometre();
        long elapsedTime = chronometre.getElapsedTime();
        String stringTemps = LongToReadableTime.getElapsedTimeFormatted(elapsedTime);
        temps.setText(stringTemps);
    }

    private void resfreshNbCoups() {
        int nombreDeCoups = 1;
        String stringCoups = nombreDeCoups + " coups";
        nbCoups.setText(stringCoups);
    }

    @Override
    public void refreshData() {
        refreshTemps();
        resfreshNbCoups();
    }

    @Override
    public void apparition(int startOffSet, long duration) {
        temps.apparition(startOffSet, duration);
        nbCoups.apparition(startOffSet, duration);
    }

    @Override
    public void disparition(long duration) {
        temps.disparition(duration);
        nbCoups.disparition(duration);
    }
}
