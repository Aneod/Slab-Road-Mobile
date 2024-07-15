package com.example.veritablejeu.Game.Editeur;

import android.os.Bundle;

import com.example.veritablejeu.Game.Editeur.SelectionElement.SelectionElement;
import com.example.veritablejeu.Game.Game;
import com.example.veritablejeu.Game.Board.Board;
import com.example.veritablejeu.Navigation.Preset.NavigationEditeur.NavigationEditeur;

public class Editeur extends Game {

    private boolean fencesShown = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new NavigationEditeur(this);
        new SelectionElement(this);
        showHideFences();
    }

    public void showHideFences() {
        fencesShown = !fencesShown;
        if(fencesShown) {
            showFences();
        } else {
            hideFences();
        }
    }

    public void showFences() {
        plateauModulaireSet.forEach(Board::showFence);
    }

    public void hideFences() {
        plateauModulaireSet.forEach(Board::hideFence);
    }

}
