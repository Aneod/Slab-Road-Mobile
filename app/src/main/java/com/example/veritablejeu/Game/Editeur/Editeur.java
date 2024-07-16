package com.example.veritablejeu.Game.Editeur;

import android.os.Bundle;

import com.example.veritablejeu.Game.Board.BoardElement.Square.ModularSlab.Version.CabledSlab.Cable.Cable;
import com.example.veritablejeu.Game.Game;
import com.example.veritablejeu.Game.Board.Board;
import com.example.veritablejeu.Navigation.Preset.NavigationEditeur.NavigationEditeur;

public class Editeur extends Game {

    private NavigationEditeur navigationEditeur;
    private BoutonNavigationRetour boutonNavigationRetour;
    private boolean fencesShown = false;
    private boolean isCableEditing = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        navigationEditeur = new NavigationEditeur(this);
        boutonNavigationRetour = new BoutonNavigationRetour(this);
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

    /**
     * Activate the cable editor.
     * <p>
     *     When a cable is editing, others board elements can't be edited.
     *     <br>
     *     The ATH disappear. And a cross appear for close the cable editing.
     * </p>
     * <p>
     *     How cables are modify ?
     *     <br>
     *     There are only <u>four actions</u> :
     *     <br>
     *     Click on a <b>square with intersection</b> : Remove the intersection.
     *     <br>
     *     Click on a <b>square without intersection</b> : Create an intersection on this square.
     *     <br>
     *     Click on a <b>none connected door</b> : Connect the cable to the clicked door.
     *     <br>
     *     Click on the <b>connected door</b> : Disconnect the door to the cable.
     *     <br>
     *     For each actions the graphic cable will be refreshed.
     * </p>
     * @param cable the cable to modify.
     */
    public void enableCableEditing(Cable cable) {
        hideATH();
        boutonNavigationRetour.show();
        enableCableEditorListener(cable);
        isCableEditing = true;
    }

    public void disableCableEditing() {
        showATH();
        boutonNavigationRetour.hide();
        enableEditorListener();
        isCableEditing = false;
    }

    private void enableEditorListener() {
        plateauModulaireSet.forEach(Board::enableEditorListeners);
    }

    private void enableCableEditorListener(Cable cable) {
        plateauModulaireSet.forEach(board -> board.enableCableEditorListeners(cable));
    }

    private void showATH() {
        navigationEditeur.show();
    }

    private void hideATH() {
        navigationEditeur.hide();
    }

    public boolean isCableEditing() {
        return isCableEditing;
    }
}
